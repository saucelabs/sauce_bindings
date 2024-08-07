package com.saucelabs.saucebindings.testng.examples;

import com.saucelabs.saucebindings.testng.SauceBaseTest;
import org.testng.annotations.Test;

// 1. Extend the provided base test class
public class SessionTest extends SauceBaseTest {

  @Test
  public void startSession() {
    // 2. Session and Driver are created automatically by superclass

    // 3. Use the driver in your tests just like normal
    getDriver().get("https://www.saucedemo.com/");

    // 4. Session is stopped and results are sent to Sauce Labs automatically by the superclass
  }
}
