package com.saucelabs.saucebindings.configs;

import com.saucelabs.saucebindings.*;
import com.saucelabs.saucebindings.options.SauceOptionsFactory;
import org.junit.Test;
import org.openqa.selenium.ie.InternetExplorerOptions;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class SauceIEConfigsTest {
    @Test
    public void createsSauceOptionsW3CValues() {
        SauceOptions options = SauceOptionsFactory.internetExplorer()
                .setPlatformName(SaucePlatform.MAC_CATALINA)
                .setBrowserVersion("77")
                .setPageLoadStrategy(PageLoadStrategy.EAGER)
                .setAcceptInsecureCerts(true)
                .setSetWindowRect(true)
                .setUnhandledPromptBehavior(UnhandledPromptBehavior.IGNORE)
                .setStrictFileInteractability(true)
                .setImplicitWaitTimeout(1)
                .setPageLoadTimeout(100)
                .setScriptTimeout(10)
                .build();

        Map<Timeouts, Integer> timeouts = new HashMap<>();
        timeouts.put(Timeouts.IMPLICIT, 1);
        timeouts.put(Timeouts.PAGE_LOAD, 100);
        timeouts.put(Timeouts.SCRIPT, 10);

        assertEquals(Browser.INTERNET_EXPLORER, options.getBrowserName());
        assertEquals(SaucePlatform.MAC_CATALINA, options.getPlatformName());
        assertEquals("77", options.getBrowserVersion());
        assertEquals(PageLoadStrategy.EAGER, options.getPageLoadStrategy());
        assertTrue(options.getAcceptInsecureCerts());
        assertTrue(options.getSetWindowRect());
        assertEquals(UnhandledPromptBehavior.IGNORE, options.getUnhandledPromptBehavior());
        assertTrue(options.getStrictFileInteractability());
        assertEquals(timeouts, options.getTimeouts());
    }

    @Test
    public void createsSauceOptionsInternetExplorerValues() {
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

        SauceOptions sauceOptions = SauceOptionsFactory.internetExplorer()
                .setPlatformName(SaucePlatform.MAC_CATALINA)
                .setBrowserVersion("77")
                .sauce()
                        .setAvoidProxy(true)
                        .setBuild("Sample Build Name")
                        .setCommandTimeout(2)
                        .setCustomData(customData)
                        .setIdleTimeout(3)
                        .setIedriverVersion("3.14")
                        .setMaxDuration(300)
                        .setName("Test name")
                        .setParentTunnel("Mommy")
                        .setPrerun(prerun)
                        .setPriority(0)
                        .setJobVisibility(JobVisibility.TEAM)
                        .setRecordLogs(false)
                        .setRecordScreenshots(false)
                        .setRecordVideo(false)
                        .setScreenResolution("10x10")
                        .setSeleniumVersion("3.141.0")
                        .setTags(tags)
                        .setTimeZone("San Francisco")
                        .setTunnelIdentifier("tunnelname")
                        .setVideoUploadOnPass(false)
                        .build()
                .build();

        assertEquals(Browser.INTERNET_EXPLORER, sauceOptions.getBrowserName());
        assertEquals(SaucePlatform.MAC_CATALINA, sauceOptions.getPlatformName());
        assertEquals("77", sauceOptions.getBrowserVersion());

        assertTrue(sauceOptions.sauce().getAvoidProxy());
        assertEquals("Sample Build Name", sauceOptions.sauce().getBuild());
        assertEquals(Integer.valueOf(2), sauceOptions.sauce().getCommandTimeout());
        assertEquals(customData, sauceOptions.sauce().getCustomData());
        assertEquals(Integer.valueOf(3), sauceOptions.sauce().getIdleTimeout());
        assertEquals("3.14", sauceOptions.sauce().getIedriverVersion());
        assertEquals(Integer.valueOf(300), sauceOptions.sauce().getMaxDuration());
        assertEquals("Test name", sauceOptions.sauce().getName());
        assertEquals("Mommy", sauceOptions.sauce().getParentTunnel());
        assertEquals(prerun, sauceOptions.sauce().getPrerun());
        assertEquals(Integer.valueOf(0), sauceOptions.sauce().getPriority());
        assertEquals(JobVisibility.TEAM, sauceOptions.sauce().getJobVisibility());
        assertEquals(false, sauceOptions.sauce().getRecordLogs());
        assertEquals(false, sauceOptions.sauce().getRecordScreenshots());
        assertEquals(false, sauceOptions.sauce().getRecordVideo());
        assertEquals("10x10", sauceOptions.sauce().getScreenResolution());
        assertEquals("3.141.0", sauceOptions.sauce().getSeleniumVersion());
        assertEquals(tags, sauceOptions.sauce().getTags());
        assertEquals("San Francisco", sauceOptions.sauce().getTimeZone());
        assertEquals("tunnelname", sauceOptions.sauce().getTunnelIdentifier());
        assertEquals(false, sauceOptions.sauce().getVideoUploadOnPass());
    }

    @Test
    public void createsInternetExplorerOptionsInternetExplorerValues() {
        InternetExplorerOptions internetExplorerOptions = new InternetExplorerOptions();
        internetExplorerOptions.requireWindowFocus();

        SauceOptions sauceOptions = SauceOptionsFactory.internetExplorer(internetExplorerOptions)
                .setPlatformName(SaucePlatform.MAC_CATALINA)
                .setBrowserVersion("77").build();

        assertEquals(Browser.INTERNET_EXPLORER, sauceOptions.getBrowserName());
        assertEquals(SaucePlatform.MAC_CATALINA, sauceOptions.getPlatformName());
        assertEquals("77", sauceOptions.getBrowserVersion());
        assertEquals(internetExplorerOptions, sauceOptions.getCapabilities());
    }

    @Test
    public void createsVisualValues() {
        SauceOptions sauceOptions = SauceOptionsFactory.internetExplorer()
                .visual()
                    .setProjectName("Project Name")
                    .build()
                .sauce()
                    .setBuild("My Build")
                    .build()
                .build();
        assertEquals("Project Name", sauceOptions.visual().getProjectName());
        assertEquals("My Build", sauceOptions.sauce().getBuild());
        assertEquals(Browser.INTERNET_EXPLORER, sauceOptions.getBrowserName());
    }
}
