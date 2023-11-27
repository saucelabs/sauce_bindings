package com.saucelabs.saucebindings.junit5.obsolete;

import com.saucelabs.saucebindings.junit5.SauceBaseTest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class QuickstartTest extends SauceBaseTest {
  @Test
  public void useAllDefaultsObsolete() {
    driver.navigate().to("https://www.saucedemo.com");
    Assertions.assertEquals("Swag Labs", driver.getTitle());
  }
}
