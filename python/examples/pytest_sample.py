import pytest

from saucebindings.options import SauceOptions
from saucebindings.session import SauceSession

import urllib3
urllib3.disable_warnings()

browsers = [
    'internet explorer',
    'chrome',
    'firefox'
]


@pytest.fixture()
def driver(request):
    opts = SauceOptions()
    opts.name = request.node.name
    sauce = SauceSession(options=opts)
    sauce.start()

    yield sauce.driver

    # report results
    # use the test result to send the pass/fail status to Sauce Labs
    result = True

    sauce.stop(result)


@pytest.hookimpl(hookwrapper=True, tryfirst=True)
def pytest_runtest_makereport(item, call):
    # this sets the result as a test attribute for Sauce Labs reporting.
    # execute all other hooks to obtain the report object
    outcome = yield
    rep = outcome.get_result()

    # set an report attribute for each phase of a call, which can
    # be "setup", "call", "teardown"
    setattr(item, "rep_" + rep.when, rep)

def test_title(driver):
    driver.get("https://www.saucedemo.com")

    assert "Swag Labs" in driver.title
