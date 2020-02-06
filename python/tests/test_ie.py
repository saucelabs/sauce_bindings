from saucebindings.options import SauceOptions


class TestInternetExplorer(object):

    def test_defaults(self):
        sauce = SauceOptions('internet explorer')

        assert sauce.browser_name == 'internet explorer'
        assert sauce.browser_version == 'latest'
        assert sauce.platform_name == 'Windows 10'
