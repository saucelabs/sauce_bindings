package com.saucelabs.saucebindings.junit5;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.RegisterExtension;

public class DisabledTest {
  @RegisterExtension static SauceBindingsExtension sauceExtension = new SauceBindingsExtension();

  @BeforeAll
  public static void disableSauce() {
    System.setProperty("sauce.disabled", "true");
  }

  @AfterAll
  public static void resetSauce() {
    System.clearProperty("sauce.disabled");
  }

  @Test
  public void ignoresExtension() {
    Assertions.assertNull(sauceExtension.getDriver());
    Assertions.assertNull(sauceExtension.getSession());
  }
}
