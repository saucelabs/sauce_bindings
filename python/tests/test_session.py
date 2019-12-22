import pytest
import os
from simplesauce.options import SauceOptions
from simplesauce.session import SauceSession


class TestInit(object):

    def test_defaults_to_us_west_data_center(self):
        session = SauceSession()

        assert "us-west-1" in session.remote_url

    def test_overrides_default_value_for_data_center(self):
        session = SauceSession(data_center='eu')

        assert "eu-central-1" in session.remote_url

    def test_raises_exception_if_data_center_is_invalid(self):
        with pytest.raises(ValueError):
            SauceSession(data_center='invalid')

    def test_accepts_provided_options_instance(self):
        options = SauceOptions()

        session = SauceSession(options)

        assert session.options.browser_name == 'chrome'
        assert session.options.browser_version == 'latest'
        assert session.options.platform_name == 'Windows 10'

    def test_generates_default_options_instance_if_not_provided(self):
        session = SauceSession()

        assert session.options.browser_name == 'chrome'
        assert session.options.browser_version == 'latest'
        assert session.options.platform_name == 'Windows 10'

    def test_uses_username_and_access_key_if_environment_variables_are_defined(self):
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

    def test_raises_exception_if_data_center_is_invalid(self):
        session = SauceSession()

        with pytest.raises(ValueError):
            session.data_center = 'invalid'

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

    def test_creates_a_session_on_sauce_labs(self):
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


class TestURL(object):

    def test_user_can_override_default_url(self):
        session = SauceSession()
        session.remote_url = 'https://foo:bar@foobar.com/wd/hub'

        assert session.remote_url == 'https://foo:bar@foobar.com/wd/hub'


class TestStop(object):

    def test_sauce_session_ended(self):
        # TODO - Mock the driver to verify it gets the quit command
        pass

    def test_passing_case(self):
        session = SauceSession()

        session.start()

        session.stop(True)

    def test_failing_case(self):
        session = SauceSession()

        session.start()

        session.stop(False)

    def test_raises_exception_if_result_value_not_used(self):
        session = SauceSession()

        session.start()

        with pytest.raises(TypeError):
            session.stop()
