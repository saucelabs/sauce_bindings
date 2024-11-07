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
import org.openqa.selenium.chrome.ChromeOptions;

public class SauceOptionsExample {
  WebDriver driver;
  SauceSession session;

  // 1. Create SauceOptions instance in static method
  public static SauceOptions createSauceOptions() {
    ChromeOptions chromeOptions = new ChromeOptions();
    chromeOptions.addArguments("--hide-scrollbars");

    return SauceOptions.chrome(chromeOptions)
        .setPlatformName(SaucePlatform.MAC_CATALINA)
        .setUnhandledPromptBehavior(UnhandledPromptBehavior.IGNORE)
        .setIdleTimeout(Duration.ofSeconds(30))
        .build();
  }

  // 2. Create SauceBindingsWatcher rule with these options
  @Rule
  public SauceBindingsWatcher sauceWatcher =
      SauceBindingsWatcher.builder().withSauceOptions(createSauceOptions()).build();

  // 3. Enable this watcher in the static block
  static {
    SauceBindingsWatcher.enable();
  }

  // 4. Get variables created by the watcher
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
  }
}
