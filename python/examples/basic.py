"""Most basic example of using Simple Sauce.

Here we start a session on Sauce, perform some actions then close the session.
"""
from simplesauce.session import SauceSession


session = SauceSession()

session.start()
session.driver.get("https://www.saucedemo.com")

result = "Swag" in session.driver.title

session.stop(result)
