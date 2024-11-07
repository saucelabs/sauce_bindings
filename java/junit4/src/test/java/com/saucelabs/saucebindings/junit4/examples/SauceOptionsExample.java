package com.saucelabs.saucebindings.junit4.examples;

import com.saucelabs.saucebindings.SaucePlatform;
import com.saucelabs.saucebindings.SauceSession;
import com.saucelabs.saucebindings.UnhandledPromptBehavior;
import com.saucelabs.saucebindings.junit4.SauceBindingsWatcher;
import com.saucelabs.saucebindings.options.SauceOptions;
import java.time.Duration;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.openqa.selenium.WebDriver;

public class SauceOptionsExample {
  WebDriver driver;
  SauceSession session;

  // 1. Create SauceOptions instance in static method
  public static SauceOptions createSauceOptions() {
    return SauceOptions.firefox()
        .setBrowserVersion("127.0")
        .setPlatformName(SaucePlatform.WINDOWS_10)
        .setUnhandledPromptBehavior(UnhandledPromptBehavior.IGNORE)
        .setIdleTimeout(Duration.ofSeconds(45))
        .setTimeZone("Alaska")
        .build();
  }

  // 2. Pass these options to the SauceBindingsWatcher rule
  @Rule
  public SauceBindingsWatcher sauceWatcher =
      SauceBindingsWatcher.builder().withSauceOptions(createSauceOptions()).build();

  // 3. Enable SauceBindingsWatcher rule
  static {
    SauceBindingsWatcher.enable();
  }

  // 4. Get variables created by Watcher
  @Before
  public void storeVariables() {
    this.session = sauceWatcher.getSession();
    this.driver = sauceWatcher.getDriver();
  }

  @Test
  public void basicOptions() {
    // 5. Use the session instance to do Sauce Labs things
    session.annotate("Navigating to Swag Labs");

    // 6. Use the driver instance to do Selenium things
    driver.get("https://www.saucedemo.com/");

    // 7. Watcher does all teardown activities
  }
}
