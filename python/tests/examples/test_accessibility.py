import os
from saucebindings.session import SauceSession

class TestCreateSession(object):

    def test_creates_session(self):
        # 1. Create Session object with the defaults
        session = SauceSession()

        # 2. Start Session to get the Driver
        driver = session.start()

        # 3. Use the driver in your tests just like normal
        driver.get('https://www.saucedemo.com/')

        # 4a. Get accessibility default results with frame support
        session.accessibility_results()

        # 4b. Get accessibility default results without frame support
        session.accessibility_results(frames=False)

        # 4c. Get accessibility results with cross origin frame support
        session.accessibility_results(cross_origin=True)

        # 4d. Get accessibility results with a different version of Axe Core JS Library
        js_lib = open(os.path.join(os.path.dirname(__file__), "axe.min.js"), "r").read()
        session.accessibility_results(js_lib=js_lib)

        # 5. Stop the Session with whether the test passed or failed
        session.stop(True)
