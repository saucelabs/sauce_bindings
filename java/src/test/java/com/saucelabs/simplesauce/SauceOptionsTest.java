package com.saucelabs.simplesauce;

import org.junit.Rule;
import org.junit.Test;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;
import org.openqa.selenium.MutableCapabilities;
import org.openqa.selenium.PageLoadStrategy;
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
        assertEquals(Options.Browser.CHROME, sauceOptions.getBrowserName());
        assertEquals(Options.Platform.WINDOWS_10, sauceOptions.getPlatformName());
        assertEquals("latest", sauceOptions.getBrowserVersion());
    }

    @Test
    public void updatesBrowserBrowserVersionPlatformVersionValues() {
        sauceOptions.setBrowserName(Options.Browser.FIREFOX);
        sauceOptions.setPlatformName(Options.Platform.MAC_HIGH_SIERRA);
        sauceOptions.setBrowserVersion("68");

        assertEquals(Options.Browser.FIREFOX, sauceOptions.getBrowserName());
        assertEquals("68", sauceOptions.getBrowserVersion());
        assertEquals(Options.Platform.MAC_HIGH_SIERRA, sauceOptions.getPlatformName());
    }

    @Test
    public void fluentPatternWorks() {
        sauceOptions.setBrowserName(Options.Browser.FIREFOX)
                .setBrowserVersion("68")
                .setPlatformName(Options.Platform.MAC_HIGH_SIERRA);

        assertEquals(Options.Browser.FIREFOX, sauceOptions.getBrowserName());
        assertEquals("68", sauceOptions.getBrowserVersion());
        assertEquals(Options.Platform.MAC_HIGH_SIERRA, sauceOptions.getPlatformName());
    }

    @Test
    public void acceptsOtherW3CValues() {
        sauceOptions.setPageLoadStrategy(Options.PageLoadStrategy.EAGER);
        sauceOptions.setAcceptInsecureCerts(true);
        Map<Options.Timeouts, Integer> timeouts = new HashMap<>();
        timeouts.put(Options.Timeouts.IMPLICIT, 1);
        timeouts.put(Options.Timeouts.PAGE_LOAD, 100);
        timeouts.put(Options.Timeouts.SCRIPT, 10);
        sauceOptions.setTimeouts(timeouts);
        sauceOptions.setUnhandledPromptBehavior(Options.UnhandledPromptBehavior.IGNORE);

        assertEquals(Options.PageLoadStrategy.EAGER, sauceOptions.getPageLoadStrategy());
        assertEquals(true, sauceOptions.getAcceptInsecureCerts());
        assertEquals(timeouts, sauceOptions.getTimeouts());
        assertEquals(Options.UnhandledPromptBehavior.IGNORE, sauceOptions.getUnhandledPromptBehavior());
    }

    @Test
    public void acceptsSauceLabsSettings() {
        sauceOptions.setExtendedDebugging(true);
        sauceOptions.setName("Test name");
        sauceOptions.setParentTunnel("Mommy");
        sauceOptions.setJobVisibility(Options.JobVisibility.SHARE);
        Map<Options.Prerun, Object> prerun = new HashMap<>();
        prerun.put(Options.Prerun.EXECUTABLE, "http://example.com");
        List<String> args = new ArrayList<>();
        args.add("--silent");
        args.add("-a");
        args.add("-q");
        prerun.put(Options.Prerun.ARGS, args);
        prerun.put(Options.Prerun.BACKGROUND, true);
        prerun.put(Options.Prerun.TIMEOUT, 40);
        sauceOptions.setPrerun(prerun);
        List<String> tags = new ArrayList<>();
        tags.add("Foo");
        tags.add("Bar");
        tags.add("Foobar");
        sauceOptions.setTags(tags);

        assertEquals(true, sauceOptions.getExtendedDebugging());
        assertEquals("Test name", sauceOptions.getName());
        assertEquals("Mommy", sauceOptions.getParentTunnel());
        assertEquals(Options.JobVisibility.SHARE, sauceOptions.getJobVisibility());
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

        assertEquals(Options.Browser.FIREFOX, sauceOptions.getBrowserName());
        assertEquals(firefoxOptions, sauceOptions.getSeleniumCapabilities());
    }

    @Test
    public void acceptsChromeOptionsClass() {
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.addArguments("--foo");
        chromeOptions.setUnhandledPromptBehaviour(DISMISS);

        sauceOptions = new SauceOptions(chromeOptions);

        assertEquals(Options.Browser.CHROME, sauceOptions.getBrowserName());
        assertEquals(chromeOptions, sauceOptions.getSeleniumCapabilities());
    }

    @Test
    public void acceptsEdgeOptionsClass() {
        EdgeOptions edgeOptions = new EdgeOptions();
        edgeOptions.setPageLoadStrategy("eager");

        sauceOptions = new SauceOptions(edgeOptions);

        assertEquals(Options.Browser.EDGE, sauceOptions.getBrowserName());
        assertEquals(edgeOptions, sauceOptions.getSeleniumCapabilities());
    }

    @Test
    public void acceptsFirefoxOptionsClass() {
        FirefoxOptions firefoxOptions = new FirefoxOptions();
        firefoxOptions.addArguments("--foo");
        firefoxOptions.addPreference("foo", "bar");
        firefoxOptions.setUnhandledPromptBehaviour(DISMISS);

        sauceOptions = new SauceOptions(firefoxOptions);

        assertEquals(Options.Browser.FIREFOX, sauceOptions.getBrowserName());
        assertEquals(firefoxOptions, sauceOptions.getSeleniumCapabilities());
    }

    @Test
    public void acceptsInternetExplorerOptionsClass() {
        InternetExplorerOptions internetExplorerOptions = new InternetExplorerOptions();
        internetExplorerOptions.requireWindowFocus();
        internetExplorerOptions.setPageLoadStrategy(PageLoadStrategy.EAGER);
        internetExplorerOptions.setUnhandledPromptBehaviour(DISMISS);

        sauceOptions = new SauceOptions(internetExplorerOptions);

        assertEquals(Options.Browser.INTERNET_EXPLORER, sauceOptions.getBrowserName());
        assertEquals(internetExplorerOptions, sauceOptions.getSeleniumCapabilities());
    }

    @Test
    public void acceptsSafariOptionsClass() {
        SafariOptions safariOptions = new SafariOptions();
        safariOptions.setAutomaticInspection(true);
        safariOptions.setAutomaticProfiling(true);

        sauceOptions = new SauceOptions(safariOptions);

        assertEquals(Options.Browser.SAFARI, sauceOptions.getBrowserName());
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

        Map<Options.Timeouts, Integer> expectedTimeouts = new HashMap<>();
        expectedTimeouts.put(Options.Timeouts.IMPLICIT, 1);
        expectedTimeouts.put(Options.Timeouts.PAGE_LOAD, 100);
        expectedTimeouts.put(Options.Timeouts.SCRIPT, 10);

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

        Map<Options.Prerun, Object> expectedPrerun = new HashMap<>();
        expectedPrerun.put(Options.Prerun.ARGS, args);
        expectedPrerun.put(Options.Prerun.BACKGROUND, true);
        expectedPrerun.put(Options.Prerun.EXECUTABLE, "http://example.com");
        expectedPrerun.put(Options.Prerun.TIMEOUT, 40);

        List<String> tags = new ArrayList<>();
        tags.add("Foo");
        tags.add("Bar");
        tags.add("Foobar");
        map.put("tags", tags);

        sauceOptions.setCapabilities(map);

        assertEquals(Options.Browser.FIREFOX, sauceOptions.getBrowserName());
        assertEquals("68", sauceOptions.getBrowserVersion());
        assertEquals(Options.Platform.MAC_HIGH_SIERRA, sauceOptions.getPlatformName());
        assertEquals(Options.PageLoadStrategy.EAGER, sauceOptions.getPageLoadStrategy());
        assertEquals(true, sauceOptions.getAcceptInsecureCerts());
        assertEquals(expectedTimeouts, sauceOptions.getTimeouts());
        assertEquals(Options.UnhandledPromptBehavior.IGNORE, sauceOptions.getUnhandledPromptBehavior());
        assertEquals(true, sauceOptions.getExtendedDebugging());
        assertEquals("Mommy", sauceOptions.getParentTunnel());
        assertEquals(Options.JobVisibility.SHARE, sauceOptions.getJobVisibility());
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

        sauceOptions.setPageLoadStrategy(Options.PageLoadStrategy.EAGER);
        expectedCapabilities.setCapability("pageLoadStrategy", Options.PageLoadStrategy.EAGER);
        sauceOptions.setAcceptInsecureCerts(true);
        expectedCapabilities.setCapability("acceptInsecureCerts", true);
        Map<Options.Timeouts, Integer> timeouts = new HashMap<>();
        timeouts.put(Options.Timeouts.IMPLICIT, 1);
        timeouts.put(Options.Timeouts.PAGE_LOAD, 100);
        timeouts.put(Options.Timeouts.SCRIPT, 10);
        sauceOptions.setTimeouts(timeouts);
        expectedCapabilities.setCapability("timeouts", timeouts);
        sauceOptions.setUnhandledPromptBehavior(Options.UnhandledPromptBehavior.IGNORE);
        expectedCapabilities.setCapability("unhandledPromptBehavior", Options.UnhandledPromptBehavior.IGNORE);

        sauceOptions.setExtendedDebugging(true);
        sauceCapabilities.setCapability("extendedDebugging", true);
        sauceOptions.setName("Test name");
        sauceCapabilities.setCapability("name", "Test name");
        sauceOptions.setParentTunnel("Mommy");
        sauceCapabilities.setCapability("parentTunnel", "Mommy");

        sauceOptions.setJobVisibility(Options.JobVisibility.SHARE);
        sauceCapabilities.setCapability("public", Options.JobVisibility.SHARE);
        Map<Options.Prerun, Object> prerun = new HashMap<>();
        prerun.put(Options.Prerun.EXECUTABLE, "http://example.com");
        List<String> args = new ArrayList<>();
        args.add("--silent");
        args.add("-a");
        args.add("-q");
        prerun.put(Options.Prerun.ARGS, args);
        prerun.put(Options.Prerun.BACKGROUND, true);
        prerun.put(Options.Prerun.TIMEOUT, 40);
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
