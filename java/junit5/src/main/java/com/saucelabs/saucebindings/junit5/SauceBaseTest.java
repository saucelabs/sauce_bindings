package com.saucelabs.saucebindings.junit5;

import com.saucelabs.saucebindings.DataCenter;
import com.saucelabs.saucebindings.SauceSession;
import com.saucelabs.saucebindings.options.SauceOptions;
import java.util.Arrays;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.TestInfo;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.api.extension.RegisterExtension;
import org.junit.jupiter.api.extension.TestWatcher;
import org.openqa.selenium.remote.RemoteWebDriver;

/** This class is deprecated, use the SauceBindingsExtension */
@Deprecated
public class SauceBaseTest {
  protected RemoteWebDriver driver;
  protected SauceSession session;
  protected DataCenter dataCenter = DataCenter.US_WEST;

  @RegisterExtension public SauceTestWatcher watcher = new SauceTestWatcher();

  /**
   * This is designed to be able to be overridden in a subclass
   *
   * @return default instance of SauceOptions
   */
  public SauceOptions createSauceOptions() {
    return new SauceOptions();
  }

  /**
   * This method ensures a test name is set by default, and then starts the session It creates a
   * session and a driver
   */
  @BeforeEach
  public void setUp(TestInfo testinfo) {
    SauceOptions sauceOptions = createSauceOptions();
    if (sauceOptions.sauce().getName() == null) {
      sauceOptions.sauce().setName(testinfo.getDisplayName());
    }
    session = new SauceSession(sauceOptions);
    session.setDataCenter(getDataCenter());
    driver = session.start();
  }

  public DataCenter getDataCenter() {
    return this.dataCenter;
  }

  /**
   * This TestWatcher can be overridden by creating a subclass of the TestWatcher, then defining a
   * separate rule in the subclass of the SauceBaseTest class
   */
  public class SauceTestWatcher implements TestWatcher {
    @Override
    public void testSuccessful(ExtensionContext context) {
      session.stop(true);
    }

    @Override
    public void testFailed(ExtensionContext context, Throwable cause) {
      driver.executeScript("sauce:context=Failure Reason: " + cause.getMessage());

      for (Object trace : Arrays.stream(cause.getStackTrace()).toArray()) {
        if (trace.toString().contains("sun")) {
          break;
        }
        driver.executeScript("sauce:context=Backtrace: " + trace);
      }

      session.stop(false);
    }
  }
}
