from selenium import webdriver
from selenium.webdriver.remote.remote_connection import RemoteConnection
from options import SauceOptions


class SauceSession():

    def __init__(self, **kwargs):

        # TODO: flesh this out
        self.options = SauceOptions() if not kwargs else kwargs
        self.remote_url = "https://ondemand.saucelabs.com/wd/hub"
        self.driver = {}

    def start(self):
        caps = self.options

        executor = RemoteConnection(self.remote_url, resolve_ip=False)
        self.driver = webdriver.Remote(
            command_executor=executor,
            desired_capabilities=caps,
            keep_alive=True
        )
        return self.driver

    def stop(self):
        self.driver.quit()
