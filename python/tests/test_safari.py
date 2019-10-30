from simplesauce.options import SauceOptions


class TestSafari(object):

    def test_defaults(self):
        sauce = SauceOptions('safari')

        assert sauce.browserName == 'safari'
        assert sauce.browserVersion == 'latest'
        assert sauce.platformName == 'macOS 10.14'
