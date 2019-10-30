from selenium.webdriver.chrome.options import Options as ChromeOptions
from selenium.webdriver.firefox.options import Options as FirefoxOptions
from selenium.webdriver import DesiredCapabilities


class SauceOptions():

    def _set_defaults(self, browser=None):
        self.browserName = browser if browser else 'chrome'
        self.browserVersion = 'latest'
        self.platformName = 'Windows 10'

    def _set_default_w3c_options(self):
        self.options = {}
        self.options['browserName'] = self.browserName
        self.options['browserVersion'] = self.browserVersion
        self.options['platformName'] = self.platformName
        self.options['sauce:options'] = {}

    def __init__(self, browserName=None, browserVersion=None, platformName=None, options={}):
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

    """TODO: create better parsing mechanisms for different capabilities."""
    def parseBrowserName(self, name):
        if name.lower() == 'ie' or name.lower() == 'internet explorer':
            self.browserName = 'internet explorer'
        elif name.lower() == 'edge':
            self.browserName = 'MicrosoftEdge'
        elif name.lower() == 'safari':
            self.setMacOs()
        else:
            self.browserName = name

        self.options['browserName'] = self.browserName

    def parseBrowserVersion(self, version):
        self.browserVersion = browserVersion

        self.options['browserVersion'] = self.browserVersion

    def parsePlatformName(self, platform):
        print(self.browserName)
        if self.browserName.lower() == 'safari':
            print(">>>>>>>>>>>>>")
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
            
