from saucebindings.options import SauceOptions


class TestEdge(object):

    def test_defaults(self):
        sauce = SauceOptions('MicrosoftEdge')

        assert sauce.browser_name == 'MicrosoftEdge'
        assert sauce.browser_version == 'latest'
        assert sauce.platform_name == 'Windows 10'
