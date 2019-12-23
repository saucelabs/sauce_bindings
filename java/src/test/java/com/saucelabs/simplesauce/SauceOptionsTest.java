package com.saucelabs.simplesauce;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.contrib.java.lang.system.EnvironmentVariables;
import org.openqa.selenium.MutableCapabilities;
import org.openqa.selenium.Proxy;
import org.openqa.selenium.firefox.FirefoxOptions;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;

import static org.openqa.selenium.UnexpectedAlertBehaviour.DISMISS;

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
    public void acceptsW3CSettings() {
        sauceOptions.setBrowserName("firefox");
        sauceOptions.setPlatformName("macOS 10.14");
        sauceOptions.setBrowserVersion("68");

        sauceOptions.setAcceptInsecureCerts(true);
        sauceOptions.setPageLoadStrategy("eager");
        Proxy proxy = new Proxy();
        sauceOptions.setProxy(proxy);
        sauceOptions.setSetWindowRect(true);
        Map<String, Integer> timeouts = new HashMap<>();
        timeouts.put("implicit", 1);
        timeouts.put("pageLoad", 10);
        timeouts.put("script", 5);
        sauceOptions.setTimeouts(timeouts);
        sauceOptions.setStrictFileInteractability(true);
        sauceOptions.setUnhandledPromptBehavior("accept");

        assertEquals("firefox", sauceOptions.getBrowserName());
        assertEquals("macOS 10.14", sauceOptions.getPlatformName());
        assertEquals("68", sauceOptions.getBrowserVersion());
        assertEquals(true, sauceOptions.getAcceptInsecureCerts());
        assertEquals("eager", sauceOptions.getPageLoadStrategy());
        assertEquals(proxy, sauceOptions.getProxy());
        assertTrue(sauceOptions.getSetWindowRect());
        assertEquals(timeouts, sauceOptions.getTimeouts());
        assertTrue(sauceOptions.getStrictFileInteractability());
        assertEquals("accept", sauceOptions.getUnhandledPromptBehavior());
    }

    @Test
    public void acceptsSauceLabsSettings() {
        sauceOptions.setAvoidProxy(true);
        sauceOptions.setCapturePerformance(true);
        sauceOptions.setChromedriverVersion("2");
        sauceOptions.setCommandTimeout(2);

        Map<String, String> data = new HashMap<>();
        data.put("foo", "bar");
        sauceOptions.setCustomData(data);
        sauceOptions.setExtendedDebugging(true);
        sauceOptions.setIdleTimeout(3);
        sauceOptions.setIedriverVersion("3");
        sauceOptions.setMaxDuration(4);
        sauceOptions.setName("Test Name");
        sauceOptions.setParentTunnel("Mommy");

        Map<String, Object> prerun = new HashMap<>();
        prerun.put("executable", "http://url.to/your/executable.exe");
        prerun.put("background", false);
        prerun.put("timeout", 7);
        List<String> args = new ArrayList<>();
        args.add("--silent");
        args.add("-a");
        prerun.put("args", args);

        sauceOptions.setPrerun(prerun);
        sauceOptions.setPrerunUrl("http://url.to/your/executable.exe");
        sauceOptions.setPriority(0);
        sauceOptions.setPublicRestricted("team");
        sauceOptions.setRecordLogs(false);
        sauceOptions.setRecordScreenshots(false);
        sauceOptions.setRecordVideo(false);
        sauceOptions.setScreenResolution("1280x1024");
        sauceOptions.setSeleniumVersion("3.141.5");

        List<String> tags = new ArrayList<>();
        tags.add("A");
        tags.add("B");

        sauceOptions.setTags(tags);
        sauceOptions.setTimeZone("Alaska");
        sauceOptions.setTunnelIdentifier("Mine");
        sauceOptions.setVideoUploadOnPass(false);
        
        assertTrue(sauceOptions.getAvoidProxy());
        assertTrue(sauceOptions.getCapturePerformance());
        assertEquals("2", sauceOptions.getChromedriverVersion());
        assertEquals(Integer.valueOf(2), sauceOptions.getCommandTimeout());
        assertEquals(data, sauceOptions.getCustomData());
        assertTrue(sauceOptions.getExtendedDebugging());
        assertEquals(Integer.valueOf(3), sauceOptions.getIdleTimeout());
        assertEquals("3", sauceOptions.getIedriverVersion());
        assertEquals(Integer.valueOf(4), sauceOptions.getMaxDuration());
        assertEquals("Test Name", sauceOptions.getName());
        assertEquals("Mommy", sauceOptions.getParentTunnel());
        assertEquals(prerun, sauceOptions.getPrerun());
        assertEquals("http://url.to/your/executable.exe", sauceOptions.getPrerunUrl());
        assertEquals(Integer.valueOf(0), sauceOptions.getPriority());
        assertEquals("team", sauceOptions.getPublicRestricted());
        assertFalse(sauceOptions.getRecordLogs());
        assertFalse(sauceOptions.getRecordScreenshots());
        assertFalse(sauceOptions.getRecordVideo());
        assertEquals("1280x1024", sauceOptions.getScreenResolution());
        assertEquals("3.141.5", sauceOptions.getSeleniumVersion());
        assertEquals(tags, sauceOptions.getTags());
        assertEquals("Alaska", sauceOptions.getTimeZone());
        assertEquals("Mine", sauceOptions.getTunnelIdentifier());
        assertFalse(sauceOptions.getVideoUploadOnPass());
    }

    @Test
    public void acceptsSeleniumSettings() {
        FirefoxOptions firefoxOptions = new FirefoxOptions();
        firefoxOptions.addArguments("--foo");
        firefoxOptions.addPreference("foo", "bar");
        firefoxOptions.setUnhandledPromptBehaviour(DISMISS);

        sauceOptions = new SauceOptions(firefoxOptions);

        assertEquals("firefox", sauceOptions.getBrowserName());
        assertEquals(firefoxOptions, sauceOptions.getSeleniumCapabilities());
    }

    @Test
    public void setsCapabilities() {
        Map<String, Object> capabilities = new HashMap<>();
        // String
        capabilities.put("browserName", "Foo");
        capabilities.put("browserVersion", "4");
        capabilities.put("platformName", "Bar 12");
        // Boolean
        capabilities.put("acceptInsecureCerts", true);
        // Map
        Map<String, Integer> timeouts = new HashMap<>();
        timeouts.put("implicit", 1);
        timeouts.put("pageLoad", 10);
        timeouts.put("script", 5);
        capabilities.put("timeouts", timeouts);

        // Sauce Specific
        capabilities.put("avoidProxy", true);
        capabilities.put("idleTimeout", 2);

        sauceOptions.setCapabilities(capabilities);

        assertEquals("Foo", sauceOptions.getBrowserName());
        assertEquals("Bar 12", sauceOptions.getPlatformName());
        assertEquals("4", sauceOptions.getBrowserVersion());
        assertEquals(true, sauceOptions.getAcceptInsecureCerts());
        assertEquals(true, sauceOptions.getAvoidProxy());
        assertEquals(timeouts, sauceOptions.getTimeouts());
        assertEquals(Integer.valueOf(2), sauceOptions.getIdleTimeout());
    }

    @Test
    public void allowsBuildToBeSet() {
        sauceOptions.setBuild("Manual Build Set");
        assertEquals("Manual Build Set", sauceOptions.getBuild());
    }

    @Test
    public void createsDefaultBuildName() {
        assertEquals("TEMP BUILD: 11", sauceOptions.getBuild());
    }

    @Test
    public void parsesW3CAndSauceAndSeleniumSettings() {
        // Selenium browser options
        FirefoxOptions firefoxOptions = new FirefoxOptions();
        firefoxOptions.addArguments("--foo");
        firefoxOptions.addPreference("foo", "bar");

        // Selenium w3c options
        firefoxOptions.setUnhandledPromptBehaviour(DISMISS);
        sauceOptions = new SauceOptions(firefoxOptions);

        sauceOptions.setPlatformName("macOS 10.14");
        sauceOptions.setBrowserVersion("68");
        sauceOptions.setUnhandledPromptBehavior("ignore");
        sauceOptions.setPageLoadStrategy("eager");

        // sauce options
        sauceOptions.setMaxDuration(1);
        sauceOptions.setCommandTimeout(2);

        Map capabilities = sauceOptions.toCapabilities().toJson();

        // Validate w3c options
        assertEquals("firefox", capabilities.get("browserName"));
        assertEquals("macOS 10.14", capabilities.get("platformName"));
        assertEquals("68", capabilities.get("browserVersion"));
        assertEquals("eager", capabilities.get("pageLoadStrategy"));
        assertEquals("ignore", capabilities.get("unhandledPromptBehavior"));

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
}
