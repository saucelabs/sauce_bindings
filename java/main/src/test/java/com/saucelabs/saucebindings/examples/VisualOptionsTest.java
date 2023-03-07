package com.saucelabs.saucebindings.examples;

import com.saucelabs.saucebindings.VisualSession;
import com.saucelabs.saucebindings.options.SauceOptions;
import com.saucelabs.saucebindings.options.VisualOptions;
import org.junit.Test;
import org.openqa.selenium.remote.RemoteWebDriver;

public class VisualOptionsTest {

    @Test
    public void visualOptions() {

        // 1. Create Sauce Options object with desired test settings
        // Note: setting the test name is required
        SauceOptions sauceOptions = SauceOptions.chrome()
                .setName("Example Visual Options Test")
                .build();

        // 2. Create Visual Options instance with Sauce Options instance
        VisualOptions visualOptions = new VisualOptions(sauceOptions);

        // 3. Set desired visual options
        visualOptions.setFailOnNewStates(false);

        // 4. Create Visual Session instance with Visual Options instance
        VisualSession session = new VisualSession(visualOptions);

        // 5. Start Session to get the driver
        RemoteWebDriver driver = session.start();

        // 6. Use the driver in your tests just like normal
        driver.get("https://www.saucedemo.com/");

        // 7. Take snapshot
        session.takeSnapshot("Name of Snapshot");

        // 8. Stop the Session
        session.stop();
    }
}
