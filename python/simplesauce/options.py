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
