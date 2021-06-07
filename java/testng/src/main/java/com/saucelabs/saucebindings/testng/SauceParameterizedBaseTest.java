package com.saucelabs.saucebindings.testng;

import com.saucelabs.saucebindings.SauceSession;
import com.saucelabs.saucebindings.options.SauceOptions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import java.lang.reflect.Method;
import java.util.Arrays;

public abstract class SauceParameterizedBaseTest {
    private static ThreadLocal<SauceSession> session = new ThreadLocal<>();

    public  RemoteWebDriver getDriver() {
        return getSession().getDriver();
    }

    public SauceSession getSession() {
        return session.get();
    }

    /**
     * This is designed to be able to be overridden in a subclass if using parameterization
     *
     * @return default instance of SauceOptions
     */
    protected SauceOptions createSauceOptions(Method method, Object[] parameters) {
        return new SauceOptions();
    }

    @BeforeMethod
    public void setup(Method method, Object[] parameters) {
        SauceOptions sauceOptions = createSauceOptions(method, parameters);
        if (sauceOptions.sauce().getName() == null) {
            sauceOptions.sauce().setName(method.getName());
        }
        session.set(new SauceSession(sauceOptions));
        getSession().start();
    }


    @AfterMethod
    public void teardown(ITestResult result) {
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
