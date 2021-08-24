package com.saucelabs.saucebindings.junit4.examples;

import com.saucelabs.saucebindings.junit4.SauceBaseTest;
import com.saucelabs.saucebindings.options.SauceOptions;
import org.junit.Test;
import org.openqa.selenium.chrome.ChromeOptions;

// 1. Extend the provided base test class
public class BrowserOptionsTest extends SauceBaseTest {

    // 2. Create Sauce Options with Sauce Labs class for browser specific details
    public SauceOptions createSauceOptions() {
        ChromeOptions browserOptions = new ChromeOptions();
        browserOptions.addArguments("--start-fullscreen");

        return SauceOptions.chrome(browserOptions).build();
    }

    @Test
    public void browserOptions() {
        // 3. Session and Driver are created automatically by superclass

        // 4. Use the driver in your tests just like normal
        driver.get("https://www.saucedemo.com/");

        // 5. Session is stopped and results are sent to Sauce Labs automatically by the superclass
    }
}
