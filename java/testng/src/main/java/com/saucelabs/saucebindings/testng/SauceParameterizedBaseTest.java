package com.saucelabs.saucebindings.testng;

import com.saucelabs.saucebindings.SauceSession;
import com.saucelabs.saucebindings.options.SauceOptions;
import java.lang.reflect.Method;
import java.util.Arrays;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

public abstract class SauceParameterizedBaseTest {
  private static ThreadLocal<SauceSession> session = new ThreadLocal<>();

  public RemoteWebDriver getDriver() {
    return getSession().getDriver();
  }

  public SauceSession getSession() {
    return session.get();
  }

  /**
   * This is designed to be able to be overridden in a subclass if using parameterization
   *
   * @param method the Method instance required for starting Parameterized tests in subclass
   * @param parameters the DataProvider parameters object defined in the subclass
   * @return default instance of SauceOptions
   */
  protected SauceOptions createSauceOptions(Method method, Object[] parameters) {
    return new SauceOptions();
  }

  @BeforeMethod
  protected void setup(Method method, Object[] parameters) {
    SauceOptions sauceOptions = createSauceOptions(method, parameters);
    if (sauceOptions.sauce().getName() == null) {
      sauceOptions.sauce().setName(method.getName());
    }
    session.set(new SauceSession(sauceOptions));
    getSession().start();
  }

  @AfterMethod
  protected void teardown(ITestResult result) {
    if (result.isSuccess()) {
      getSession().stop(true);
    } else {
      Throwable e = result.getThrowable();
      getDriver().executeScript("sauce:context=Failure Reason: " + e.getMessage());

      for (Object trace : Arrays.stream(e.getStackTrace()).toArray()) {
        if (trace.toString().contains("sun")) {
          break;
        }
        getDriver().executeScript("sauce:context=Backtrace: " + trace);
      }

      getSession().stop(false);
    }
  }
}
