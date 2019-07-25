from simplesauce.session import SauceSession


def test_defaults():
    sauce = SauceSession()

    assert sauce.browserName == 'Chrome'
    assert sauce.browserVersion == 'latest'
    assert sauce.platformName == 'Windows 10'


def test_only_browserName():
    sauce = SauceSession('Firefox')

    assert sauce.browserName == 'Firefox'
    assert sauce.browserVersion == 'latest'
    assert sauce.platformName == 'Windows 10'


def test_ie_default():
    sauce = SauceSession('ie')

    assert sauce.browserName == 'Internet Explorer'
    assert sauce.browserVersion == '11.0'
    assert sauce.platformName == 'Windows 10'


def test_safar_default():
    sauce = SauceSession('Safari')

    assert sauce.browserName == 'Safari'
    assert sauce.browserVersion == '12.0'
    assert sauce.platformName == 'MacOS 10.13'


def test_from_dict():
    caps = {
        'browserName': 'Firefox',
        'browserVersion': '67.0',
        'platformName': 'Mac OSX 10.13'
    }

    sauce = SauceSession(**caps)

    assert sauce.browserName == 'Firefox'
    assert sauce.browserVersion == '67.0'
    assert sauce.platformName == 'Mac OSX 10.13'
