package com.saucelabs.saucebindings.options;

import com.google.common.collect.ImmutableList;
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
import org.openqa.selenium.firefox.FirefoxOptions;

import java.time.Duration;
import java.util.HashMap;
import java.util.Map;

public class FirefoxConfigurationsTest {
    FirefoxOptions firefoxOptions = new FirefoxOptions();

    @Test
    public void buildsDefaultSauceOptions() {
        SauceOptions sauceOptions = SauceOptions.firefox().build();

        Assert.assertEquals(Browser.FIREFOX, sauceOptions.getBrowserName());
        Assert.assertEquals(SaucePlatform.WINDOWS_10, sauceOptions.getPlatformName());
        Assert.assertEquals("latest", sauceOptions.getBrowserVersion());
    }

    @Test
    public void acceptsFirefoxOptionsClass() {
        firefoxOptions.addArguments("--foo");
        firefoxOptions.setUnhandledPromptBehaviour(UnexpectedAlertBehaviour.IGNORE);
        firefoxOptions.setCapability("sauce:options",
                ImmutableMap.of("build", "Build Name",
                        "maxDuration", 300));

        SauceOptions sauceOptions = SauceOptions.firefox(firefoxOptions).build();

        Assert.assertEquals(Browser.FIREFOX, sauceOptions.getBrowserName());
        Assert.assertEquals("Build Name", sauceOptions.sauce().getBuild());
        Assert.assertEquals(Integer.valueOf(300), sauceOptions.sauce().getMaxDuration());
        Assert.assertEquals(firefoxOptions, sauceOptions.getCapabilities());
    }

    @Test(expected = InvalidSauceOptionsArgumentException.class)
    public void errorsBadFirefoxOptionsCapability() {
        firefoxOptions.setCapability("invalid", "capability");

        SauceOptions.firefox(firefoxOptions);
    }

    @Test(expected = InvalidSauceOptionsArgumentException.class)
    public void errorsFirefoxOptionsBrowserMismatch() {
        firefoxOptions.setCapability("browserName", "chrome");

        SauceOptions.firefox(firefoxOptions);
    }

    @Test(expected = InvalidSauceOptionsArgumentException.class)
    public void errorsBadFirefoxOptionsSauceCapability() {
        firefoxOptions.setCapability("sauce:options", ImmutableMap.of("invalid", "value"));

        SauceOptions.firefox(firefoxOptions);
    }

    @Test(expected = InvalidSauceOptionsArgumentException.class)
    public void errorsBadFirefoxOptionsValue() {
        firefoxOptions.setCapability("unhandledPromptBehavior", "invalid");

        SauceOptions.firefox(firefoxOptions);
    }

    @Test(expected = InvalidSauceOptionsArgumentException.class)
    public void errorsBadFirefoxOptionsSauceValue() {
        firefoxOptions.setCapability("sauce:options", ImmutableMap.of("jobVisibility", "invalid"));

        SauceOptions.firefox(firefoxOptions);
    }

    @Test
    public void ignoresNameSpacedValues() {
        firefoxOptions.setCapability("foo:bar", ImmutableMap.of("matters", "not"));

        SauceOptions sauceOptions = SauceOptions.firefox(firefoxOptions).build();
        Assert.assertNotNull(sauceOptions.getCapabilities().getCapability("foo:bar"));
    }

    @Test
    public void fluentOrderDoesNotMatter() {
        // Setting these in order from superclass methods to subclass to ensure proper return instances
        SauceOptions sauceOptions = SauceOptions.firefox()
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
        SauceOptions sauceOptions = SauceOptions.firefox()
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
        Map<Prerun, Object> prerun = new HashMap<>();
        prerun.put(Prerun.EXECUTABLE, "https://url.to/your/executable.exe");
        prerun.put(Prerun.ARGS, ImmutableList.of("--silent", "-a", "-q"));
        prerun.put(Prerun.BACKGROUND, false);
        prerun.put(Prerun.TIMEOUT, 120);

        SauceOptions sauceOptions = SauceOptions.firefox()
                .setBuild("Sample Build Name")
                .setName("Test name")
                .setGeckodriverVersion("0.28")
                .setCommandTimeout(Duration.ofSeconds(2))
                .setCustomData(ImmutableMap.of("foo", "foo", "bar", "bar"))
                .setExtendedDebugging()
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
                .setTags(ImmutableList.of("Foo", "Bar", "Foobar"))
                .setTimeZone("San Francisco")
                .setTunnelIdentifier("tunnelName")
                .disableVideoUploadOnPass()
                .build();

        Assert.assertEquals("Sample Build Name", sauceOptions.sauce().getBuild());
        Assert.assertEquals(Integer.valueOf(2), sauceOptions.sauce().getCommandTimeout());
        Assert.assertEquals(ImmutableMap.of("foo", "foo", "bar", "bar"), sauceOptions.sauce().getCustomData());
        Assert.assertEquals(true, sauceOptions.sauce().getExtendedDebugging());
        Assert.assertEquals("0.28", sauceOptions.sauce().getGeckodriverVersion());
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
        Assert.assertEquals(ImmutableList.of("Foo", "Bar", "Foobar"), sauceOptions.sauce().getTags());
        Assert.assertEquals("San Francisco", sauceOptions.sauce().getTimeZone());
        Assert.assertEquals("tunnelName", sauceOptions.sauce().getTunnelIdentifier());
        Assert.assertEquals(false, sauceOptions.sauce().getVideoUploadOnPass());
    }
}
