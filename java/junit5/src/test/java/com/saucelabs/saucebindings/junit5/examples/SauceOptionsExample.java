package com.saucelabs.saucebindings.junit5.examples;

import com.saucelabs.saucebindings.SaucePlatform;
import com.saucelabs.saucebindings.SauceSession;
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

  // Register extension with SauceOptions
  @RegisterExtension
  static SauceBindingsExtension sauceExtension =
      SauceBindingsExtension.builder().withSauceOptions(getSauceOptions()).build();

  // Enable extension (this also can be done by running with -Dsauce.enabled=true)
  static {
    sauceExtension.enable();
  }

  @BeforeEach
  public void setUp(SauceSession session, WebDriver driver) {
    this.session = session;
    this.driver = driver;
  }

  public static SauceOptions getSauceOptions() {
    ChromeOptions chromeOptions = new ChromeOptions();
    chromeOptions.addArguments("--hide-scrollbars");

    return SauceOptions.chrome(chromeOptions)
        .setPlatformName(SaucePlatform.MAC_CATALINA)
        .setIdleTimeout(Duration.ofSeconds(30))
        .build();
  }

  @Test
  public void optionsExample() {
    session.annotate("Navigating to Swag Labs");
    driver.get("https://www.saucedemo.com/");
  }
}
