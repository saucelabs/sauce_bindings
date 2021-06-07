package com.saucelabs.saucebindings.testng;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.lang.reflect.Method;
import static org.testng.Assert.assertTrue;

public class AddHooksTest extends SauceBaseTest {
    Boolean working = null;

    @BeforeMethod
    public void setup(Method method) {
        working = true;
        super.setup(method);
    }

    @AfterMethod
    public void teardown() {
        System.out.println("Can do something in teardown before test watcher methods");
    }

    @Test
    public void useCustomOptions() throws AssertionError {
        assertTrue(working);
    }
}
