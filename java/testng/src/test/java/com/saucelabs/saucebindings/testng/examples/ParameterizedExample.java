package com.saucelabs.saucebindings.testng.examples;

import com.saucelabs.saucebindings.SauceSession;
import com.saucelabs.saucebindings.options.SauceOptions;
import com.saucelabs.saucebindings.testng.SauceBindingsListener;
import com.saucelabs.saucebindings.testng.SessionContext;
import java.lang.reflect.Method;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

// 1. Annotate class with Sauce Bindings listener
@Listeners(SauceBindingsListener.class)
public class ParameterizedExample {
  private WebDriver driver;
  private SauceSession session;

  // 2. Enable extension in static block (this can be done by running with -Dsauce.enabled=true)
  static {
    SauceBindingsListener.enable();
  }

  // 3. Create method for managing parameters
  SauceOptions getOptions(String browserValue) {
    switch (browserValue.toLowerCase()) {
      case "chrome":
        return SauceOptions.chrome().build();
      case "firefox":
        return SauceOptions.firefox().build();
      case "safari":
        return SauceOptions.safari().build();
      default:
        throw new IllegalArgumentException("Invalid browser value: " + browserValue);
    }
  }

  // 4. Use @Parameters annotation
  @Parameters({"browser"})
  @BeforeMethod
  public void startSession(String browserValue, Method method, ITestContext context) {
    // 5. start the session with the method that manages parameters
    SessionContext sessionContext =
        SessionContext.build(method, context).withSauceOptions(getOptions(browserValue)).start();

    // 6. Get variables from the session context
    this.driver = sessionContext.getDriver();
    this.session = sessionContext.getSession();
  }

  @Test
  public void parameterizedNavigate() {
    // 7. Use the session instance to do Sauce things
    session.annotate("Navigating to Swag Labs");

    // 8. Use the driver instance to do Selenium things
    driver.get("https://www.saucedemo.com/");
  }
}
