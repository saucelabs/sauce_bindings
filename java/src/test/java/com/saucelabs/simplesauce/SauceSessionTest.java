package com.saucelabs.simplesauce;

import org.junit.Rule;
import org.junit.Test;
import org.openqa.selenium.MutableCapabilities;

import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;

import static org.junit.Assert.*;
import static org.openqa.selenium.UnexpectedAlertBehaviour.ACCEPT_AND_NOTIFY;

import org.junit.contrib.java.lang.system.EnvironmentVariables;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class SauceSessionTest {
    private SauceSession sauceSession;
    private String actualUsername = System.getenv("SAUCE_USERNAME");
    private String actualAccessKey = System.getenv("SAUCE_ACCESS_KEY");

    @Rule
    public final EnvironmentVariables environmentVariables = new EnvironmentVariables()
            .set("BUILD_TAG", " ")
            .set("BUILD_NAME", "TEMP BUILD")
            .set("BUILD_NUMBER", "11")
            .set("SAUCE_USERNAME", "user-name")
            .set("SAUCE_ACCESS_KEY", "1234");

    @Test
    public void sauceSessionCreatesDefaultSauceOptions() {
        sauceSession = new SauceSession();
        SauceOptions sauceOptions = sauceSession.getSauceOptions();

        assertNotNull(sauceOptions);
        assertEquals("chrome", sauceOptions.getBrowserName());
        assertEquals("Windows 10", sauceOptions.getPlatformName());
        assertEquals("latest", sauceOptions.getBrowserVersion());
        assertEquals("TEMP BUILD: 11", sauceOptions.getBuild());
    }

    @Test
    public void sauceSessionUsesProvidedSauceOptions() {
        // Selenium browser options
        FirefoxOptions firefoxOptions = new FirefoxOptions();
        firefoxOptions.addArguments("--foo");
        firefoxOptions.addPreference("foo", "bar");

        // Selenium w3c options
        firefoxOptions.setUnhandledPromptBehaviour(ACCEPT_AND_NOTIFY);

        SauceOptions options = new SauceOptions(firefoxOptions);

        // w3c options
        options.setPlatformName("macOS 10.14");
        options.setBrowserVersion("68");
        options.setAcceptInsecureCerts(true);
        options.setPageLoadStrategy("eager");

        // sauce options
        options.setMaxDuration(1);
        options.setCommandTimeout(2);

        sauceSession = new SauceSession(options);

        SauceOptions sauceOptions = sauceSession.getSauceOptions();
        Map capabilities = sauceOptions.toCapabilities().toJson();

        // Validate w3c options
        assertEquals("firefox", capabilities.get("browserName"));
        assertEquals("macOS 10.14", capabilities.get("platformName"));
        assertEquals("68", capabilities.get("browserVersion"));
        assertEquals("eager", capabilities.get("pageLoadStrategy"));
        assertEquals(ACCEPT_AND_NOTIFY, capabilities.get("unhandledPromptBehavior"));

        // Validate Sauce options
        MutableCapabilities sauceCapabilities = (MutableCapabilities) capabilities.get("sauce:options");
        assertEquals(1, sauceCapabilities.getCapability("maxDuration"));
        assertEquals(2, sauceCapabilities.getCapability("commandTimeout"));
        assertEquals("TEMP BUILD: 11", sauceCapabilities.getCapability("build"));

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
    public void defaultsToUSWestDataCenter() throws MalformedURLException {
        sauceSession = new SauceSession();
        String expectedUrl = "https://user-name:1234@ondemand.us-west-1.saucelabs.com:443/wd/hub";
        assertEquals(sauceSession.getSauceUrl().toString(), expectedUrl);
    }

    @Test
    public void setsDataCenter() throws MalformedURLException {
        sauceSession = new SauceSession();
        sauceSession.setSauceDataCenter(SauceDataCenter.EU_CENTRAL);

        String expectedUrl = "https://user-name:1234@ondemand.eu-central-1.saucelabs.com:443/wd/hub";
        assertEquals(sauceSession.getSauceUrl().toString(), expectedUrl);
    }

    @Test
    public void setsSauceURLDirectly() throws MalformedURLException {
        sauceSession = new SauceSession();

        sauceSession.setSauceUrl(new URL("http://example.com"));
        String expectedUrl = "http://example.com";
        assertEquals(expectedUrl, sauceSession.getSauceUrl().toString());
    }

    @Test
    public void setsSauceUsernameAndAccessKey() throws MalformedURLException {
        sauceSession = new SauceSession();
        sauceSession.setSauceUsername("me");
        sauceSession.setSauceAccessKey("321");

        String expectedUrl = "https://me:321@ondemand.us-west-1.saucelabs.com:443/wd/hub";
        assertEquals(expectedUrl, sauceSession.getSauceUrl().toString());
    }

    @Test
    public void startThrowsErrorWithoutUsername() throws MalformedURLException {
        environmentVariables.clear("SAUCE_USERNAME");
        sauceSession = new SauceSession();

        try {
            sauceSession.start();
            fail("Expected a SauceEnvironmentVariablesNotSetException to be thrown");
        } catch (SauceEnvironmentVariablesNotSetException exception) {
            assertEquals("Sauce Username was not provided", exception.getMessage());
        }
    }

    @Test
    public void startThrowsErrorWithoutAccessKey() throws MalformedURLException {
        environmentVariables.clear("SAUCE_ACCESS_KEY");
        sauceSession = new SauceSession();

        try {
            sauceSession.start();
            fail("Expected a SauceEnvironmentVariablesNotSetException to be thrown");
        } catch (SauceEnvironmentVariablesNotSetException exception) {
            assertEquals("Sauce Access Key was not provided", exception.getMessage());
        }
    }

    // TODO: This needs to get mocked
    @Test
    public void startsSessionAndReturnsSeleniumDriver() throws MalformedURLException {
        sauceSession = new SauceSession();
        sauceSession.setSauceUsername(actualUsername);
        sauceSession.setSauceAccessKey(actualAccessKey);

        WebDriver driver = sauceSession.start();
        assertNotNull(driver);

        driver.quit();
    }

    // TODO: This needs to get mocked
    @Test
    public void startsSessionWithProvidedOptions() throws MalformedURLException {
        SauceOptions sauceOptions = new SauceOptions();
        sauceOptions.setBrowserName("firefox");
        sauceOptions.setPlatformName("macOS 10.14");
        sauceOptions.setBrowserVersion("68");

        sauceSession = new SauceSession(sauceOptions);
        sauceSession.setSauceUsername(actualUsername);
        sauceSession.setSauceAccessKey(actualAccessKey);

        RemoteWebDriver driver = (RemoteWebDriver) sauceSession.start();
        MutableCapabilities capabilities = (MutableCapabilities) driver.getCapabilities();

        assertEquals("firefox", capabilities.getBrowserName());
        assertEquals("68.0", capabilities.getCapability("browserVersion"));
        assertEquals(Platform.MAC, capabilities.getCapability("platformName"));
        driver.quit();
    }

    // TODO: This needs to get mocked
    @Test
    public void stopsSessionWithPassingResult() throws MalformedURLException {
        sauceSession = new SauceSession();
        sauceSession.setSauceUsername(actualUsername);
        sauceSession.setSauceAccessKey(actualAccessKey);

        sauceSession.start();
        sauceSession.stop(TestResult.PASS);

        boolean isNotMocked = true;
        assertTrue(isNotMocked);
    }

    // TODO: This needs to get mocked
    @Test
    public void stopsSessionWithFailingResult() throws MalformedURLException {
        sauceSession = new SauceSession();
        sauceSession.setSauceUsername(actualUsername);
        sauceSession.setSauceAccessKey(actualAccessKey);

        sauceSession.start();
        sauceSession.stop(TestResult.FAIL);

        boolean isNotMocked = true;
        assertTrue(isNotMocked);
    }
}
