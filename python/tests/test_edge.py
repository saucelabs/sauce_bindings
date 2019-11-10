from simplesauce.options import SauceOptions


class TestEdge(object):

    def test_defaults(self):
        sauce = SauceOptions('edge')

        assert sauce.browserName == 'MicrosoftEdge'
        assert sauce.browserVersion == 'latest'
        assert sauce.platformName == 'Windows 10'
