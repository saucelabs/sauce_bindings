package com.saucelabs.simplesauce;

import org.junit.Ignore;
import org.junit.Test;
import org.openqa.selenium.MutableCapabilities;
import org.openqa.selenium.firefox.FirefoxOptions;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.spy;
import static org.openqa.selenium.UnexpectedAlertBehaviour.DISMISS;

public class SauceOptionsTest extends BaseConfigurationTest{
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
        sauceOptions.setCapability("pageLoadStrategy", Options.PageLoadStrategy.EAGER);
        sauceOptions.setCapability("acceptInsecureCerts", true);

        Map<Options.Timeouts, Integer> timeouts = new HashMap<>();
        timeouts.put(Options.Timeouts.IMPLICIT, 1);
        timeouts.put(Options.Timeouts.PAGE_LOAD, 100);
        timeouts.put(Options.Timeouts.SCRIPT, 10);

        sauceOptions.setCapability("timeouts", timeouts);
        sauceOptions.setCapability("unhandledPromptBehavior", Options.UnhandledPromptBehavior.IGNORE);

        assertEquals(Options.PageLoadStrategy.EAGER, sauceOptions.getCapability("pageLoadStrategy"));
        assertEquals(true, sauceOptions.getCapability("acceptInsecureCerts"));
        assertEquals(timeouts, sauceOptions.getCapability("timeouts"));
        assertEquals(Options.UnhandledPromptBehavior.IGNORE, sauceOptions.getCapability("unhandledPromptBehavior"));
    }

    @Test
    public void acceptsSauceLabsSettings() {
        sauceOptions.setCapability("extendedDebugging", true);
        sauceOptions.setCapability("name", "Test name");
        sauceOptions.setCapability("parentTunnel", "Mommy");
        sauceOptions.setCapability("public", Options.Public.SHARE);
        Map<Options.PreRun, Object> prerun = new HashMap<>();
        prerun.put(Options.PreRun.EXECUTABLE, "http://example.com");
        List<String> args = new ArrayList<>();
        args.add("--silent");
        args.add("-a");
        args.add("-q");
        prerun.put(Options.PreRun.ARGS, args);
        prerun.put(Options.PreRun.BACKGROUND, true);
        prerun.put(Options.PreRun.TIMEOUT, 40);
        sauceOptions.setCapability("prerun", prerun);
        List<String> tags = new ArrayList<>();
        tags.add("Foo");
        tags.add("Bar");
        tags.add("Foobar");
        sauceOptions.setCapability("tags", tags);

        assertEquals(true, sauceOptions.getCapability("extendedDebugging"));
        assertEquals("Mommy", sauceOptions.getCapability("parentTunnel"));
        assertEquals(Options.Public.SHARE, sauceOptions.getCapability("public"));
        assertEquals(prerun, sauceOptions.getCapability("prerun"));
        assertEquals(tags, sauceOptions.getCapability("tags"));
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
    public void acceptsSeleniumMutableCapabilitiesClass() {
        MutableCapabilities caps = new MutableCapabilities();
        caps.setCapability("browserName", "firefox");
        SauceOptions sauceOptions = new SauceOptions(caps);
        assertEquals(caps, sauceOptions.getSeleniumCapabilities());

        caps.setCapability("browserName", "chrome");
        assertEquals("firefox", sauceOptions.getSeleniumCapabilities().getBrowserName());
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
        map.put("public", "share");
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

        Map<Options.PreRun, Object> expectedPrerun = new HashMap<>();
        expectedPrerun.put(Options.PreRun.ARGS, args);
        expectedPrerun.put(Options.PreRun.BACKGROUND, true);
        expectedPrerun.put(Options.PreRun.EXECUTABLE, "http://example.com");
        expectedPrerun.put(Options.PreRun.TIMEOUT, 40);

        List<String> tags = new ArrayList<>();
        tags.add("Foo");
        tags.add("Bar");
        tags.add("Foobar");
        map.put("tags", tags);

        sauceOptions.setCapabilities(map);

        assertEquals(Options.Browser.FIREFOX, sauceOptions.getBrowserName());
        assertEquals("68", sauceOptions.getBrowserVersion());
        assertEquals(Options.Platform.MAC_HIGH_SIERRA, sauceOptions.getPlatformName());

        assertEquals(Options.PageLoadStrategy.EAGER, sauceOptions.getCapability("pageLoadStrategy"));
        assertEquals(true, sauceOptions.getCapability("acceptInsecureCerts"));
        assertEquals(expectedTimeouts, sauceOptions.getCapability("timeouts"));
        assertEquals(Options.UnhandledPromptBehavior.IGNORE, sauceOptions.getCapability("unhandledPromptBehavior"));

        assertEquals(true, sauceOptions.getCapability("extendedDebugging"));
        assertEquals("Mommy", sauceOptions.getCapability("parentTunnel"));
        assertEquals(Options.Public.SHARE, sauceOptions.getCapability("public"));
        assertEquals(expectedPrerun, sauceOptions.getCapability("prerun"));
        assertEquals(tags, sauceOptions.getCapability("tags"));
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
        expectedCapabilities.setCapability("browserName", Options.Browser.FIREFOX);
        expectedCapabilities.setCapability("browserVersion", "latest");
        expectedCapabilities.setCapability("platformName", Options.Platform.WINDOWS_10);
        expectedCapabilities.setCapability("acceptInsecureCerts", true);

        sauceOptions.setCapability("build", "CUSTOM BUILD: 12");
        sauceCapabilities.setCapability("build", "CUSTOM BUILD: 12");

        sauceOptions.setCapability("pageLoadStrategy", Options.PageLoadStrategy.EAGER);
        expectedCapabilities.setCapability("pageLoadStrategy", Options.PageLoadStrategy.EAGER);
        sauceOptions.setCapability("acceptInsecureCerts", true);
        expectedCapabilities.setCapability("acceptInsecureCerts", true);
        Map<Options.Timeouts, Integer> timeouts = new HashMap<>();
        timeouts.put(Options.Timeouts.IMPLICIT, 1);
        timeouts.put(Options.Timeouts.PAGE_LOAD, 100);
        timeouts.put(Options.Timeouts.SCRIPT, 10);
        sauceOptions.setCapability("timeouts", timeouts);
        expectedCapabilities.setCapability("timeouts", timeouts);
        sauceOptions.setCapability("unhandledPromptBehavior", Options.UnhandledPromptBehavior.IGNORE);
        expectedCapabilities.setCapability("unhandledPromptBehavior", Options.UnhandledPromptBehavior.IGNORE);

        sauceOptions.setCapability("extendedDebugging", true);
        sauceCapabilities.setCapability("extendedDebugging", true);
        sauceOptions.setCapability("name", "Test name");
        sauceCapabilities.setCapability("name", "Test name");
        sauceOptions.setCapability("parentTunnel", "Mommy");
        sauceCapabilities.setCapability("parentTunnel", "Mommy");

        sauceOptions.setCapability("public", Options.Public.SHARE);
        sauceCapabilities.setCapability("public", Options.Public.SHARE);
        Map<Options.PreRun, Object> prerun = new HashMap<>();
        prerun.put(Options.PreRun.EXECUTABLE, "http://example.com");
        List<String> args = new ArrayList<>();
        args.add("--silent");
        args.add("-a");
        args.add("-q");
        prerun.put(Options.PreRun.ARGS, args);
        prerun.put(Options.PreRun.BACKGROUND, true);
        prerun.put(Options.PreRun.TIMEOUT, 40);
        sauceOptions.setCapability("prerun", prerun);
        sauceCapabilities.setCapability("prerun", prerun);
        List<String> tags = new ArrayList<>();
        tags.add("Foo");
        tags.add("Bar");
        tags.add("Foobar");
        sauceOptions.setCapability("tags", tags);
        sauceCapabilities.setCapability("tags", tags);

        expectedCapabilities.setCapability("sauce:options", sauceCapabilities);
        MutableCapabilities actualCapabilities = sauceOptions.toCapabilities();

        // Firefox Options ImmutableSortedMap gets weird when comparing, so using toString which is the important part anyway
        assertEquals(expectedCapabilities.toString(), actualCapabilities.toString());
    }
}
