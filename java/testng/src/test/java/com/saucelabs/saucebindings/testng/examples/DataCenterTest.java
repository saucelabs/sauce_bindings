package com.saucelabs.saucebindings.testng.examples;

import com.saucelabs.saucebindings.DataCenter;
import com.saucelabs.saucebindings.testng.SauceBaseTest;
import org.testng.annotations.Test;

// 1. Extend the provided base test class
public class DataCenterTest extends SauceBaseTest {
  // 2. Change the Data Center you want to use if it isn't US_WEST
  @Override
  public DataCenter getDataCenter() {
    return DataCenter.EU_CENTRAL;
  }

  @Test
  public void changeDataCenter() {
    // 3. Session and Driver are created automatically by superclass

    // 4. Use the driver in your tests just like normal
    getDriver().get("https://www.saucedemo.com/");

    // 5. Session is stopped and results are sent to Sauce Labs automatically by the superclass
  }
}
