package com.saucelabs.saucebindings;

import lombok.SneakyThrows;
import org.junit.Test;
import org.openqa.selenium.MutableCapabilities;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.yaml.snakeyaml.Yaml;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.openqa.selenium.UnexpectedAlertBehaviour.DISMISS;

public class SauceOptionsDeprecatedTest {
    private SauceOptions sauceOptions = new SauceOptions();

    @Test
    public void acceptsSauceLabsSettings() {
        Map<String, Object> customData = new HashMap<>();
        customData.put("foo", "foo");
        customData.put("bar", "bar");

        List<String> args = new ArrayList<>();
        args.add("--silent");
        args.add("-a");
        args.add("-q");

        Map<Prerun, Object> prerun = new HashMap<>();
        prerun.put(Prerun.EXECUTABLE, "http://url.to/your/executable.exe");
        prerun.put(Prerun.ARGS, args);
        prerun.put(Prerun.BACKGROUND, false);
        prerun.put(Prerun.TIMEOUT, 120);

        List<String> tags = new ArrayList<>();
        tags.add("Foo");
        tags.add("Bar");
        tags.add("Foobar");

        sauceOptions.setAvoidProxy(true);
        sauceOptions.setBuild("Sample Build Name");
        sauceOptions.setCapturePerformance(true);
        sauceOptions.setChromedriverVersion("71");
        sauceOptions.setCommandTimeout(2);
        sauceOptions.setCustomData(customData);
        sauceOptions.setExtendedDebugging(true);
        sauceOptions.setIdleTimeout(3);
        sauceOptions.setIedriverVersion("3.141.0");
        sauceOptions.setMaxDuration(300);
        sauceOptions.setName("Test name");
        sauceOptions.setParentTunnel("Mommy");
        sauceOptions.setPrerun(prerun);
        sauceOptions.setPriority(0);
        sauceOptions.setJobVisibility(JobVisibility.TEAM);
        sauceOptions.setRecordLogs(false);
        sauceOptions.setRecordScreenshots(false);
        sauceOptions.setRecordVideo(false);
        sauceOptions.setScreenResolution("10x10");
        sauceOptions.setSeleniumVersion("3.141.59");
        sauceOptions.setTags(tags);
        sauceOptions.setTimeZone("San Francisco");
        sauceOptions.setTunnelIdentifier("tunnelname");
        sauceOptions.setVideoUploadOnPass(false);

        assertEquals(true, sauceOptions.getAvoidProxy());
        assertEquals("Sample Build Name", sauceOptions.getBuild());
        assertEquals(true, sauceOptions.getCapturePerformance());
        assertEquals("71", sauceOptions.getChromedriverVersion());
        assertEquals(Integer.valueOf(2), sauceOptions.getCommandTimeout());
        assertEquals(customData, sauceOptions.getCustomData());
        assertEquals(true, sauceOptions.getExtendedDebugging());
        assertEquals(Integer.valueOf(3), sauceOptions.getIdleTimeout());
        assertEquals("3.141.0", sauceOptions.getIedriverVersion());
        assertEquals(Integer.valueOf(300), sauceOptions.getMaxDuration());
        assertEquals("Test name", sauceOptions.getName());
        assertEquals("Mommy", sauceOptions.getParentTunnel());
        assertEquals(prerun, sauceOptions.getPrerun());
        assertEquals(Integer.valueOf(0), sauceOptions.getPriority());
        assertEquals(JobVisibility.TEAM, sauceOptions.getJobVisibility());
        assertEquals(false, sauceOptions.getRecordLogs());
        assertEquals(false, sauceOptions.getRecordScreenshots());
        assertEquals(false, sauceOptions.getRecordVideo());
        assertEquals("10x10", sauceOptions.getScreenResolution());
        assertEquals("3.141.59", sauceOptions.getSeleniumVersion());
        assertEquals(tags, sauceOptions.getTags());
        assertEquals("San Francisco", sauceOptions.getTimeZone());
        assertEquals("tunnelname", sauceOptions.getTunnelIdentifier());
        assertEquals(false, sauceOptions.getVideoUploadOnPass());
    }

