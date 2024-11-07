package com.saucelabs.saucebindings.junit5.examples;

import com.saucelabs.saucebindings.SaucePlatform;
import com.saucelabs.saucebindings.SauceSession;
import com.saucelabs.saucebindings.UnhandledPromptBehavior;
import com.saucelabs.saucebindings.junit5.SauceBindingsExtension;
import com.saucelabs.saucebindings.options.SauceOptions;
import java.time.Duration;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.RegisterExtension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class SauceOptionsExample {
  WebDriver driver;
  SauceSession session;

  // 1. Create SauceOptions instance in static method
  public static SauceOptions getSauceOptions() {
    ChromeOptions chromeOptions = new ChromeOptions();
    chromeOptions.addArguments("--hide-scrollbars");

    return SauceOptions.chrome(chromeOptions)
        .setPlatformName(SaucePlatform.MAC_CATALINA)
        .setUnhandledPromptBehavior(UnhandledPromptBehavior.IGNORE)
        .setIdleTimeout(Duration.ofSeconds(30))
        .build();
  }

  // 2. Register Sauce Bindings extension with that SauceOptions instance
  @RegisterExtension
  static SauceBindingsExtension sauceExtension =
      SauceBindingsExtension.builder().withSauceOptions(getSauceOptions()).build();

  // 3. Enable extension (this also can be done by running with -Dsauce.enabled=true)
  static {
    sauceExtension.enable();
  }

  // 4. Get variables created by the Sauce Bindings extension
  @BeforeEach
  public void setUp(SauceSession session, WebDriver driver) {
    this.session = session;
    this.driver = driver;
  }

  @Test
  public void optionsExample() {
    // 5. Use the session instance to do Sauce Labs things
    session.annotate("Navigating to Swag Labs");

    // 6. Use the driver instance to do Selenium things
    driver.get("https://www.saucedemo.com/");
  }
}
