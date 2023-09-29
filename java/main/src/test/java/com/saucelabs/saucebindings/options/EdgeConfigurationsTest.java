package com.saucelabs.saucebindings.options;

import com.saucelabs.saucebindings.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.edge.EdgeOptions;

import java.time.Duration;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class EdgeConfigurationsTest {

    @Test
    public void buildsDefaultSauceOptions() {
        SauceOptions sauceOptions = SauceOptions.edge().build();

        Assertions.assertEquals(Browser.EDGE, sauceOptions.getBrowserName());
        Assertions.assertEquals(SaucePlatform.WINDOWS_10, sauceOptions.getPlatformName());
        Assertions.assertEquals("latest", sauceOptions.getBrowserVersion());
    }

    @Test
    public void acceptsEdgeOptionsClass() {
        EdgeOptions edgeOptions = new EdgeOptions();

        SauceOptions sauceOptions = SauceOptions.edge(edgeOptions).build();

        Assertions.assertEquals(Browser.EDGE, sauceOptions.getBrowserName());
        Assertions.assertEquals(edgeOptions, sauceOptions.getCapabilities());
    }

    @Test
    public void fluentOrderDoesNotMatter() {
        // Setting these in order from superclass methods to subclass to ensure proper return instances
        SauceOptions sauceOptions = SauceOptions.edge()
                .setPlatformName(SaucePlatform.MAC_HIGH_SIERRA)
                .setBrowserVersion("68")
                .setSeleniumVersion("3.141.1")
                .build();

        Assertions.assertEquals(SaucePlatform.MAC_HIGH_SIERRA, sauceOptions.getPlatformName());
        Assertions.assertEquals("68", sauceOptions.getBrowserVersion());
        Assertions.assertEquals("3.141.1", sauceOptions.sauce().getSeleniumVersion());
    }

    @Test
    public void acceptsOtherW3CValues() {
        SauceOptions sauceOptions = SauceOptions.edge()
                .setAcceptInsecureCerts()
                .setPageLoadStrategy(PageLoadStrategy.EAGER)
                .setUnhandledPromptBehavior(UnhandledPromptBehavior.IGNORE)
                .setStrictFileInteractability()
                .setImplicitWaitTimeout(Duration.ofSeconds(1))
                .setPageLoadTimeout(Duration.ofSeconds(100))
                .setScriptTimeout(Duration.ofSeconds(10))
                .build();

        Assertions.assertEquals(true, sauceOptions.getAcceptInsecureCerts());
        Assertions.assertEquals(PageLoadStrategy.EAGER, sauceOptions.getPageLoadStrategy());
        Assertions.assertEquals(UnhandledPromptBehavior.IGNORE, sauceOptions.getUnhandledPromptBehavior());
        Assertions.assertEquals(true, sauceOptions.getStrictFileInteractability());
        Assertions.assertEquals(Duration.ofSeconds(1), sauceOptions.getImplicitWaitTimeout());
        Assertions.assertEquals(Duration.ofSeconds(100), sauceOptions.getPageLoadTimeout());
        Assertions.assertEquals(Duration.ofSeconds(10), sauceOptions.getScriptTimeout());
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

        SauceOptions sauceOptions = SauceOptions.edge()
                .setBuild("Sample Build Name")
                .setName("Test name")
                .setCommandTimeout(Duration.ofSeconds(2))
                .setCustomData(customData)
                .setIdleTimeout(Duration.ofSeconds(20))
                .setMaxDuration(Duration.ofSeconds(300))
                .setParentTunnel("Mommy")
                .setPrerun(prerun)
                .setPriority(0)
                .setJobVisibility(JobVisibility.TEAM)
                .setSeleniumVersion("3.141.0")
                .disableRecordLogs()
                .disableRecordScreenshots()
                .disableRecordVideo()
                .setScreenResolution("1024x768")
                .setSeleniumVersion("3.141.0")
                .setTags(tags)
                .setTimeZone("San Francisco")
                .setTunnelIdentifier("tunnelname")
                .disableVideoUploadOnPass()
                .build();

        Assertions.assertEquals("Sample Build Name", sauceOptions.sauce().getBuild());
        Assertions.assertEquals(Integer.valueOf(2), sauceOptions.sauce().getCommandTimeout());
        Assertions.assertEquals(customData, sauceOptions.sauce().getCustomData());
        Assertions.assertEquals(Integer.valueOf(20), sauceOptions.sauce().getIdleTimeout());
        Assertions.assertEquals(Integer.valueOf(300), sauceOptions.sauce().getMaxDuration());
        Assertions.assertEquals("Test name", sauceOptions.sauce().getName());
        Assertions.assertEquals("Mommy", sauceOptions.sauce().getParentTunnel());
        Assertions.assertEquals(prerun, sauceOptions.sauce().getPrerun());
        Assertions.assertEquals(Integer.valueOf(0), sauceOptions.sauce().getPriority());
        Assertions.assertEquals(JobVisibility.TEAM, sauceOptions.sauce().getJobVisibility());
        Assertions.assertEquals(false, sauceOptions.sauce().getRecordLogs());
        Assertions.assertEquals(false, sauceOptions.sauce().getRecordScreenshots());
        Assertions.assertEquals(false, sauceOptions.sauce().getRecordVideo());
        Assertions.assertEquals("3.141.0", sauceOptions.sauce().getSeleniumVersion());
        Assertions.assertEquals("1024x768", sauceOptions.sauce().getScreenResolution());
        Assertions.assertEquals(tags, sauceOptions.sauce().getTags());
        Assertions.assertEquals("San Francisco", sauceOptions.sauce().getTimeZone());
        Assertions.assertEquals("tunnelname", sauceOptions.sauce().getTunnelIdentifier());
        Assertions.assertEquals(false, sauceOptions.sauce().getVideoUploadOnPass());
    }
}