    @Test
    public void createsDefaultBuildName() {
        assertNotNull(sauceOptions.getBuild());
    }

    @SneakyThrows
    public Map<String, Object> serialize(String key) {
        InputStream input = new FileInputStream(new File("src/test/java/com/saucelabs/saucebindings/deprecated_options.yml"));
        Yaml yaml = new Yaml();
        Map<String, Object> data = yaml.load(input);
        return (Map<String, Object>) data.get(key);
    }

    @Test
    public void setsCapabilitiesFromMap() {
        Map<String, Object> map = serialize("exampleValues");

        sauceOptions.mergeCapabilities(map);

        Map<String, Object> customData = new HashMap<>();
        customData.put("foo", "foo");
        customData.put("bar", "bar");

        List<String> args = new ArrayList<>();
        args.add("--silent");
        args.add("-a");
        args.add("-q");

        Map<Prerun, Object> prerun = new HashMap<>();
        prerun.put(Prerun.EXECUTABLE, "http://url.to/your/executable.exe");
        prerun.put(Prerun.ARGS, args);
        prerun.put(Prerun.BACKGROUND, false);
        prerun.put(Prerun.TIMEOUT, 120);

        List<String> tags = new ArrayList<>();
        tags.add("foo");
        tags.add("bar");
        tags.add("foobar");

        Map<Timeouts, Integer> timeouts = new HashMap<>();
        timeouts.put(Timeouts.IMPLICIT, 1);
        timeouts.put(Timeouts.PAGE_LOAD, 59);
        timeouts.put(Timeouts.SCRIPT, 29);

        assertEquals(Browser.FIREFOX, sauceOptions.getBrowserName());
        assertEquals("68", sauceOptions.getBrowserVersion());
        assertEquals(SaucePlatform.MAC_HIGH_SIERRA, sauceOptions.getPlatformName());
        assertEquals(true, sauceOptions.getAcceptInsecureCerts());
        assertEquals(PageLoadStrategy.EAGER, sauceOptions.getPageLoadStrategy());
        assertEquals(true, sauceOptions.getSetWindowRect());
        assertEquals(UnhandledPromptBehavior.ACCEPT, sauceOptions.getUnhandledPromptBehavior());
        assertEquals(true, sauceOptions.getStrictFileInteractability());
        assertEquals(timeouts, sauceOptions.getTimeouts());
        assertEquals(true, sauceOptions.getAvoidProxy());
        assertEquals("Sample Build Name", sauceOptions.getBuild());
        assertEquals(true, sauceOptions.getCapturePerformance());
        assertEquals("71", sauceOptions.getChromedriverVersion());
        assertEquals(Integer.valueOf(2), sauceOptions.getCommandTimeout());
        assertEquals(customData, sauceOptions.getCustomData());
        assertEquals(true, sauceOptions.getExtendedDebugging());
        assertEquals(Integer.valueOf(3), sauceOptions.getIdleTimeout());
        assertEquals("3.141.0", sauceOptions.getIedriverVersion());
        assertEquals(Integer.valueOf(300), sauceOptions.getMaxDuration());
        assertEquals("Sample Test Name", sauceOptions.getName());
        assertEquals("Mommy", sauceOptions.getParentTunnel());
        assertEquals(prerun, sauceOptions.getPrerun());
        assertEquals(Integer.valueOf(0), sauceOptions.getPriority());
        assertEquals(JobVisibility.TEAM, sauceOptions.getJobVisibility());
        assertEquals(false, sauceOptions.getRecordLogs());
        assertEquals(false, sauceOptions.getRecordScreenshots());
        assertEquals(false, sauceOptions.getRecordVideo());
        assertEquals("10x10", sauceOptions.getScreenResolution());
        assertEquals("3.141.59", sauceOptions.getSeleniumVersion());
        assertEquals(tags, sauceOptions.getTags());
        assertEquals("San Francisco", sauceOptions.getTimeZone());
        assertEquals("tunnelname", sauceOptions.getTunnelIdentifier());
        assertEquals(false, sauceOptions.getVideoUploadOnPass());
    }

