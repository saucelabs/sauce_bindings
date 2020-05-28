package com.saucelabs.saucebindings.integration;

import com.saucelabs.saucebindings.DataCenter;
import com.saucelabs.saucebindings.SauceOptions;
import com.saucelabs.saucebindings.SaucePlatform;
import com.saucelabs.saucebindings.SauceSession;
import org.junit.Rule;
import org.junit.Test;
import org.openqa.selenium.remote.RemoteWebDriver;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

public class DesktopBrowserTest {
    private SauceSession session = new SauceSession();
    private RemoteWebDriver webDriver;

    @Rule
    public SauceTestWatcher testWatcher = new SauceTestWatcher();

    @Test
    public void defaultsToUSWest() {
        testWatcher.setSauceSession(session);

        webDriver = session.start();

        assertNotNull(webDriver);
        assertTrue(session.getSauceUrl().toString().contains("us-west-"));
    }

    @Test
    public void runsUSEast() {
        SauceOptions options = new SauceOptions();
        options.setPlatformName(SaucePlatform.LINUX);
        session = new SauceSession(options);

        session.setDataCenter(DataCenter.US_EAST);
        testWatcher.setSauceSession(session);

        webDriver = session.start();

        assertNotNull(webDriver);
        assertTrue(session.getSauceUrl().toString().contains("us-east-1"));
    }

    @Test
    public void runsEUCentral() {
        session.setDataCenter(DataCenter.EU_CENTRAL);
        testWatcher.setSauceSession(session);

        webDriver = session.start();

        assertNotNull(webDriver);
        assertTrue(session.getSauceUrl().toString().contains("eu-central-1"));
    }
}
