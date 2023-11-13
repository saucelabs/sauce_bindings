package com.saucelabs.saucebindings.junit5;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class QuickstartTest extends SauceBaseTest {
  @Test
  public void useAllDefaults() {
    driver.navigate().to("https://www.saucedemo.com");
    Assertions.assertEquals("Swag Labs", driver.getTitle());
  }
}
