package com.saucelabs.saucebindings.testng.examples;

import com.deque.html.axecore.results.Results;
import com.saucelabs.saucebindings.SauceSession;
import com.saucelabs.saucebindings.testng.SauceBindingsListener;
import com.saucelabs.saucebindings.testng.SessionContext;
import java.lang.reflect.Method;
import org.openqa.selenium.WebDriver;
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
    SessionContext sessionContext = SessionContext.build(method, context).start();

    this.driver = sessionContext.getDriver();
    this.session = sessionContext.getSession();
  }

  @Test
  public void accessibilityResults() {
    session.annotate("Navigating to Swag Labs");
    driver.get("https://www.saucedemo.com/");

    // store the accessibility results into an object you can make assertions on
    Results accessibilityResults = session.getAccessibilityResults();
  }
}
