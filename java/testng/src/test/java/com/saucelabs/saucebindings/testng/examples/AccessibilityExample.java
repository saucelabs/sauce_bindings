package com.saucelabs.saucebindings.testng.examples;

import com.deque.html.axecore.results.Results;
import com.saucelabs.saucebindings.SauceSession;
import com.saucelabs.saucebindings.testng.SauceBindingsListener;
import java.lang.reflect.Method;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.testng.ITestContext;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

@Listeners(SauceBindingsListener.class)
public class AccessibilityExample {
  private WebDriver driver;
  private SauceSession session;

  @BeforeMethod
  public void startSession(Method method, ITestContext context) {
    SauceBindingsListener.startSession(method, context);

    this.driver = SauceBindingsListener.getDriver(context);
    this.session = SauceBindingsListener.getSession(context);
  }

  @Test
  public void accessibilityResults() {
    session.annotate("Navigating to Swag Labs");
    driver.get("https://www.saucedemo.com/");

    // store the accessibility results into an object you can make assertions on
    Results accessibilityResults = session.getAccessibilityResults();
  }
}
