package com.saucelabs.saucebindings;

import com.saucelabs.saucebindings.exceptions.InvalidSauceOptionsArgumentException;
import com.saucelabs.saucebindings.options.SauceOptions;
import com.saucelabs.saucebindings.options.capabilities.*;
import org.junit.Test;
import org.openqa.selenium.UnexpectedAlertBehaviour;
import org.openqa.selenium.chrome.ChromeOptions;

import java.time.Duration;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class ChromeConfigurationsTest {

    @Test
    public void buildsDefaultSauceOptions() {
        SauceOptions sauceOptions = SauceOptions.chrome().build();

        assertEquals(Browser.CHROME, sauceOptions.getBrowserName());
        assertEquals(SaucePlatform.WINDOWS_10, sauceOptions.getPlatformName());
        assertEquals("latest", sauceOptions.getBrowserVersion());
    }

    @Test
    public void acceptsChromeOptionsClass() {
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.addArguments("--foo");
        chromeOptions.setUnhandledPromptBehaviour(UnexpectedAlertBehaviour.DISMISS);

        SauceOptions sauceOptions = SauceOptions.chrome(chromeOptions).build();

        assertEquals(Browser.CHROME, sauceOptions.getBrowserName());
        assertEquals(chromeOptions, sauceOptions.getSeleniumCapabilities());
    }

    @Test
    public void fluentOrderDoesNotMatter() {
        // Setting these in order from superclass methods to subclass to ensure proper return instances
        SauceOptions sauceOptions = SauceOptions.chrome()
                .setPlatformName(SaucePlatform.MAC_HIGH_SIERRA)
                .setBrowserVersion("68")
                .setExtendedDebugging()
                .build();

        assertEquals(SaucePlatform.MAC_HIGH_SIERRA, sauceOptions.getPlatformName());
        assertEquals("68", sauceOptions.getBrowserVersion());
        assertTrue(sauceOptions.getExtendedDebugging());
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

        Map<Timeouts, Integer> timeouts = new HashMap<>();
        timeouts.put(Timeouts.IMPLICIT, 1);
        timeouts.put(Timeouts.PAGE_LOAD, 100);
        timeouts.put(Timeouts.SCRIPT, 10);

        assertEquals(true, sauceOptions.getAcceptInsecureCerts());
        assertEquals(PageLoadStrategy.EAGER, sauceOptions.getPageLoadStrategy());
        assertEquals(UnhandledPromptBehavior.IGNORE, sauceOptions.getUnhandledPromptBehavior());
        assertEquals(true, sauceOptions.getStrictFileInteractability());
        assertEquals(timeouts, sauceOptions.getTimeouts());
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
        prerun.put(Prerun.EXECUTABLE, "http://url.to/your/executable.exe");
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
                .setScreenResolution(ScreenResolution.XGA)
                .setTags(tags)
                .setTimeZone("San Francisco")
                .setTunnelIdentifier("tunnelname")
                .disableVideoUploadOnPass()
                .build();

        assertEquals("Sample Build Name", sauceOptions.getBuild());
        assertEquals(true, sauceOptions.getCapturePerformance());
        assertEquals("71", sauceOptions.getChromedriverVersion());
        assertEquals(Integer.valueOf(2), sauceOptions.getCommandTimeout());
        assertEquals(customData, sauceOptions.getCustomData());
        assertEquals(true, sauceOptions.getExtendedDebugging());
        assertEquals(Integer.valueOf(20), sauceOptions.getIdleTimeout());
        assertEquals(Integer.valueOf(300), sauceOptions.getMaxDuration());
        assertEquals("Test name", sauceOptions.getName());
        assertEquals("Mommy", sauceOptions.getParentTunnel());
        assertEquals(prerun, sauceOptions.getPrerun());
        assertEquals(Integer.valueOf(0), sauceOptions.getPriority());
        assertEquals(JobVisibility.TEAM, sauceOptions.getJobVisibility());
        assertEquals(false, sauceOptions.getRecordLogs());
        assertEquals(false, sauceOptions.getRecordScreenshots());
        assertEquals(false, sauceOptions.getRecordVideo());
        assertEquals("1024x768", sauceOptions.getScreenResolution());
        assertEquals(tags, sauceOptions.getTags());
        assertEquals("San Francisco", sauceOptions.getTimeZone());
        assertEquals("tunnelname", sauceOptions.getTunnelIdentifier());
        assertEquals(false, sauceOptions.getVideoUploadOnPass());
    }
}
