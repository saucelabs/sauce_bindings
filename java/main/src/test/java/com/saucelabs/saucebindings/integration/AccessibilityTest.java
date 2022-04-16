package com.saucelabs.saucebindings.integration;

import com.deque.html.axecore.results.Results;
import com.deque.html.axecore.results.Rule;
import com.saucelabs.saucebindings.SauceSession;
import org.junit.After;
import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.util.List;

public class AccessibilityTest {
    private final SauceSession session = new SauceSession();
    private RemoteWebDriver webDriver;

    @After
    public void cleanUp() {
        session.stop(true);
    }

    @Test
    public void analyzesPageWithNestedIFrames() {
        webDriver = session.start();
        webDriver.get("http://watir.com/examples/nested_iframes.html");

        Results results = session.getAccessibilityResults();
        List<Rule> violations = results.getViolations();
        int problems = violations.stream().map(e -> e.getNodes().size()).reduce(0, Integer::sum);
        Assert.assertEquals(16, problems);
    }

    @Test
    public void analyzesPageWithNestedFrames() {
        webDriver = session.start();
        webDriver.get("http://watir.com/examples/nested_frames.html");

        Results results = session.getAccessibilityResults();
        List<Rule> violations = results.getViolations();
        int problems = violations.stream().map(e -> e.getNodes().size()).reduce(0, Integer::sum);
        Assert.assertEquals(14, problems);
    }

    @Test
    public void analyzesPageWithOutIFrames() {
        webDriver = session.start();
        webDriver.get("http://watir.com/examples/nested_iframes.html");

        Results results = session.getAccessibilityResults(false);
        List<Rule> violations = results.getViolations();
        int problems = violations.stream().map(e -> e.getNodes().size()).reduce(0, Integer::sum);
        Assert.assertEquals(7, problems);
    }

    @Test
    public void analyzesPageWithOutFrames() {
        webDriver = session.start();
        webDriver.get("http://watir.com/examples/nested_frames.html");

        Results results = session.getAccessibilityResults(false);
        List<Rule> violations = results.getViolations();
        int problems = violations.stream().map(e -> e.getNodes().size()).reduce(0, Integer::sum);
        Assert.assertEquals(6, problems);
    }
}
