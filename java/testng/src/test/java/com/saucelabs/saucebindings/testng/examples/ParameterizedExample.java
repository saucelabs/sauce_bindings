package com.saucelabs.saucebindings.testng.examples;

import com.saucelabs.saucebindings.SauceSession;
import com.saucelabs.saucebindings.options.SauceOptions;
import com.saucelabs.saucebindings.testng.SauceBindingsListener;
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
    SauceBindingsListener.startSession(getConfiguration(browserValue), method, context);

    this.driver = SauceBindingsListener.getDriver(context);
    this.session = SauceBindingsListener.getSession(context);
  }

  @Test
  public void parameterizedNavigate() {
    session.annotate("Navigating to Swag Labs");
    driver.get("https://www.saucedemo.com/");
  }

  private SauceBindingsListener.SessionConfigurationBuilder getConfiguration(String browserValue) {
    switch (browserValue.toLowerCase()) {
      case "chrome":
        return SauceBindingsListener.configure().withSauceOptions(SauceOptions.chrome().build());
      case "firefox":
        return SauceBindingsListener.configure().withSauceOptions(SauceOptions.firefox().build());
      case "safari":
        return SauceBindingsListener.configure().withSauceOptions(SauceOptions.safari().build());
      default:
        throw new IllegalArgumentException("Invalid browser value: " + browserValue);
    }
  }
}
