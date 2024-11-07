package com.saucelabs.saucebindings.testng.examples;

import com.saucelabs.saucebindings.SauceSession;
import com.saucelabs.saucebindings.testng.SauceBindingsListener;
import com.saucelabs.saucebindings.testng.SessionContext;
import java.lang.reflect.Method;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

// 1. Annotate class with Sauce Bindings listener
@Listeners(SauceBindingsListener.class)
public class QuickStartExample {
  private WebDriver driver;
  private SauceSession session;

  // 2. Enable extension in static block (this can be done by running with -Dsauce.enabled=true)
  static {
    SauceBindingsListener.enable();
  }

  @BeforeMethod
  public void startSession(Method method, ITestContext context) {
    // 3. start the session with defaults
    SessionContext sessionContext = SessionContext.build(method, context).start();

    // 4. Get variables from the session context
    this.driver = sessionContext.getDriver();
    this.session = sessionContext.getSession();
  }

  @Test
  public void quickNavigate() {
    // 5. Use the session instance to do Sauce things
    session.annotate("Navigating to Swag Labs");

    // 6. Use the driver instance to do Selenium things
    driver.get("https://www.saucedemo.com/");
  }
}
