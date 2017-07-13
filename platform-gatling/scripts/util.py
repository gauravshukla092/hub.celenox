import itertools
import json
import os
import re
import subprocess
import time
import sys
from datetime import datetime, timedelta
from StringIO import StringIO
from operator import itemgetter

import boto3


ACCOUNT_NUMBER = "966044814082"

SSH_N_TRIES = 10
SSH_KEY_PATH = os.path.expanduser("~/.ssh/{}.pem")

PROGRAMMATIC_ENV_VARS = {"KAFKA_HOSTS", "TITAN_HOST", "ES_HOSTS", "ES_CLUSTER_NAME", "ES_PORT", "ES_SHIELD_USERNAME"}

FEATURE_FLAGS = set()


my_dir = os.path.dirname(os.path.realpath(__file__))


class RemoteRunner(object):
    def __init__(self, env, instance):
        self.user = "ec2-user"
        self.public_ip_address = instance.public_ip_address
        self.private_ip_address = instance.private_ip_address
        self.instance_ssh = "{}@{}".format(self.user, instance.public_ip_address)
        self.key_path = SSH_KEY_PATH.format(env["sshKeyName"])

    # By default this does an interactive SSH session
    def run(self, command=None, tty=None, capture_output=False):
        for i in itertools.count():
            try:
                if tty is None:
                    tty = not bool(command)
                args = (
                    [
                        "ssh",
                        "-i", self.key_path,
                        "-o", "StrictHostKeyChecking=no",
                        "-o", "UserKnownHostsFile=/dev/null",
                        self.instance_ssh
                    ] +
                    (["-t"] if tty else []) +
                    ([command] if command else [])
                )
                if capture_output:
                    return subprocess.check_output(args)
                else:
                    return subprocess.check_call(args)

            except subprocess.CalledProcessError as e:
                if e.returncode != 255:
                    print "Return code was not 255, raising error..."
                    raise
                if i == SSH_N_TRIES:
                    print "Maximum number of tries exceeded, raising error..."
                    raise

                print "Retrying SSH command after error: ", e
                time.sleep(i ** 2)

    def open_tunnel(self, local_port, remote_port):
        if (local_port is None) or (remote_port is None):
            raise ValueError("Must provide local_port and remote_port")
        try:
            args = [
                    "ssh",
                    "-i", self.key_path,
                    "-o", "StrictHostKeyChecking=no",
                    "-o", "UserKnownHostsFile=/dev/null",
                    "-L", "{}:{}:{}".format(local_port, self.public_ip_address, remote_port),
                    self.instance_ssh]
            subprocess.check_output(args)
        except subprocess.CalledProcessError as e:
            if e.returncode != 255:
                print "Return code was not 255, raising error..."
                raise

    def scp(self, from_remote, from_path, to_remote, to_path):
        subprocess.check_call([
            "scp",
            "-rp",
            "-i", self.key_path,
            "-o", "StrictHostKeyChecking=no",
            "-o", "UserKnownHostsFile=/dev/null",
            "{}{}".format(self.instance_ssh + ":" if from_remote else "", from_path),
            "{}{}".format(self.instance_ssh + ":" if to_remote else "", to_path),
        ])


def load_config():
    with open(os.path.join(my_dir, "config.json")) as config_file:
        return json.load(config_file)


def sync_remote_scripts(env_id, role, index=None, local_path=os.path.join(my_dir, "remote"), remote_path="scripts"):
    # Don't call this except on a deploy because it overwrites config.json
    env = load_config()["env"][env_id]
    ec2 = boto3.resource("ec2", region_name=env["region"])

    instance, = list(get_instances(ec2, env_id=env_id, role=role, index=index))

    runner = RemoteRunner(env, instance)
    runner.run("rm -rf {}".format(remote_path))
    runner.scp(False, local_path, True, remote_path)


def wait_for_log(runner, log_path, wait_for_regex, command=None, kill_regex=None):
    # This command does not have a timeout
    # Grep exits immediately once the string is found
    # Using < instead of | so that the tail exits when grep exits
    # Tail -F -n +1 tails continuously, starting at the beginning of the file
    # Tee prints the contents of the log out to the user via stderr
    # If the grep command finishes quickly then tee might not actually print out to stderr, I don't know
    if command:
        assert log_path is None
    else:
        command = "tail -F -n +1 {}".format(log_path)
    runner.run("grep -m1 '{}' <({} | tee /dev/stderr)".format(wait_for_regex, command))
    #Kill the process if specified regex
    if kill_regex:
        try:
            runner.run("ps aux | grep '{}' | grep -v 'color=auto' | awk '{{print $2}}' | xargs kill -9".format(kill_regex))
        except subprocess.CalledProcessError as e:
            assert e.returncode == 123
            print "Ingestion Successful"


