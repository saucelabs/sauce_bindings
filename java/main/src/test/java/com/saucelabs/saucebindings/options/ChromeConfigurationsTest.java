package com.saucelabs.saucebindings.options;

import com.google.common.collect.ImmutableMap;
import com.saucelabs.saucebindings.Browser;
import com.saucelabs.saucebindings.JobVisibility;
import com.saucelabs.saucebindings.PageLoadStrategy;
import com.saucelabs.saucebindings.Prerun;
import com.saucelabs.saucebindings.SaucePlatform;
import com.saucelabs.saucebindings.UnhandledPromptBehavior;
import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.UnexpectedAlertBehaviour;
import org.openqa.selenium.chrome.ChromeOptions;

import java.time.Duration;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ChromeConfigurationsTest {
    ChromeOptions chromeOptions = new ChromeOptions();

    @Test
    public void buildsDefaultSauceOptions() {
        SauceOptions sauceOptions = SauceOptions.chrome().build();

        Assert.assertEquals(Browser.CHROME, sauceOptions.getBrowserName());
        Assert.assertEquals(SaucePlatform.WINDOWS_10, sauceOptions.getPlatformName());
        Assert.assertEquals("latest", sauceOptions.getBrowserVersion());
    }

    @Test
    public void acceptsChromeOptionsClass() {
        chromeOptions.addArguments("--foo");
        chromeOptions.setUnhandledPromptBehaviour(UnexpectedAlertBehaviour.IGNORE);
        chromeOptions.setCapability("pageLoadStrategy", PageLoadStrategy.EAGER);
        chromeOptions.setCapability("sauce:options",
                ImmutableMap.of("build", "Build Name",
                        "maxDuration", 300));

        SauceOptions sauceOptions = SauceOptions.chrome(chromeOptions).build();

        Assert.assertEquals(Browser.CHROME, sauceOptions.getBrowserName());
        Assert.assertEquals(PageLoadStrategy.EAGER, sauceOptions.getPageLoadStrategy());
        Assert.assertEquals("Build Name", sauceOptions.sauce().getBuild());
        Assert.assertEquals(Integer.valueOf(300), sauceOptions.sauce().getMaxDuration());
        Assert.assertEquals(chromeOptions, sauceOptions.getCapabilities());
    }

    @Test(expected = InvalidSauceOptionsArgumentException.class)
    public void errorsBadChromeOptionsCapability() {
        chromeOptions.setCapability("invalid", "capability");

        SauceOptions.chrome(chromeOptions).build();
    }

    @Test(expected = InvalidSauceOptionsArgumentException.class)
    public void errorsChromeOptionsBrowserMismatch() {
        chromeOptions.setCapability("browserName", "firefox");

        SauceOptions.chrome(chromeOptions).build();
    }

    @Test(expected = InvalidSauceOptionsArgumentException.class)
    public void errorsBadChromeOptionsSauceCapability() {
        chromeOptions.setCapability("sauce:options", ImmutableMap.of("invalid", "value"));

        SauceOptions.chrome(chromeOptions).build();
    }

    @Test(expected = InvalidSauceOptionsArgumentException.class)
    public void errorsBadChromeOptionsValue() {
        chromeOptions.setCapability("unhandledPromptBehavior", "invalid");

        SauceOptions.chrome(chromeOptions).build();
    }

    @Test(expected = InvalidSauceOptionsArgumentException.class)
    public void errorsBadChromeOptionsSauceValue() {
        chromeOptions.setCapability("sauce:options", ImmutableMap.of("jobVisibility", "invalid"));

        SauceOptions.chrome(chromeOptions).build();
    }

    @Test
    public void ignoresNameSpacedValues() {
        chromeOptions.setCapability("foo:bar", ImmutableMap.of("matters", "not"));

        SauceOptions sauceOptions = SauceOptions.chrome(chromeOptions).build();
        Assert.assertNotNull(sauceOptions.getCapabilities().getCapability("foo:bar"));
    }

    @Test
    public void fluentOrderDoesNotMatter() {
        // Setting these in order from superclass methods to subclass to ensure proper return instances
        SauceOptions sauceOptions = SauceOptions.chrome()
                .setPlatformName(SaucePlatform.MAC_HIGH_SIERRA)
                .setBrowserVersion("68")
                .setExtendedDebugging()
                .build();

        Assert.assertEquals(SaucePlatform.MAC_HIGH_SIERRA, sauceOptions.getPlatformName());
        Assert.assertEquals("68", sauceOptions.getBrowserVersion());
        Assert.assertTrue(sauceOptions.sauce().getExtendedDebugging());
    }

    @Test
    public void acceptsOtherW3CValues() {
        SauceOptions sauceOptions = SauceOptions.chrome()
                .setAcceptInsecureCerts()
                .setPageLoadStrategy(PageLoadStrategy.EAGER)
                .setUnhandledPromptBehavior(UnhandledPromptBehavior.IGNORE)
                .setStrictFileInteractability()
                .setImplicitWaitTimeout(Duration.ofSeconds(1))
                .setPageLoadTimeout(Duration.ofSeconds(100))
                .setScriptTimeout(Duration.ofSeconds(10))
                .build();

        Assert.assertEquals(true, sauceOptions.getAcceptInsecureCerts());
        Assert.assertEquals(PageLoadStrategy.EAGER, sauceOptions.getPageLoadStrategy());
        Assert.assertEquals(UnhandledPromptBehavior.IGNORE, sauceOptions.getUnhandledPromptBehavior());
        Assert.assertEquals(true, sauceOptions.getStrictFileInteractability());
        Assert.assertEquals(Duration.ofSeconds(1), sauceOptions.getImplicitWaitTimeout());
        Assert.assertEquals(Duration.ofSeconds(100), sauceOptions.getPageLoadTimeout());
        Assert.assertEquals(Duration.ofSeconds(10), sauceOptions.getScriptTimeout());
    }

    @Test(expected = InvalidSauceOptionsArgumentException.class)
    public void capturePerformanceRequiresName() {
        SauceOptions.chrome().setCapturePerformance().build();
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
        prerun.put(Prerun.EXECUTABLE, "https://url.to/your/executable.exe");
        prerun.put(Prerun.ARGS, args);
        prerun.put(Prerun.BACKGROUND, false);
        prerun.put(Prerun.TIMEOUT, 120);

        List<String> tags = new ArrayList<>();
        tags.add("Foo");
        tags.add("Bar");
        tags.add("Foobar");

        SauceOptions sauceOptions = SauceOptions.chrome()
                .setBuild("Sample Build Name")
                .setName("Test name")
                .setCapturePerformance()
                .setChromedriverVersion("71")
                .setCommandTimeout(Duration.ofSeconds(2))
                .setCustomData(customData)
                .setExtendedDebugging()
                .setIdleTimeout(Duration.ofSeconds(20))
                .setMaxDuration(Duration.ofSeconds(300))
                .setParentTunnel("Mommy")
                .setPrerun(prerun)
                .setPriority(0)
                .setJobVisibility(JobVisibility.TEAM)
                .disableRecordLogs()
                .disableRecordScreenshots()
                .disableRecordVideo()
                .setScreenResolution("1024x768")
                .setTags(tags)
                .setTimeZone("San Francisco")
                .setTunnelIdentifier("tunnelName")
                .disableVideoUploadOnPass()
                .build();

        Assert.assertEquals("Sample Build Name", sauceOptions.sauce().getBuild());
        Assert.assertEquals(true, sauceOptions.sauce().getCapturePerformance());
        Assert.assertEquals("71", sauceOptions.sauce().getChromedriverVersion());
        Assert.assertEquals(Integer.valueOf(2), sauceOptions.sauce().getCommandTimeout());
        Assert.assertEquals(customData, sauceOptions.sauce().getCustomData());
        Assert.assertEquals(true, sauceOptions.sauce().getExtendedDebugging());
        Assert.assertEquals(Integer.valueOf(20), sauceOptions.sauce().getIdleTimeout());
        Assert.assertEquals(Integer.valueOf(300), sauceOptions.sauce().getMaxDuration());
        Assert.assertEquals("Test name", sauceOptions.sauce().getName());
        Assert.assertEquals("Mommy", sauceOptions.sauce().getParentTunnel());
        Assert.assertEquals(prerun, sauceOptions.sauce().getPrerun());
        Assert.assertEquals(Integer.valueOf(0), sauceOptions.sauce().getPriority());
        Assert.assertEquals(JobVisibility.TEAM, sauceOptions.sauce().getJobVisibility());
        Assert.assertEquals(false, sauceOptions.sauce().getRecordLogs());
        Assert.assertEquals(false, sauceOptions.sauce().getRecordScreenshots());
        Assert.assertEquals(false, sauceOptions.sauce().getRecordVideo());
        Assert.assertEquals("1024x768", sauceOptions.sauce().getScreenResolution());
        Assert.assertEquals(tags, sauceOptions.sauce().getTags());
        Assert.assertEquals("San Francisco", sauceOptions.sauce().getTimeZone());
        Assert.assertEquals("tunnelName", sauceOptions.sauce().getTunnelIdentifier());
        Assert.assertEquals(false, sauceOptions.sauce().getVideoUploadOnPass());
    }
}
