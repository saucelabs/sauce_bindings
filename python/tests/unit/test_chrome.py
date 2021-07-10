import os

import pytest

from saucebindings.options import SauceOptions
from selenium.webdriver.chrome.options import Options as ChromeOptions


class TestInit(object):

    def test_defaults(self):
        sauce = SauceOptions.chrome()

        assert sauce.browser_name == 'chrome'
        assert sauce.browser_version == 'latest'
        assert sauce.platform_name == 'Windows 10'

    def test_accepts_browser_version_platform_name(self):
        sauce = SauceOptions.chrome(browserVersion='75.0', platformName='macOS 10.13')

        assert sauce.browser_name == 'chrome'
        assert sauce.browser_version == '75.0'
        assert sauce.platform_name == 'macOS 10.13'

    def test_accepts_w3c_values_with_dict(self):
        timeouts = {'implicit': 1,
                    'pageLoad': 59,
                    'script': 29}
        options = {'acceptInsecureCerts': True,
                   'pageLoadStrategy': 'eager',
                   'setWindowRect': True,
                   'unhandledPromptBehavior': 'accept',
                   'strictFileInteractability': True,
                   'timeouts': timeouts}

        sauce = SauceOptions.chrome(**options)

        assert sauce.accept_insecure_certs is True
        assert sauce.page_load_strategy == 'eager'
        assert sauce.set_window_rect is True
        assert sauce.unhandled_prompt_behavior == 'accept'
        assert sauce.strict_file_interactability is True
        assert sauce.timeouts == timeouts

    def test_accepts_w3c_values_as_params(self):
        timeouts = {'implicit': 1,
                    'pageLoad': 59,
                    'script': 29}

        sauce = SauceOptions.chrome(acceptInsecureCerts=True,
                                    pageLoadStrategy='eager',
                                    setWindowRect=True,
                                    unhandledPromptBehavior='accept',
                                    strictFileInteractability=True,
                                    timeouts=timeouts)

        assert sauce.accept_insecure_certs is True
        assert sauce.page_load_strategy == 'eager'
        assert sauce.set_window_rect is True
        assert sauce.unhandled_prompt_behavior == 'accept'
        assert sauce.strict_file_interactability is True
        assert sauce.timeouts == timeouts

    def test_accepts_sauce_values_with_dict(self):
        options = {'build': 'bar',
                   'capturePerformance': True,
                   'chromedriverVersion': "71",
                   'commandTimeout': 2,
                   'customData': {'foo': 'foo',
                                  'bar': 'bar'},
                   'extendedDebugging': True,
                   'idleTimeout': 3,
                   'maxDuration': 1,
                   'name': 'foo',
                   'parentTunnel': 'bar',
                   'prerun': {'executable': 'http://url.to/your/executable.exe',
                              'args': ['--silent', '-a', '-q'],
                              'background': False,
                              'timeout': 120},
                   'priority': 0,
                   'public': 'team',
                   'recordLogs': False,
                   'recordScreenshots': False,
                   'recordVideo': False,
                   'screenResolution': '10x10',
                   'tags': ['foo', 'bar'],
                   'timeZone': 'Foo',
                   'tunnelIdentifier': 'foobar',
                   'videoUploadOnPass': False}

        sauce = SauceOptions.chrome(**options)

        assert sauce.build == 'bar'
        assert sauce.capture_performance is True
        assert sauce.chromedriver_version == '71'
        assert sauce.command_timeout == 2
        assert sauce.custom_data == {'foo': 'foo',
                                     'bar': 'bar'}
        assert sauce.extended_debugging is True
        assert sauce.idle_timeout == 3
        assert sauce.max_duration == 1
        assert sauce.name == 'foo'
        assert sauce.parent_tunnel == 'bar'
        assert sauce.prerun == {'executable': 'http://url.to/your/executable.exe',
                                'args': ['--silent', '-a', '-q'],
                                'background': False,
                                'timeout': 120}
        assert sauce.priority == 0
        assert sauce.public == 'team'
        assert sauce.record_logs is False
        assert sauce.record_screenshots is False
        assert sauce.record_video is False
        assert sauce.screen_resolution == '10x10'
        assert sauce.tags == ['foo', 'bar']
        assert sauce.time_zone == 'Foo'
        assert sauce.tunnel_identifier == 'foobar'
        assert sauce.video_upload_on_pass is False

    def test_accepts_sauce_values_as_params(self):
        custom_data = {'foo': 'foo',
                       'bar': 'bar'}
        prerun = {'executable': 'http://url.to/your/executable.exe',
                  'args': ['--silent', '-a', '-q'],
                  'background': False,
                  'timeout': 120}
        sauce = SauceOptions.chrome(build='bar',
                                    capturePerformance=True,
                                    chromedriverVersion='71',
                                    commandTimeout=2,
                                    customData=custom_data,
                                    extendedDebugging=True,
                                    idleTimeout=3,
                                    maxDuration=1,
                                    name='foo',
                                    parentTunnel='bar',
                                    prerun=prerun,
                                    priority=0,
                                    public='team',
                                    recordLogs=False,
                                    recordScreenshots=False,
                                    recordVideo=False,
                                    screenResolution='10x10',
                                    tags=['foo', 'bar'],
                                    timeZone='Foo',
                                    tunnelIdentifier='foobar',
                                    videoUploadOnPass=False)

        assert sauce.build == 'bar'
        assert sauce.capture_performance is True
        assert sauce.chromedriver_version == '71'
        assert sauce.command_timeout == 2
        assert sauce.custom_data == {'foo': 'foo',
                                     'bar': 'bar'}
        assert sauce.extended_debugging is True
        assert sauce.idle_timeout == 3
        assert sauce.max_duration == 1
        assert sauce.name == 'foo'
        assert sauce.parent_tunnel == 'bar'
        assert sauce.prerun == {'executable': 'http://url.to/your/executable.exe',
                                'args': ['--silent', '-a', '-q'],
                                'background': False,
                                'timeout': 120}
        assert sauce.priority == 0
        assert sauce.public == 'team'
        assert sauce.record_logs is False
        assert sauce.record_screenshots is False
        assert sauce.record_video is False
        assert sauce.screen_resolution == '10x10'
        assert sauce.tags == ['foo', 'bar']
        assert sauce.time_zone == 'Foo'
        assert sauce.tunnel_identifier == 'foobar'
        assert sauce.video_upload_on_pass is False

    def test_accepts_selenium_browser_options_instance(self):
        options = ChromeOptions()
        options.add_argument('--foo')

        sauce = SauceOptions.chrome(seleniumOptions=options)

        assert sauce.browser_name == 'chrome'
        assert sauce.selenium_options['goog:chromeOptions'] == {'args': ['--foo'], 'extensions': []}

    def test_accepts_w3c_sauce_options_capabilities(self):
        browser_options = ChromeOptions()
        browser_options.add_argument('--foo')

        options = {'maxDuration': 1,
                   'commandTimeout': 2}

        w3c_options = {'acceptInsecureCerts': True,
                       'pageLoadStrategy': 'eager'}

        options.update(w3c_options)
        sauce = SauceOptions.chrome(seleniumOptions=browser_options, **options)

        assert sauce.browser_name == 'chrome'
        assert sauce.accept_insecure_certs is True
        assert sauce.page_load_strategy == 'eager'
        assert sauce.max_duration == 1
        assert sauce.command_timeout == 2
        assert sauce.selenium_options['goog:chromeOptions'] == {'args': ['--foo'], 'extensions': []}

    def test_default_build_name(self):
        os.environ['BUILD_TAG'] = ' '
        os.environ['BUILD_NAME'] = 'BUILD NAME'
        os.environ['BUILD_NUMBER'] = '123'

        sauce = SauceOptions.chrome()

        assert sauce.build == 'BUILD NAME: 123'

        os.environ.pop("BUILD_TAG")
        os.environ.pop("BUILD_NAME")
        os.environ.pop("BUILD_NUMBER")

    def test_argument_error_as_param(self):
        with pytest.raises(AttributeError):
            SauceOptions.chrome(foo='Bar')

    def test_argument_error_from_dict(self):
        options = {'foo': 'Bar'}
        with pytest.raises(AttributeError):
            SauceOptions.chrome(**options)


