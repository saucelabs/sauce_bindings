package com.exampleservice.junit5;

import com.saucelabs.saucebindings.SauceSession;
import com.saucelabs.saucebindings.junit5.SauceBindingsExtension;
import com.saucelabs.saucebindings.options.SauceOptions;

import java.time.Duration;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.AfterEachCallback;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.api.extension.RegisterExtension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class BaseTest {
  WebDriver driver;
  SauceSession session;

  @RegisterExtension
  static SauceBindingsExtension sauceExtension =
      new SauceBindingsExtension.Builder()
          .withSauceOptions(
              SauceOptions.chrome()
                  .setImplicitWaitTimeout(Duration.ofSeconds(2))
                  .setScreenResolution("1920x1200")
                  .build())
          .build();

  @RegisterExtension LocalAfterHook after = new LocalAfterHook();

  @BeforeEach
  public void setUp(SauceSession session, WebDriver driver) {
    this.session = session;
    this.driver = SauceSession.isEnabled() ? driver : new ChromeDriver(getChromeOptions());
  }

  class LocalAfterHook implements AfterEachCallback {
    @Override
    public void afterEach(ExtensionContext extensionContext) {
      if (!SauceSession.isEnabled()) {
        driver.quit();
      }
    }
  }

  private ChromeOptions getChromeOptions() {
    ChromeOptions options = new ChromeOptions();
    options.setImplicitWaitTimeout(Duration.ofSeconds(2));
    return options;
  }
}
