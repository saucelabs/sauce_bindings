package com.saucelabs.saucebindings.examples;

import com.saucelabs.saucebindings.SauceSession;
import com.saucelabs.saucebindings.options.SauceOptions;
import org.junit.Test;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.RemoteWebDriver;

public class BrowserOptionsTest {

    @Test
    public void browserOptions() {
        // 1. Create Selenium Browser Options instance
        ChromeOptions browserOptions = new ChromeOptions();
        browserOptions.addArguments("--start-fullscreen");

        // 2. Create SauceOptions instance with static browser method and build()
        SauceOptions sauceOptions = SauceOptions.chrome(browserOptions).build();

        // 3. Create Session object with the Sauce Options object instance
        SauceSession session = new SauceSession(sauceOptions);

        // 4. Start Session to get the Driver
        RemoteWebDriver driver = session.start();

        // 5. Use the driver in your tests just like normal
        driver.get("https://www.saucedemo.com/");

        // 6. Stop the Session with whether the test passed or failed
        session.stop(true);
    }
}