    @Test(expected = InvalidSauceOptionsArgumentException.class)
    public void setsBadJobVisibilityFromMap() {
        Map<String, Object> map = serialize("badJobVisibility");
        sauceOptions.mergeCapabilities(map);
    }

    @Test(expected = InvalidSauceOptionsArgumentException.class)
    public void setsBadPrerunFromMap() {
        Map<String, Object> map = serialize("badPrerun");
        sauceOptions.mergeCapabilities(map);
    }

    @Test
    public void parsesCapabilitiesFromSauceValues() {
        Map<String, Object> customData = new HashMap<>();
        customData.put("foo", "foo");
        customData.put("bar", "bar");

        List<String> args = new ArrayList<>();
        args.add("--silent");
        args.add("-a");
        args.add("-q");

        Map<Prerun, Object> prerun = new HashMap<>();
        prerun.put(Prerun.EXECUTABLE, "http://url.to/your/executable.exe");
        prerun.put(Prerun.ARGS, args);
        prerun.put(Prerun.BACKGROUND, false);
        prerun.put(Prerun.TIMEOUT, 120);

        List<String> tags = new ArrayList<>();
        tags.add("Foo");
        tags.add("Bar");
        tags.add("Foobar");

        sauceOptions.setAvoidProxy(true);
        sauceOptions.setBuild("Sample Build Name");
        sauceOptions.setCapturePerformance(true);
        sauceOptions.setChromedriverVersion("71");
        sauceOptions.setCommandTimeout(2);
        sauceOptions.setCustomData(customData);
        sauceOptions.setExtendedDebugging(true);
        sauceOptions.setIdleTimeout(3);
        sauceOptions.setIedriverVersion("3.141.0");
        sauceOptions.setMaxDuration(300);
        sauceOptions.setName("Test name");
        sauceOptions.setParentTunnel("Mommy");
        sauceOptions.setPrerun(prerun);
        sauceOptions.setPriority(0);
        sauceOptions.setJobVisibility(JobVisibility.TEAM);
        sauceOptions.setRecordLogs(false);
        sauceOptions.setRecordScreenshots(false);
        sauceOptions.setRecordVideo(false);
        sauceOptions.setScreenResolution("10x10");
        sauceOptions.setSeleniumVersion("3.141.59");
        sauceOptions.setTags(tags);
        sauceOptions.setTimeZone("San Francisco");
        sauceOptions.setTunnelIdentifier("tunnelname");
        sauceOptions.setVideoUploadOnPass(false);

        MutableCapabilities sauceCapabilities = new MutableCapabilities();
        sauceCapabilities.setCapability("avoidProxy", true);
        sauceCapabilities.setCapability("build", "Sample Build Name");
        sauceCapabilities.setCapability("capturePerformance", true);
        sauceCapabilities.setCapability("chromedriverVersion", "71");
        sauceCapabilities.setCapability("commandTimeout", 2);
        sauceCapabilities.setCapability("customData", customData);
        sauceCapabilities.setCapability("extendedDebugging", true);
        sauceCapabilities.setCapability("idleTimeout", 3);
        sauceCapabilities.setCapability("iedriverVersion", "3.141.0");
        sauceCapabilities.setCapability("maxDuration", 300);
        sauceCapabilities.setCapability("name", "Test name");
        sauceCapabilities.setCapability("parentTunnel", "Mommy");
        sauceCapabilities.setCapability("prerun", prerun);
        sauceCapabilities.setCapability("priority", 0);
        sauceCapabilities.setCapability("public", "team");
        sauceCapabilities.setCapability("recordLogs", false);
        sauceCapabilities.setCapability("recordScreenshots", false);
        sauceCapabilities.setCapability("recordVideo", false);
        sauceCapabilities.setCapability("screenResolution", "10x10");
        sauceCapabilities.setCapability("seleniumVersion", "3.141.59");
        sauceCapabilities.setCapability("tags", tags);
        sauceCapabilities.setCapability("timeZone", "San Francisco");
        sauceCapabilities.setCapability("tunnelIdentifier", "tunnelname");
        sauceCapabilities.setCapability("videoUploadOnPass", false);
        sauceCapabilities.setCapability("username", SystemManager.get("SAUCE_USERNAME"));
        sauceCapabilities.setCapability("accessKey", SystemManager.get("SAUCE_ACCESS_KEY"));

        MutableCapabilities expectedCapabilities = new MutableCapabilities();
        expectedCapabilities.setCapability("browserName", "chrome");
        expectedCapabilities.setCapability("browserVersion", "latest");
        expectedCapabilities.setCapability("platformName", "Windows 10");

        expectedCapabilities.setCapability("sauce:options", sauceCapabilities);
        MutableCapabilities actualCapabilities = sauceOptions.toCapabilities();

        // toString() serializes the enums
        assertEquals(expectedCapabilities.asMap().toString(), actualCapabilities.asMap().toString());
    }

