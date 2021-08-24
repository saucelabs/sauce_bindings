from saucebindings.options import SauceOptions
from saucebindings.session import SauceSession
from selenium.webdriver.chrome.options import Options as ChromeOptions


class TestBrowserOptions(object):

    def test_creates_session(self):

        # 1. Create Selenium Browser Options instance
        browserOptions = ChromeOptions()
        browserOptions.add_argument('--start-fullscreen')

        # 2. Create Sauce Options object with the Browser Options object instance
        sauceOptions = SauceOptions.firefox(seleniumOptions=browserOptions)

        # 3. Create Session object with SauceOptions object instance
        session = SauceSession(sauceOptions)

        # 4. Start Session to get the Driver
        driver = session.start()

        # 5. Use the driver in your tests just like normal
        driver.get('https://www.saucedemo.com/')

        # 6. Stop the Session with whether the test passed or failed
        session.stop(True)
