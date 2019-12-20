from selenium.webdriver.chrome.options import Options as ChromeOptions
from selenium.webdriver.firefox.options import Options as FirefoxOptions
from selenium.webdriver.edge.options import Options as EdgeOptions
from selenium.webdriver.ie.options import Options as IEOptions
from datetime import datetime
import os
import re

w3c_configs = {
    'browser_name': 'browserName',
    'browser_version': 'browserVersion',
    'platform_name': 'platformName',
    'accept_insecure_certs': 'acceptInsecureCerts',
    'page_load_strategy': 'pageLoadStrategy',
    'proxy': 'proxy',
    'set_window_rect': 'setWindowRect',
    'timeouts': 'timeouts',
    'unhandled_prompt_behavior': 'unhandledPromptBehavior',
    'strict_file_interactability': 'strictFileInteractability'
}

sauce_configs = {
    'access_key': 'accessKey',
    'avoid_proxy': 'avoidProxy',
    'build': 'build',
    'capture_html': 'captureHtml',
    'chromedriver_version': 'chromedriverVersion',
    'command_timeout': 'commandTimeout',
    'crmuxdriver_version': 'crmuxdriverVersion',
    'custom_data': 'customData',
    'disable_popup_handler': 'disablePopupHandler',
    'extended_debugging': 'extendedDebugging',
    'firefox_adapter_version': 'firefoxAdapterVersion',
    'firefox_profile_url': 'firefoxProfileUrl',
    'idle_timeout': 'idleTimeout',
    'iedriver_version': 'iedriverVersion',
    'max_duration': 'maxDuration',
    'name': 'name',
    'parent_tunnel': 'parentTunnel',
    'passed': 'passed',
    'prerun': 'prerun',
    'prevent_requeue': 'preventRequeue',
    'priority': 'priority',
    'proxy_host': 'proxyHost',
    'public': 'public',
    'record_logs': 'recordLogs',
    'record_screenshots': 'recordScreenshots',
    'record_video': 'recordVideo',
    'restricted_public_info': 'restrictedPublicInfo',
    'screen_resolution': 'screenResolution',
    'selenium_version': 'seleniumVersion',
    'source': 'source',
    'tags': 'tags',
    'time_zone': 'timeZone',
    'tunnel_identifier': 'tunnelIdentifier',
    'username': 'username',
    'video_upload_on_pass': 'videoUploadOnPass',
    'capture_performance': 'capturePerformance'
}

browser_names = {
    'ie': 'internet explorer',
    'edge': 'MicrosoftEdge',
}

option_types = {
    FirefoxOptions: 'firefox',
    ChromeOptions: 'chrome',
    IEOptions: 'internet explorer',
    EdgeOptions: 'MicrosoftEdge'
}


class SauceOptions():

    def _set_default_build_name(self):
        if 'build' in self.options['sauce:options']:
            return
        # Jenkins
        elif os.environ.get("BUILD_TAG"):
            self.build = "{}: {}".format(os.environ['BUILD_NAME'], os.environ['BUILD_NUMBER'])
        # Bamboo
        elif os.environ.get('bamboo_agentId'):
            self.build = "{}: {}".format(os.environ['bamboo_shortJobName'], os.environ['bamboo_buildNumber'])
        # Travis
        elif os.environ.get('TRAVIS_JOB_ID'):
            self.build = "{}: {}".format(os.environ['TRAVIS_JOB_NAME'], os.environ['TRAVIS_JOB_NUMBER'])
        # Circle
        elif os.environ.get('CIRCLE_JOB'):
            self.build = "{}: {}".format(os.environ['CIRCLE_JOB'], os.environ['CIRCLE_BUILD_NUM'])
        # Gitlab
        elif os.environ.get('CI'):
            self.build = "{}: {}".format(os.environ['CI_JOB_NAME'], os.environ['CI_JOB_ID'])
        # Team City
        elif os.environ.get('TEAMCITY_VERSION'):
            self.build = "{}: {}".format(os.environ['TEAMCITY_PROJECT_NAME'], os.environ['BUILD_NUMBER'])
        else:
            self.build = 'Build Time: {}'.format(datetime.utcnow())

    def _set_browser_name(self, browser_name):
        if self.browser_name is None and browser_name is None:
            self.set_capability('browserName', 'chrome')
        elif browser_name and browser_name.lower() in browser_names.keys():
            self.set_capability('browserName', browser_names[browser_name.lower()])
        elif browser_name:
            self.set_capability('browserName', browser_name.lower())

        self._set_platform_name()

    def _set_platform_name(self):
        if self.browser_name == 'safari' and self.browser_version == 'latest':
            self.set_capability('platformName', 'macOS 10.14')
        elif self.platform_name is None:
            self.set_capability('platformName', 'Windows 10')

    def __init__(self, browserName=None, **kwargs):
        super(SauceOptions, self).__setattr__('options', {'sauce:options': {}})
        super(SauceOptions, self).__setattr__('seleniumOptions', {})

        for key, value in kwargs.items():
            if key is 'seleniumOptions':
                if type(value) in option_types:
                    self.browser_name = option_types[type(value)]

                self.selenium_options = value
            else:
                self.set_capability(key, value)

        if self.browser_version is None:
            self.browser_version = 'latest'

        self._set_browser_name(browserName)
        self._set_platform_name()

        self._set_default_build_name()

    def __setattr__(self, key, value):
        self.set_option(key, value)

    def __getattr__(self, key):
        if key is 'selenium_options':
            if 'opts' in self.seleniumOptions.keys():
                return self.seleniumOptions['opts']
            else:
                return None
        try:
            if key in sauce_configs.keys():
                return self.options['sauce:options'][sauce_configs[key]]
            elif key in w3c_configs.keys():
                return self.options[w3c_configs[key]]
            else:
                raise AttributeError
        except KeyError:
            return None

    # Sets with camelCase
    def set_capability(self, key, value):
        if key in sauce_configs.values():
            self.options['sauce:options'][key] = value
        elif key in w3c_configs.values():
            self.options[key] = value
        else:
            raise AttributeError

    # Sets with snake_case
    def set_option(self, key, value):
        if key == 'browser_name':
            self._set_browser_name(value)
        elif key == 'platform_name':
            self._set_platform_name(value)
        elif key is 'selenium_options':
            self.seleniumOptions['opts'] = value.to_capabilities()
        elif key in sauce_configs.keys():
            self.options['sauce:options'][sauce_configs[key]] = value
        elif key in w3c_configs.keys():
            self.options[w3c_configs[key]] = value
        else:
            raise AttributeError

    def to_capabilities(self):
        if self.selenium_options:
            self.options.update(self.selenium_options)
        return self.options
