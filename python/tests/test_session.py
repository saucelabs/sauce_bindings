from simplesauce.session import SauceSession


def test_start_default_session():
    sauce = SauceSession()

    driver = sauce.start()

    driver.close()


def test_start_session_by_browserName():
    sauce = SauceSession('Chrome')

    driver = sauce.start()

    driver.close()
