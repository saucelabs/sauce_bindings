from simplesauce.options import SauceOptions


class TestInit(object):

    def test_defaults_to_win10_chrome_latest(self):
        sauce = SauceOptions()

        assert sauce.browserName == 'Chrome'
        assert sauce.browserVersion == 'latest'
        assert sauce.platformName == 'Windows 10'

    def test_accepts_browser_version_platform_name(self):
        sauce = SauceOptions('Firefox', '67.0', 'Windows 10')

        assert sauce.browserName == 'Firefox'
        assert sauce.browserVersion == '67.0'
        assert sauce.platformName == 'Windows 10'

    def test_accepts_w3c_values(self):
        options = {
            "browserName": "Chrome",
            "browserVersion": "75.0",
            "platformName": "Windows 10"
        }

        sauce = SauceOptions(options=options)

        assert sauce.browserName == 'Chrome'
        assert sauce.browserVersion == '75.0'
        assert sauce.platformName == 'Windows 10'

    def test_accepts_Sauce_values(self):
        pass

    def test_accepts_browser_option_values(self):
        pass

    def test_accepts_selenium_browser_options_instance(self):
        pass

    def test_accepts_selenium_browser_capabilities_instance(self):
        pass


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
