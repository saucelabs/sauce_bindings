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
    public void cleanUp() {
        sauce.stop(true);
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
    public void startSession_noSauceOptionsSet_returnsDriver() {
        sauce = new SauceSession();
        webDriver = sauce.start();
        webDriver.get("https://www.saucedemo.com/");
        assertNotNull(webDriver);
        webDriver.findElement(By.id("user-name")).sendKeys("test");
    }
}
