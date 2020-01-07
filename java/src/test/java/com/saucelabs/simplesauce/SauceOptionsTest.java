package com.saucelabs.simplesauce;

import org.junit.Rule;
import org.junit.Test;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;
import org.openqa.selenium.MutableCapabilities;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.ie.InternetExplorerOptions;
import org.openqa.selenium.safari.SafariOptions;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.spy;
import static org.openqa.selenium.UnexpectedAlertBehaviour.DISMISS;

public class SauceOptionsTest {
    protected SauceOptions sauceOptions = new SauceOptions();

    @Rule
    public MockitoRule initRule = MockitoJUnit.rule();

    @Test
    public void usesLatestChromeWindowsVersionsByDefault() {
        assertEquals(Browser.CHROME, sauceOptions.getBrowserName());
        assertEquals(SaucePlatform.WINDOWS_10, sauceOptions.getPlatformName());
        assertEquals("latest", sauceOptions.getBrowserVersion());
    }

    @Test
    public void updatesBrowserBrowserVersionPlatformVersionValues() {
        sauceOptions.setBrowserName(Browser.FIREFOX);
        sauceOptions.setPlatformName(SaucePlatform.MAC_HIGH_SIERRA);
        sauceOptions.setBrowserVersion("68");

        assertEquals(Browser.FIREFOX, sauceOptions.getBrowserName());
        assertEquals("68", sauceOptions.getBrowserVersion());
        assertEquals(SaucePlatform.MAC_HIGH_SIERRA, sauceOptions.getPlatformName());
    }

    @Test
    public void fluentPatternWorks() {
        sauceOptions.setBrowserName(Browser.FIREFOX)
                .setBrowserVersion("68")
                .setPlatformName(SaucePlatform.MAC_HIGH_SIERRA);

        assertEquals(Browser.FIREFOX, sauceOptions.getBrowserName());
        assertEquals("68", sauceOptions.getBrowserVersion());
        assertEquals(SaucePlatform.MAC_HIGH_SIERRA, sauceOptions.getPlatformName());
    }

    @Test
    public void acceptsOtherW3CValues() {
        sauceOptions.setPageLoadStrategy(PageLoadStrategy.EAGER);
        sauceOptions.setAcceptInsecureCerts(true);
        Map<Timeouts, Integer> timeouts = new HashMap<>();
        timeouts.put(Timeouts.IMPLICIT, 1);
        timeouts.put(Timeouts.PAGE_LOAD, 100);
        timeouts.put(Timeouts.SCRIPT, 10);
        sauceOptions.setTimeouts(timeouts);
        sauceOptions.setUnhandledPromptBehavior(UnhandledPromptBehavior.IGNORE);

        assertEquals(PageLoadStrategy.EAGER, sauceOptions.getPageLoadStrategy());
        assertEquals(true, sauceOptions.getAcceptInsecureCerts());
        assertEquals(timeouts, sauceOptions.getTimeouts());
        assertEquals(UnhandledPromptBehavior.IGNORE, sauceOptions.getUnhandledPromptBehavior());
    }

    @Test
    public void setsTimeoutsDirectly() {
        sauceOptions.timeout.setImplicitWait(1)
                .setPageLoad(40)
                .setScript(29);

        Map<Timeouts, Integer> timeouts = new HashMap<>();
        timeouts.put(Timeouts.IMPLICIT, 1);
        timeouts.put(Timeouts.PAGE_LOAD, 40);
        timeouts.put(Timeouts.SCRIPT, 29);

        assertEquals(timeouts, sauceOptions.getTimeouts());
    }

