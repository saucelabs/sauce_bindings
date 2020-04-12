package com.saucelabs.saucebindings.acceptance;

import com.saucelabs.saucebindings.DataCenter;
import com.saucelabs.saucebindings.SauceOptions;
import com.saucelabs.saucebindings.SauceSession;
import org.junit.After;
import org.junit.Test;
import org.openqa.selenium.remote.RemoteWebDriver;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

public class SauceDesktopTest {
    private SauceSession session = new SauceSession();
    private RemoteWebDriver webDriver;

    @After
    public void cleanUp() {
        session.stop(true);
    }

    @Test
    public void defaultsToLegacy() {
        webDriver = session.start();
        assertNotNull(webDriver);
        assertTrue(session.getSauceUrl().toString().contains("ondemand.saucelabs"));
    }

    @Test
    public void runsUSWest() {
        session.setDataCenter(DataCenter.US_WEST);
        webDriver = session.start();

        assertNotNull(webDriver);
        assertTrue(session.getSauceUrl().toString().contains("us-west-1"));
    }

    @Test
    public void runsEUCentral() {
        session.setDataCenter(DataCenter.EU_CENTRAL);
        webDriver = session.start();

        assertNotNull(webDriver);
        assertTrue(session.getSauceUrl().toString().contains("eu-central-1"));
    }

    // Same as US_EAST just more descriptive
    @Test
    public void createsHeadlessDriver() {
        SauceOptions sauceOptions = SauceOptions.headless();
        session = new SauceSession(sauceOptions);
        session.setDataCenter(DataCenter.HEADLESS);
        webDriver = session.start();

        assertNotNull(webDriver);
        assertTrue(session.getSauceUrl().toString().contains("us-east-1"));
    }
}
