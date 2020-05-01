from saucebindings.session import SauceSession


class TestDataCenter(object):

    def test_creates_session(self):
        # 1. Create Session object with the desired Data Center
        session = SauceSession(data_center='eu-central')

        # 2. Start Session to get the Driver
        driver = session.start()

        # 3. Use the driver in your tests just like normal
        driver.get('https://www.saucedemo.com/')

        # 4. Stop the Session with whether the test passed or failed
        session.stop(True)
