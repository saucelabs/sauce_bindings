package com.saucelabs.simplesauce;

import org.junit.Ignore;
import org.junit.Test;
import org.openqa.selenium.MutableCapabilities;
import org.openqa.selenium.PageLoadStrategy;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.ie.InternetExplorerOptions;
import org.openqa.selenium.remote.BrowserType;
import org.openqa.selenium.safari.SafariOptions;

import java.util.HashMap;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.spy;
import static org.openqa.selenium.UnexpectedAlertBehaviour.DISMISS;

public class SauceOptionsTest extends BaseConfigurationTest{
    @Test
    public void usesLatestChromeWindowsVersions() {
        assertEquals(BrowserType.CHROME, sauceOptions.getBrowserName());
        assertEquals("Windows 10", sauceOptions.getPlatformName());
        assertEquals("latest", sauceOptions.getBrowserVersion());
    }

    @Test
    public void updatesBrowserBrowserVersionPlatformVersionValues() {
        sauceOptions.setBrowserName(BrowserType.FIREFOX);
        sauceOptions.setPlatformName(Platforms.MAC_OS_HIGH_SIERRA.getOsVersion());
        sauceOptions.setBrowserVersion("68");

        assertEquals(BrowserType.FIREFOX, sauceOptions.getBrowserName());
        assertEquals("68", sauceOptions.getBrowserVersion());
        assertEquals("macOS 10.13", sauceOptions.getPlatformName());
    }

    @Test
    @Ignore("Not Implemented Yet")
    public void acceptsOtherW3CValues() {
    }

    @Test
    @Ignore("Not Implemented Yet")
    public void acceptsSauceLabsSettings() {
    }

    @Test
    public void acceptsChromeOptionsClass() {
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.addArguments("--foo");
        chromeOptions.setUnhandledPromptBehaviour(DISMISS);

        sauceOptions = new SauceOptions(chromeOptions);

        assertEquals("chrome", sauceOptions.getBrowserName());
        assertEquals(chromeOptions, sauceOptions.getSeleniumCapabilities());
    }

    @Test
    public void acceptsEdgeOptionsClass() {
        EdgeOptions edgeOptions = new EdgeOptions();
        edgeOptions.setPageLoadStrategy("eager");

        sauceOptions = new SauceOptions(edgeOptions);

        assertEquals("MicrosoftEdge", sauceOptions.getBrowserName());
        assertEquals(edgeOptions, sauceOptions.getSeleniumCapabilities());
    }

    @Test
    public void acceptsFirefoxOptionsClass() {
        FirefoxOptions firefoxOptions = new FirefoxOptions();
        firefoxOptions.addArguments("--foo");
        firefoxOptions.addPreference("foo", "bar");
        firefoxOptions.setUnhandledPromptBehaviour(DISMISS);

        sauceOptions = new SauceOptions(firefoxOptions);

        assertEquals("firefox", sauceOptions.getBrowserName());
        assertEquals(firefoxOptions, sauceOptions.getSeleniumCapabilities());
    }

    @Test
    public void acceptsInternetExplorerOptionsClass() {
        InternetExplorerOptions internetExplorerOptions = new InternetExplorerOptions();
        internetExplorerOptions.requireWindowFocus();
        internetExplorerOptions.setPageLoadStrategy(PageLoadStrategy.EAGER);
        internetExplorerOptions.setUnhandledPromptBehaviour(DISMISS);

        sauceOptions = new SauceOptions(internetExplorerOptions);

        assertEquals("internet explorer", sauceOptions.getBrowserName());
        assertEquals(internetExplorerOptions, sauceOptions.getSeleniumCapabilities());
    }

    @Test
    public void acceptsSafariOptionsClass() {
        SafariOptions safariOptions = new SafariOptions();
        safariOptions.setAutomaticInspection(true);
        safariOptions.setAutomaticProfiling(true);

        sauceOptions = new SauceOptions(safariOptions);

        assertEquals("safari", sauceOptions.getBrowserName());
        assertEquals(safariOptions, sauceOptions.getSeleniumCapabilities());
    }

    @Test
    @Ignore("Not Implemented Yet")
    public void setsCapabilitiesFromMap() {
    }

    @Test
    public void allowsBuildToBeSet() {
        sauceOptions.setBuild("Manual Build Set");
        assertEquals("Manual Build Set", sauceOptions.getBuild());
    }

    @Test
    public void createsDefaultBuildName() {
        SauceOptions sauceOptions = spy(new SauceOptions());
        doReturn("Not Empty").when(sauceOptions).getEnvironmentVariable("BUILD_TAG");
        doReturn("TEMP BUILD").when(sauceOptions).getEnvironmentVariable("BUILD_NAME");
        doReturn("11").when(sauceOptions).getEnvironmentVariable("BUILD_NUMBER");

        assertEquals("TEMP BUILD: 11", sauceOptions.getBuild());
    }

    // TODO: This needs to get fleshed out a lot more
    @Test
    public void parsesW3CAndSauceAndSeleniumSettings() {
        FirefoxOptions firefoxOptions = new FirefoxOptions();
        firefoxOptions.addArguments("--foo");
        firefoxOptions.addPreference("foo", "bar");
        firefoxOptions.setUnhandledPromptBehaviour(DISMISS);

        sauceOptions = new SauceOptions(firefoxOptions);

        sauceOptions.setBrowserName(BrowserType.FIREFOX);
        sauceOptions.setPlatformName(Platforms.MAC_OS_HIGH_SIERRA.getOsVersion());
        sauceOptions.setBrowserVersion("68");

        MutableCapabilities expectedCaps = new MutableCapabilities();
        expectedCaps.setCapability("acceptInsecureCerts", true);
        expectedCaps.setCapability("browserName", "firefox");
        expectedCaps.setCapability("browserVersion", "68");
        expectedCaps.setCapability("platformName", "macOS 10.13");
        expectedCaps.setCapability("unhandledPromptBehavior", DISMISS);
        expectedCaps.setCapability("sauce:options", new HashMap<>());
        expectedCaps.setCapability("moz:firefoxOptions", firefoxOptions.toJson().get("moz:firefoxOptions"));

        MutableCapabilities actualCaps = sauceOptions.toCapabilities();
        assertEquals(expectedCaps, actualCaps);
    }
}
