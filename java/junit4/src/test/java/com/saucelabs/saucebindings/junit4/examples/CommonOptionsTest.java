package com.saucelabs.saucebindings.junit4.examples;

import com.saucelabs.saucebindings.SaucePlatform;
import com.saucelabs.saucebindings.UnhandledPromptBehavior;
import com.saucelabs.saucebindings.junit4.SauceBaseTest;
import com.saucelabs.saucebindings.options.SauceOptions;
import org.junit.Test;

// 1. Extend the provided base test class
public class CommonOptionsTest extends SauceBaseTest {

    // 2. Create SauceOptions instance with common w3c options
    public SauceOptions createSauceOptions() {
        return SauceOptions.firefox()
                .setBrowserVersion("85.0")
                .setPlatformName(SaucePlatform.WINDOWS_8)
                .setUnhandledPromptBehavior(UnhandledPromptBehavior.IGNORE)
                .build();
    }

    @Test
    public void basicOptions() {
        // 3. Session and Driver are created automatically by superclass

        // 4. Use the driver in your tests just like normal
        driver.get("https://www.saucedemo.com/");

        // 5. Session is stopped and results are sent to Sauce Labs automatically by the superclass
    }
}
