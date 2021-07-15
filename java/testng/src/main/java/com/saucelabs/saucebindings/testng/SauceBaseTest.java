package com.saucelabs.saucebindings.testng;

import com.saucelabs.saucebindings.SauceSession;
import com.saucelabs.saucebindings.options.SauceOptions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import java.lang.reflect.Method;
import java.util.Arrays;

/**
 * @deprecated
 * The name for this project has been changed; Please update your POM to use saucebindings-junit4.
 * @see <a href="https://opensource.saucelabs.com/sauce_bindings/docs/getting-started">Getting Started With Sauce Bindings</a> for more information
 */
@Deprecated
public class SauceBaseTest {
    private static ThreadLocal<SauceSession> session = new ThreadLocal<>();

    public  RemoteWebDriver getDriver() {
        return getSession().getDriver();
    }

    public SauceSession getSession() {
        return session.get();
    }

    /**
     * This is designed to be able to be overridden in a subclass
     *
     * @return default instance of SauceOptions
     */
    protected SauceOptions createSauceOptions() {
        return new SauceOptions();
    }

    /**
     * This method ensures a test name is set by default, and then starts the session
     * It creates a session and a driver
     *
     * @param method the default parameter for BeforeMethod
     */
    @BeforeMethod
    protected void setup(Method method) {
        SauceOptions sauceOptions = createSauceOptions();
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
