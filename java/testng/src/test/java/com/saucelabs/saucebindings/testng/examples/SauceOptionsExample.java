package com.saucelabs.saucebindings.testng.examples;

import com.saucelabs.saucebindings.SaucePlatform;
import com.saucelabs.saucebindings.SauceSession;
import com.saucelabs.saucebindings.UnhandledPromptBehavior;
import com.saucelabs.saucebindings.options.SauceOptions;
import com.saucelabs.saucebindings.testng.SauceBindingsListener;
import com.saucelabs.saucebindings.testng.SessionContext;
import java.lang.reflect.Method;
import java.time.Duration;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.ITestContext;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

// 1. Annotate class with Sauce Bindings listener
@Listeners(SauceBindingsListener.class)
public class SauceOptionsExample {
  private WebDriver driver;
  private SauceSession session;

  // 2. Enable extension in static block (this can be done by running with -Dsauce.enabled=true)
  static {
    SauceBindingsListener.enable();
  }

  // 3. Create SauceOptions instance in a method
  public static SauceOptions getSauceOptions() {
    ChromeOptions chromeOptions = new ChromeOptions();
    chromeOptions.addArguments("--hide-scrollbars");

    return SauceOptions.chrome(chromeOptions)
        .setPlatformName(SaucePlatform.MAC_CATALINA)
        .setUnhandledPromptBehavior(UnhandledPromptBehavior.IGNORE)
        .setIdleTimeout(Duration.ofSeconds(30))
        .build();
  }

  @BeforeMethod
  public void startSession(Method method, ITestContext context) {
    // 4. start the session with those SauceOptions
    SessionContext sessionContext =
        SessionContext.build(method, context).withSauceOptions(getSauceOptions()).start();

    // 5. Get variables from the session context
    this.driver = sessionContext.getDriver();
    this.session = sessionContext.getSession();
  }

  @Test
  public void optionsNavigate() {
    // 6. Use the session instance to do Sauce things
    session.annotate("Navigating to Swag Labs");

    // 7. Use the driver instance to do Selenium things
    driver.get("https://www.saucedemo.com/");
  }
}
