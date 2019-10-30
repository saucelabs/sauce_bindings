from simplesauce.options import SauceOptions
from selenium.webdriver.chrome.options import Options as ChromeOptions
from selenium.webdriver.firefox.options import Options as FirefoxOptions
from selenium.webdriver import DesiredCapabilities


class TestInit(object):

    def test_defaults_to_win10_chrome_latest(self):
        sauce = SauceOptions()

        assert sauce.browserName == 'chrome'
        assert sauce.browserVersion == 'latest'
        assert sauce.platformName == 'windows 10'

    def test_accepts_browser_version_platform_name(self):
        sauce = SauceOptions('Firefox')

        assert sauce.browserName == 'Firefox'
        assert sauce.browserVersion == 'latest'
        assert sauce.platformName == 'windows 10'

    def test_accepts_w3c_values(self):
        options = {
            "browserName": "chrome",
            "browserVersion": "75.0",
            "platformName": "windows 10",
            "sauce:options": {
                "browserName": "chrome",
                "browserVersion": "latest",
                "platformName": "windows 10"
            }
        }

        sauce = SauceOptions(options=options)

        assert sauce.browserName == 'chrome'
        assert sauce.browserVersion == 'latest'
        assert sauce.platformName == 'windows 10'

    def test_accepts_Sauce_values(self):
        options = {
            "browserName": "chrome",
            "browserVersion": "75.0",
            "platformName": "windows 10",
            "name": "sample test",
            "build": "sample build"
        }

        sauce = SauceOptions(options=options)

        assert sauce.options['sauce:options']['name'] == 'sample test'
        assert sauce.options['sauce:options']['build'] == 'sample build'

    def test_accepts_browser_option_values(self):
        pass

    def test_accepts_selenium_browser_options_instance(self):
        options = ChromeOptions()

        sauce = SauceOptions(options=options)

        assert sauce.browserName == 'chrome'
        assert sauce.browserVersion == 'latest'
        assert sauce.platformName == 'windows 10'

    def test_accepts_selenium_browser_capabilities_instance(self):
        options = DesiredCapabilities.CHROME.copy()

        sauce = SauceOptions(options=options)

        assert sauce.browserName == 'chrome'
        assert sauce.platformName == 'windows 10'
        assert sauce.browserVersion == 'latest'

class TestAccessorVariables(object):

    def test_overrides_default_values_for_browser_version_and_platform_name(self):
        pass

    def test_accepts_provided_w3c_values(self):
        pass

    def test_accepts_provided_Sauce_values(self):
        pass

    def test_accepts_provided_browser_option_values(self):
        pass


class TestAsJSON(object):

    def creates_the_correct_hash_representation(self):
        pass
