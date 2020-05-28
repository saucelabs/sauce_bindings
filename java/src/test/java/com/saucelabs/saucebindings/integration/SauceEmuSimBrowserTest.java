package com.saucelabs.saucebindings.integration;

import com.saucelabs.saucebindings.DataCenter;
import com.saucelabs.saucebindings.SauceOptions;
import com.saucelabs.saucebindings.SauceSession;
import org.junit.Rule;
import org.junit.Test;
import org.openqa.selenium.remote.RemoteWebDriver;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

public class SauceEmuSimBrowserTest {
    private SauceOptions options;
    private SauceSession<RemoteWebDriver> session;
    private RemoteWebDriver driver;

    @Rule
    public SauceTestWatcher testWatcher = new SauceTestWatcher();

    @Test
    public void androidUSWest() {
        options = SauceOptions.android();
        session = new SauceSession<>(options);
        testWatcher.setSauceSession(session);
        driver = session.start();

        assertNotNull(driver);
        assertTrue(session.getSauceUrl().toString().contains("us-west-"));
    }

    @Test
    public void runsEUCentral() {
        options = SauceOptions.android();
        session = new SauceSession<>(options);
        session.setDataCenter(DataCenter.EU_CENTRAL);
        testWatcher.setSauceSession(session);
        driver = session.start();

        assertNotNull(driver);
        assertTrue(session.getSauceUrl().toString().contains("eu-central-1"));
    }

    @Test
    public void iosUSWest() {
        options = SauceOptions.ios();
        session = new SauceSession<>(options);
        testWatcher.setSauceSession(session);
        driver = session.start();

        assertNotNull(driver);
        assertTrue(session.getSauceUrl().toString().contains("us-west-"));
    }

    @Test
    public void iosEUCentral() {
        options = SauceOptions.ios();
        session = new SauceSession<>(options);
        session.setDataCenter(DataCenter.EU_CENTRAL);
        testWatcher.setSauceSession(session);
        driver = session.start();

        assertNotNull(driver);
        assertTrue(session.getSauceUrl().toString().contains("eu-central-1"));
    }
}
