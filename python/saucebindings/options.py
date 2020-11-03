from selenium.webdriver.chrome.options import Options as ChromeOptions
from selenium.webdriver.firefox.options import Options as FirefoxOptions
from selenium.webdriver.edge.options import Options as EdgeOptions
from selenium.webdriver.ie.options import Options as IEOptions
from datetime import datetime
import os


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
    'avoid_proxy': 'avoidProxy',
    'build': 'build',
    'chromedriver_version': 'chromedriverVersion',
    'command_timeout': 'commandTimeout',
    'custom_data': 'customData',
    'extended_debugging': 'extendedDebugging',
    'idle_timeout': 'idleTimeout',
    'iedriver_version': 'iedriverVersion',
    'max_duration': 'maxDuration',
    'name': 'name',
    'parent_tunnel': 'parentTunnel',
    'prerun': 'prerun',
    'priority': 'priority',
    'public': 'public',
    'record_logs': 'recordLogs',
    'record_screenshots': 'recordScreenshots',
    'record_video': 'recordVideo',
    'screen_resolution': 'screenResolution',
    'selenium_version': 'seleniumVersion',
    'tags': 'tags',
    'time_zone': 'timeZone',
    'tunnel_identifier': 'tunnelIdentifier',
    'video_upload_on_pass': 'videoUploadOnPass',
    'capture_performance': 'capturePerformance'
}

browser_names = {
    FirefoxOptions: 'firefox',
    ChromeOptions: 'chrome',
    IEOptions: 'internet explorer',
    EdgeOptions: 'MicrosoftEdge'
}


class SauceOptions:

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
        # GitHub Actions
        elif os.environ.get("GITHUB_SHA"):
            self.build = "{}: {}".format(os.environ['GITHUB_WORKFLOW'], os.environ['GITHUB_SHA'])
        # Gitlab
        elif os.environ.get('CI_JOB_ID'):
            self.build = "{}: {}".format(os.environ['CI_JOB_NAME'], os.environ['CI_JOB_ID'])
        # Team City
        elif os.environ.get('TEAMCITY_PROJECT_NAME'):
            self.build = "{}: {}".format(os.environ['TEAMCITY_PROJECT_NAME'], os.environ['BUILD_NUMBER'])
        else:
            self.build = 'Build Time: {}'.format(datetime.utcnow())

    def __init__(self, browserName=None, **kwargs):
        super(SauceOptions, self).__setattr__('options', {'sauce:options': {}})
        super(SauceOptions, self).__setattr__('seleniumOptions', {})

        for key, value in kwargs.items():
            if key == 'seleniumOptions':
                if isinstance(value, tuple(browser_names)):
                    self.set_capability('browserName', browser_names[type(value)])

                self.seleniumOptions['caps'] = value.to_capabilities()
            else:
                self.set_capability(key, value)

        if self.browser_version is None:
            self.set_capability('browserVersion', 'latest')

        if browserName:
            self.set_capability('browserName', browserName)
        elif self.browser_name:
            pass  # browser name set by Selenium Class
        else:
            self.set_capability('browserName', 'chrome')

        if self.platform_name is None:
            self.set_capability('platformName', 'Windows 10')

        self._set_default_build_name()

    def __setattr__(self, key, value):
        self.set_option(key, value)

    def __getattr__(self, key):
        if key == 'selenium_options':
            if 'caps' in self.seleniumOptions.keys():
                return self.seleniumOptions['caps']
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

    def merge_capabilities(self, capabilities):
        for key, value in capabilities.items():
            self.set_capability(key, value)

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
        if key in sauce_configs.keys():
            self.options['sauce:options'][sauce_configs[key]] = value
        elif key in w3c_configs.keys():
            self.options[w3c_configs[key]] = value
        else:
            raise AttributeError

    def to_capabilities(self):
        if self.selenium_options:
            self.options.update(self.selenium_options)
        return self.options
