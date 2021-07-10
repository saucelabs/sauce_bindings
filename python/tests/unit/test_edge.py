import pytest

from saucebindings.options import SauceOptions
from selenium.webdriver import __version__ as seleniumVersion

class TestEdge(object):

    @pytest.mark.skipif(seleniumVersion[0] == '4', reason="requires Selenium 4")
    def test_selenium3_error(self):
        with pytest.raises(NotImplementedError):
            SauceOptions.edge()
