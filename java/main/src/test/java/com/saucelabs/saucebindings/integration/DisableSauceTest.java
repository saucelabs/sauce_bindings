package com.saucelabs.saucebindings.integration;

import com.saucelabs.saucebindings.SauceSession;
import java.util.List;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.condition.DisabledIfSystemProperty;
import org.junit.jupiter.api.condition.EnabledIfSystemProperty;
import org.openqa.selenium.WebDriver;

public class DisableSauceTest {

  @Test
  @EnabledIfSystemProperty(named = "sauce.disabled", matches = "true")
  @DisabledIfSystemProperty(named = "sauce.disabled", matches = "(?!true)") // Needed for IntelliJ
  public void disableSauce() {
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
