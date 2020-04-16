package com.saucelabs.saucebindings.acceptance;

import com.saucelabs.saucebindings.*;
import org.junit.After;
import org.junit.Test;
import org.openqa.selenium.remote.RemoteWebDriver;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

public class SauceEmuSimBrowserTest {
    private SauceSession session = new SauceSession();
    private RemoteWebDriver driver;

    @After
    public void tearDown() {
        session.stop(true);
    }

    @Test
    public void defaultsToLegacy() {
        driver = session.start();

        assertNotNull(driver);
        assertTrue(session.getSauceUrl().toString().contains("ondemand.saucelabs"));
    }

    @Test
    public void runsUSWest() {
        session.setDataCenter(DataCenter.US_WEST);
        driver = session.start();

        assertNotNull(driver);
        assertTrue(session.getSauceUrl().toString().contains("us-west-1"));
    }

    @Test
    public void runsEUCentral() {
        session.setDataCenter(DataCenter.EU_CENTRAL);
        driver = session.start();

        assertNotNull(driver);
        assertTrue(session.getSauceUrl().toString().contains("eu-central-1"));
    }

    @Test
    public void androidEmulatorCase() {
        SauceAndroidOptions options = new SauceAndroidOptions("Android Emulator", "9.0", DeviceOrientation.PORTRAIT);
        options.setBrowserName(Browser.CHROME);
        session = new SauceSession(options);
        driver = session.start();

        assertNotNull(driver);
        assertTrue(session.getSauceUrl().toString().contains("us-west-1"));
    }
}
