from simplesauce.options import SauceOptions


class TestInit(object):

    def test_defaults_to_win10_chrome_latest(self):
        pass

    def test_accepts_browser_version_platform_name(self):
        pass

    def test_accepts_w3c_values(self):
        pass

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

"""

def test_defaults():
    sauce = SauceOptions()

    assert sauce.browserName == 'Chrome'
    assert sauce.browserVersion == 'latest'
    assert sauce.platformName == 'Windows 10'


def test_only_browserName():
    sauce = SauceOptions('Firefox')

    assert sauce.browserName == 'Firefox'
    assert sauce.browserVersion == 'latest'
    assert sauce.platformName == 'Windows 10'


def test_ie_default():
    sauce = SauceOptions('ie')

    assert sauce.browserName == 'Internet Explorer'
    assert sauce.browserVersion == '11.0'
    assert sauce.platformName == 'Windows 10'


def test_safar_default():
    sauce = SauceOptions('Safari')

    assert sauce.browserName == 'Safari'
    assert sauce.browserVersion == '12.0'
    assert sauce.platformName == 'MacOS 10.13'


def test_from_dict():
    caps = {
        'browserName': 'Firefox',
        'browserVersion': '67.0',
        'platformName': 'Mac OSX 10.13'
    }

    sauce = SauceOptions(**caps)

    assert sauce.browserName == 'Firefox'
    assert sauce.browserVersion == '67.0'
    assert sauce.platformName == 'Mac OSX 10.13'
"""