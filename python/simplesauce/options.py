class SauceOptions():

    def __init__(self, browserName=None, browserVersion=None, platformName=None, **kwargs):

        if not any([browserName, browserVersion, platformName]):
            self.browserName = 'Chrome'
            self.browserVersion = 'latest'
            self.platformName = 'Windows 10'
            return

        self.browserName = browserName
        self.browserVersion = browserVersion
        self.platformName = platformName
