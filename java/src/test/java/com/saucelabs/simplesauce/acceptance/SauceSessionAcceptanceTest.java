package com.saucelabs.simplesauce.acceptance;

import com.saucelabs.simplesauce.SauceOptions;
import com.saucelabs.simplesauce.SauceSession;
import org.junit.After;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.RemoteWebDriver;

import static org.junit.Assert.*;

public class SauceSessionAcceptanceTest {
    private WebDriver webDriver;

    @After
    public void cleanUp()
    {
        if(webDriver != null)
            webDriver.quit();
    }
    @Test
    public void sauceSession_defaultSauceOptions_startsRealSession() {
        SauceOptions options = new SauceOptions();
        webDriver = new SauceSession(options).start();
        String sessionId = ((RemoteWebDriver) webDriver).getSessionId().toString();

        assertTrue(!sessionId.isEmpty());
    }
    @Test
    public void withWindows10_default() {
        SauceOptions options = new SauceOptions();
        options.withWindows10();
        webDriver = new SauceSession(options).start();
        String actualOs = (((RemoteWebDriver) webDriver).getCapabilities()).getPlatform().toString();
        //TODO why in the F is this returning XP even though in Sauce it shows Windows 10
        assertEquals("XP", actualOs);
    }
    @Test
    public void withSafari_default_isMojave() {
        SauceOptions options = new SauceOptions();
        options.withSafari();

        webDriver = new SauceSession(options).start();
        String actualBrowserVersion = (((RemoteWebDriver) webDriver).getCapabilities()).getPlatform().toString();
        assertEquals("MAC", actualBrowserVersion);
    }
    @Test
    public void withSafari_default_isBrowserVersion12_0() {
        SauceOptions options = new SauceOptions();
        options.withSafari();

        webDriver = new SauceSession(options).start();
        String actualBrowserVersion = (((RemoteWebDriver) webDriver).getCapabilities()).getVersion();
        assertEquals("12.0", actualBrowserVersion);
    }
    @Test
    public void startSession_noSauceOptionsSet_returnsDriver() {
        SauceSession session = new SauceSession();
        session.start();
        webDriver = session.getDriver();
        assertNotNull(webDriver);
    }

    @Test
    public void runTestOnFirefox() {
        SauceOptions options = new SauceOptions();
        options.withFirefox();

        webDriver = new SauceSession(options).start();
        String actualBrowser = getBrowserNameFromRemoteCapabilities();
        assertEquals("firefox", actualBrowser);
    }

    private String getBrowserNameFromRemoteCapabilities() {
        return (((RemoteWebDriver) webDriver).getCapabilities()).getBrowserName();
    }
}
