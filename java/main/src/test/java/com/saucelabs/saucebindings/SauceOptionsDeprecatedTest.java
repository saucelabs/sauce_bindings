package com.saucelabs.saucebindings;

import com.saucelabs.saucebindings.options.InvalidSauceOptionsArgumentException;
import lombok.SneakyThrows;
import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;
import org.openqa.selenium.MutableCapabilities;
import org.openqa.selenium.UnexpectedAlertBehaviour;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.ie.InternetExplorerOptions;
import org.openqa.selenium.safari.SafariOptions;
import org.yaml.snakeyaml.Yaml;

import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SauceOptionsDeprecatedTest {
    private SauceOptions sauceOptions = new SauceOptions();

    @Rule
    public MockitoRule initRule = MockitoJUnit.rule();

    @Test
    public void usesLatestChromeWindowsVersionsByDefault() {
        Assert.assertEquals(Browser.CHROME, sauceOptions.getBrowserName());
        Assert.assertEquals(SaucePlatform.WINDOWS_10, sauceOptions.getPlatformName());
        Assert.assertEquals("latest", sauceOptions.getBrowserVersion());
    }

    @Test
    public void updatesBrowserBrowserVersionPlatformVersionValues() {
        sauceOptions.setBrowserName(Browser.FIREFOX);
        sauceOptions.setPlatformName(SaucePlatform.MAC_HIGH_SIERRA);
        sauceOptions.setBrowserVersion("68");

        Assert.assertEquals(Browser.FIREFOX, sauceOptions.getBrowserName());
        Assert.assertEquals("68", sauceOptions.getBrowserVersion());
        Assert.assertEquals(SaucePlatform.MAC_HIGH_SIERRA, sauceOptions.getPlatformName());
    }

    @Test
    public void fluentPatternWorks() {
        sauceOptions.setBrowserName(Browser.FIREFOX)
                .setBrowserVersion("68")
                .setPlatformName(SaucePlatform.MAC_HIGH_SIERRA);

        Assert.assertEquals(Browser.FIREFOX, sauceOptions.getBrowserName());
        Assert.assertEquals("68", sauceOptions.getBrowserVersion());
        Assert.assertEquals(SaucePlatform.MAC_HIGH_SIERRA, sauceOptions.getPlatformName());
    }

    @Test
    public void acceptsOtherW3CValues() {
        sauceOptions.setAcceptInsecureCerts(true);
        sauceOptions.setPageLoadStrategy(PageLoadStrategy.EAGER);
        sauceOptions.setSetWindowRect(true);
        sauceOptions.setUnhandledPromptBehavior(UnhandledPromptBehavior.IGNORE);
        sauceOptions.setStrictFileInteractability(true);

        sauceOptions.timeout.setImplicitWait(1000);
        sauceOptions.timeout.setPageLoad(100000);
        sauceOptions.timeout.setScript(10000);

        Map<Timeouts, Integer> timeouts = new HashMap<>();
        timeouts.put(Timeouts.IMPLICIT, 1000);
        timeouts.put(Timeouts.PAGE_LOAD, 100000);
        timeouts.put(Timeouts.SCRIPT, 10000);

        Assert.assertEquals(true, sauceOptions.getAcceptInsecureCerts());
        Assert.assertEquals(PageLoadStrategy.EAGER, sauceOptions.getPageLoadStrategy());
        Assert.assertEquals(true, sauceOptions.getSetWindowRect());
        Assert.assertEquals(UnhandledPromptBehavior.IGNORE, sauceOptions.getUnhandledPromptBehavior());
        Assert.assertEquals(true, sauceOptions.getStrictFileInteractability());
        Assert.assertEquals(timeouts, sauceOptions.getTimeouts());
    }

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

        Assert.assertEquals(true, sauceOptions.getAvoidProxy());
        Assert.assertEquals("Sample Build Name", sauceOptions.getBuild());
        Assert.assertEquals(true, sauceOptions.getCapturePerformance());
        Assert.assertEquals("71", sauceOptions.getChromedriverVersion());
        Assert.assertEquals(Integer.valueOf(2), sauceOptions.getCommandTimeout());
        Assert.assertEquals(customData, sauceOptions.getCustomData());
        Assert.assertEquals(true, sauceOptions.getExtendedDebugging());
        Assert.assertEquals(Integer.valueOf(3), sauceOptions.getIdleTimeout());
        Assert.assertEquals("3.141.0", sauceOptions.getIedriverVersion());
        Assert.assertEquals(Integer.valueOf(300), sauceOptions.getMaxDuration());
        Assert.assertEquals("Test name", sauceOptions.getName());
        Assert.assertEquals("Mommy", sauceOptions.getParentTunnel());
        Assert.assertEquals(prerun, sauceOptions.getPrerun());
        Assert.assertEquals(Integer.valueOf(0), sauceOptions.getPriority());
        Assert.assertEquals(JobVisibility.TEAM, sauceOptions.getJobVisibility());
        Assert.assertEquals(false, sauceOptions.getRecordLogs());
        Assert.assertEquals(false, sauceOptions.getRecordScreenshots());
        Assert.assertEquals(false, sauceOptions.getRecordVideo());
        Assert.assertEquals("10x10", sauceOptions.getScreenResolution());
        Assert.assertEquals("3.141.59", sauceOptions.getSeleniumVersion());
        Assert.assertEquals(tags, sauceOptions.getTags());
        Assert.assertEquals("San Francisco", sauceOptions.getTimeZone());
        Assert.assertEquals("tunnelname", sauceOptions.getTunnelIdentifier());
        Assert.assertEquals(false, sauceOptions.getVideoUploadOnPass());
    }

    @Test
    public void acceptsChromeOptionsClass() {
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.addArguments("--foo");
        chromeOptions.setUnhandledPromptBehaviour(UnexpectedAlertBehaviour.DISMISS);

        sauceOptions = new SauceOptions(chromeOptions);

        Assert.assertEquals(Browser.CHROME, sauceOptions.getBrowserName());
        Assert.assertEquals(chromeOptions, sauceOptions.getSeleniumCapabilities());
    }

    @Test
    public void acceptsEdgeOptionsClass() {
        EdgeOptions edgeOptions = new EdgeOptions();

        sauceOptions = new SauceOptions(edgeOptions);

        Assert.assertEquals(Browser.EDGE, sauceOptions.getBrowserName());
        Assert.assertEquals(edgeOptions, sauceOptions.getSeleniumCapabilities());
    }

    @Test
    public void acceptsFirefoxOptionsClass() {
        FirefoxOptions firefoxOptions = new FirefoxOptions();
        firefoxOptions.addArguments("--foo");
        firefoxOptions.addPreference("foo", "bar");
        firefoxOptions.setUnhandledPromptBehaviour(UnexpectedAlertBehaviour.DISMISS);

        sauceOptions = new SauceOptions(firefoxOptions);

        Assert.assertEquals(Browser.FIREFOX, sauceOptions.getBrowserName());
        Assert.assertEquals(firefoxOptions, sauceOptions.getSeleniumCapabilities());
    }

    @Test
    public void acceptsInternetExplorerOptionsClass() {
        InternetExplorerOptions internetExplorerOptions = new InternetExplorerOptions();
        internetExplorerOptions.requireWindowFocus();
        internetExplorerOptions.setPageLoadStrategy(org.openqa.selenium.PageLoadStrategy.EAGER);
        internetExplorerOptions.setUnhandledPromptBehaviour(UnexpectedAlertBehaviour.DISMISS);

        sauceOptions = new SauceOptions(internetExplorerOptions);

        Assert.assertEquals(Browser.INTERNET_EXPLORER, sauceOptions.getBrowserName());
        Assert.assertEquals(internetExplorerOptions, sauceOptions.getSeleniumCapabilities());
    }

    @Test
    public void acceptsSafariOptionsClass() {
        SafariOptions safariOptions = new SafariOptions();
        safariOptions.setAutomaticInspection(true);
        safariOptions.setAutomaticProfiling(true);

        sauceOptions = new SauceOptions(safariOptions);

        Assert.assertEquals(Browser.SAFARI, sauceOptions.getBrowserName());
        Assert.assertEquals(safariOptions, sauceOptions.getSeleniumCapabilities());
    }

    @Test
    public void createsDefaultBuildName() {
        Assert.assertNotNull(sauceOptions.getBuild());
    }

    @SneakyThrows
    public Map<String, Object> serialize(String key) {
        InputStream input = Files.newInputStream(Paths.get("src/test/java/com/saucelabs/saucebindings/deprecated_options.yml"));
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
        timeouts.put(Timeouts.IMPLICIT, 1000);
        timeouts.put(Timeouts.PAGE_LOAD, 59000);
        timeouts.put(Timeouts.SCRIPT, 29000);

        Assert.assertEquals(Browser.FIREFOX, sauceOptions.getBrowserName());
        Assert.assertEquals("68", sauceOptions.getBrowserVersion());
        Assert.assertEquals(SaucePlatform.MAC_HIGH_SIERRA, sauceOptions.getPlatformName());
        Assert.assertEquals(true, sauceOptions.getAcceptInsecureCerts());
        Assert.assertEquals(PageLoadStrategy.EAGER, sauceOptions.getPageLoadStrategy());
        Assert.assertEquals(true, sauceOptions.getSetWindowRect());
        Assert.assertEquals(UnhandledPromptBehavior.ACCEPT, sauceOptions.getUnhandledPromptBehavior());
        Assert.assertEquals(true, sauceOptions.getStrictFileInteractability());
        Assert.assertEquals(timeouts, sauceOptions.getTimeouts());
        Assert.assertEquals(true, sauceOptions.getAvoidProxy());
        Assert.assertEquals("Sample Build Name", sauceOptions.getBuild());
        Assert.assertEquals(true, sauceOptions.getCapturePerformance());
        Assert.assertEquals("71", sauceOptions.getChromedriverVersion());
        Assert.assertEquals(Integer.valueOf(2), sauceOptions.getCommandTimeout());
        Assert.assertEquals(customData, sauceOptions.getCustomData());
        Assert.assertEquals(true, sauceOptions.getExtendedDebugging());
        Assert.assertEquals(Integer.valueOf(3), sauceOptions.getIdleTimeout());
        Assert.assertEquals("3.141.0", sauceOptions.getIedriverVersion());
        Assert.assertEquals(Integer.valueOf(300), sauceOptions.getMaxDuration());
        Assert.assertEquals("Sample Test Name", sauceOptions.getName());
        Assert.assertEquals("Mommy", sauceOptions.getParentTunnel());
        Assert.assertEquals(prerun, sauceOptions.getPrerun());
        Assert.assertEquals(Integer.valueOf(0), sauceOptions.getPriority());
        Assert.assertEquals(JobVisibility.TEAM, sauceOptions.getJobVisibility());
        Assert.assertEquals(false, sauceOptions.getRecordLogs());
        Assert.assertEquals(false, sauceOptions.getRecordScreenshots());
        Assert.assertEquals(false, sauceOptions.getRecordVideo());
        Assert.assertEquals("10x10", sauceOptions.getScreenResolution());
        Assert.assertEquals("3.141.59", sauceOptions.getSeleniumVersion());
        Assert.assertEquals(tags, sauceOptions.getTags());
        Assert.assertEquals("San Francisco", sauceOptions.getTimeZone());
        Assert.assertEquals("tunnelname", sauceOptions.getTunnelIdentifier());
        Assert.assertEquals(false, sauceOptions.getVideoUploadOnPass());
    }

    @Test(expected = InvalidSauceOptionsArgumentException.class)
    public void setsBadBrowserFromMap()  {
        Map<String, Object> map = serialize("badBrowser");
        sauceOptions.mergeCapabilities(map);
    }

    @Test(expected = InvalidSauceOptionsArgumentException.class)
    public void setsBadPlatformFromMap() {
        Map<String, Object> map = serialize("badPlatform");
        sauceOptions.mergeCapabilities(map);
    }

    @Test(expected = InvalidSauceOptionsArgumentException.class)
    public void setsBadJobVisibilityFromMap() {
        Map<String, Object> map = serialize("badJobVisibility");
        sauceOptions.mergeCapabilities(map);
    }

    @Test(expected = InvalidSauceOptionsArgumentException.class)
    public void setsBadPromptFromMap() {
        Map<String, Object> map = serialize("badPrompt");
        sauceOptions.mergeCapabilities(map);
    }

    @Test(expected = InvalidSauceOptionsArgumentException.class)
    public void setsBadTimeoutFromMap() {
        Map<String, Object> map = serialize("badTimeout");
        sauceOptions.mergeCapabilities(map);
    }

    @Test(expected = InvalidSauceOptionsArgumentException.class)
    public void setsBadPrerunFromMap() {
        Map<String, Object> map = serialize("badPrerun");
        sauceOptions.mergeCapabilities(map);
    }

    @Test(expected = InvalidSauceOptionsArgumentException.class)
    public void setsBadPageLoadFromMap() {
        Map<String, Object> map = serialize("badPageLoad");
        sauceOptions.mergeCapabilities(map);
    }

    @Test
    public void parsesCapabilitiesFromW3CValues() {
        sauceOptions.setBrowserName(Browser.FIREFOX);
        sauceOptions.setPlatformName(SaucePlatform.MAC_HIGH_SIERRA);
        sauceOptions.setBrowserVersion("77");
        sauceOptions.setAcceptInsecureCerts(true);
        sauceOptions.setPageLoadStrategy(PageLoadStrategy.EAGER);
        sauceOptions.setSetWindowRect(true);
        sauceOptions.setUnhandledPromptBehavior(UnhandledPromptBehavior.IGNORE);
        sauceOptions.setStrictFileInteractability(true);
        sauceOptions.timeout.setImplicitWait(1000);
        sauceOptions.timeout.setPageLoad(100000);
        sauceOptions.timeout.setScript(10000);
        sauceOptions.setBuild("Build Name");

        Map<Timeouts, Integer> timeouts = new HashMap<>();
        timeouts.put(Timeouts.IMPLICIT, 1000);
        timeouts.put(Timeouts.SCRIPT, 10000);
        timeouts.put(Timeouts.PAGE_LOAD, 100000);

        MutableCapabilities expectedCapabilities = new MutableCapabilities();
        expectedCapabilities.setCapability("browserName", "firefox");
        expectedCapabilities.setCapability("browserVersion", "77");
        expectedCapabilities.setCapability("platformName", "macOS 10.13");
        expectedCapabilities.setCapability("acceptInsecureCerts", true);
        expectedCapabilities.setCapability("setWindowRect", true);
        expectedCapabilities.setCapability("strictFileInteractability", true);
        expectedCapabilities.setCapability("pageLoadStrategy", PageLoadStrategy.EAGER);
        expectedCapabilities.setCapability("timeouts", timeouts);
        expectedCapabilities.setCapability("unhandledPromptBehavior", UnhandledPromptBehavior.IGNORE);

        MutableCapabilities sauceCapabilities = new MutableCapabilities();
        sauceCapabilities.setCapability("build", "Build Name");
        sauceCapabilities.setCapability("username", SystemManager.get("SAUCE_USERNAME"));
        sauceCapabilities.setCapability("accessKey", SystemManager.get("SAUCE_ACCESS_KEY"));
        expectedCapabilities.setCapability("sauce:options", sauceCapabilities);
        MutableCapabilities actualCapabilities = sauceOptions.toCapabilities();

        Assert.assertEquals(expectedCapabilities.asMap().toString(), actualCapabilities.asMap().toString());
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
        sauceCapabilities.setCapability("custom-data", customData);
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
        Assert.assertEquals(expectedCapabilities.asMap().toString(), actualCapabilities.asMap().toString());
    }

    @Test
    public void parsesCapabilitiesFromSeleniumValues() {
        FirefoxOptions firefoxOptions = new FirefoxOptions();
        firefoxOptions.addArguments("--foo");
        firefoxOptions.addPreference("foo", "bar");
        firefoxOptions.setUnhandledPromptBehaviour(UnexpectedAlertBehaviour.DISMISS);

        sauceOptions = new SauceOptions(firefoxOptions);
        sauceOptions.setBuild("Build Name");

        MutableCapabilities expectedCapabilities = new MutableCapabilities();
        expectedCapabilities.setCapability("browserName", "firefox");
        expectedCapabilities.setCapability("browserVersion", "latest");
        expectedCapabilities.setCapability("platformName", "Windows 10");
        expectedCapabilities = expectedCapabilities.merge(firefoxOptions);

        MutableCapabilities sauceCapabilities = new MutableCapabilities();
        sauceCapabilities.setCapability("build", "Build Name");
        sauceCapabilities.setCapability("username", SystemManager.get("SAUCE_USERNAME"));
        sauceCapabilities.setCapability("accessKey", SystemManager.get("SAUCE_ACCESS_KEY"));
        expectedCapabilities.setCapability("sauce:options", sauceCapabilities);
        MutableCapabilities actualCapabilities = sauceOptions.toCapabilities();

        Assert.assertEquals(expectedCapabilities.asMap().toString(), actualCapabilities.asMap().toString());
    }

    @Test
    public void parsesW3CAndSauceAndSeleniumSettings() {
        MutableCapabilities expectedCapabilities = new MutableCapabilities();
        MutableCapabilities sauceCapabilities = new MutableCapabilities();

        FirefoxOptions firefoxOptions = new FirefoxOptions();
        firefoxOptions.addArguments("--foo");
        firefoxOptions.setUnhandledPromptBehaviour(UnexpectedAlertBehaviour.DISMISS);

        sauceOptions = new SauceOptions(firefoxOptions);

        expectedCapabilities = expectedCapabilities.merge(firefoxOptions);
        expectedCapabilities.setCapability("browserVersion", "latest");
        expectedCapabilities.setCapability("platformName", "Windows 10");
        expectedCapabilities.setCapability("acceptInsecureCerts", true);

        sauceOptions.setBuild("CUSTOM BUILD: 12");
        sauceCapabilities.setCapability("build", "CUSTOM BUILD: 12");

        sauceOptions.setPageLoadStrategy(PageLoadStrategy.EAGER);
        expectedCapabilities.setCapability("pageLoadStrategy", PageLoadStrategy.EAGER);
        sauceOptions.setAcceptInsecureCerts(true);
        expectedCapabilities.setCapability("acceptInsecureCerts", true);
        sauceOptions.timeout.setImplicitWait(1000)
                .setPageLoad(100000)
                .setScript(10000);
        Map<Timeouts, Integer> timeouts = new HashMap<>();
        timeouts.put(Timeouts.IMPLICIT, 1000);
        timeouts.put(Timeouts.SCRIPT, 10000);
        timeouts.put(Timeouts.PAGE_LOAD, 100000);
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

        Assert.assertEquals(expectedCapabilities.asMap().toString(), actualCapabilities.asMap().toString());
    }
}
