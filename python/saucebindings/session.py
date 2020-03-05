import os
from selenium import webdriver
from selenium.webdriver.remote.remote_connection import RemoteConnection
from .options import SauceOptions


data_centers = {
    'us-west': 'ondemand.us-west-1.saucelabs.com',
    'us-east': 'ondemand.us-east-1.saucelabs.com',
    'eu': 'ondemand.eu-central-1.saucelabs.com'
}


class SauceSession():

    def __init__(self, options=None, data_center='us-west'):
        self.options = options if options else SauceOptions()
        self._username = os.getenv('SAUCE_USERNAME', None)
        self._access_key = os.getenv('SAUCE_ACCESS_KEY', None)
        self.data_center = data_center if data_center else 'us-west'
        self._remote_url = None
        self.driver = None

    @property
    def data_center(self):
        return self._data_center

    @data_center.setter
    def data_center(self, data_center):
        if data_center.lower() not in data_centers.keys():
            raise ValueError("Invalid Data Center value, please select from 'us-west', 'us-east' or 'eu'")

        self._data_center = data_center

    @property
    def remote_url(self):
        if self._remote_url is None:
            data_center = data_centers[self._data_center]
            return 'https://{}:{}@{}:443/wd/hub'.format(self._username, self._access_key, data_center)
        else:
            return self._remote_url

    @remote_url.setter
    def remote_url(self, remote_url):
        self._remote_url = remote_url

    def start(self):
        if not self._username:
            raise KeyError("Cannot start session, Sauce Username is not set.")
        elif not self._access_key:
            raise KeyError("Cannot start session, Sauce Access Key is not set.")

        executor = RemoteConnection(self.remote_url, resolve_ip=False)
        self.driver = webdriver.Remote(
            command_executor=executor,
            desired_capabilities=self.options.to_capabilities(),
            keep_alive=True
        )
        return self.driver

    def stop(self, result):
        self.update_test_result(result)
        self.driver.quit()

    def update_test_result(self, result_in):
        result = ''

        if result_in is True:
            result = 'passed'
        elif result_in is False:
            result = 'failed'
        elif result_in and 'pass' in result_in.lower():
            result = 'passed'
        elif result_in and 'fail' in result_in.lower():
            result = 'failed'

        self.driver.execute_script('sauce:job-result={}'.format(result))
