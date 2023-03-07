package com.saucelabs.saucebindings.examples;

import com.saucelabs.saucebindings.VisualSession;
import org.junit.Test;
import org.openqa.selenium.remote.RemoteWebDriver;

public class VisualTest {

    @Test
    public void visualSession() {

        // 1. Create Visual Session object with minimum required
        VisualSession session = new VisualSession("Example test name");

        // 2. Start Session to get the driver
        RemoteWebDriver driver = session.start();

        // 3. Use the driver in your tests just like normal
        driver.get("https://www.saucedemo.com/");

        // 4. Take snapshot
        session.takeSnapshot("Name of Snapshot");

        // 5. Stop the Session
        session.stop();
    }
}
