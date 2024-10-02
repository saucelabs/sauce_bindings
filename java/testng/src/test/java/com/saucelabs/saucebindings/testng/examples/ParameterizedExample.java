package com.saucelabs.saucebindings.testng.examples;

import com.saucelabs.saucebindings.SauceSession;
import com.saucelabs.saucebindings.options.SauceOptions;
import com.saucelabs.saucebindings.testng.SauceBindingsListener;
import java.lang.reflect.Method;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

@Listeners(SauceBindingsListener.class)
public class ParameterizedExample {
  private WebDriver driver;
  private SauceSession session;

  @BeforeClass
  @Parameters({"browser"})
  public static void createListenerValues(String browserValue) {
    switch (browserValue.toLowerCase()) {
      case "chrome":
        SauceBindingsListener.setSauceOptions(SauceOptions.chrome().build());
      case "firefox":
        SauceBindingsListener.setSauceOptions(SauceOptions.firefox().build());
        break;
      case "safari":
        SauceBindingsListener.setSauceOptions(SauceOptions.safari().build());
        break;
      default:
        throw new IllegalArgumentException("Invalid browser value: " + browserValue);
    }
  }

  @BeforeMethod
  public void startSession(Method method, ITestContext context) {
    SauceBindingsListener.startSession(method, context);
    this.driver = SauceBindingsListener.getDriver(context);
    this.session = SauceBindingsListener.getSession(context);
  }

  @Test
  public void parameterizedNavigate() {
    session.annotate("Navigating to Swag Labs");
    driver.get("https://www.saucedemo.com/");
  }
}
