package com.saucelabs.saucebindings.testng.examples;

import com.deque.html.axecore.selenium.AxeBuilder;
import com.saucelabs.saucebindings.testng.SauceBaseTest;
import org.testng.annotations.Test;

// 1. Extend the provided base test class
public class AccessibilityTest extends SauceBaseTest {

  @Test
  public void startSession() {
    // 2. Session and Driver are created automatically by superclass

    // 2. Use the driver in your tests just like normal
    getDriver().get("https://www.saucedemo.com/");

    // 3a. Get accessibility default results with frame support
    getSession().getAccessibilityResults();

    // 3b. Get accessibility default results without frame support
    getSession().getAccessibilityResults(false);

    // 3c. Get accessibility default results with Deque Builder instance
    //     Options for configuring AxeBuilder are here:
    // https://github.com/dequelabs/axe-core-maven-html#:~:text=axebuilder
    AxeBuilder builder = new AxeBuilder();
    getSession().getAccessibilityResults(builder);

    // 4. Session is stopped and results are sent to Sauce Labs automatically by the superclass
  }
}
