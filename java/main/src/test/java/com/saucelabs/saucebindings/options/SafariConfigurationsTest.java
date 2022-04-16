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
import org.openqa.selenium.safari.SafariOptions;

import java.time.Duration;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SafariConfigurationsTest {
    SafariOptions safariOptions = new SafariOptions();

    @Test
    public void buildsDefaultSauceOptions() {
        SauceOptions sauceOptions = SauceOptions.safari().build();

        Assert.assertEquals(Browser.SAFARI, sauceOptions.getBrowserName());
        Assert.assertEquals(SaucePlatform.MAC_CATALINA, sauceOptions.getPlatformName());
        Assert.assertEquals("latest", sauceOptions.getBrowserVersion());
    }

    @Test
    public void acceptsSafariOptionsClass() {
        safariOptions.setAutomaticProfiling(true);
        safariOptions.setUnhandledPromptBehaviour(UnexpectedAlertBehaviour.IGNORE);
        safariOptions.setCapability("sauce:options",
                ImmutableMap.of("build", "Build Name",
                        "maxDuration", 300));

        SauceOptions sauceOptions = SauceOptions.safari(safariOptions).build();

        Assert.assertEquals(Browser.SAFARI, sauceOptions.getBrowserName());
        Assert.assertEquals("Build Name", sauceOptions.sauce().getBuild());
        Assert.assertEquals(Integer.valueOf(300), sauceOptions.sauce().getMaxDuration());
        Assert.assertEquals(safariOptions, sauceOptions.getCapabilities());
    }

    @Test(expected = InvalidSauceOptionsArgumentException.class)
    public void errorsBadSafariOptionsCapability() {
        safariOptions.setCapability("invalid", "value");

        SauceOptions.safari(safariOptions).build();
    }

    @Test(expected = InvalidSauceOptionsArgumentException.class)
    public void errorsSafariOptionsBrowserMismatch() {
        safariOptions.setCapability("browserName", "firefox");

        SauceOptions.safari(safariOptions).build();
    }

    @Test(expected = InvalidSauceOptionsArgumentException.class)
    public void errorsBadSafariOptionsSauceCapability() {
        safariOptions.setCapability("sauce:options", ImmutableMap.of("invalid", "value"));

        SauceOptions.safari(safariOptions).build();
    }

    @Test(expected = InvalidSauceOptionsArgumentException.class)
    public void errorsBadSafariOptionsValue() {
        safariOptions.setCapability("unhandledPromptBehavior", "invalid");

        SauceOptions.safari(safariOptions).build();
    }

    @Test(expected = InvalidSauceOptionsArgumentException.class)
    public void errorsBadSafariOptionsSauceValue() {
        safariOptions.setCapability("sauce:options", ImmutableMap.of("jobVisibility", "invalid"));

        SauceOptions.safari(safariOptions).build();
    }

    @Test
    public void ignoresNameSpacedValues() {
        safariOptions.setCapability("foo:bar", ImmutableMap.of("matters", "not"));

        SauceOptions sauceOptions = SauceOptions.safari(safariOptions).build();
        Assert.assertNotNull(sauceOptions.getCapabilities().getCapability("foo:bar"));
    }

    @Test
    public void fluentOrderDoesNotMatter() {
        // Setting these in order from superclass methods to subclass to ensure proper return instances
        SauceOptions sauceOptions = SauceOptions.safari()
                .setPlatformName(SaucePlatform.MAC_HIGH_SIERRA)
                .setBrowserVersion("12")
                .setAvoidProxy()
                .build();

        Assert.assertEquals(SaucePlatform.MAC_HIGH_SIERRA, sauceOptions.getPlatformName());
        Assert.assertEquals("12", sauceOptions.getBrowserVersion());
        Assert.assertTrue(sauceOptions.sauce().getAvoidProxy());
    }

    @Test
    public void acceptsOtherW3CValues() {
        SauceOptions sauceOptions = SauceOptions.safari()
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

        SauceOptions sauceOptions = SauceOptions.safari()
                .setAvoidProxy()
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
                .setTags(tags)
                .setTimeZone("San Francisco")
                .setTunnelIdentifier("tunnelname")
                .disableVideoUploadOnPass()
                .build();

        Assert.assertEquals("Sample Build Name", sauceOptions.sauce().getBuild());
        Assert.assertEquals(Integer.valueOf(2), sauceOptions.sauce().getCommandTimeout());
        Assert.assertEquals(customData, sauceOptions.sauce().getCustomData());
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
        Assert.assertEquals("3.141.0", sauceOptions.sauce().getSeleniumVersion());
        Assert.assertEquals("1024x768", sauceOptions.sauce().getScreenResolution());
        Assert.assertEquals(tags, sauceOptions.sauce().getTags());
        Assert.assertEquals("San Francisco", sauceOptions.sauce().getTimeZone());
        Assert.assertEquals("tunnelname", sauceOptions.sauce().getTunnelIdentifier());
        Assert.assertEquals(false, sauceOptions.sauce().getVideoUploadOnPass());
    }
}
