package com.saucelabs.saucebindings.integration;

import com.saucelabs.saucebindings.DataCenter;
import com.saucelabs.saucebindings.SauceSession;
import com.saucelabs.saucebindings.options.SauceOptions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.util.List;

public class DisableSauceTest {
  private SauceSession session = new SauceSession();
  private RemoteWebDriver driver;

  @AfterEach
  public void cleanUp() {
    System.clearProperty("sauce.disabled");
    if (session != null) {
      session.stop(true);
    }
  }

  @Test
  public void disableSauce() {
    System.setProperty("sauce.disabled", "true");
    SauceSession session = new SauceSession();
    WebDriver driver = session.start();
    Assertions.assertNull(driver);

    Assertions.assertNull(session.getDriver());
    Assertions.assertDoesNotThrow(
        () -> {
          session.annotate("This gets ignored");
          session.addTags(List.of("ignored"));
          session.stopNetwork();
          session.enableLogging();
          session.getAccessibilityResults();
          session.stop(true);
        });
  }
}
