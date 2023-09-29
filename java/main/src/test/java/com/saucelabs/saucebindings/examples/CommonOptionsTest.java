package com.saucelabs.saucebindings.examples;

import com.saucelabs.saucebindings.options.SauceOptions;
import com.saucelabs.saucebindings.*;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.remote.RemoteWebDriver;

public class CommonOptionsTest {

    @Test
    public void basicOptions() {
        // 1. Create SauceOptions instance with common w3c options
        SauceOptions sauceOptions = SauceOptions.firefox()
                .setBrowserVersion("85.0")
                .setPlatformName(SaucePlatform.WINDOWS_8)
                .setUnhandledPromptBehavior(UnhandledPromptBehavior.IGNORE)
                .build();

        // 2. Create Session object with the Options object instance
        SauceSession session = new SauceSession(sauceOptions);

        // 3. Start Session to get the Driver
        RemoteWebDriver driver = session.start();

        // 4. Use the driver in your tests just like normal
        driver.get("https://www.saucedemo.com/");

        // 5. Stop the Session with whether the test passed or failed
        session.stop(true);
    }
}
