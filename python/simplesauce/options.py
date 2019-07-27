class SauceOptions():

    def __init__(self, browserName=None, **kwargs):

        if not browserName:
            self.browserName = 'Chrome'
        elif browserName == 'ie':
            self.browserName = 'Internet Explorer'
            self.browserVersion = '11.0'
            self.platformName = 'Windows 10'
            return
        elif browserName == 'Safari':
            self.browserName = 'Safari'
            self.browserVersion = '12.0'
            self.platformName = 'MacOS 10.13'
            return
        else:
            self.browserName = browserName

        if 'browserVersion' not in kwargs:
            self.browserVersion = 'latest'
        else:
            self.browserVersion = kwargs['browserVersion']

        if 'platformName' not in kwargs:
            self.platformName = 'Windows 10'
        else:
            self.platformName = kwargs['platformName']
