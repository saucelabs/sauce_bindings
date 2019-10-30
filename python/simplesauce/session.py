import os
from selenium import webdriver
from selenium.webdriver.remote.remote_connection import RemoteConnection
from simplesauce.options import SauceOptions


SAUCE_USERNAME = os.getenv('SAUCE_USERNAME', None)
SAUCE_ACCCESS_KEY = os.getenv('SAUCE_ACCESS_KEY', None)

us_ondemand = 'ondemand.us-west-1.saucelabs.com'
eu_ondemand = 'ondemand.eu-central-1.saucelabs.com'

US_SAUCE_DC_URL = 'https://{}:{}@{}/wd/hub'.format(SAUCE_USERNAME, SAUCE_ACCCESS_KEY, us_ondemand)
EU_SAUCE_DC_URL = 'https://{}:{}@{}/wd/hub'.format(SAUCE_USERNAME, SAUCE_ACCCESS_KEY, eu_ondemand)


class SauceSession():

    def __init__(self, data_center='us', username=None, access_key=None, options=None):

        # TODO: flesh this out
        self.options = options if options else SauceOptions()

        self._username = username if username else SAUCE_USERNAME
        self._access_key = access_key if access_key else SAUCE_ACCCESS_KEY
        self._data_center = data_center

        if data_center.lower() == 'eu':
            self.remote_url = EU_SAUCE_DC_URL
        elif data_center.lower() == 'us':
            self.remote_url = US_SAUCE_DC_URL
        else:
            raise KeyError("Invalid Data Center value, please select from 'us' or 'eu'")
        
        self.driver = {}

    @property
    def username(self):
        return self._username

    @username.setter
    def username(self, username):
        self._username = username

    @property
    def access_key(self):
        return self._access_key

    @access_key.setter
    def access_key(self, access_key):
        self._access_key = access_key

    @property
    def data_center(self):
        return self._data_center

    @data_center.setter
    def data_center(self, data_center):
        if data_center.lower() == 'eu':
            self._data_center = data_center
            self.remote_url = EU_SAUCE_DC_URL
        elif data_center.lower() == 'us':
            self._data_center = data_center
            self.remote_url = US_SAUCE_DC_URL
        else:
            raise KeyError("Invalid Data Center value, please select from 'us' or 'eu'")

    def start(self):
        if not self._username:
            raise KeyError("Cannot start session, Sauce Username is not set.")
        elif not self._access_key:
            raise KeyError("Cannot start session, Sauce Access Key is not set.")

        caps = self.options

        executor = RemoteConnection(self.remote_url, resolve_ip=False)
        self.driver = webdriver.Remote(
            command_executor=executor,
            desired_capabilities=caps.options,
            keep_alive=True
        )
        return self.driver

    def stop(self):
        self.driver.quit()
