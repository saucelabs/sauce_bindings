package com.saucelabs.saucebindings.acceptance;

import com.saucelabs.saucebindings.SauceMobileSession;
import org.junit.Test;
import org.openqa.selenium.remote.RemoteWebDriver;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

public class SauceEmuSimBrowserTest {
    private SauceMobileSession session = new SauceMobileSession();
    private RemoteWebDriver webDriver;

    @Test
    public void defaultsToLegacy() {
        webDriver = session.start();
        assertNotNull(webDriver);
        assertTrue(session.getSauceUrl().toString().contains("ondemand.saucelabs"));

        session.stop(true);
    }
}
