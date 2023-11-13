package com.saucelabs.saucebindings.examples;

import com.saucelabs.saucebindings.*;
import com.saucelabs.saucebindings.options.SauceOptions;
import java.time.Duration;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.remote.RemoteWebDriver;

public class SauceLabsOptionsTest {

  @Test
  public void sauceOptions() {
    // 1. Specify Sauce Specific Options Based on Browser
    SauceOptions sauceOptions =
        SauceOptions.firefox()
            .setExtendedDebugging()
            .setIdleTimeout(Duration.ofSeconds(45))
            .setTimeZone("Alaska")
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
