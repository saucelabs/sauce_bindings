package com.saucelabs.saucebindings.examples;

import com.saucelabs.saucebindings.SaucePlatform;
import com.saucelabs.saucebindings.SauceSession;
import com.saucelabs.saucebindings.options.SauceOptions;
import com.saucelabs.saucebindings.options.VisualOptions;
import org.junit.Test;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.util.Map;

public class VisualTest {

    @Test
    public void startSession() {
        // 1. Create Visual Options instance
        VisualOptions visualOptions = new VisualOptions();
        visualOptions.setProjectName("My Project")
                .setViewportSize("1024x768");

        // 2. Use setVisualOptions() to add the visual options; Make sure setName() has a value
        SauceOptions sauceOptions = SauceOptions.chrome()
                .setName("My Test Name")
                .setPlatformName(SaucePlatform.MAC_HIGH_SIERRA)
                .setBrowserVersion("68")
                .setVisualOptions(visualOptions)
                .build();

        // 3. Start the Session with SauceOptions
        SauceSession session = new SauceSession(sauceOptions);

        // 4. Start Session to get the Driver
        RemoteWebDriver driver = session.start();

        // 5. Use the driver in your tests just like normal
        driver.get("https://www.saucedemo.com/");

        // 6. Use the session to take a snapshot
        session.visual().takeSnapshot("Sauce Demo Page");

        // 7. Use the session to get visual results
        Map results = session.visual().getResults();

        // 8. Stop the Session with whether the test passed or failed
        session.stop(true);
    }
}
