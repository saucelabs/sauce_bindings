package com.saucelabs.saucebindings.acceptance;

import com.saucelabs.saucebindings.DataCenter;
import com.saucelabs.saucebindings.SauceMobileSession;
import org.junit.After;
import org.junit.Test;
import org.openqa.selenium.remote.RemoteWebDriver;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

public class SauceEmuSimBrowserTest {
    private SauceMobileSession session = new SauceMobileSession();
    private RemoteWebDriver webDriver;

    @After
    public void tearDown() {
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
}