    @Test
    public void acceptsSauceLabsSettings() {
        sauceOptions.setExtendedDebugging(true);
        sauceOptions.setName("Test name");
        sauceOptions.setParentTunnel("Mommy");
        sauceOptions.setJobVisibility(JobVisibility.SHARE);
        Map<Prerun, Object> prerun = new HashMap<>();
        prerun.put(Prerun.EXECUTABLE, "http://example.com");
        List<String> args = new ArrayList<>();
        args.add("--silent");
        args.add("-a");
        args.add("-q");
        prerun.put(Prerun.ARGS, args);
        prerun.put(Prerun.BACKGROUND, true);
        prerun.put(Prerun.TIMEOUT, 40);
        sauceOptions.setPrerun(prerun);
        List<String> tags = new ArrayList<>();
        tags.add("Foo");
        tags.add("Bar");
        tags.add("Foobar");
        sauceOptions.setTags(tags);

        assertEquals(true, sauceOptions.getExtendedDebugging());
        assertEquals("Test name", sauceOptions.getName());
        assertEquals("Mommy", sauceOptions.getParentTunnel());
        assertEquals(JobVisibility.SHARE, sauceOptions.getJobVisibility());
        assertEquals(prerun, sauceOptions.getPrerun());
        assertEquals(tags, sauceOptions.getTags());
    }

    @Test
    public void acceptsSeleniumBrowserOptionsClass() {
        FirefoxOptions firefoxOptions = new FirefoxOptions();
        firefoxOptions.addArguments("--foo");
        firefoxOptions.addPreference("foo", "bar");
        firefoxOptions.setUnhandledPromptBehaviour(DISMISS);

        sauceOptions = new SauceOptions(firefoxOptions);

        assertEquals(Browser.FIREFOX, sauceOptions.getBrowserName());
        assertEquals(firefoxOptions, sauceOptions.getSeleniumCapabilities());
    }

    @Test
    public void acceptsChromeOptionsClass() {
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.addArguments("--foo");
        chromeOptions.setUnhandledPromptBehaviour(DISMISS);

        sauceOptions = new SauceOptions(chromeOptions);

        assertEquals(Browser.CHROME, sauceOptions.getBrowserName());
        assertEquals(chromeOptions, sauceOptions.getSeleniumCapabilities());
    }

    @Test
    public void acceptsEdgeOptionsClass() {
        EdgeOptions edgeOptions = new EdgeOptions();
        edgeOptions.setPageLoadStrategy("eager");

        sauceOptions = new SauceOptions(edgeOptions);

        assertEquals(Browser.EDGE, sauceOptions.getBrowserName());
        assertEquals(edgeOptions, sauceOptions.getSeleniumCapabilities());
    }

    @Test
    public void acceptsFirefoxOptionsClass() {
        FirefoxOptions firefoxOptions = new FirefoxOptions();
        firefoxOptions.addArguments("--foo");
        firefoxOptions.addPreference("foo", "bar");
        firefoxOptions.setUnhandledPromptBehaviour(DISMISS);

        sauceOptions = new SauceOptions(firefoxOptions);

        assertEquals(Browser.FIREFOX, sauceOptions.getBrowserName());
        assertEquals(firefoxOptions, sauceOptions.getSeleniumCapabilities());
    }

    @Test
    public void acceptsInternetExplorerOptionsClass() {
        InternetExplorerOptions internetExplorerOptions = new InternetExplorerOptions();
        internetExplorerOptions.requireWindowFocus();
        internetExplorerOptions.setPageLoadStrategy(org.openqa.selenium.PageLoadStrategy.EAGER);
        internetExplorerOptions.setUnhandledPromptBehaviour(DISMISS);

        sauceOptions = new SauceOptions(internetExplorerOptions);

        assertEquals(Browser.INTERNET_EXPLORER, sauceOptions.getBrowserName());
        assertEquals(internetExplorerOptions, sauceOptions.getSeleniumCapabilities());
    }

    @Test
    public void acceptsSafariOptionsClass() {
        SafariOptions safariOptions = new SafariOptions();
        safariOptions.setAutomaticInspection(true);
        safariOptions.setAutomaticProfiling(true);

        sauceOptions = new SauceOptions(safariOptions);

        assertEquals(Browser.SAFARI, sauceOptions.getBrowserName());
        assertEquals(safariOptions, sauceOptions.getSeleniumCapabilities());
    }