def resource_name(env_id, role):
    legal = re.compile("^\w+$")
    assert legal.match(env_id)
    assert legal.match(role)
    return "{}-{}".format(env_id, role)


def get_sgs(ec2, env_id, role):
    return ec2.security_groups.filter(Filters=[{"Name": "group-name", "Values": [resource_name(env_id, role)]}])


def get_instances(ec2, env_id=None, role=None, index=None):
    filters = [{"Name": "instance-state-name", "Values": ["pending", "running"]}]
    if env_id:
        filters.append({"Name": "tag:Environment", "Values": [env_id]})
    if role:
        filters.append({"Name": "tag:Role", "Values": [role]})
    if index:
        filters.append({"Name": "tag:Index", "Values": [index]})

    return ec2.instances.filter(Filters=filters)


def simplify_tags(tags):
    if tags is None:
        return {}

    result = {}
    for tag in tags:
        result[tag["Key"]] = tag["Value"]
    return result


def standard_tags(env_id, role):
    return [
        {'Key': 'Name', 'Value': resource_name(env_id, role)},
        {'Key': 'Environment', 'Value': env_id},
        {'Key': 'Role', 'Value': role},
        {'Key': 'CreatedBy', 'Value': get_my_email()},
        {'Key': 'CreatedWith', 'Value': "F.A.I.L."},
    ]


def create_sg(client_ec2, ec2, env_id, role, ingress=None):
    print "Creating security group..."
    created = client_ec2.create_security_group(
        GroupName=resource_name(env_id, role),
        Description='Created by F.A.I.L.'
    )
    print created

    sg, = get_sgs(ec2, env_id, role)
    if ingress is not None:
        print "Adding ingress to security group..."
        sg.authorize_ingress(**ingress)
    sg.create_tags(Tags=standard_tags(env_id, role))

    print "Security group created."

    return created


def create_instance(env_id, role, sg_opts, run_instance_opts, more_sg_roles=None):
    more_sg_roles = more_sg_roles if more_sg_roles is not None else []

    env = load_config()["env"][env_id]
    client = boto3.client('ec2', region_name=env["region"])
    ec2 = boto3.resource('ec2', region_name=env["region"])

    print "Creating security groups..."
    for sg_role, ingress in sg_opts:
        create_sg(client, ec2, env_id, sg_role, ingress)

    print "Creating instance..."
    default_run_instance_opts = {
        "ImageId": "ami-b04e92d0",
        "MinCount": 1,
        "MaxCount": 1,
        "KeyName": env["sshKeyName"],
        "SecurityGroups": (
            [resource_name(env_id, "ssh")] +
            [resource_name(env_id, sg_role) for sg_role, _ in sg_opts] +
            [resource_name(env_id, sg_role) for sg_role in more_sg_roles]
        ),
        "Monitoring": {"Enabled": True}
    }
    default_run_instance_opts.update(run_instance_opts)
    ran = client.run_instances(**default_run_instance_opts)
    print ran
    ran_instance, = ran["Instances"]
    instance_id = ran_instance["InstanceId"]

    print "Tagging instance..."
    instance = ec2.Instance(instance_id)
    print instance.create_tags(Tags=standard_tags(env_id, role))

    print "Waiting for instance to be running..."
    waiter = client.get_waiter('instance_running')
    waiter.wait(InstanceIds=[instance_id])

    print "Waiting for cloud init to complete..."
    runner = RemoteRunner(env, instance)
    while True:
        try:
            runner.run("grep 'Cloud-init.*finished' /var/log/cloud-init.log")
        except subprocess.CalledProcessError as e:
            if e.returncode != 1:
                print "Unexpected error checking cloud init"
                raise
            else:
                print "Cloud init is not finished"
                continue
        else:
            print "Cloud init finished"
            break

    print "Printing cloud init logs..."
    runner.run("cat /var/log/cloud-init-output.log")

    print "Checking for cloud init warnings..."
    runner.run("! grep WARNING /var/log/cloud-init.log")

    return {
        "instance": ran_instance,
        "runner": runner
    }


