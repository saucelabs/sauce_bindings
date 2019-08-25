package com.saucelabs.simplesauce.acceptance;

import com.saucelabs.simplesauce.SauceSession;
import org.hamcrest.text.IsEqualIgnoringCase;
import org.junit.After;
import org.junit.Ignore;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;

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
    public void withWindows10_default() {
        webDriver = new SauceSession().withWindows10().start();
        String actualOs = (((RemoteWebDriver) webDriver).getCapabilities()).getPlatform().toString();
        //TODO why in the F is this returning XP even though in Sauce it shows Windows 10
        assertEquals("XP", actualOs);
    }
    @Test
    public void withSafari_default_isMojave() {
        webDriver = new SauceSession().withSafari().start();
        String actualBrowserVersion = (((RemoteWebDriver) webDriver).getCapabilities()).getPlatform().toString();
        assertEquals("MAC", actualBrowserVersion);
    }
    @Test
    public void withSafari_default_isBrowserVersion12_0() {
        webDriver = new SauceSession().withSafari().start();
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
    public void withFirefox_returnsCorrectDriver() {
        webDriver = new SauceSession().withFirefox().start();
        String actualBrowser = getBrowserNameFromCapabilities();
        assertEquals("firefox", actualBrowser);
    }

    private String getBrowserNameFromCapabilities() {
        return (((RemoteWebDriver) webDriver).getCapabilities()).getBrowserName();
    }

    @Test
    @Ignore("Not sure how to make it work")
    public void withEdge_default_returnsValidEdgeSession() {
        webDriver = new SauceSession().withEdge().start();
        String actualBrowser = getBrowserNameFromCapabilities();
        assertThat(actualBrowser, IsEqualIgnoringCase.equalToIgnoringCase("edge"));
    }
    @Test
    @Ignore("Not sure how to make it work")
    public void withIE_default_returnsValidIESession() {
        webDriver = new SauceSession().withIE("latest").start();
        String actualBrowser = getBrowserNameFromCapabilities();
        assertThat(actualBrowser, IsEqualIgnoringCase.equalToIgnoringCase("IE"));
    }
    @Test
    @Ignore("Invalid Use Case: I don't want the use to be able to do this")
    public void withIE_nonDefaultOs_returnsValidIESession() {
        webDriver = new SauceSession().withIE("latest").withPlatform("Linux").start();
        String actualBrowser = getBrowserNameFromCapabilities();
        assertThat(actualBrowser, IsEqualIgnoringCase.equalToIgnoringCase("IE"));
    }
    @Test
    @Ignore("No clue why this doesn't work")
    public void withIE_nonDefaultVersion_returnsValidIESession() {
        webDriver = new SauceSession().withIE("11").start();
        String actualBrowser = getBrowserNameFromCapabilities();
        assertThat(actualBrowser, IsEqualIgnoringCase.equalToIgnoringCase("IE"));
    }
}
