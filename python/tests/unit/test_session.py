import os

import pytest

from saucebindings.exceptions import SessionNotStartedException, InvalidPlatformException
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
        options = SauceOptions.chrome()

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


class TestAnnotations(object):

    def test_annotation_requires_start(self):
        sauce_session = SauceSession()
        with pytest.raises(SessionNotStartedException):
            sauce_session.annotate("comment")

    def test_annotates(self, mocker):
        sauce_session = SauceSession()
        mocker.patch.object(sauce_session, 'create_driver')

        driver = sauce_session.start()
        mocker.patch.object(driver, 'quit')
        mocker.patch.object(driver, 'execute_script')

        sauce_session.annotate("comment")

        driver.execute_script.assert_called_once_with("sauce:context=comment")

    def test_pause_requires_start(self):
        sauce_session = SauceSession()
        with pytest.raises(SessionNotStartedException):
            sauce_session.pause()

    def test_pauses(self, mocker):
        sauce_session = SauceSession()
        mocker.patch.object(sauce_session, 'create_driver')

        driver = sauce_session.start()
        mocker.patch.object(driver, 'quit')
        mocker.patch.object(driver, 'execute_script')

        sauce_session.pause()

        driver.execute_script.assert_called_once_with("sauce: break")

    def test_disable_logging_requires_start(self):
        sauce_session = SauceSession()
        with pytest.raises(SessionNotStartedException):
            sauce_session.disable_logging()

    def test_disables_logging(self, mocker):
        sauce_session = SauceSession()
        mocker.patch.object(sauce_session, 'create_driver')

        driver = sauce_session.start()
        mocker.patch.object(driver, 'quit')
        mocker.patch.object(driver, 'execute_script')

        sauce_session.disable_logging()

        driver.execute_script.assert_called_once_with("sauce: disable log")

    def test_enable_logging_requires_start(self):
        sauce_session = SauceSession()
        with pytest.raises(SessionNotStartedException):
            sauce_session.enable_logging()

    def test_enables_logging(self, mocker):
        sauce_session = SauceSession()
        mocker.patch.object(sauce_session, 'create_driver')

        driver = sauce_session.start()
        mocker.patch.object(driver, 'quit')
        mocker.patch.object(driver, 'execute_script')

        sauce_session.enable_logging()

        driver.execute_script.assert_called_once_with("sauce: enable log")

    def test_stop_network_requires_start(self):
        sauce_session = SauceSession()
        with pytest.raises(SessionNotStartedException):
            sauce_session.stop_network()

    def test_stop_network_requires_mac(self, mocker):
        sauce_session = SauceSession()
        mocker.patch.object(sauce_session, 'create_driver')
        sauce_session.start()

        with pytest.raises(InvalidPlatformException):
            sauce_session.stop_network()

    def test_stops_network(self, mocker):
        sauce_session = SauceSession(SauceOptions.safari())
        mocker.patch.object(sauce_session, 'create_driver')

        driver = sauce_session.start()
        mocker.patch.object(driver, 'quit')
        mocker.patch.object(driver, 'execute_script')

        sauce_session.stop_network()

        driver.execute_script.assert_called_once_with("sauce: stop network")

    def test_start_network_requires_start(self):
        sauce_session = SauceSession()
        with pytest.raises(SessionNotStartedException):
            sauce_session.start_network()

    def test_start_network_requires_mac(self, mocker):
        sauce_session = SauceSession()
        mocker.patch.object(sauce_session, 'create_driver')
        sauce_session.start()

        with pytest.raises(InvalidPlatformException):
            sauce_session.start_network()

    def test_starts_network(self, mocker):
        sauce_session = SauceSession(SauceOptions.safari())
        mocker.patch.object(sauce_session, 'create_driver')

        driver = sauce_session.start()
        mocker.patch.object(driver, 'quit')
        mocker.patch.object(driver, 'execute_script')

        sauce_session.start_network()

        driver.execute_script.assert_called_once_with("sauce: start network")

    def test_change_name_requires_start(self):
        sauce_session = SauceSession()
        with pytest.raises(SessionNotStartedException):
            sauce_session.change_name("New Name")

    def test_changes_name(self, mocker):
        sauce_session = SauceSession()
        mocker.patch.object(sauce_session, 'create_driver')

        driver = sauce_session.start()
        mocker.patch.object(driver, 'quit')
        mocker.patch.object(driver, 'execute_script')

        sauce_session.change_name("New Name")

        driver.execute_script.assert_called_once_with("sauce:job-name=New Name")

    def test_add_tags_requires_start(self):
        sauce_session = SauceSession()
        with pytest.raises(SessionNotStartedException):
            sauce_session.add_tags(['foo', 'bar'])

    def test_adds_tags_as_array(self, mocker):
        sauce_session = SauceSession()
        mocker.patch.object(sauce_session, 'create_driver')

        driver = sauce_session.start()
        mocker.patch.object(driver, 'quit')
        mocker.patch.object(driver, 'execute_script')

        sauce_session.add_tags(['foo', 'bar'])

        driver.execute_script.assert_called_once_with("sauce:job-tags=foo,bar")

    def test_adds_tags_as_string(self, mocker):
        sauce_session = SauceSession()
        mocker.patch.object(sauce_session, 'create_driver')

        driver = sauce_session.start()
        mocker.patch.object(driver, 'quit')
        mocker.patch.object(driver, 'execute_script')

        sauce_session.add_tags('foo')

        driver.execute_script.assert_called_once_with("sauce:job-tags=foo")

    def test_adds_tags_as_csv_string(self, mocker):
        sauce_session = SauceSession()
        mocker.patch.object(sauce_session, 'create_driver')

        driver = sauce_session.start()
        mocker.patch.object(driver, 'quit')
        mocker.patch.object(driver, 'execute_script')

        sauce_session.add_tags('foo,bar')

        driver.execute_script.assert_called_once_with("sauce:job-tags=foo,bar")
