package com.saucelabs.saucebindings.examples;

import com.deque.html.axecore.selenium.AxeBuilder;
import com.saucelabs.saucebindings.SauceSession;
import org.junit.Test;
import org.openqa.selenium.remote.RemoteWebDriver;

public class AccessibilityTest {

    @Test
    public void startSession() {
        // 1. Create Session object with the defaults
        SauceSession session = new SauceSession();

        // 2. Start Session to get the Driver
        RemoteWebDriver driver = session.start();

        // 3. Use the driver in your tests just like normal
        driver.get("https://www.saucedemo.com/");

        // 4a. Get accessibility default results with frame support
        session.getAccessibilityResults();

        // 4b. Get accessibility default results without frame support
        session.getAccessibilityResults(false);

        // 4c. Get accessibility default results with Deque Builder instance
        //     Options for configuring AxeBuilder are here: https://github.com/dequelabs/axe-core-maven-html#:~:text=axebuilder
        AxeBuilder builder = new AxeBuilder();
        session.getAccessibilityResults(builder);

        // 5. Stop the Session with whether the test passed or failed
        session.stop(true);
    }
}
