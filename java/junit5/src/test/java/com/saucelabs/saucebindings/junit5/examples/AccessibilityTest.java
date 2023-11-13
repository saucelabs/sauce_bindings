package com.saucelabs.saucebindings.junit5.examples;

import com.deque.html.axecore.selenium.AxeBuilder;
import com.saucelabs.saucebindings.junit5.SauceBaseTest;
import org.junit.jupiter.api.Test;

// 1. Extend the provided base test class
public class AccessibilityTest extends SauceBaseTest {

  @Test
  public void startSession() {
    // 2. Session and Driver are created automatically by superclass

    // 2. Use the driver in your tests just like normal
    driver.get("https://www.saucedemo.com/");

    // 3a. Get accessibility default results with frame support
    session.getAccessibilityResults();

    // 3b. Get accessibility default results without frame support
    session.getAccessibilityResults(false);

    // 3c. Get accessibility default results with Deque Builder instance
    //     Options for configuring AxeBuilder are here:
    // https://github.com/dequelabs/axe-core-maven-html#:~:text=axebuilder
    AxeBuilder builder = new AxeBuilder();
    session.getAccessibilityResults(builder);

    // 4. Session is stopped and results are sent to Sauce Labs automatically by the superclass
  }
}