    @Test
    public void parsesW3CAndSauceAndSeleniumSettings() {
        MutableCapabilities expectedCapabilities = new MutableCapabilities();
        MutableCapabilities sauceCapabilities = new MutableCapabilities();

        FirefoxOptions firefoxOptions = new FirefoxOptions();
        firefoxOptions.addArguments("--foo");
        firefoxOptions.setUnhandledPromptBehaviour(DISMISS);

        sauceOptions = new SauceOptions(firefoxOptions);

        expectedCapabilities.merge(firefoxOptions);
        expectedCapabilities.setCapability("browserVersion", "latest");
        expectedCapabilities.setCapability("platformName", "Windows 10");
        expectedCapabilities.setCapability("acceptInsecureCerts", true);

        sauceOptions.setBuild("CUSTOM BUILD: 12");
        sauceCapabilities.setCapability("build", "CUSTOM BUILD: 12");

        sauceOptions.setPageLoadStrategy(PageLoadStrategy.EAGER);
        expectedCapabilities.setCapability("pageLoadStrategy", PageLoadStrategy.EAGER);
        sauceOptions.setAcceptInsecureCerts(true);
        expectedCapabilities.setCapability("acceptInsecureCerts", true);
        sauceOptions.timeout.setImplicitWait(1)
                .setPageLoad(100)
                .setScript(10);
        Map<Timeouts, Integer> timeouts = new HashMap<>();
        timeouts.put(Timeouts.IMPLICIT, 1);
        timeouts.put(Timeouts.SCRIPT, 10);
        timeouts.put(Timeouts.PAGE_LOAD, 100);
        expectedCapabilities.setCapability("timeouts", timeouts);
        sauceOptions.setUnhandledPromptBehavior(UnhandledPromptBehavior.IGNORE);
        expectedCapabilities.setCapability("unhandledPromptBehavior", UnhandledPromptBehavior.IGNORE);

        sauceOptions.setExtendedDebugging(true);
        sauceCapabilities.setCapability("extendedDebugging", true);
        sauceOptions.setName("Test name");
        sauceCapabilities.setCapability("name", "Test name");
        sauceOptions.setParentTunnel("Mommy");
        sauceCapabilities.setCapability("parentTunnel", "Mommy");

        sauceOptions.setJobVisibility(JobVisibility.SHARE);
        sauceCapabilities.setCapability("public", JobVisibility.SHARE);
        sauceCapabilities.setCapability("username", SystemManager.get("SAUCE_USERNAME"));
        sauceCapabilities.setCapability("accessKey", SystemManager.get("SAUCE_ACCESS_KEY"));

        expectedCapabilities.setCapability("sauce:options", sauceCapabilities);
        MutableCapabilities actualCapabilities = sauceOptions.toCapabilities();

        assertEquals(expectedCapabilities.asMap().toString(), actualCapabilities.asMap().toString());
    }
}
