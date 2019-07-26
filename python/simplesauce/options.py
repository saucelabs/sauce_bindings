from selenium import webdriver
from selenium.webdriver.remote.remote_connection import RemoteConnection
import os


SAUCE_USERNAME = os.getenv('SAUCE_USERNAME', None)
SAUCE_ACCCESS_KEY = os.getenv('SAUCE_ACCESS_KEY', None)

us_ondemand = 'ondemand.saucelabs.com'
eu_ondemand = 'ondemand.eu-central-1.saucelabs.com'

US_SAUCE_DC_URL = 'https://{}:{}@{}/wd/hub'.format(SAUCE_USERNAME, SAUCE_ACCCESS_KEY, us_ondemand)
EU_SAUCE_DC_URL = 'https://{}:{}@o{}/wd/hub'.format(SAUCE_USERNAME, SAUCE_ACCCESS_KEY, eu_ondemand)


class SauceOptions():

    def __init__(self, browserName=None, **kwargs):

        self._driver = {}
        self._remote_url = US_SAUCE_DC_URL

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