class TestSettingSpecificOptions(object):

    def test_w3c_options(self):
        timeouts = {'implicit': 1,
                    'pageLoad': 59,
                    'script': 29}

        options = SauceOptions.chrome()
        options.browser_version = '7'
        options.platform_name = 'macOS 10.14'
        options.accept_insecure_certs = True
        options.page_load_strategy = 'eager'
        options.set_window_rect = True
        options.unhandled_prompt_behavior = 'accept'
        options.strict_file_interactability = True
        options.timeouts = timeouts

        assert options.browser_name == 'chrome'
        assert options.browser_version == '7'
        assert options.platform_name == 'macOS 10.14'
        assert options.accept_insecure_certs is True
        assert options.page_load_strategy == 'eager'
        assert options.set_window_rect is True
        assert options.unhandled_prompt_behavior == 'accept'
        assert options.strict_file_interactability is True
        assert options.timeouts == timeouts

    def test_sauce_options(self):
        prerun = {'executable': 'http://url.to/your/executable.exe',
                  'args': ['--silent', '-a', '-q'],
                  'background': False,
                  'timeout': 120}
        custom_data = {'foo': 'foo',
                       'bar': 'bar'}
        tags = ['foo', 'bar']

        options = SauceOptions.chrome()

        options.build = 'Sample Build Name'
        options.capture_performance = True
        options.chromedriver_version = '71'
        options.command_timeout = 2
        options.custom_data = custom_data
        options.extended_debugging = True
        options.idle_timeout = 3
        options.max_duration = 300
        options.name = 'Sample Test Name'
        options.parent_tunnel = 'Mommy'
        options.prerun = prerun
        options.priority = 0
        options.public = 'team'
        options.record_logs = False
        options.record_screenshots = False
        options.record_video = False
        options.screen_resolution = '10x10'
        options.tags = tags
        options.time_zone = 'San Francisco'
        options.tunnel_identifier = 'tunnelname'
        options.video_upload_on_pass = False

        assert options.build == 'Sample Build Name'
        assert options.capture_performance is True
        assert options.chromedriver_version == '71'
        assert options.command_timeout == 2
        assert options.custom_data == custom_data
        assert options.extended_debugging is True
        assert options.idle_timeout == 3
        assert options.max_duration == 300
        assert options.name == 'Sample Test Name'
        assert options.parent_tunnel == 'Mommy'
        assert options.prerun == prerun
        assert options.priority == 0
        assert options.public == 'team'
        assert options.record_logs is False
        assert options.record_screenshots is False
        assert options.record_video is False
        assert options.screen_resolution == '10x10'
        assert options.tags == tags
        assert options.time_zone == 'San Francisco'
        assert options.tunnel_identifier == 'tunnelname'
        assert options.video_upload_on_pass is False

    def test_setting_browser_name(self):
        options = SauceOptions.chrome()

        with pytest.raises(AttributeError):
            options.browser_name = 'chrome'

    def test_setting_invalid_option(self):
        options = SauceOptions.chrome()

        with pytest.raises(AttributeError):
            options.iedriver_version = '3.14'


