from saucebindings.session import SauceSession


def numberProblems(results):
    return sum(list(map(lambda x: len(x.get("nodes")), results.get('violations'))))


class TestAccessibility(object):

    def test_iframes(self):
        session = SauceSession()
        driver = session.start()
        driver.get("http://watir.com/examples/nested_iframes.html")
        results = session.accessibility_results()

        assert numberProblems(results) == 16, "Wrong number of violations found"
        session.stop(True)

    def test_frames(self):
        session = SauceSession()

        driver = session.start()
        driver.get("http://watir.com/examples/nested_frames.html")

        results = session.accessibility_results()
        assert numberProblems(results) == 14, "Wrong number of violations found"
        session.stop(True)

    def test_toggle_off_frames_for_iframes(self):
        session = SauceSession()

        driver = session.start()
        driver.get("http://watir.com/examples/nested_iframes.html")

        results = session.accessibility_results(frames=False)
        assert numberProblems(results) == 7, "Wrong number of violations found"
        session.stop(True)

    def test_toggle_off_frames_for_Frames(self):
        session = SauceSession()

        driver = session.start()
        driver.get("http://watir.com/examples/nested_frames.html")

        results = session.accessibility_results(frames=False)
        assert numberProblems(results) == 6, "Wrong number of violations found"
        session.stop(True)
