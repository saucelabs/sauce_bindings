package com.saucelabs.saucebindings.examples;

import com.saucelabs.saucebindings.*;
import com.saucelabs.saucebindings.SauceOptions;
import com.saucelabs.saucebindings.options.SauceOptionsFactory;
import org.junit.Test;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.RemoteWebDriver;

public class BrowserOptions {

    @Test
    public void browserOptions() {
        // 1. Create Selenium Browser Options instance
        FirefoxOptions browserOptions = new FirefoxOptions();
        browserOptions.addArguments("--foo");

        // 2. Create Sauce Options object with the Browser Options object instance
        SauceOptions sauceOptions = SauceOptionsFactory.firefox(browserOptions).build();

        // 2. Create Session object with the Sauce Options object instance
        SauceSession session = new SauceSession(sauceOptions);

        // 3. Start Session to get the Driver
        RemoteWebDriver driver = session.start();

        // 4. Use the driver in your tests just like normal
        driver.get("https://www.saucedemo.com/");

        // 5. Stop the Session with whether the test passed or failed
        session.stop(true);
    }
}