def delete_sg(ec2, env_id, sg_role):
    sgs = get_sgs(ec2, env_id, sg_role)
    for sg in sgs:
        print "Deleting", sg
        sg.delete()


def delete_instance(env_id, role, sg_roles):
    env = load_config()["env"][env_id]
    ec2 = boto3.resource('ec2', region_name=env["region"])
    client = boto3.client('ec2', region_name=env["region"])

    print "Terminating instances..."
    instances = get_instances(ec2, env_id, role)
    instances_list = list(instances)
    print instances_list
    instances.terminate()

    if instances_list:
        print "Waiting for instances to terminate..."
        waiter = client.get_waiter('instance_terminated')
        waiter.wait(InstanceIds=[i.id for i in instances_list])

    print "Deleting security groups..."
    for sg_role in sg_roles:
        delete_sg(ec2, env_id, sg_role)

    print "Successfully deleted instance."


def get_env_vars(env_id):
    s3 = boto3.client('s3')
    download_to = StringIO()
    s3.download_fileobj('freebird-fail', 'env/vars/{}.json'.format(env_id), download_to)
    return download_to.getvalue()


def parse_env_vars(as_str):
    as_obj = json.loads(as_str)

    seen_names = set()
    for v in as_obj["vars"]:
        name = v["name"]
        if name in seen_names:
            raise AssertionError("Duplicate name: {}".format(name))
        if name in PROGRAMMATIC_ENV_VARS:
            raise AssertionError("Var should be set programmatically, not manually: {}".format(name))
        seen_names.add(name)

    as_obj["vars"] = sorted(as_obj["vars"], key=itemgetter("name"))

    return as_obj


def upload_env_vars_to_s3(env_id, as_obj):
    s3 = boto3.client('s3')
    upload_from = StringIO(json.dumps(as_obj, indent=2, sort_keys=True))
    upload_from.seek(0)
    s3.upload_fileobj(upload_from, 'freebird-fail', 'env/vars/{}.json'.format(env_id))


def export_env_vars_bash(as_obj):
    # This might crash on crazy names or values with characters like backslashes or quotes
    exported = ""
    for v in as_obj["vars"]:
        exported += "export {}='{}'\n".format(v["name"], v["value"])
    return exported


def add_programmatic_env_vars(as_obj, env_id):
    # This method modifies as_obj
    env = load_config()["env"][env_id]
    ec2 = boto3.resource('ec2', region_name=env["region"])

    elasticsearch_env_vars = {}

    if 'hostedElasticsearch' in env:
        es_cluster_name = env['hostedElasticsearch']['clusterName']
        es_port = env['hostedElasticsearch']['port']
        es_shield_username = env['hostedElasticsearch']['shieldUsername']

        elasticsearch_env_vars = {
            "ES_CLUSTER_NAME": es_cluster_name,
            "ES_PORT": es_port,
            "ES_HOSTS": "%s.%s.aws.found.io" % (es_cluster_name, env['region']),
            "ES_SHIELD_USERNAME": es_shield_username
        }
    else:
        es_hosts = [i.private_ip_address for i in get_instances(ec2, env_id=env_id, role="elasticsearch")]
        assert es_hosts, "No Elasticsearch hosts found"

        elasticsearch_env_vars = {
            "ES_CLUSTER_NAME": "elasticsearch",
            "ES_PORT": 9300,
            "ES_HOSTS": ",".join(es_hosts)
        }

    programmatic_env_vars = {
        "KAFKA_HOSTS": "{}:9092".format(get_hostname(ec2, env_id, "kafka")),
        "TITAN_HOST": get_hostname(ec2, env_id, env["cassandraRole"]),
    }
    programmatic_env_vars.update(elasticsearch_env_vars)

    for k in programmatic_env_vars.keys():
        assert k in PROGRAMMATIC_ENV_VARS

    as_obj["vars"] += [{"name": k, "value": v} for k, v in programmatic_env_vars.iteritems()]


def make_env_lines(vars):
    return ["{}={}".format(v["name"], v["value"]) for v in sorted(vars, key=itemgetter("name"))]


def make_env_file(vars):
    return "".join([v + "\n" for v in make_env_lines(vars)])


def change_feature_flags(env_id, change_ffs):
    env_vars = json.loads(get_env_vars(env_id))
    env_vars["featureFlags"] = change_ffs(env_vars.get("featureFlags", {}))
    upload_env_vars_to_s3(env_id, env_vars)


