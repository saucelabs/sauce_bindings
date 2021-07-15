package com.saucelabs.saucebindings.testng.examples;

import com.saucelabs.saucebindings.options.SauceOptions;
import com.saucelabs.saucebindings.testng.SauceBaseTest;
import org.testng.annotations.Test;

import java.time.Duration;

// 1. Extend the provided base test class
public class SauceLabsOptionsTest extends SauceBaseTest {

    // 2. Specify Sauce Specific Options Based on Browser
    protected SauceOptions createSauceOptions() {
        return SauceOptions.firefox()
                .setExtendedDebugging()
                .setIdleTimeout(Duration.ofSeconds(45))
                .setTimeZone("Alaska")
                .build();
    }

    @Test
    public void sauceOptions() {
        // 3. Driver and Session are created automatically by the superclass

        // 4. Use the driver in your tests just like normal
        getDriver().get("https://www.saucedemo.com/");

        // 5. Session is stopped and results are sent to Sauce Labs automatically by the superclass
    }
}
