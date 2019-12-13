import os
import pytest

from simplesauce.options import SauceOptions


class TestInit(object):

    def test_defaults_to_win10_chrome_latest(self):
        sauce = SauceOptions()

        assert sauce.browserName == 'chrome'
        assert sauce.browserVersion == 'latest'
        assert sauce.platformName == 'Windows 10'

    def test_accepts_browser_name(self):
        sauce = SauceOptions('Firefox')

        assert sauce.browserName == 'firefox'
        assert sauce.browserVersion == 'latest'
        assert sauce.platformName == 'Windows 10'

    def test_accepts_browser_version_platform_name(self):
        sauce = SauceOptions(browserName='Chrome', browserVersion='75.0', platformName='macOS 10.13')

        assert sauce.browserName == 'chrome'
        assert sauce.browserVersion == '75.0'
        assert sauce.platformName == 'macOS 10.13'

    def test_accepts_w3c_values_as_options(self):
        options = {
            "browserName": "chrome",
            "browserVersion": "75.0",
            "platformName": "Windows 10"
        }

        sauce = SauceOptions(options=options)

        assert sauce.browserName == 'chrome'
        assert sauce.browserVersion == '75.0'
        assert sauce.platformName == 'Windows 10'

    def test_accepts_Sauce_values(self):
        options = {
            "browserName": "chrome",
            "browserVersion": "75.0",
            "platformName": "Windows 10",
            "name": "sample test",
            "build": "sample build"
        }

        sauce = SauceOptions(options=options)

        assert sauce.options['sauce:options']['name'] == 'sample test'
        assert sauce.options['sauce:options']['build'] == 'sample build'

    def test_default_build_value(self):
        sauce = SauceOptions()

        assert sauce.build is not None

    def test_raises_exception_if_option_is_invalid(self):
        options = {
            "invalid": "value"
        }

        with pytest.raises(AttributeError):
            SauceOptions(options=options)


class TestSauceSpecificOptions(object):

    def test_add_test_name(self):
        sauce = SauceOptions()
        sauce.name = 'my-test'

        assert "my-test" == sauce.name

    def test_add_build_name(self):
        sauce = SauceOptions()
        sauce.build = 'my-build'

        assert "my-build" == sauce.build


class TestAccessorVariables(object):

    def test_overrides_default_values_for_w3c_settings(self):
        # How do I delete these after test?
        os.environ["BUILD_TAG"] = "A"
        os.environ["BUILD_NAME"] = "TEMP BUILD"
        os.environ["BUILD_NUMBER"] = "11"

        sauce = SauceOptions()
        sauce.browserName = 'firefox'
        sauce.browserVersion = '7'
        sauce.platformName = 'macOS 10.14'

        expected_results = {'browserName': 'firefox',
                            'browserVersion': '7',
                            'platformName': 'macOS 10.14',
                            'sauce:options': {'build': 'TEMP BUILD: 11'}}

        assert sauce.options == expected_results

    def test_overrides_default_values_for_sauce_settings(self):
        # How do I delete these after test?
        os.environ["BUILD_TAG"] = "A"
        os.environ["BUILD_NAME"] = "TEMP BUILD"
        os.environ["BUILD_NUMBER"] = "11"

        sauce = SauceOptions()

        sauce.idleTimeout = 3
        sauce.build = 'CUSTOM BUILD'
        sauce.name = 'TEST NAME'
        sauce.recordScreenshots = False

        expected_results = {'idleTimeout': 3,
                            'recordScreenshots': False,
                            'name': 'TEST NAME',
                            'build': 'CUSTOM BUILD'}

        assert sauce.options['sauce:options'] == expected_results
