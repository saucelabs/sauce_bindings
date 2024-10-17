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

@Listeners(SauceBindingsListener.class)
public class ParameterizedExample {
  private WebDriver driver;
  private SauceSession session;

  @Parameters({"browser"})
  @BeforeMethod
  public void startSession(String browserValue, Method method, ITestContext context) {
    SessionContext sessionContext =
        SessionContext.build(method, context).withSauceOptions(getOptions(browserValue)).start();

    this.driver = sessionContext.getDriver();
    this.session = sessionContext.getSession();
  }

  @Test
  public void parameterizedNavigate() {
    session.annotate("Navigating to Swag Labs");
    driver.get("https://www.saucedemo.com/");
  }

  private SauceOptions getOptions(String browserValue) {
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
}
