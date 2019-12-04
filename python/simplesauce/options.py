from selenium.webdriver.chrome.options import Options as ChromeOptions
from selenium.webdriver.firefox.options import Options as FirefoxOptions
from datetime import datetime
import os


class SauceOptions():

    def _set_defaults(self, browser=None):
        self.browserName = browser if browser else 'chrome'
        self.browserVersion = 'latest'
        self.platformName = 'Windows 10'

        self.options['browserName'] = self.browserName

    def _set_default_w3c_options(self):
        self.options['sauce:options']['browserName'] = self.browserName
        self.options['sauce:options']['browserVersion'] = self.browserVersion
        self.options['sauce:options']['platformName'] = self.platformName
        self.options['sauce:options']['name'] = self._test_name
        self.options['sauce:options']['build'] = self._sauce_build

    def _set_default_build_name(self):
        """Look CI build tag values first before defaulting to a generic timestamp-based value."""
        self.build = 'Local execution: {}'.format(datetime.utcnow())

        build_tags = [
            "BUILD_NAME",
            "BUILD_TAG",
            "SAUCE_BAMBOO_BUILDNUMBER",
            "TRAVIS_BUILD_ID",
            "CIRCLE_BUILD_NUM",
        ]

        for b in build_tags:
            if os.environ.get(b):
                if 'TRAVIS' in b:
                    self.build = "{}: {}".format(os.environ['TRAVIS_REPO_SLUG'], os.environ['TRAVIS_JOB_NUMBER'])
                elif 'CIRCLE' in b:
                    self.build = "{}: {}".format(os.environ['CIRCLE_JOB'], os.environ['CIRCLE_BUILD_NUM'])
                else:
                    self.build = b

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
            self.parseOptions(options)
            return

        if browserName:
            self.parseBrowserName(browserName)
        if browserVersion:
            self.parseBrowserVersion(browserVersion)
        if platformName:
            self.parsePlatformName(platformName)

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
    def parseBrowserName(self, name):
        name = name.lower()

        if name == 'ie' or name == 'internet explorer':
            self.browserName = 'internet explorer'
        elif name == 'edge':
            self.browserName = 'MicrosoftEdge'
        elif name == 'safari':
            self.setMacOs()
        else:
            self.browserName = name

        self.options['browserName'] = self.browserName

    def parseBrowserVersion(self, version):
        self.browserVersion = version

        self.options['browserVersion'] = self.browserVersion

    def parsePlatformName(self, platform):
        if self.browserName.lower() == 'safari':
            self.setMacOs()
        elif 'windows' in platform.lower():
            self.platformName = 'Windows 10'
        else:
            self.platformName = platform

        self.options['platformName'] = self.platformName

    def parseOptions(self, options):

        if type(options) == ChromeOptions:
            self._set_defaults()
        elif type(options) == FirefoxOptions:
            self._set_defaults('firefox')
        elif 'version' in options:
            self.browserName = options['browserName'] if options['browserName'] else 'chrome'
            self.browserVersion = options['version'] if options['version'] else 'latest'
            self.platformName = 'Windows 10' if not options['version'] else options['version']
            self.options['browserName'] = self.browserName
            self.options['browserVersion'] = self.browserVersion
            self.options['platformName'] = self.platformName
        elif 'sauce:options' in options:
            w3cSauceOptions = options
            self.browserName = w3cSauceOptions.get('browserName')
            self.browserVersion = w3cSauceOptions['sauce:options'].get('browserVersion')
            self.platformName = w3cSauceOptions['sauce:options'].get('platformName')

            self.options['browserName'] = self.browserName
            self.options['browserName'] = self.browserName
            self.options['browserVersion'] = self.browserVersion
            self.options['platformName'] = self.platformName

            self.options['sauce:options']['name'] = w3cSauceOptions.get('name')
            self.options['sauce:options']['build'] = w3cSauceOptions.get('build')
        else:
            self.browserName = options.get('browserName')
            self.browserVersion = options.get('browserVersion')
            self.platformName = options.get('platformName')

            self.options['browserName'] = self.browserName
            self.options['browserVersion'] = self.browserVersion
            self.options['platformName'] = self.platformName
            self.options['sauce:options']['name'] = options.get('name')
            self.options['sauce:options']['build'] = options.get('build')

    # TODO: definitely figure this out :)
    def setMacOs(self):
        self.browserName = 'safari'
        self.browserVersion = 'latest'
        self.platformName = 'macOS 10.14'

        self.options['browserName'] = self.browserName
        self.options['browserVersion'] = self.browserVersion
        self.options['platformName'] = self.platformName
