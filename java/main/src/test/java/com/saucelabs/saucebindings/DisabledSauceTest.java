package com.saucelabs.saucebindings;

import java.util.List;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;

public class DisabledSauceTest {

  @Test
  public void disabledByDefault() {
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
