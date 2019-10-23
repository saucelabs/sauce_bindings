from selenium.webdriver.chrome.options import Options as ChromeOptions
from selenium.webdriver.firefox.options import Options as FirefoxOptions
from selenium.webdriver import DesiredCapabilities


class SauceOptions():

    def _set_defaults(self, browser=None):
        self.browserName = browser if browser else 'chrome'
        self.browserVersion = 'latest'
        self.platformName = 'windows 10'

    def __init__(self, browserName=None, browserVersion=None, platformName=None, options={}):

        if not any([browserName, browserVersion, platformName, options]):
            self._set_defaults()
            return
        elif not any([browserName, browserVersion, platformName]):
            self.parseOptions(options)
            return

        self.browserName = browserName
        self.browserVersion = browserVersion
        self.platformName = platformName

    def parseOptions(self, options):

        if type(options) == ChromeOptions:
            self._set_defaults()
        elif type(options) == FirefoxOptions:
            self._set_defaults('firefox')
        elif 'version' in options:
            self.browserName = options['browserName'] if options['browserName'] else 'chrome'
            self.browserVersion = options['version'] if options['version'] else 'latest'
            self.platformName = 'windows 10' if not options['version'] else options['version']
        elif 'sauce:options' in options:
            w3cSauceOptions = options['sauce:options']
            self.browserName = w3cSauceOptions.get('browserName')
            self.browserVersion = w3cSauceOptions.get('browserVersion')
            self.platformName = w3cSauceOptions.get('platformName')
            self.name = w3cSauceOptions.get('name')
            self.build = w3cSauceOptions.get('build')
        else:
            self.browserName = options.get('browserName')
            self.browserVersion = options.get('browserVersion')
            self.platformName = options.get('platformName')
            self.name = options.get('name')
            self.build = options.get('build')
