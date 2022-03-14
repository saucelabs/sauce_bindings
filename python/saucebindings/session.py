import os

from sa11y.analyze import Analyze
from selenium import webdriver
from selenium.webdriver.remote.remote_connection import RemoteConnection
from .options import SauceOptions
import warnings


data_centers = {
    'us-west': 'ondemand.us-west-1.saucelabs.com',
    'us-east': 'ondemand.us-east-1.saucelabs.com',
    'eu-central': 'ondemand.eu-central-1.saucelabs.com',
    'apac-southeast': 'ondemand.apac-southeast-1.saucelabs.com'
}


class SauceSession():

    def __init__(self, options=None, data_center='us-west', resolve_ip=False):
        self.options = options if options else SauceOptions.chrome()
        self.data_center = data_center if data_center else 'us-west'
        self._remote_url = None
        self._resolve_ip = resolve_ip if resolve_ip else False
        self.driver = None

    @property
    def data_center(self):
        return self._data_center

    @data_center.setter
    def data_center(self, data_center):
        if data_center.lower() not in data_centers.keys():
            raise ValueError("Invalid Data Center value, please select from:", list(data_centers.keys()))

        self._data_center = data_center

    @property
    def data_center_test_url(self):
        if self.data_center != "us-west":
            return "https://{}/tests/".format(data_centers[self._data_center]).replace("ondemand", "app")
        else:
            return "https://app.saucelabs.com/tests/"

    @property
    def remote_url(self):
        if self._remote_url is None:
            data_center = data_centers[self._data_center]
            return 'https://{}/wd/hub'.format(data_center)
        else:
            return self._remote_url

    @remote_url.setter
    def remote_url(self, remote_url):
        self._remote_url = remote_url

    def start(self):
        self.driver = self.create_driver(self.remote_url, self.options.to_capabilities())
        return self.driver

    def stop(self, result):
        self.update_test_result(result)
        self.driver.quit()


    def accessibility_results(self, js_lib=None, frames=True, cross_origin=False):
        return Analyze(self.driver, js_lib=js_lib, frames=frames, cross_origin=cross_origin).results()

    def update_test_result(self, result_in):
        result = ''

        if result_in is True:
            result = 'passed'
        elif result_in is False:
            result = 'failed'
        elif result_in and 'pass' in result_in.lower():
            warnings.warn(
                "string arguments are deprecated, please pass in True or False to indicate if the test has passed",
                DeprecationWarning
            )
            result = 'passed'
        elif result_in and 'fail' in result_in.lower():
            warnings.warn(
                "string arguments are deprecated, please pass in True or False to indicate if the test has passed",
                DeprecationWarning
            )
            result = 'failed'
        else:
            warnings.warn(
                "string arguments are deprecated, please pass in True or False to indicate if the test has passed",
                DeprecationWarning
            )

        self.driver.execute_script('sauce:job-result={}'.format(result))

        # Add output for the Sauce OnDemand Jenkins plugin
        # The first print statement will automatically populate links on Jenkins to Sauce
        # The second print statement will output the job link to logging/console
        if self.driver is not None:
            print("SauceOnDemandSessioID={} job-name={}".format(self.driver.session_id, self.options.name))
            print("Test Job Link: {}{}".format(self.data_center_test_url, self.driver.session_id))

    def create_driver(self, url, capabilities):
        return webdriver.Remote(
            command_executor=RemoteConnection(url, self._resolve_ip),
            desired_capabilities=capabilities,
            keep_alive=True
        )
