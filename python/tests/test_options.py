import os

import pytest

from simplesauce.options import SauceOptions
from selenium.webdriver.firefox.options import Options as FirefoxOptions
from selenium.webdriver import DesiredCapabilities


class TestInit(object):

    def test_defaults_to_win10_chrome_latest(self):
        sauce = SauceOptions()

        assert sauce.browser_name == 'chrome'
        assert sauce.browser_version == 'latest'
        assert sauce.platform_name == 'Windows 10'

    def test_accepts_browser_name(self):
        sauce = SauceOptions('firefox')

        assert sauce.browser_name == 'firefox'
        assert sauce.browser_version == 'latest'
        assert sauce.platform_name == 'Windows 10'

    def test_accepts_browser_version_platform_name(self):
        sauce = SauceOptions(browserName='firefox', browserVersion='75.0', platformName='macOS 10.13')

        assert sauce.browser_name == 'firefox'
        assert sauce.browser_version == '75.0'
        assert sauce.platform_name == 'macOS 10.13'

    def test_accepts_w3c_values(self):
        sauce = SauceOptions(acceptInsecureCerts=True, pageLoadStrategy='eager')

        assert sauce.accept_insecure_certs is True
        assert sauce.page_load_strategy == 'eager'

    def test_accepts_sauce_values_with_dict(self):
        options = {'maxDuration': 1,
                   'commandTimeout': 2,
                   'idleTimeout': 3,
                   'name': 'foo',
                   'build': 'bar',
                   'tags': ['foo', 'bar'],
                   'parentTunnel': 'bar',
                   'tunnelIdentifier': 'foobar',
                   'screenResolution': '10x10',
                   'timeZone': 'Foo',
                   'extendedDebugging': True,
                   'capturePerformance': True,
                   'recordVideo': False,
                   'videoUploadOnPass': False,
                   'recordScreenshots': False,
                   'recordLogs': False}

        sauce = SauceOptions(**options)

        assert sauce.max_duration == 1
        assert sauce.command_timeout == 2
        assert sauce.idle_timeout == 3
        assert sauce.name == 'foo'
        assert sauce.build == 'bar'
        assert sauce.tags == ['foo', 'bar']
        assert sauce.parent_tunnel == 'bar'
        assert sauce.tunnel_identifier == 'foobar'
        assert sauce.screen_resolution == '10x10'
        assert sauce.time_zone == 'Foo'
        assert sauce.extended_debugging is True
        assert sauce.capture_performance is True
        assert sauce.record_video is False
        assert sauce.video_upload_on_pass is False
        assert sauce.record_screenshots is False
        assert sauce.record_logs is False

    def test_accepts_sauce_values_as_params(self):
        sauce = SauceOptions(maxDuration=1, commandTimeout=2)
        assert sauce.max_duration == 1
        assert sauce.command_timeout == 2

    def test_accepts_selenium_browser_options_instance(self):
        options = FirefoxOptions()
        options.add_argument('--foo')
        options.set_preference('foo', 'bar')

        sauce = SauceOptions(seleniumOptions=options)

        assert sauce.browser_name == 'firefox'
        assert sauce.selenium_options['moz:firefoxOptions'] == {'args': ['--foo'], 'prefs': {'foo': 'bar'}}

    def test_accepts_w3c_sauce_options_capabilities(self):
        browser_options = FirefoxOptions()
        browser_options.add_argument('--foo')
        browser_options.set_preference('foo', 'bar')

        options = {'maxDuration': 1,
                   'commandTimeout': 2}

        w3c_options = {'acceptInsecureCerts': True,
                       'pageLoadStrategy': 'eager'}

        options.update(w3c_options)
        sauce = SauceOptions(seleniumOptions=browser_options, **options)

        assert sauce.browser_name == 'firefox'
        assert sauce.accept_insecure_certs is True
        assert sauce.page_load_strategy == 'eager'
        assert sauce.max_duration == 1
        assert sauce.command_timeout == 2
        assert sauce.selenium_options['moz:firefoxOptions'] == {'args': ['--foo'], 'prefs': {'foo': 'bar'}}

    def test_default_build_name(self):
        os.environ['BUILD_TAG'] = ' '
        os.environ['BUILD_NAME'] = 'BUILD NAME'
        os.environ['BUILD_NUMBER'] = '123'

        sauce = SauceOptions()

        assert sauce.build == 'BUILD NAME: 123'

        os.environ.pop("BUILD_TAG")
        os.environ.pop("BUILD_NAME")
        os.environ.pop("BUILD_NUMBER")

    def test_argument_error_as_param(self):
        with pytest.raises(AttributeError):
            SauceOptions(foo='Bar')

    def test_argument_error_from_dict(self):
        options = {'foo': 'Bar'}
        with pytest.raises(AttributeError):
            SauceOptions(**options)


class TestSauceSpecificOptions(object):

    def test_w3c_options(self):
        sauce = SauceOptions()
        sauce.browser_name = 'safari'
        sauce.platform_name = 'macOS 10.14'

        sauce.accept_insecure_certs = True

        assert sauce.browser_name == 'safari'
        assert sauce.accept_insecure_certs is True
        assert sauce.platform_name == 'macOS 10.14'

    def test_sauce_options(self):
        sauce = SauceOptions()
        sauce.name = 'my-test'
        sauce.record_screenshots = False

        assert sauce.name == 'my-test'
        assert sauce.record_screenshots is False


class TestCapabilitiesCreation(object):

    def test_capabilities_for_w3c(self):
        sauce = SauceOptions()
        sauce.browser_name = 'safari'
        sauce.platform_name = 'macOS 10.14'
        sauce.accept_insecure_certs = True

        capabilities = sauce.to_capabilities()

        assert capabilities['acceptInsecureCerts'] is True
        assert capabilities['browserName'] == 'safari'
        assert capabilities['browserVersion'] == 'latest'
        assert capabilities['sauce:options']['build'] is not None

    def test_capabilities_for_sauce(self):
        sauce = SauceOptions()
        sauce.name = 'my-test'
        sauce.record_screenshots = False

        capabilities = sauce.to_capabilities()

        assert capabilities['sauce:options']['recordScreenshots'] is False
        assert capabilities['sauce:options']['name'] == 'my-test'

    def test_capabilities_for_selenium(self):
        browser_options = FirefoxOptions()
        browser_options.add_argument('--foo')
        browser_options.set_preference('foo', 'bar')

        sauce = SauceOptions(seleniumOptions=browser_options)

        capabilities = sauce.to_capabilities()

        assert capabilities['moz:firefoxOptions']['args'] == ['--foo']
        assert capabilities['moz:firefoxOptions']['prefs'] == {'foo': 'bar'}
