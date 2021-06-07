package com.saucelabs.saucebindings.junit4;

import com.saucelabs.saucebindings.SauceSession;
import com.saucelabs.saucebindings.options.SauceOptions;
import org.junit.Before;
import org.junit.Rule;
import org.junit.rules.TestName;
import org.junit.rules.TestWatcher;
import org.junit.runner.Description;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.util.Arrays;

public class SauceBaseTest {
    protected RemoteWebDriver driver;
    protected SauceSession session;

    @Rule
    public SauceTestWatcher watcher = new SauceTestWatcher();

    @Rule
    public TestName testName = new TestName();

    /**
     * This is designed to be able to be overridden in a subclass
     *
     * @return default instance of SauceOptions
     */
    public SauceOptions createSauceOptions() {
        return new SauceOptions();
    }

    /**
     * This method ensures a test name is set by default, and then starts the session
     * It creates a session and a driver
     */
    @Before
    public void setup() {
        SauceOptions sauceOptions = createSauceOptions();
        if (sauceOptions.sauce().getName() == null) {
            sauceOptions.sauce().setName(testName.getMethodName());
        }
        session = new SauceSession(sauceOptions);
        driver = session.start();
    }

    /**
     * This TestWatcher can be overridden by creating a subclass of the TestWatcher,
     * then defining a separate rule in the subclass of the SauceBaseTest class
     */
    public class SauceTestWatcher extends TestWatcher {
        @Override
        protected void succeeded(Description description) {
            session.stop(true);
        }

        @Override
        protected void failed(Throwable e, Description description) {
            driver.executeScript("sauce:context=Failure Reason: " + e.getMessage());

            for (Object trace : Arrays.stream(e.getStackTrace()).toArray()) {
                if (trace.toString().contains("sun")) {
                    break;
                }
                driver.executeScript("sauce:context=Backtrace: " + trace);
            }

            session.stop(false);
        }
    }
}
