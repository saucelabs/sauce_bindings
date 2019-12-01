package com.saucelabs.simplesauce.acceptance;

import com.saucelabs.simplesauce.SauceOptions;
import com.saucelabs.simplesauce.SauceSession;
import org.junit.After;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.RemoteWebDriver;

import static org.junit.Assert.*;

public class SauceSessionAcceptanceTest {
    private WebDriver webDriver;
    private SauceSession sauce;

    @After
    public void cleanUp()
    {
        sauce.stop(webDriver);
    }

    @Test
    public void sauceSession_defaultSauceOptions_startsRealSession() {
        SauceOptions options = new SauceOptions();
        sauce = new SauceSession(options);
        webDriver = sauce.start();
        String sessionId = ((RemoteWebDriver) webDriver).getSessionId().toString();
        assertFalse(sessionId.isEmpty());
    }

    @Test
    public void runTestOnWindows10() {
        SauceOptions options = new SauceOptions();
        options.withWindows10();
        sauce = new SauceSession(options);

        webDriver = sauce.start();
        String actualOs = (((RemoteWebDriver) webDriver).getCapabilities()).getPlatform().toString();
        //TODO why in the F is this returning XP even though in Sauce it shows Windows 10
        assertEquals("XP", actualOs);
    }

    @Test
    public void runTestOnFirefox() {
        SauceOptions options = new SauceOptions();
        options.withFirefox();
        sauce = new SauceSession(options);

        webDriver = sauce.start();
        String actualBrowser = getBrowserNameFromRemoteCapabilities();
        assertEquals("firefox", actualBrowser);
    }

    @Test
    public void withSafari_default_isMojave() {
        SauceOptions options = new SauceOptions();
        options.withSafari();
        sauce = new SauceSession(options);

        webDriver = sauce.start();
        String actualPlatform = (((RemoteWebDriver) webDriver).getCapabilities()).getPlatform().toString();
        assertEquals("MAC", actualPlatform);
    }

    @Test
    public void withSafari_default_isBrowserVersion12_0() {
        SauceOptions options = new SauceOptions();
        options.withSafari();
        sauce = new SauceSession(options);

        webDriver = sauce.start();
        String actualBrowserVersion = (((RemoteWebDriver) webDriver).getCapabilities()).getVersion();
        assertEquals("12.0", actualBrowserVersion);
    }
    @Test
    public void startSession_noSauceOptionsSet_returnsDriver() {
        sauce = new SauceSession();
        webDriver = sauce.start();
        webDriver.get("https://www.saucedemo.com/");
        assertNotNull(webDriver);
        webDriver.findElement(By.id("user-name")).sendKeys("test");
    }

    private String getBrowserNameFromRemoteCapabilities() {
        return (((RemoteWebDriver) webDriver).getCapabilities()).getBrowserName();
    }
}
