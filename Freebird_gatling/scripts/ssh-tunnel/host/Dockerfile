FROM amazonlinux:latest

RUN yum -y update
RUN yum -y install openssh-server

ADD sshd_config /etc/ssh/sshd_config
ADD launch /root/launch

CMD /root/launch
