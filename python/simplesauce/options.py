class SauceOptions():

    def __init__(self, browserName=None, browserVersion=None, platformName=None, options={}):

        if not any([browserName, browserVersion, platformName, options]):
            self.browserName = 'chrome'
            self.browserVersion = 'latest'
            self.platformName = 'windows 10'
            return
        elif not any([browserName, browserVersion, platformName]):
            self.parseOptions(options)
            return

        self.browserName = browserName
        self.browserVersion = browserVersion
        self.platformName = platformName

    def parseOptions(self, options):

        if 'sauce:options' in options:
            w3cSauceOptions = options['sauce:options']
            self.browserName = w3cSauceOptions['browserName']
            self.browserVersion = w3cSauceOptions['browserVersion']
            self.platformName = w3cSauceOptions['platformName']
            self.name = w3cSauceOptions['name']
            self.build = w3cSauceOptions['build']
        else:
            self.browserName = options['browserName']
            self.browserVersion = options['browserVersion']
            self.platformName = options['platformName']
            self.name = options['name']
            self.build = options['build']