class TestCapabilitiesCreation(object):

    def test_capabilities_for_w3c(self):
        options = SauceOptions.chrome()

        options.browser_version = '7'
        options.platform_name = 'macOS 10.14'
        options.accept_insecure_certs = True
        options.page_load_strategy = 'eager'
        options.set_window_rect = True
        options.unhandled_prompt_behavior = 'accept'
        options.strict_file_interactability = True
        options.timeouts = {'implicit': 1,
                            'pageLoad': 59,
                            'script': 29}
        options.build = "Build Name"

        expected_capabilities = {'browserName': 'chrome',
                                 'browserVersion': '7',
                                 'platformName': 'macOS 10.14',
                                 'acceptInsecureCerts': True,
                                 'pageLoadStrategy': 'eager',
                                 'setWindowRect': True,
                                 'unhandledPromptBehavior': 'accept',
                                 'strictFileInteractability': True,
                                 'timeouts': {'implicit': 1,
                                              'pageLoad': 59,
                                              'script': 29},
                                 'sauce:options': {'build': 'Build Name'}}

        assert options.to_capabilities() == expected_capabilities

    def test_capabilities_for_sauce(self):
        prerun = {'executable': 'http://url.to/your/executable.exe',
                  'args': ['--silent', '-a', '-q'],
                  'background': False,
                  'timeout': 120}
        custom_data = {'foo': 'foo',
                       'bar': 'bar'}
        tags = ['foo', 'bar']

        options = SauceOptions.chrome()

        options.build = 'Sample Build Name'
        options.capture_performance = True
        options.chromedriver_version = '71'
        options.command_timeout = 2
        options.custom_data = custom_data
        options.extended_debugging = True
        options.idle_timeout = 3
        options.max_duration = 300
        options.name = 'Sample Test Name'
        options.parent_tunnel = 'Mommy'
        options.prerun = prerun
        options.priority = 0
        options.public = 'team'
        options.record_logs = False
        options.record_screenshots = False
        options.record_video = False
        options.screen_resolution = '10x10'
        options.tags = tags
        options.time_zone = 'San Francisco'
        options.tunnel_identifier = 'tunnelname'
        options.video_upload_on_pass = False

        expected_capabilities = {'browserName': 'chrome',
                                 'browserVersion': 'latest',
                                 'platformName': 'Windows 10',
                                 'sauce:options': {'build': 'Sample Build Name',
                                                   'capturePerformance': True,
                                                   'chromedriverVersion': '71',
                                                   'commandTimeout': 2,
                                                   'customData': {'foo': 'foo',
                                                                  'bar': 'bar'},
                                                   'extendedDebugging': True,
                                                   'idleTimeout': 3,
                                                   'maxDuration': 300,
                                                   'name': 'Sample Test Name',
                                                   'parentTunnel': 'Mommy',
                                                   'prerun': prerun,
                                                   'priority': 0,
                                                   'public': 'team',
                                                   'recordLogs': False,
                                                   'recordScreenshots': False,
                                                   'recordVideo': False,
                                                   'screenResolution': '10x10',
                                                   'tags': ['foo', 'bar'],
                                                   'timeZone': 'San Francisco',
                                                   'tunnelIdentifier': 'tunnelname',
                                                   'videoUploadOnPass': False}}

        assert options.to_capabilities() == expected_capabilities

    def test_capabilities_for_selenium(self):
        browser_options = ChromeOptions()
        browser_options.add_argument('--foo')

        options = SauceOptions.chrome(seleniumOptions=browser_options)
        options.build = 'Sample Build Name'

        expected_capabilities = {'browserName': 'chrome',
                                 'browserVersion': 'latest',
                                 'platformName': 'Windows 10',
                                 'goog:chromeOptions': {'args': ['--foo'], 'extensions': []},
                                 'sauce:options': {'build': 'Sample Build Name'},
                                 'platform': 'ANY',
                                 'version': ''}

        assert options.to_capabilities() == expected_capabilities
