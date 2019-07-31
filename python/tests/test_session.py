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

        session.options.browserName == 'chrome'
        session.options.browserVersion == 'latest'
        session.options.platformName == 'Windows 10'

    def test_generates_default_Options_instance_if_not_provided(self):
        session = SauceSession()

        session.options.browserName == 'chrome'
        session.options.browserVersion == 'latest'
        session.options.platformName == 'Windows 10'

    def test_uses_username_and_access_key_if_ENV_variables_are_defined(self):
        session = SauceSession()

        session.username == os.environ['SAUCE_USERNAME']
        session.access_key == os.environ['SAUCE_ACCESS_KEY']

    def test_accepts_provided_username_and_access_key(self):
        user = 'alice.smith'
        access_key = 'abce-defg-hijk'

        session = SauceSession(username=user, access_key=access_key)

        session.username == os.environ['SAUCE_USERNAME']
        session.access_key == os.environ['SAUCE_ACCESS_KEY']


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
        pass

    def test_raises_exception_if_no_username_set(self):
        pass

    def test_raises_exception_if_no_access_key_set(self):
        pass

class TestStop(object):

    def test_it_quits_the_driver(self):
        pass


"""
def test_start_default_session():
    sauce = SauceSession()

    driver = sauce.start()

    driver.close()


def test_start_session_by_browserName():
    sauce = SauceSession('Chrome')

    driver = sauce.start()

    driver.close()
"""