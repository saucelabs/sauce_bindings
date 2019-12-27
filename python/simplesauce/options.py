from selenium.webdriver.chrome.options import Options as ChromeOptions
from selenium.webdriver.firefox.options import Options as FirefoxOptions
from datetime import datetime
import os
import re


class SauceOptions():

    def _set_defaults(self, browser=None):
        self.browser_name = browser if browser else 'chrome'
        self.browser_version = 'latest'
        self.platform_name = 'Windows 10'

        self.options['browserName'] = self.browser_name

    def _set_default_w3c_options(self):
        self.options['sauce:options']['browserName'] = self.browser_name
        self.options['sauce:options']['browserVersion'] = self.browser_version
        self.options['sauce:options']['platformName'] = self.platform_name
        self.options['sauce:options']['name'] = self._test_name
        self.options['sauce:options']['build'] = self._sauce_build

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

    def __init__(self, browserName=None, browserVersion=None, platformName=None, options={}):
        self.options = {}
        self.options['sauce:options'] = {}

        self._test_name = 'test'
        self._set_default_build_name()

        self._set_defaults()
        self._set_default_w3c_options()

        if not any([browserName, browserVersion, platformName, options]):
            return
        elif not any([browserName, browserVersion, platformName]):
            self.parse_options(options)
            return

        if browserName:
            self.parse_browser_name(browserName)
        if browserVersion:
            self.parse_browser_version(browserVersion)
        if platformName:
            self.parse_platform_name(platformName)

    @property
    def name(self):
        return self._test_name

    @name.setter
    def name(self, name):
        self._test_name = name

        self.options['sauce:options']['name'] = self._test_name

    @property
    def build(self):
        return self._sauce_build

    @build.setter
    def build(self, build):
        self._sauce_build = build

        self.options['sauce:options']['name'] = self._sauce_build

    def set_capability(self, key, val):
        self.options[key] = val

    def set_sauce_capability(self, key, val):
        self.options['sauce:options'][key] = val

    """TODO: create better parsing mechanisms for different capabilities."""
    def parse_browser_name(self, name):
        name = name.lower()

        if name == 'ie' or name == 'internet explorer':
            self.browser_name = 'internet explorer'
        elif name == 'edge':
            self.browser_name = 'MicrosoftEdge'
        elif name == 'safari':
            self.set_mac_os()
        else:
            self.browser_name = name

        self.options['browserName'] = self.browser_name

    def parse_browser_version(self, version):
        self.browser_version = version

        self.options['browserVersion'] = self.browser_version

    def parse_platform_name(self, platform):
        if self.browser_name.lower() == 'safari':
            self.set_mac_os()
        elif 'windows' in platform.lower():
            self.platform_name = 'Windows 10'
        else:
            self.platform_name = platform

        self.options['platformName'] = self.platform_name

    def parse_options(self, options):

        if type(options) == ChromeOptions:
            self._set_defaults()
        elif type(options) == FirefoxOptions:
            self._set_defaults('firefox')
        elif 'version' in options:
            self.browser_name = options['browserName'] if options['browserName'] else 'chrome'
            self.browser_version = options['version'] if options['version'] else 'latest'
            self.platform_name = 'Windows 10' if not options['version'] else options['version']
            self.options['browserName'] = self.browser_name
            self.options['browserVersion'] = self.browser_version
            self.options['platformName'] = self.platform_name
        elif 'sauce:options' in options:
            w3cSauceOptions = options
            self.browser_name = w3cSauceOptions.get('browserName')
            self.browser_version = w3cSauceOptions['sauce:options'].get('browserVersion')
            self.platform_name = w3cSauceOptions['sauce:options'].get('platformName')

            self.options['browserName'] = self.browser_name
            self.options['browserName'] = self.browser_name
            self.options['browserVersion'] = self.browser_version
            self.options['platformName'] = self.platform_name

            self.options['sauce:options']['name'] = w3cSauceOptions.get('name')
            self.options['sauce:options']['build'] = w3cSauceOptions.get('build')
        else:
            self.browser_name = options.get('browserName')
            self.browser_version = options.get('browserVersion')
            self.platform_name = options.get('platformName')

            self.options['browserName'] = self.browser_name
            self.options['browserVersion'] = self.browser_version
            self.options['platformName'] = self.platform_name
            self.options['sauce:options']['name'] = options.get('name')
            self.options['sauce:options']['build'] = options.get('build')

    # TODO: definitely figure this out :)
    def set_mac_os(self):
        self.browser_name = 'safari'
        self.browser_version = 'latest'
        self.platform_name = 'macOS 10.14'

        self.options['browserName'] = self.browser_name
        self.options['browserVersion'] = self.browser_version
        self.options['platformName'] = self.platform_name
