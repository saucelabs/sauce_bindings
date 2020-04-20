import pytest
import os
from saucebindings.options import SauceOptions
from saucebindings.session import SauceSession

SAUCE_USERNAME_HOLDER = os.getenv('SAUCE_USERNAME', None)
SAUCE_ACCESS_KEY_HOLDER = os.getenv('SAUCE_ACCESS_KEY', None)


class TestDataCenter(object):

    def test_defaults_to_west(self):
        session = SauceSession()

        session.data_center = 'eu'
        session.start()

        assert session.driver.session_id
        assert "eu-central-1" in session.remote_url

    def test_runs_on_us_east(self):
        options = SauceOptions()
        options.platform_name = 'Linux'
        session = SauceSession(options)
        session.data_center = 'us-east'
        session.start()

        assert session.driver.session_id
        assert "us-east-1" in session.remote_url

    def test_runs_on_eu_central(self):
        session = SauceSession()
        session.data_center = 'eu'

        session.start()

        assert session.driver.session_id
        assert "eu-central-1" in session.remote_url
