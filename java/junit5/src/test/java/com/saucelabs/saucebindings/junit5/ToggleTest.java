package com.saucelabs.saucebindings.junit5;

import com.saucelabs.saucebindings.SauceSession;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.condition.DisabledIfSystemProperty;
import org.junit.jupiter.api.condition.EnabledIfSystemProperty;
import org.junit.jupiter.api.extension.RegisterExtension;
import org.openqa.selenium.WebDriver;

import java.util.List;

public class ToggleTest {
  @RegisterExtension static SauceBindingsExtension sauceExtension = new SauceBindingsExtension();

  @Test
  @EnabledIfSystemProperty(named = "sauce.disabled", matches = "true")
  @DisabledIfSystemProperty(named = "sauce.disabled", matches = "(?!true)") // Needed for IntelliJ
  public void disableSauce(SauceSession session, WebDriver driver) {
    Assertions.assertNull(driver);

    Assertions.assertDoesNotThrow(
        () -> {
          session.annotate("This gets ignored");
          session.addTags(List.of("ignored"));
          session.stopNetwork();
          session.enableLogging();
          session.getAccessibilityResults();
        });
  }
}
