package com.saucelabs.saucebindings.testng.examples;

import com.saucelabs.saucebindings.DataCenter;
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
public class DataCenterExample {
  private WebDriver driver;
  private SauceSession session;

  @BeforeMethod
  public void startSession(Method method, ITestContext context) {
    SessionContext sessionContext =
        SessionContext.build(method, context).withDataCenter(DataCenter.EU_CENTRAL).start();

    this.driver = sessionContext.getDriver();
    this.session = sessionContext.getSession();
  }

  @Test
  public void dataCenterNavigate() {
    session.annotate("Navigating to Swag Labs");
    driver.get("https://www.saucedemo.com/");
  }
}
