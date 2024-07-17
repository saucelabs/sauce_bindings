import os

from saucebindings.options import SauceOptions
from saucebindings.session import SauceSession


class TestSauceOptions(object):

    def test_creates_session(self):
        # 1. Create a SauceOptions instance using tunnelName of a tunnel started earlier
        tunnel_name = "sauce-bindings-{}".format(os.environ.get('GITHUB_SHA') or "test")
        sauceOptions = SauceOptions.chrome(extendedDebugging=True,
                                           idleTimeout=45,
                                           tunnelName=tunnel_name)

        # 2. Create Session object with SauceOptions object instance
        session = SauceSession(sauceOptions)

        # 3. Start Session to get the Driver
        driver = session.start()

        # 4. Use the driver in your tests just like normal
        driver.get('https://www.saucedemo.com/')

        # 5. Stop the Session with whether the test passed or failed
        session.stop(True)
