package com.saucelabs.saucebindings.testng.examples;

import com.saucelabs.saucebindings.options.SauceOptions;
import com.saucelabs.saucebindings.testng.SauceBaseTest;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.testng.annotations.Test;

// 1. Extend the provided base test class
public class BrowserOptionsTest extends SauceBaseTest {

    // 2. Create Sauce Options with Sauce Labs class for browser specific details
    @Override
    protected SauceOptions createSauceOptions() {
        ChromeOptions browserOptions = new ChromeOptions();
        browserOptions.addArguments("--start-fullscreen");

        return SauceOptions.firefox(browserOptions).build();
    }

    @Test
    public void browserOptions() {
        // 3. Session and Driver are created automatically by superclass

        // 4. Use the driver in your tests just like normal
        getDriver().get("https://www.saucedemo.com/");

        // 5. Session is stopped and results are sent to Sauce Labs automatically by the superclass
    }
}
