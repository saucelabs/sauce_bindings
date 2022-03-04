import os

import pytest

from saucebindings.options import SauceOptions
from saucebindings.session import SauceSession


class TestInit(object):

    def test_defaults_to_us_west_data_center(self):
        session = SauceSession()

        assert "us-west-1" in session.remote_url

    def test_overrides_default_value_for_data_center(self):
        session = SauceSession(data_center='eu-central')

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

        session.data_center = 'eu-central'

        assert "eu-central-1" in session.remote_url

    def test_raises_exception_if_data_center_is_invalid(self):
        session = SauceSession()

        with pytest.raises(ValueError):
            session.data_center = 'invalid'


class TestStart(object):
    @pytest.fixture(autouse=True)
    def set_users(self, monkeypatch):
        monkeypatch.setenv("BUILD_TAG", "PRESENT")
        monkeypatch.setenv("BUILD_NAME", "TEST BUILD")
        monkeypatch.setenv("BUILD_NUMBER", "11")
        monkeypatch.setenv("SAUCE_USERNAME", "test-user")
        monkeypatch.setenv("SAUCE_ACCESS_KEY", "1234")

    def test_creates_a_session_on_sauce_labs(self, mocker):
        default_url = 'https://ondemand.us-west-1.saucelabs.com/wd/hub'
        expected_caps = {'browserName': 'chrome',
                         'browserVersion': 'latest',
                         'platformName': 'Windows 10',
                         'sauce:options': {'build': 'TEST BUILD: 11',
                                                   'username': os.getenv('SAUCE_USERNAME'),
                                                   'accessKey': os.getenv('SAUCE_ACCESS_KEY')}}

        sauce_session = SauceSession()
        mocker.patch.object(sauce_session, 'create_driver')

        sauce_session.start()

        sauce_session.create_driver.assert_called_once_with(default_url, expected_caps)

class TestURL(object):

    def test_user_can_override_default_url(self):
        session = SauceSession()
        session.remote_url = 'https://foo:bar@foobar.com/wd/hub'

        assert session.remote_url == 'https://foo:bar@foobar.com/wd/hub'


class TestStop(object):

    def test_sauce_session_ended(self, mocker):
        sauce_session = SauceSession()
        mocker.patch.object(sauce_session, 'create_driver')

        driver = sauce_session.start()
        mocker.patch.object(driver, 'quit')

        sauce_session.stop(True)
        driver.quit.assert_called_once()

    def test_passing_case(self, mocker):
        sauce_session = SauceSession()
        mocker.patch.object(sauce_session, 'create_driver')

        driver = sauce_session.start()
        mocker.patch.object(driver, 'execute_script')

        sauce_session.stop(True)

        driver.execute_script.assert_called_once_with('sauce:job-result=passed')

    def test_failing_case(self, mocker):
        sauce_session = SauceSession()
        mocker.patch.object(sauce_session, 'create_driver')

        driver = sauce_session.start()
        mocker.patch.object(driver, 'execute_script')

        sauce_session.stop(False)

        driver.execute_script.assert_called_once_with('sauce:job-result=failed')
