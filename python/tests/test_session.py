import pytest
import os
from simplesauce.options import SauceOptions
from simplesauce.session import SauceSession


class TestInit(object):

    def test_defaults_to_US_West_data_center(self):
        session = SauceSession()

        assert "us-west-1" in session.remote_url

    def test_overrides_default_value_for_data_center(self):
        session = SauceSession(data_center='eu')

        assert "eu-central-1" in session.remote_url

    def test_raises_exception_if_data_center_is_invalid(self):
        with pytest.raises(KeyError):
            session = SauceSession(data_center='uu')

        with pytest.raises(KeyError):
            session = SauceSession(data_center='')

    def test_accepts_provided_Options_instance(self):
        options = SauceOptions()

        session = SauceSession(options=options)

        assert session.options.browserName == 'chrome'
        assert session.options.browserVersion == 'latest'
        assert session.options.platformName == 'windows 10'

    def test_generates_default_Options_instance_if_not_provided(self):
        session = SauceSession()

        assert session.options.browserName == 'chrome'
        assert session.options.browserVersion == 'latest'
        assert session.options.platformName == 'windows 10'

    def test_uses_username_and_access_key_if_ENV_variables_are_defined(self):
        session = SauceSession()

        assert session.username == os.environ['SAUCE_USERNAME']
        assert session.access_key == os.environ['SAUCE_ACCESS_KEY']

    def test_accepts_provided_username_and_access_key(self):
        user = 'alice.smith'
        access_key = 'abce-defg-hijk'

        session = SauceSession(username=user, access_key=access_key)

        assert session.username == user
        assert session.access_key == access_key


class TestDataCenter(object):

    def test_overrides_default_value_for_data_center(self):
        session = SauceSession()

        session.data_center = 'eu'

        assert "eu-central-1" in session.remote_url


class TestUsername(object):

    def test_accepts_provided_username(self):
        user = 'bob.smith'
        session = SauceSession()

        session.username = user

        assert session.username == user


class TestAccessKey(object):

    def test_accepts_provided_access_key(self):
        key = 'abcd-1234-5678'
        session = SauceSession()

        session.access_key = key

        assert session.access_key == key


class TestStart(object):

    def test_creates_a_session_on_Sauce_labs(self):
        session = SauceSession()

        session.start()

        assert session.driver.session_id

    def test_raises_exception_if_no_username_set(self):
        session = SauceSession()

        session.username = None
        
        with pytest.raises(KeyError):
            session.start()

    def test_raises_exception_if_no_access_key_set(self):
        session = SauceSession()

        session.access_key = None

        with pytest.raises(KeyError):
            session.start()

class TestStop(object):

    def test_it_quits_the_driver(self):
        session = SauceSession()

        session.start()

        session.stop()

class TestStatus(object):

    def test_setting_pass_status(self):

        session = SauceSession()
        session.start()

        assert session.set_test_status('passed')

        session.stop()

    def test_setting_fail_status(self):

        session = SauceSession()
        session.start()

        assert not session.set_test_status('fail')

        session.stop()

    def test_setting_pass_boolean(self):

        session = SauceSession()
        session.start()

        assert session.set_test_status(True)

        session.stop()

    def test_setting_fail_boolean(self):

        session = SauceSession()
        session.start()

        assert not session.set_test_status(False)

        session.stop()

