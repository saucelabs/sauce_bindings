class SauceOptions():

    def __init__(self, browserName=None, browserVersion=None, platformName=None, options={}):

        if not any([browserName, browserVersion, platformName, options]):
            self.browserName = 'Chrome'
            self.browserVersion = 'latest'
            self.platformName = 'Windows 10'
            return
        elif not any([browserName, browserVersion, platformName]):
            self.parseOptions(options)
            return

        self.browserName = browserName
        self.browserVersion = browserVersion
        self.platformName = platformName

    def parseOptions(self, options):
        self.browserName = options['browserName']
        self.browserVersion = options['browserVersion']
        self.platformName = options['platformName']
