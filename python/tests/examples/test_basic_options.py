from saucebindings.options import SauceOptions
from saucebindings.session import SauceSession


class TestBasicOptions(object):

    def test_creates_session(self):
        # 1. Create a SauceOptions instance with the 3 primary parameters
        sauceOptions = SauceOptions(browserName='firefox',
                                    browserVersion='73.0',
                                    platformName='Windows 8')

        # 2. Create Session object with SauceOptions object instance
        session = SauceSession(sauceOptions)

        # 3. Start Session to get the Driver
        driver = session.start()

        # 4. Use the driver in your tests just like normal
        driver.get('https://www.saucedemo.com/')

        # 5. Stop the Session with whether the test passed or failed
        session.stop(True)
