from datetime import datetime

import pytest

import util


def test_time_window():
    a_time = datetime(year=2016, month=5, day=15, hour=12, minute=16, second=3)
    with pytest.raises(AssertionError):
        util.check_window(a_time, "13:00", "11:00")
    assert util.check_window(a_time, "11:00", "13:00")
    assert not util.check_window(a_time, "13:00", "15:00")


def test_verify_deploy_window_no_window():
    util.verify_deploy_window({}, datetime.utcnow(), None)


def test_verify_deploy_window_window():
    env = {
        "deployWindowStartUtc": "11:00",
        "deployWindowEndUtc": "13:00",
    }

    now_utc = datetime(year=2000, month=1, day=2, hour=3)

    with pytest.raises(AssertionError):
        # allowDeploysUntilUtc is not set
        util.verify_deploy_window(env, now_utc, None)

    with pytest.raises(AssertionError):
        # allowDeploysUntilUtc is in the past
        util.verify_deploy_window(env, now_utc, datetime(year=2000, month=1, day=2, hour=2))

    with pytest.raises(AssertionError):
        # allowDeploysUntilUtc is too far in the future
        util.verify_deploy_window(env, now_utc, datetime(year=2000, month=1, day=2, hour=22))

    # allowDeploysUntilUtc is just right
    util.verify_deploy_window(env, now_utc, datetime(year=2000, month=1, day=2, hour=4))

    # During the deploy window
    util.verify_deploy_window(env, datetime(year=2000, month=1, day=2, hour=12), None)
