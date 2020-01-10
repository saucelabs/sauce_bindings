import pytest
import os
from simplesauce.options import SauceOptions
from simplesauce.session import SauceSession

SAUCE_USERNAME_HOLDER = os.getenv('SAUCE_USERNAME', None)
SAUCE_ACCESS_KEY_HOLDER = os.getenv('SAUCE_ACCESS_KEY', None)


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


class TestDataCenter(object):

    def test_overrides_default_value_for_data_center(self):
        session = SauceSession()

        session.data_center = 'eu'

        assert "eu-central-1" in session.remote_url

    def test_raises_exception_if_data_center_is_invalid(self):
        session = SauceSession()

        with pytest.raises(ValueError):
            session.data_center = 'invalid'


class TestStart(object):

    def test_creates_a_session_on_sauce_labs(self):
        session = SauceSession()

        session.start()

        assert session.driver.session_id

    def test_raises_exception_if_no_username_set(self):
        del os.environ['SAUCE_USERNAME']
        session = SauceSession()

        with pytest.raises(KeyError):
            session.start()

        os.environ['SAUCE_USERNAME'] = SAUCE_USERNAME_HOLDER

    def test_raises_exception_if_no_access_key_set(self):
        del os.environ['SAUCE_ACCESS_KEY']
        session = SauceSession()

        with pytest.raises(KeyError):
            session.start()

        os.environ['SAUCE_ACCESS_KEY'] = SAUCE_ACCESS_KEY_HOLDER


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
