from simplesauce.options import SauceOptions


class TestInternetExplorer(object):

    def test_defaults(self):
        sauce = SauceOptions('internet explorer')

        assert sauce.browserName == 'internet explorer'
        assert sauce.browserVersion == 'latest'
        assert sauce.platformName == 'windows 10'
