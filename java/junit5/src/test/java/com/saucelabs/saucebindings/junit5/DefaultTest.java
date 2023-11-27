package com.saucelabs.saucebindings.junit5;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.RegisterExtension;

public class DefaultTest {
  static {
    System.setProperty("sauce.enabled", "false");
  }

  @RegisterExtension static SauceBindingsExtension sauceExtension = new SauceBindingsExtension();

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
