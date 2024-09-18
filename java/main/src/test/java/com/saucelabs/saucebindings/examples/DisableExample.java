package com.saucelabs.saucebindings.examples;

import com.saucelabs.saucebindings.SauceSession;
import java.util.List;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;

public class DisableExample {

  @Test
  public void startSession() {
    // 1. Toggle off sauce labs
    System.setProperty("sauce.disabled", "true");

    // 2. Create a Sauce Session
    SauceSession session = new SauceSession();

    // 3. Starting the session will not create a driver
    WebDriver driver = session.start();
    Assertions.assertNull(driver);

    // 4. All session commands will be ignored
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

  @AfterEach
  public void stopSession() {
    System.clearProperty("sauce.disabled");
  }
}
