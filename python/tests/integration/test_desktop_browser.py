from saucebindings.options import SauceOptions
from saucebindings.session import SauceSession


class TestDataCenter(object):

    def test_defaults_to_west(self):
        session = SauceSession()

        session.start()

        assert session.driver.session_id
        assert "us-west-1" in session.remote_url

        session.stop(True)

    def test_runs_on_eu_central(self):
        session = SauceSession()
        session.data_center = 'eu-central'

        session.start()

        assert session.driver.session_id
        assert "eu-central-1" in session.remote_url

        session.stop(True)


class TestAnnotations(object):

    def test_stops_starts_network(self):
        sauce_session = SauceSession(SauceOptions.safari())
        driver = sauce_session.start()

        sauce_session.stop_network()

        driver.get("https://www.saucedemo.com")
        assert driver.title == "Failed to open page"

        sauce_session.start_network()
        driver.get("https://www.saucedemo.com")
        assert driver.title == "Swag Labs"

        sauce_session.stop(True)
