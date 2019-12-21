package com.saucelabs.simplesauce;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.contrib.java.lang.system.EnvironmentVariables;
import org.openqa.selenium.MutableCapabilities;
import org.openqa.selenium.firefox.FirefoxOptions;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.*;
import static org.openqa.selenium.PageLoadStrategy.EAGER;
import static org.openqa.selenium.UnexpectedAlertBehaviour.ACCEPT_AND_NOTIFY;

public class SauceOptionsTest {
    private SauceOptions sauceOptions;

    @Rule
    public final EnvironmentVariables environmentVariables = new EnvironmentVariables();

    @Before
    public void setUp() {
        environmentVariables.set("BUILD_TAG", " ");
        environmentVariables.set("BUILD_NAME", "TEMP BUILD");
        environmentVariables.set("BUILD_NUMBER", "11");

        sauceOptions = new SauceOptions();
    }

    @Test
    public void usesLatestChromeWindowsVersions() {
        assertEquals("chrome", sauceOptions.getBrowserName());
        assertEquals("Windows 10", sauceOptions.getPlatformName());
        assertEquals("latest", sauceOptions.getBrowserVersion());
    }

    @Test
    public void acceptsBrowserVersionPlatform() {
        sauceOptions.setBrowserName("firefox");
        sauceOptions.setPlatformName("macOS 10.14");
        sauceOptions.setBrowserVersion("68");

        assertEquals("firefox", sauceOptions.getBrowserName());
        assertEquals("macOS 10.14", sauceOptions.getPlatformName());
        assertEquals("68", sauceOptions.getBrowserVersion());
    }

    @Test
    public void acceptsW3CSettings() {
        sauceOptions.setAcceptInsecureCerts(true);
        sauceOptions.setPageLoadStrategy(EAGER);

        assertEquals(true, sauceOptions.getAcceptInsecureCerts());
        assertEquals(EAGER, sauceOptions.getPageLoadStrategy());
    }

    @Test
    public void acceptsSauceLabsSettings() {
        sauceOptions.setMaxDuration(1);
        sauceOptions.setCommandTimeout(2);

        assertEquals(Integer.valueOf(1), sauceOptions.getMaxDuration());
        assertEquals(Integer.valueOf(2), sauceOptions.getCommandTimeout());
    }

    @Test
    public void acceptsSeleniumSettings() {
        FirefoxOptions firefoxOptions = new FirefoxOptions();
        firefoxOptions.addArguments("--foo");
        firefoxOptions.addPreference("foo", "bar");
        firefoxOptions.setUnhandledPromptBehaviour(ACCEPT_AND_NOTIFY);

        sauceOptions.setSeleniumOptions(firefoxOptions);

        assertEquals(firefoxOptions, sauceOptions.getSeleniumOptions());
    }

    @Test
    public void parsesW3CAndSauceAndSeleniumSettings() {
        // w3c options
        sauceOptions.setPlatformName("macOS 10.14");
        sauceOptions.setBrowserVersion("68");
        sauceOptions.setAcceptInsecureCerts(true);
        sauceOptions.setPageLoadStrategy(EAGER);

        // sauce options
        sauceOptions.setMaxDuration(1);
        sauceOptions.setCommandTimeout(2);

        // Selenium browser options
        FirefoxOptions firefoxOptions = new FirefoxOptions();
        firefoxOptions.addArguments("--foo");
        firefoxOptions.addPreference("foo", "bar");

        // Selenium w3c options
        firefoxOptions.setUnhandledPromptBehaviour(ACCEPT_AND_NOTIFY);
        sauceOptions.setSeleniumOptions(firefoxOptions);

        Map capabilities = sauceOptions.toCapabilities().toJson();

        // Validate w3c options
        assertEquals("firefox", capabilities.get("browserName"));
        assertEquals("macOS 10.14", capabilities.get("platformName"));
        assertEquals("68", capabilities.get("browserVersion"));
        assertEquals(EAGER, capabilities.get("pageLoadStrategy"));
        assertEquals(ACCEPT_AND_NOTIFY, capabilities.get("unhandledPromptBehavior"));

        // Validate Sauce options
        MutableCapabilities sauceCapabilities = (MutableCapabilities) capabilities.get("sauce:options");
        assertEquals(1, sauceCapabilities.getCapability("maxDuration"));
        assertEquals(2, sauceCapabilities.getCapability("commandTimeout"));

        // Validate Selenium options
        Map firefoxCapabilities = (Map) capabilities.get("moz:firefoxOptions");

        ArrayList<String> expectedArgs = new ArrayList<>();
        expectedArgs.add("--foo");
        assertEquals(expectedArgs, firefoxCapabilities.get("args"));

        Map<String, Object> expectedPrefs = new HashMap<>();
        expectedPrefs.put("foo", "bar");
        assertEquals(expectedPrefs, firefoxCapabilities.get("prefs"));
    }

    @Test
    public void createsDefaultBuildName() {
        assertEquals("TEMP BUILD: 11", sauceOptions.getBuild());
    }
}
