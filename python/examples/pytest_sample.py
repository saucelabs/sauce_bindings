import pytest
from simplesauce.options import SauceOptions
from simplesauce.session import SauceSession


browsers = [
  'internet explorer', 
  'chrome', 
  'firefox',
  'safari', 
  'edge'
]


@pytest.fixture(params=browsers)
def driver(request):
    opts = SauceOptions(browserName=request.param)
    sauce = SauceSession(options=opts)
    sauce.start()

    yield sauce.driver

    sauce.stop()

def test_demo(driver):
    driver.get('https://www.saucedemo.com')
    assert "Swag" in driver.title

    
