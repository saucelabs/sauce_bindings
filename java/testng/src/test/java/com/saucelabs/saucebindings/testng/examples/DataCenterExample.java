package com.saucelabs.saucebindings.testng.examples;

import com.saucelabs.saucebindings.DataCenter;
import com.saucelabs.saucebindings.SauceSession;
import com.saucelabs.saucebindings.testng.SauceBindingsListener;
import java.lang.reflect.Method;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

@Listeners(SauceBindingsListener.class)
public class DataCenterExample {
  private WebDriver driver;
  private SauceSession session;

  @BeforeClass
  public static void createListenerValues() {
    SauceBindingsListener.setDataCenter(DataCenter.EU_CENTRAL);
  }

  @BeforeMethod
  public void startSession(Method method, ITestContext context) {
    SauceBindingsListener.startSession(method, context);
    this.driver = SauceBindingsListener.getDriver(context);
    this.session = SauceBindingsListener.getSession(context);
  }

  @Test
  public void dataCenterNavigate() {
    session.annotate("Navigating to Swag Labs");
    driver.get("https://www.saucedemo.com/");
  }
}
