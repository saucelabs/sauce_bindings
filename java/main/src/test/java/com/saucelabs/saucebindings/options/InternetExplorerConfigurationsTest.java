package com.saucelabs.saucebindings.options;

import com.saucelabs.saucebindings.*;
import java.time.Duration;
import java.util.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.UnexpectedAlertBehaviour;
import org.openqa.selenium.ie.InternetExplorerOptions;

public class InternetExplorerConfigurationsTest {

  @Test
  public void buildsDefaultSauceOptions() {
    SauceOptions sauceOptions = SauceOptions.ie().build();

    Assertions.assertEquals(Browser.INTERNET_EXPLORER, sauceOptions.getBrowserName());
    Assertions.assertEquals(SaucePlatform.WINDOWS_10, sauceOptions.getPlatformName());
    Assertions.assertEquals("latest", sauceOptions.getBrowserVersion());
  }

  @Test
  public void acceptsIEOptionsClass() {
    InternetExplorerOptions internetExplorerOptions = new InternetExplorerOptions();
    internetExplorerOptions.requireWindowFocus();
    internetExplorerOptions.setCapability(
        "UnexpectedAlertBehaviour", UnexpectedAlertBehaviour.DISMISS);

    SauceOptions sauceOptions = SauceOptions.ie(internetExplorerOptions).build();

    Assertions.assertEquals(Browser.INTERNET_EXPLORER, sauceOptions.getBrowserName());
    Assertions.assertEquals(internetExplorerOptions, sauceOptions.getCapabilities());
  }

  @Test
  public void fluentOrderDoesNotMatter() {
    // Setting these in order from superclass methods to subclass to ensure proper return instances
    SauceOptions sauceOptions =
        SauceOptions.ie()
            .setPlatformName(SaucePlatform.WINDOWS_8)
            .setBrowserVersion("10")
            .setAvoidProxy()
            .build();

    Assertions.assertEquals(SaucePlatform.WINDOWS_8, sauceOptions.getPlatformName());
    Assertions.assertEquals("10", sauceOptions.getBrowserVersion());
    Assertions.assertTrue(sauceOptions.sauce().getAvoidProxy());
  }

  @Test
  public void acceptsOtherW3CValues() {
    SauceOptions sauceOptions =
        SauceOptions.ie()
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
    Assertions.assertEquals(
        UnhandledPromptBehavior.IGNORE, sauceOptions.getUnhandledPromptBehavior());
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

    SauceOptions sauceOptions =
        SauceOptions.ie()
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

    // Fix up the tags in the "expected" option to account for the sauce-bindings tags
    List<String> expectedTags = new ArrayList<>(Arrays.asList("sauce-bindings", "java"));
    expectedTags.addAll(tags);

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
    Assertions.assertEquals(expectedTags, sauceOptions.sauce().getTags());
    Assertions.assertEquals("San Francisco", sauceOptions.sauce().getTimeZone());
    Assertions.assertEquals("tunnelname", sauceOptions.sauce().getTunnelIdentifier());
    Assertions.assertEquals(false, sauceOptions.sauce().getVideoUploadOnPass());
  }
}
