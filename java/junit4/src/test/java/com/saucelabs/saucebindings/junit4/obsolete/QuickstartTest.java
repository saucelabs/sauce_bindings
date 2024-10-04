package com.saucelabs.saucebindings.junit4.obsolete;

import com.saucelabs.saucebindings.junit4.SauceBaseTest;
import org.junit.Assert;
import org.junit.Test;

public class QuickstartTest extends SauceBaseTest {
  @Test
  public void useAllDefaults() {
    driver.navigate().to("https://www.saucedemo.com");
    Assert.assertEquals(driver.getTitle(), "Swag Labs");
  }
}
