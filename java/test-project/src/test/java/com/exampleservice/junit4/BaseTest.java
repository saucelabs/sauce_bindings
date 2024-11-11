package com.exampleservice.junit4;

import com.saucelabs.saucebindings.SauceSession;
import com.saucelabs.saucebindings.junit4.SauceBindingsWatcher;
import org.junit.Before;
import org.junit.Rule;
import org.junit.rules.TestWatcher;
import org.junit.runner.Description;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.time.Duration;

public class BaseTest {
  WebDriver driver;
  SauceSession session;

  @Rule
  public SauceBindingsWatcher sauceWatcher =
      SauceBindingsWatcher.builder().withCapabilities(getChromeOptions()).build();

  @Rule public TestWatcher localWatcher = new LocalTestWatcher();

  @Before
  public void storeVariables() {
    this.session = sauceWatcher.getSession();
    this.driver =
        SauceSession.isEnabled() ? sauceWatcher.getDriver() : new ChromeDriver(getChromeOptions());
  }

  private ChromeOptions getChromeOptions() {
    ChromeOptions options = new ChromeOptions();
    options.setImplicitWaitTimeout(Duration.ofSeconds(2));
    return options;
  }

  public interface ContentTests {}

  public interface NavigationTests {}

  public interface FeatureTests {}

  private class LocalTestWatcher extends TestWatcher {
    @Override
    protected void failed(Throwable e, Description description) {
      if (!SauceSession.isEnabled()) {
        driver.quit();
      }
    }

    @Override
    protected void succeeded(Description description) {
      if (!SauceSession.isEnabled()) {
        driver.quit();
      }
    }
  }
}