    @Test
    public void setsCapabilitiesFromMap() {
        Map<String, Object> map = new HashMap<>();
        map.put("browserName", "firefox");
        map.put("platformName", "macOS 10.13");
        map.put("browserVersion", "68");
        map.put("pageLoadStrategy", "eager");
        map.put("acceptInsecureCerts", true);
        Map<String, Integer> timeouts = new HashMap<>();
        timeouts.put("implicit", 1);
        timeouts.put("pageLoad", 100);
        timeouts.put("script", 10);

        Map<Timeouts, Integer> expectedTimeouts = new HashMap<>();
        expectedTimeouts.put(Timeouts.IMPLICIT, 1);
        expectedTimeouts.put(Timeouts.PAGE_LOAD, 100);
        expectedTimeouts.put(Timeouts.SCRIPT, 10);

        map.put("timeouts", timeouts);
        map.put("unhandledPromptBehavior", "ignore");
        map.put("extendedDebugging", true);
        map.put("parentTunnel", "Mommy");
        map.put("jobVisibility", "share");
        Map<String, Object> prerun = new HashMap<>();
        prerun.put("executable", "http://example.com");
        List<String> args = new ArrayList<>();
        args.add("--silent");
        args.add("-a");
        args.add("-q");
        prerun.put("args", args);
        prerun.put("background", true);
        prerun.put("timeout", 40);
        map.put("prerun", prerun);

        Map<Prerun, Object> expectedPrerun = new HashMap<>();
        expectedPrerun.put(Prerun.ARGS, args);
        expectedPrerun.put(Prerun.BACKGROUND, true);
        expectedPrerun.put(Prerun.EXECUTABLE, "http://example.com");
        expectedPrerun.put(Prerun.TIMEOUT, 40);

        List<String> tags = new ArrayList<>();
        tags.add("Foo");
        tags.add("Bar");
        tags.add("Foobar");
        map.put("tags", tags);

        sauceOptions.mergeCapabilities(map);

        assertEquals(Browser.FIREFOX, sauceOptions.getBrowserName());
        assertEquals("68", sauceOptions.getBrowserVersion());
        assertEquals(SaucePlatform.MAC_HIGH_SIERRA, sauceOptions.getPlatformName());
        assertEquals(PageLoadStrategy.EAGER, sauceOptions.getPageLoadStrategy());
        assertEquals(true, sauceOptions.getAcceptInsecureCerts());
        assertEquals(expectedTimeouts, sauceOptions.getTimeouts());
        assertEquals(UnhandledPromptBehavior.IGNORE, sauceOptions.getUnhandledPromptBehavior());
        assertEquals(true, sauceOptions.getExtendedDebugging());
        assertEquals("Mommy", sauceOptions.getParentTunnel());
        assertEquals(JobVisibility.SHARE, sauceOptions.getJobVisibility());
        assertEquals(expectedPrerun, sauceOptions.getPrerun());
        assertEquals(tags, sauceOptions.getTags());
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

    @Test
    public void parsesW3CAndSauceAndSeleniumSettings() {
        MutableCapabilities expectedCapabilities = new MutableCapabilities();
        MutableCapabilities sauceCapabilities = new MutableCapabilities();

        FirefoxOptions firefoxOptions = new FirefoxOptions();
        firefoxOptions.addArguments("--foo");
        firefoxOptions.addPreference("foo", "bar");
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
        timeouts.put(Timeouts.PAGE_LOAD, 100);
        timeouts.put(Timeouts.SCRIPT, 10);
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
        Map<Prerun, Object> prerun = new HashMap<>();
        prerun.put(Prerun.EXECUTABLE, "http://example.com");
        List<String> args = new ArrayList<>();
        args.add("--silent");
        args.add("-a");
        args.add("-q");
        prerun.put(Prerun.ARGS, args);
        prerun.put(Prerun.BACKGROUND, true);
        prerun.put(Prerun.TIMEOUT, 40);
        sauceOptions.setPrerun(prerun);
        sauceCapabilities.setCapability("prerun", prerun);
        List<String> tags = new ArrayList<>();
        tags.add("Foo");
        tags.add("Bar");
        tags.add("Foobar");
        sauceOptions.setTags(tags);
        sauceCapabilities.setCapability("tags", tags);

        expectedCapabilities.setCapability("sauce:options", sauceCapabilities);

        // Firefox Options ImmutableSortedMap gets weird when comparing, so using toString()
        // this also makes sense from the standpoint that this is getting serialized
        assertEquals(expectedCapabilities.toString(), sauceOptions.toCapabilities().toString());
    }
}
