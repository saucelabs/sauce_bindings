package com.saucelabs.saucebindings.integration;

import com.deque.html.axecore.results.Results;
import com.deque.html.axecore.results.Rule;
import com.saucelabs.saucebindings.SauceSession;
import java.util.List;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.remote.RemoteWebDriver;

public class AccessibilityTest {
  private SauceSession session;
  private RemoteWebDriver webDriver;

  @BeforeEach
  public void setUp() {
    this.session = new SauceSession();
  }

  @AfterEach
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
    Assertions.assertEquals(16, problems);
  }

  @Test
  public void analyzesPageWithNestedFrames() {
    webDriver = session.start();
    webDriver.get("http://watir.com/examples/nested_frames.html");

    Results results = session.getAccessibilityResults();
    List<Rule> violations = results.getViolations();
    int problems = violations.stream().map(e -> e.getNodes().size()).reduce(0, Integer::sum);
    Assertions.assertEquals(14, problems);
  }

  @Test
  public void analyzesPageWithOutIFrames() {
    webDriver = session.start();
    webDriver.get("http://watir.com/examples/nested_iframes.html");

    Results results = session.getAccessibilityResults(false);
    List<Rule> violations = results.getViolations();
    int problems = violations.stream().map(e -> e.getNodes().size()).reduce(0, Integer::sum);
    Assertions.assertEquals(7, problems);
  }

  @Test
  public void analyzesPageWithOutFrames() {
    webDriver = session.start();
    webDriver.get("http://watir.com/examples/nested_frames.html");

    Results results = session.getAccessibilityResults(false);
    List<Rule> violations = results.getViolations();
    int problems = violations.stream().map(e -> e.getNodes().size()).reduce(0, Integer::sum);
    Assertions.assertEquals(6, problems);
  }
}
