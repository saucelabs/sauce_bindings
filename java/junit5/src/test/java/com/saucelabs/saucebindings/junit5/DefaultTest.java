package com.saucelabs.saucebindings.junit5;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.RegisterExtension;

public class DefaultTest {
  @RegisterExtension static SauceBindingsExtension sauceExtension = new SauceBindingsExtension();

  @BeforeAll
  public static void disableSauce() {
    System.setProperty("sauce.disabled", "true");
  }

  @AfterAll
  public static void resetSauce() {
    System.clearProperty("sauce.disabled");
  }

  @Disabled("This test is disabled because this feature is not yet implemented")
  @Test
  public void useDefaults() {
    Assertions.assertDoesNotThrow(
        () -> {
          sauceExtension.getSession().annotate("Annotating test");
          sauceExtension.getDriver().get("https://www.saucedemo.com/");
        },
        "Driver and Session should be available");
  }

  @AfterEach
  public void teardown() {
    Assertions.assertDoesNotThrow(
        () -> sauceExtension.getDriver().getTitle(), "Session should not be stopped, yet");
  }
}