def upload_env_file(runner, env_file):
    runner.run("mkdir -p .freebird")
    runner.run('cat << "END-OF-ENV-VARS" > .freebird/env-vars\n{}END-OF-ENV-VARS'.format(env_file))


def verify_setup(env_ids):
    for env_id in env_ids:
        verify_feature_flags(env_id)


def verify_feature_flags(env_id):
    for env_flag in json.loads(get_env_vars(env_id)).get("featureFlags", {}):
        if env_flag not in FEATURE_FLAGS:
            raise AssertionError("You need a version of FAIL with the {} flag.".format(env_flag))


def get_hostname(ec2, env_id, role):
    instance, = list(get_instances(ec2, env_id=env_id, role=role))
    return instance.private_ip_address


def create_user(env_id, role, policy_arns):
    user_name = resource_name(env_id, role)
    create_user_by_name(user_name, policy_arns)


def create_user_by_name(user_name, policy_arns):
    client_iam = boto3.client("iam")

    print "Creating user..."
    user = client_iam.create_user(UserName=user_name)
    print user

    print "Attaching policy..."
    for policy_arn in policy_arns:
        print client_iam.attach_user_policy(
            UserName=user_name,
            PolicyArn=policy_arn
        )

    print "Creating access key..."
    access_key = client_iam.create_access_key(UserName=user_name)
    print access_key

    print "User created."


def delete_user(env_id, role):
    user_name = resource_name(env_id, role)

    print "Finding user..."
    iam = boto3.resource('iam')
    user = iam.User(user_name)

    print "Deleting access keys..."
    for access_key in user.access_keys.all():
        access_key.delete()

    print "Detaching policies..."
    for policy in user.attached_policies.all():
        policy.detach_user(UserName=user_name)

    print "Deleting user..."
    user.delete()

    print "User deleted."


def get_my_email():
    if "MY_EMAIL" not in os.environ:
        raise AssertionError("Env var MY_EMAIL was not found. It's recommended to set this in your ~/.bashrc file.")
    return os.environ["MY_EMAIL"]


def check_window(time_of_day, window_start, window_end):
    def normalize_to_1900(date):
        return date.replace(year=1900, month=1, day=1)

    time_normalized = normalize_to_1900(time_of_day)
    start_normalized = normalize_to_1900(datetime.strptime(window_start, "%H:%M"))
    end_normalized = normalize_to_1900(datetime.strptime(window_end, "%H:%M"))

    assert start_normalized < end_normalized  # Support for start > end is an advanced feature

    print start_normalized, time_normalized, end_normalized, start_normalized < time_normalized < end_normalized
    return start_normalized < time_normalized < end_normalized


def verify_deploy_window(env, now_utc, allow_deploys_until_utc):
    window_start = env.get("deployWindowStartUtc")
    window_end = env.get("deployWindowEndUtc")
    if not (window_start and window_end):
        print "No deploy window exists."
        return

    if check_window(now_utc, window_start, window_end):
        print "Within deploy window."
        return

    if not allow_deploys_until_utc:
        raise AssertionError("Out of deploy window. You can make an exception by setting the value of "
                             "allowDeploysUntilUtc in the env var JSON.")

    if allow_deploys_until_utc - now_utc < timedelta():
        raise AssertionError("Out of deploy window and allowDeploysUntilUtc is in the past. You may change its value "
                             "if you want to allow a deploy outside of the deploy window.")

    if allow_deploys_until_utc - now_utc > timedelta(hours=8):
        raise AssertionError("Out of deploy window and allowDeploysUntilUtc is too far in the future. Please reset it "
                             "to a value closer to the present.")

    print "Out of deploy window, but allowDeploysUntilUtc is set to allow an exception."


def verify_deploy_window_for_env(env_id):
    config = load_config()
    env = config["env"][env_id]
    now_utc = datetime.utcnow()
    allow_deploys_until_utc_str = json.loads(get_env_vars(env_id)).get("allowDeploysUntilUtc")
    if allow_deploys_until_utc_str:
        allow_deploys_until_utc = time.strptime(allow_deploys_until_utc_str, "YYYY-MM-DDTHH:MM:SS")
    else:
        allow_deploys_until_utc = None
    verify_deploy_window(env, now_utc, allow_deploys_until_utc)