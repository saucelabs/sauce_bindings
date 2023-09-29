package com.saucelabs.saucebindings.integration;

import com.saucelabs.saucebindings.DataCenter;
import com.saucelabs.saucebindings.SauceSession;
import com.saucelabs.saucebindings.options.SauceOptions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.remote.RemoteWebDriver;

public class DesktopBrowserTest {
    private SauceSession session = new SauceSession();
    private RemoteWebDriver driver;

    @AfterEach
    public void cleanUp() {
        if (session != null) {
            session.stop(true);
        }
    }

    @Test
    public void defaultsToUSWest() {
        driver = session.start();
        Assertions.assertNotNull(driver);
        Assertions.assertTrue(session.getSauceUrl().toString().contains(DataCenter.US_WEST.toString()));
    }

    @Test
    public void runsEUCentral() {
        session.setDataCenter(DataCenter.EU_CENTRAL);
        driver = session.start();

        Assertions.assertNotNull(driver);
        Assertions.assertTrue(session.getSauceUrl().toString().contains(DataCenter.EU_CENTRAL.toString()));
    }

    @Test
    public void storesResultOfFirstStop() {
        session.start();

        session.stop(true);
        session.stop(false);

        Assertions.assertEquals("passed", session.getResult());
    }

    @Test
    public void nullsDriver() {
        driver = session.start();
        session.stop(true);

        Assertions.assertNull(session.getDriver());
    }

    @Test
    public void stopsNetwork() {
        session = new SauceSession(SauceOptions.safari());
        driver = session.start();

        session.stopNetwork();

        driver.get("https://www.saucedemo.com");

        Assertions.assertEquals("Failed to open page", driver.getTitle());

        session.startNetwork();
        driver.get("https://www.saucedemo.com");

        Assertions.assertEquals("Swag Labs", driver.getTitle());
    }
}
