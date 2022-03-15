import os

from sa11y.analyze import Analyze
from selenium import webdriver
from selenium.webdriver.remote.remote_connection import RemoteConnection
from .options import SauceOptions
from .exceptions import SessionNotStartedException, InvalidPlatformException
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
        if self.driver is not None:
            self.update_test_result(result)
            self.driver.quit()
            self.driver = None

    def validate_session_started(self, method):
        if self.driver is None:
            raise SessionNotStartedException("Session must be started before executing: {}".format(method))

    def accessibility_results(self, js_lib=None, frames=True, cross_origin=False):
        self.validate_session_started("accessibility_results")
        return Analyze(self.driver, js_lib=js_lib, frames=frames, cross_origin=cross_origin).results()

    def annotate(self, comment):
        self.validate_session_started("annotate")
        self.driver.execute_script("sauce:context={}".format(comment))

    def pause(self):
        self.validate_session_started("pause")
        self.driver.execute_script("sauce: break")
        print("\nThis test has been stopped; no more driver commands will be accepted")
        print("\nYou can take manual control of the test from the Sauce Labs UI here: {}{}".format(
            self.data_center_test_url, self.driver.session_id))
        self.driver = None

    def disable_logging(self):
        self.validate_session_started('disable_logging')
        self.driver.execute_script("sauce: disable log")

    def enable_logging(self):
        self.validate_session_started('enable_logging')
        self.driver.execute_script("sauce: enable log")

    def stop_network(self):
        self.validate_session_started('stop_network')
        if not self.options.is_mac():
            error = "Can only start or stop the network on a Mac; current platform is: {}".format(
                self.options.platform_name)
            raise InvalidPlatformException(error)

        self.driver.execute_script("sauce: stop network")

    def start_network(self):
        self.validate_session_started('start_network')
        if not self.options.is_mac():
            error = "Can only start or stop the network on a Mac; current platform is: {}".format(
                self.options.platform_name)
            raise InvalidPlatformException(error)

        self.driver.execute_script("sauce: start network")

    def change_name(self, name):
        self.validate_session_started('change_name')

        self.driver.execute_script("sauce:job-name={}".format(name))

    def add_tags(self, tags):
        self.validate_session_started('tags=')
        tags = [tags] if isinstance(tags, str) else tags

        self.driver.execute_script("sauce:job-tags={}".format(",".join(tags)))

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
