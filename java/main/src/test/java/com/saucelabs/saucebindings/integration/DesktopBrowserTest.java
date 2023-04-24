package com.saucelabs.saucebindings.integration;

import com.saucelabs.saucebindings.DataCenter;
import com.saucelabs.saucebindings.SaucePlatform;
import com.saucelabs.saucebindings.SauceSession;
import com.saucelabs.saucebindings.options.SauceOptions;
import org.junit.After;
import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.remote.RemoteWebDriver;

public class DesktopBrowserTest {
    private SauceSession session = new SauceSession();
    private RemoteWebDriver driver;

    @After
    public void cleanUp() {
        if (session != null) {
            session.stop(true);
        }
    }

    @Test
    public void defaultsToUSWest() {
        driver = session.start();
        Assert.assertNotNull(driver);
        Assert.assertTrue(session.getSauceUrl().toString().contains("us-west-"));
    }

    @Test
    public void runsUSEast() {
        SauceOptions options = new SauceOptions();
        options.setPlatformName(SaucePlatform.LINUX);
        session = new SauceSession(options);
        session.setDataCenter(DataCenter.US_EAST);
        driver = session.start();

        Assert.assertNotNull(driver);
        Assert.assertTrue(session.getSauceUrl().toString().contains("us-east-1"));
    }

    @Test
    public void runsAPACSoutheast() {
        session.setDataCenter(DataCenter.APAC_SOUTHEAST);
        driver = session.start();

        Assert.assertNotNull(driver);
        Assert.assertTrue(session.getSauceUrl().toString().contains("apac-southeast"));
    }

    @Test
    public void runsEUCentral() {
        session.setDataCenter(DataCenter.EU_CENTRAL);
        driver = session.start();

        Assert.assertNotNull(driver);
        Assert.assertTrue(session.getSauceUrl().toString().contains("eu-central-1"));
    }

    @Test
    public void storesResultOfFirstStop() {
        session.start();

        session.stop(true);
        session.stop(false);

        Assert.assertEquals("true", session.getResult());
    }

    @Test
    public void nullsDriver() {
        driver = session.start();
        session.stop(true);

        Assert.assertNull(session.getDriver());
    }

    @Test
    public void stopsNetwork() {
        session = new SauceSession(SauceOptions.safari());
        driver = session.start();

        session.stopNetwork();

        driver.get("https://www.saucedemo.com");

        Assert.assertEquals("Failed to open page", driver.getTitle());

        session.startNetwork();
        driver.get("https://www.saucedemo.com");

        Assert.assertEquals("Swag Labs", driver.getTitle());
    }
}
