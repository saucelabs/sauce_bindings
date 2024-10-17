package com.practicesoftwaretesting.testng;

import com.saucelabs.saucebindings.SauceSession;
import com.saucelabs.saucebindings.testng.SauceBindingsListener;
import com.saucelabs.saucebindings.testng.SessionContext;
import java.lang.reflect.Method;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;

@Listeners(SauceBindingsListener.class)
public class BaseTest {
  protected final ThreadLocal<WebDriver> driver = new ThreadLocal<>();
  protected final ThreadLocal<SauceSession> session = new ThreadLocal<>();

  @BeforeMethod
  public void startSession(Method method, ITestContext context) {
    SessionContext sessionContext = SessionContext.build(method, context).start();

    this.driver.set(sessionContext.getDriver());
    this.session.set(sessionContext.getSession());
  }

  @AfterMethod
  public void cleanup() {
    driver.remove();
    session.remove();
  }
}
