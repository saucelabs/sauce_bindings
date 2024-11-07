package com.saucelabs.saucebindings.testng.examples;

import com.saucelabs.saucebindings.SauceSession;
import com.saucelabs.saucebindings.testng.SauceBindingsListener;
import com.saucelabs.saucebindings.testng.SessionContext;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.safari.SafariOptions;
import org.testng.ITestContext;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

// 1. Annotate class with Sauce Bindings listener
@Listeners(SauceBindingsListener.class)
public class CapabilitiesExample {
  private WebDriver driver;
  private SauceSession session;

  // 2. Enable extension in static block (this can be done by running with -Dsauce.enabled=true)
  static {
    SauceBindingsListener.enable();
  }

  // 3. Create method with Selenium Capabilities
  Capabilities getCapabilities() {
    SafariOptions browserOptions = new SafariOptions();
    browserOptions.setPlatformName("macOS 12");
    browserOptions.setBrowserVersion("latest");
    Map<String, Object> sauceOptions = new HashMap<>();
    sauceOptions.put("idleTimeout", 30);
    browserOptions.setCapability("sauce:options", sauceOptions);
    return browserOptions;
  }

  @BeforeMethod
  public void startSession(Method method, ITestContext context) {
    // 4. start the session with those capabilities
    SessionContext sessionContext =
        SessionContext.build(method, context).withCapabilities(getCapabilities()).start();

    // 5. Get variables from the session context
    this.driver = sessionContext.getDriver();
    this.session = sessionContext.getSession();
  }

  @Test
  public void capabilitiesNavigate() {
    // 6. Use the session instance to do Sauce things
    session.annotate("Navigating to Swag Labs");

    // 7. Use the driver instance to do Selenium things
    driver.get("https://www.saucedemo.com/");
  }
}
