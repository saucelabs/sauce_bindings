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
import org.openqa.selenium.ie.InternetExplorerOptions;

import java.time.Duration;
import java.util.HashMap;
import java.util.Map;

public class InternetExplorerConfigurationsTest {
    InternetExplorerOptions internetExplorerOptions = new InternetExplorerOptions();

    @Test
    public void buildsDefaultSauceOptions() {
        SauceOptions sauceOptions = SauceOptions.ie().build();

        Assert.assertEquals(Browser.INTERNET_EXPLORER, sauceOptions.getBrowserName());
        Assert.assertEquals(SaucePlatform.WINDOWS_10, sauceOptions.getPlatformName());
        Assert.assertEquals("latest", sauceOptions.getBrowserVersion());
    }

    @Test
    public void acceptsIEOptionsClass() {
        internetExplorerOptions.requireWindowFocus();
        internetExplorerOptions.setUnhandledPromptBehaviour(UnexpectedAlertBehaviour.IGNORE);
        internetExplorerOptions.setCapability("sauce:options",
                ImmutableMap.of("build", "Build Name",
                        "maxDuration", 300));

        SauceOptions sauceOptions = SauceOptions.ie(internetExplorerOptions).build();

        Assert.assertEquals(Browser.INTERNET_EXPLORER, sauceOptions.getBrowserName());
        Assert.assertEquals("Build Name", sauceOptions.sauce().getBuild());
        Assert.assertEquals(Integer.valueOf(300), sauceOptions.sauce().getMaxDuration());
        Assert.assertEquals(internetExplorerOptions, sauceOptions.getCapabilities());
    }

    @Test(expected = InvalidSauceOptionsArgumentException.class)
    public void errorsBadInternetExplorerOptionsCapability() {
        internetExplorerOptions.setCapability("invalid", "capability");

        SauceOptions.ie(internetExplorerOptions);
    }

    @Test(expected = InvalidSauceOptionsArgumentException.class)
    public void errorsInternetExplorerOptionsBrowserMismatch() {
        internetExplorerOptions.setCapability("browserName", "firefox");

        SauceOptions.ie(internetExplorerOptions);
    }

    @Test(expected = InvalidSauceOptionsArgumentException.class)
    public void errorsBadInternetExplorerOptionsSauceCapability() {
        internetExplorerOptions.setCapability("sauce:options", ImmutableMap.of("invalid", "value"));

        SauceOptions.ie(internetExplorerOptions);
    }

    @Test(expected = InvalidSauceOptionsArgumentException.class)
    public void errorsBadInternetExplorerOptionsValue() {
        internetExplorerOptions.setCapability("unhandledPromptBehavior", "invalid");

        SauceOptions.ie(internetExplorerOptions);
    }

    @Test(expected = InvalidSauceOptionsArgumentException.class)
    public void errorsBadInternetExplorerOptionsSauceValue() {
        internetExplorerOptions.setCapability("sauce:options", ImmutableMap.of("jobVisibility", "invalid"));

        SauceOptions.ie(internetExplorerOptions);
    }

    @Test
    public void ignoresNameSpacedValues() {
        internetExplorerOptions.setCapability("foo:bar", ImmutableMap.of("matters", "not"));

        SauceOptions sauceOptions = SauceOptions.ie(internetExplorerOptions).build();
        Assert.assertNotNull(sauceOptions.getCapabilities().getCapability("foo:bar"));
    }

    @Test
    public void fluentOrderDoesNotMatter() {
        // Setting these in order from superclass methods to subclass to ensure proper return instances
        SauceOptions sauceOptions = SauceOptions.ie()
                .setPlatformName(SaucePlatform.WINDOWS_8)
                .setBrowserVersion("10")
                .setAvoidProxy()
                .build();

        Assert.assertEquals(SaucePlatform.WINDOWS_8, sauceOptions.getPlatformName());
        Assert.assertEquals("10", sauceOptions.getBrowserVersion());
        Assert.assertTrue(sauceOptions.sauce().getAvoidProxy());
    }

    @Test
    public void acceptsOtherW3CValues() {
        SauceOptions sauceOptions = SauceOptions.ie()
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

        SauceOptions sauceOptions = SauceOptions.ie()
                .setAvoidProxy()
                .setBuild("Sample Build Name")
                .setName("Test name")
                .setCommandTimeout(Duration.ofSeconds(2))
                .setCustomData(ImmutableMap.of("foo", "foo", "bar", "bar"))
                .setIdleTimeout(Duration.ofSeconds(20))
                .setIedriverVersion("4.1")
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
        Assert.assertEquals(Integer.valueOf(20), sauceOptions.sauce().getIdleTimeout());
        Assert.assertEquals(Integer.valueOf(300), sauceOptions.sauce().getMaxDuration());
        Assert.assertEquals("Test name", sauceOptions.sauce().getName());
        Assert.assertEquals("4.1", sauceOptions.sauce().getIedriverVersion());
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
