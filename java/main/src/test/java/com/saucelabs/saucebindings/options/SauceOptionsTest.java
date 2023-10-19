package com.saucelabs.saucebindings.options;

import com.saucelabs.saucebindings.*;
import java.io.FileInputStream;
import java.io.InputStream;
import java.time.Duration;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import lombok.SneakyThrows;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.MutableCapabilities;
import org.openqa.selenium.UnexpectedAlertBehaviour;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.yaml.snakeyaml.Yaml;

public class SauceOptionsTest {
  private SauceOptions sauceOptions = new SauceOptions();

  @Test
  public void usesLatestChromeWindowsVersionsByDefault() {
    Assertions.assertEquals(Browser.CHROME, sauceOptions.getBrowserName());
    Assertions.assertEquals(SaucePlatform.WINDOWS_10, sauceOptions.getPlatformName());
    Assertions.assertEquals("latest", sauceOptions.getBrowserVersion());
  }

  @Test
  public void updatesBrowserBrowserVersionPlatformVersionValues() {
    sauceOptions.setBrowserName(Browser.FIREFOX);
    sauceOptions.setPlatformName(SaucePlatform.MAC_HIGH_SIERRA);
    sauceOptions.setBrowserVersion("68");

    Assertions.assertEquals(Browser.FIREFOX, sauceOptions.getBrowserName());
    Assertions.assertEquals("68", sauceOptions.getBrowserVersion());
    Assertions.assertEquals(SaucePlatform.MAC_HIGH_SIERRA, sauceOptions.getPlatformName());
  }

  @Test
  public void fluentPatternWorks() {
    sauceOptions
        .setBrowserName(Browser.FIREFOX)
        .setBrowserVersion("68")
        .setPlatformName(SaucePlatform.MAC_HIGH_SIERRA);

    Assertions.assertEquals(Browser.FIREFOX, sauceOptions.getBrowserName());
    Assertions.assertEquals("68", sauceOptions.getBrowserVersion());
    Assertions.assertEquals(SaucePlatform.MAC_HIGH_SIERRA, sauceOptions.getPlatformName());
  }

  @Test
  public void acceptsOtherW3CValues() {
    sauceOptions.setAcceptInsecureCerts(true);
    sauceOptions.setPageLoadStrategy(PageLoadStrategy.EAGER);
    sauceOptions.setSetWindowRect(true);
    sauceOptions.setUnhandledPromptBehavior(UnhandledPromptBehavior.IGNORE);
    sauceOptions.setStrictFileInteractability(true);

    sauceOptions.timeout.setImplicitWait(1000);
    sauceOptions.timeout.setPageLoad(100000);
    sauceOptions.timeout.setScript(10000);

    Assertions.assertEquals(true, sauceOptions.getAcceptInsecureCerts());
    Assertions.assertEquals(PageLoadStrategy.EAGER, sauceOptions.getPageLoadStrategy());
    Assertions.assertEquals(true, sauceOptions.getSetWindowRect());
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

    sauceOptions.sauce().setAvoidProxy(true);
    sauceOptions.sauce().setBuild("Sample Build Name");
    sauceOptions.sauce().setCapturePerformance(true);
    sauceOptions.sauce().setChromedriverVersion("71");
    sauceOptions.sauce().setCommandTimeout(2);
    sauceOptions.sauce().setCustomData(customData);
    sauceOptions.sauce().setExtendedDebugging(true);
    sauceOptions.sauce().setIdleTimeout(3);
    sauceOptions.sauce().setIedriverVersion("3.141.0");
    sauceOptions.sauce().setMaxDuration(300);
    sauceOptions.sauce().setName("Test name");
    sauceOptions.sauce().setParentTunnel("Mommy");
    sauceOptions.sauce().setPrerun(prerun);
    sauceOptions.sauce().setPriority(0);
    sauceOptions.sauce().setJobVisibility(JobVisibility.TEAM);
    sauceOptions.sauce().setRecordLogs(false);
    sauceOptions.sauce().setRecordScreenshots(false);
    sauceOptions.sauce().setRecordVideo(false);
    sauceOptions.sauce().setScreenResolution("10x10");
    sauceOptions.sauce().setSeleniumVersion("3.141.59");
    sauceOptions.sauce().setTags(tags);
    sauceOptions.sauce().setTimeZone("San Francisco");
    sauceOptions.sauce().setTunnelIdentifier("tunnelname");
    sauceOptions.sauce().setVideoUploadOnPass(false);

    Assertions.assertEquals(true, sauceOptions.sauce().getAvoidProxy());
    Assertions.assertEquals("Sample Build Name", sauceOptions.sauce().getBuild());
    Assertions.assertEquals(true, sauceOptions.sauce().getCapturePerformance());
    Assertions.assertEquals("71", sauceOptions.sauce().getChromedriverVersion());
    Assertions.assertEquals(Integer.valueOf(2), sauceOptions.sauce().getCommandTimeout());
    Assertions.assertEquals(customData, sauceOptions.sauce().getCustomData());
    Assertions.assertEquals(true, sauceOptions.sauce().getExtendedDebugging());
    Assertions.assertEquals(Integer.valueOf(3), sauceOptions.sauce().getIdleTimeout());
    Assertions.assertEquals("3.141.0", sauceOptions.sauce().getIedriverVersion());
    Assertions.assertEquals(Integer.valueOf(300), sauceOptions.sauce().getMaxDuration());
    Assertions.assertEquals("Test name", sauceOptions.sauce().getName());
    Assertions.assertEquals("Mommy", sauceOptions.sauce().getParentTunnel());
    Assertions.assertEquals(prerun, sauceOptions.sauce().getPrerun());
    Assertions.assertEquals(Integer.valueOf(0), sauceOptions.sauce().getPriority());
    Assertions.assertEquals(JobVisibility.TEAM, sauceOptions.sauce().getJobVisibility());
    Assertions.assertEquals(false, sauceOptions.sauce().getRecordLogs());
    Assertions.assertEquals(false, sauceOptions.sauce().getRecordScreenshots());
    Assertions.assertEquals(false, sauceOptions.sauce().getRecordVideo());
    Assertions.assertEquals("10x10", sauceOptions.sauce().getScreenResolution());
    Assertions.assertEquals("3.141.59", sauceOptions.sauce().getSeleniumVersion());
    Assertions.assertEquals(tags, sauceOptions.sauce().getTags());
    Assertions.assertEquals("San Francisco", sauceOptions.sauce().getTimeZone());
    Assertions.assertEquals("tunnelname", sauceOptions.sauce().getTunnelIdentifier());
    Assertions.assertEquals(false, sauceOptions.sauce().getVideoUploadOnPass());
  }

  @Test
  public void createsDefaultBuildName() {
    Assertions.assertNotNull(sauceOptions.sauce().getBuild());
  }

  @SneakyThrows
  public Map<String, Object> serialize(String key) {
    InputStream input =
        new FileInputStream("src/test/java/com/saucelabs/saucebindings/options.yml");
    Yaml yaml = new Yaml();
    Map<String, Object> data = yaml.load(input);
    return (Map<String, Object>) data.get(key);
  }

  @Test
  public void setsCapabilitiesFromMap() {
    Map<String, Object> map = serialize("exampleValues");

    sauceOptions.mergeCapabilities(map);

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
    tags.add("foo");
    tags.add("bar");
    tags.add("foobar");

    Assertions.assertEquals(Browser.FIREFOX, sauceOptions.getBrowserName());
    Assertions.assertEquals("68", sauceOptions.getBrowserVersion());
    Assertions.assertEquals(SaucePlatform.MAC_HIGH_SIERRA, sauceOptions.getPlatformName());
    Assertions.assertEquals(true, sauceOptions.getAcceptInsecureCerts());
    Assertions.assertEquals(PageLoadStrategy.EAGER, sauceOptions.getPageLoadStrategy());
    Assertions.assertEquals(true, sauceOptions.getSetWindowRect());
    Assertions.assertEquals(
        UnhandledPromptBehavior.ACCEPT, sauceOptions.getUnhandledPromptBehavior());
    Assertions.assertEquals(true, sauceOptions.getStrictFileInteractability());
    Assertions.assertEquals(Duration.ofSeconds(1), sauceOptions.getImplicitWaitTimeout());
    Assertions.assertEquals(Duration.ofSeconds(59), sauceOptions.getPageLoadTimeout());
    Assertions.assertEquals(Duration.ofSeconds(29), sauceOptions.getScriptTimeout());
    Assertions.assertEquals(true, sauceOptions.sauce().getAvoidProxy());
    Assertions.assertEquals("Sample Build Name", sauceOptions.sauce().getBuild());
    Assertions.assertEquals(true, sauceOptions.sauce().getCapturePerformance());
    Assertions.assertEquals("71", sauceOptions.sauce().getChromedriverVersion());
    Assertions.assertEquals(Integer.valueOf(2), sauceOptions.sauce().getCommandTimeout());
    Assertions.assertEquals(customData, sauceOptions.sauce().getCustomData());
    Assertions.assertEquals(true, sauceOptions.sauce().getExtendedDebugging());
    Assertions.assertEquals(Integer.valueOf(3), sauceOptions.sauce().getIdleTimeout());
    Assertions.assertEquals("3.141.0", sauceOptions.sauce().getIedriverVersion());
    Assertions.assertEquals(Integer.valueOf(300), sauceOptions.sauce().getMaxDuration());
    Assertions.assertEquals("Sample Test Name", sauceOptions.sauce().getName());
    Assertions.assertEquals("Mommy", sauceOptions.sauce().getParentTunnel());
    Assertions.assertEquals(prerun, sauceOptions.sauce().getPrerun());
    Assertions.assertEquals(Integer.valueOf(0), sauceOptions.sauce().getPriority());
    Assertions.assertEquals(JobVisibility.TEAM, sauceOptions.sauce().getJobVisibility());
    Assertions.assertEquals(false, sauceOptions.sauce().getRecordLogs());
    Assertions.assertEquals(false, sauceOptions.sauce().getRecordScreenshots());
    Assertions.assertEquals(false, sauceOptions.sauce().getRecordVideo());
    Assertions.assertEquals("10x10", sauceOptions.sauce().getScreenResolution());
    Assertions.assertEquals("3.141.59", sauceOptions.sauce().getSeleniumVersion());
    Assertions.assertEquals(tags, sauceOptions.sauce().getTags());
    Assertions.assertEquals("San Francisco", sauceOptions.sauce().getTimeZone());
    Assertions.assertEquals("tunnelname", sauceOptions.sauce().getTunnelIdentifier());
    Assertions.assertEquals(false, sauceOptions.sauce().getVideoUploadOnPass());
  }

  @Test
  public void setsBadBrowserFromMap() {
    Map<String, Object> map = serialize("badBrowser");

    Assertions.assertThrows(
        InvalidSauceOptionsArgumentException.class, () -> sauceOptions.mergeCapabilities(map));
  }

  @Test
  public void setsBadPlatformFromMap() {
    Map<String, Object> map = serialize("badPlatform");
    Assertions.assertThrows(
        InvalidSauceOptionsArgumentException.class, () -> sauceOptions.mergeCapabilities(map));
  }

  @Test
  public void setsBadJobVisibilityFromMap() {
    Map<String, Object> map = serialize("badJobVisibility");
    Assertions.assertThrows(
        InvalidSauceOptionsArgumentException.class, () -> sauceOptions.mergeCapabilities(map));
  }

  @Test
  public void setsBadPromptFromMap() {
    Map<String, Object> map = serialize("badPrompt");
    Assertions.assertThrows(
        InvalidSauceOptionsArgumentException.class, () -> sauceOptions.mergeCapabilities(map));
  }

  @Test
  public void setsBadTimeoutFromMap() {
    Map<String, Object> map = serialize("badTimeout");
    Assertions.assertThrows(
        InvalidSauceOptionsArgumentException.class, () -> sauceOptions.mergeCapabilities(map));
  }

  @Test
  public void setsBadPrerunFromMap() {
    Map<String, Object> map = serialize("badPrerun");
    Assertions.assertThrows(
        InvalidSauceOptionsArgumentException.class, () -> sauceOptions.mergeCapabilities(map));
  }

  @Test
  public void setsBadPageLoadFromMap() {
    Map<String, Object> map = serialize("badPageLoad");
    Assertions.assertThrows(
        InvalidSauceOptionsArgumentException.class, () -> sauceOptions.mergeCapabilities(map));
  }

  @Test
  public void parsesCapabilitiesFromW3CValues() {
    sauceOptions.setBrowserName(Browser.FIREFOX);
    sauceOptions.setPlatformName(SaucePlatform.MAC_BIG_SUR);
    sauceOptions.setBrowserVersion("77");
    sauceOptions.setAcceptInsecureCerts(true);
    sauceOptions.setPageLoadStrategy(PageLoadStrategy.EAGER);
    sauceOptions.setSetWindowRect(true);
    sauceOptions.setUnhandledPromptBehavior(UnhandledPromptBehavior.IGNORE);
    sauceOptions.setStrictFileInteractability(true);
    sauceOptions.timeout.setImplicitWait(1000);
    sauceOptions.timeout.setPageLoad(100000);
    sauceOptions.timeout.setScript(10000);
    sauceOptions.sauce().setBuild("Build Name");

    Map<Timeouts, Integer> timeouts = new HashMap<>();
    timeouts.put(Timeouts.IMPLICIT, 1000);
    timeouts.put(Timeouts.SCRIPT, 10000);
    timeouts.put(Timeouts.PAGE_LOAD, 100000);

    MutableCapabilities expectedCapabilities = new MutableCapabilities();
    expectedCapabilities.setCapability("browserName", "firefox");
    expectedCapabilities.setCapability("browserVersion", "77");
    expectedCapabilities.setCapability("platformName", "macOS 11");
    expectedCapabilities.setCapability("acceptInsecureCerts", true);
    expectedCapabilities.setCapability("setWindowRect", true);
    expectedCapabilities.setCapability("strictFileInteractability", true);
    expectedCapabilities.setCapability("pageLoadStrategy", PageLoadStrategy.EAGER);
    expectedCapabilities.setCapability("timeouts", timeouts);
    expectedCapabilities.setCapability("unhandledPromptBehavior", UnhandledPromptBehavior.IGNORE);

    MutableCapabilities sauceCapabilities = new MutableCapabilities();
    sauceCapabilities.setCapability("build", "Build Name");
    sauceCapabilities.setCapability("username", SystemManager.get("SAUCE_USERNAME"));
    sauceCapabilities.setCapability("accessKey", SystemManager.get("SAUCE_ACCESS_KEY"));
    expectedCapabilities.setCapability("sauce:options", sauceCapabilities);
    MutableCapabilities actualCapabilities = sauceOptions.toCapabilities();

    Assertions.assertEquals(
        expectedCapabilities.asMap().toString(), actualCapabilities.asMap().toString());
  }

  @Test
  public void parsesCapabilitiesFromSauceValues() {
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

    sauceOptions.sauce().setAvoidProxy(true);
    sauceOptions.sauce().setBuild("Sample Build Name");
    sauceOptions.sauce().setCapturePerformance(true);
    sauceOptions.sauce().setChromedriverVersion("71");
    sauceOptions.sauce().setCommandTimeout(2);
    sauceOptions.sauce().setCustomData(customData);
    sauceOptions.sauce().setExtendedDebugging(true);
    sauceOptions.sauce().setIdleTimeout(3);
    sauceOptions.sauce().setIedriverVersion("3.141.0");
    sauceOptions.sauce().setMaxDuration(300);
    sauceOptions.sauce().setName("Test name");
    sauceOptions.sauce().setParentTunnel("Mommy");
    sauceOptions.sauce().setPrerun(prerun);
    sauceOptions.sauce().setPriority(0);
    sauceOptions.sauce().setJobVisibility(JobVisibility.TEAM);
    sauceOptions.sauce().setRecordLogs(false);
    sauceOptions.sauce().setRecordScreenshots(false);
    sauceOptions.sauce().setRecordVideo(false);
    sauceOptions.sauce().setScreenResolution("10x10");
    sauceOptions.sauce().setSeleniumVersion("3.141.59");
    sauceOptions.sauce().setTags(tags);
    sauceOptions.sauce().setTimeZone("San Francisco");
    sauceOptions.sauce().setTunnelIdentifier("tunnelname");
    sauceOptions.sauce().setVideoUploadOnPass(false);

    MutableCapabilities sauceCapabilities = new MutableCapabilities();
    sauceCapabilities.setCapability("avoidProxy", true);
    sauceCapabilities.setCapability("build", "Sample Build Name");
    sauceCapabilities.setCapability("capturePerformance", true);
    sauceCapabilities.setCapability("chromedriverVersion", "71");
    sauceCapabilities.setCapability("commandTimeout", 2);
    sauceCapabilities.setCapability("custom-data", customData);
    sauceCapabilities.setCapability("extendedDebugging", true);
    sauceCapabilities.setCapability("idleTimeout", 3);
    sauceCapabilities.setCapability("iedriverVersion", "3.141.0");
    sauceCapabilities.setCapability("maxDuration", 300);
    sauceCapabilities.setCapability("name", "Test name");
    sauceCapabilities.setCapability("parentTunnel", "Mommy");
    sauceCapabilities.setCapability("prerun", prerun);
    sauceCapabilities.setCapability("priority", 0);
    sauceCapabilities.setCapability("public", "team");
    sauceCapabilities.setCapability("recordLogs", false);
    sauceCapabilities.setCapability("recordScreenshots", false);
    sauceCapabilities.setCapability("recordVideo", false);
    sauceCapabilities.setCapability("screenResolution", "10x10");
    sauceCapabilities.setCapability("seleniumVersion", "3.141.59");
    sauceCapabilities.setCapability("tags", tags);
    sauceCapabilities.setCapability("timeZone", "San Francisco");
    sauceCapabilities.setCapability("tunnelIdentifier", "tunnelname");
    sauceCapabilities.setCapability("videoUploadOnPass", false);
    sauceCapabilities.setCapability("username", SystemManager.get("SAUCE_USERNAME"));
    sauceCapabilities.setCapability("accessKey", SystemManager.get("SAUCE_ACCESS_KEY"));

    MutableCapabilities expectedCapabilities = new MutableCapabilities();
    expectedCapabilities.setCapability("browserName", "chrome");
    expectedCapabilities.setCapability("browserVersion", "latest");
    expectedCapabilities.setCapability("platformName", "Windows 10");

    expectedCapabilities.setCapability("sauce:options", sauceCapabilities);
    MutableCapabilities actualCapabilities = sauceOptions.toCapabilities();

    // toString() serializes the enums
    Assertions.assertEquals(
        expectedCapabilities.asMap().toString(), actualCapabilities.asMap().toString());
  }

  @Test
  public void parsesCapabilitiesFromSeleniumValues() {
    FirefoxOptions firefoxOptions = new FirefoxOptions();
    firefoxOptions.addArguments("--foo");
    firefoxOptions.addPreference("foo", "bar");
    firefoxOptions.setUnhandledPromptBehaviour(UnexpectedAlertBehaviour.DISMISS);

    sauceOptions = SauceOptions.firefox(firefoxOptions).build();
    sauceOptions.sauce().setBuild("Build Name");

    MutableCapabilities expectedCapabilities = new MutableCapabilities();
    expectedCapabilities.setCapability("browserName", "firefox");
    expectedCapabilities.setCapability("browserVersion", "latest");
    expectedCapabilities.setCapability("platformName", "Windows 10");
    expectedCapabilities = expectedCapabilities.merge(firefoxOptions);

    MutableCapabilities sauceCapabilities = new MutableCapabilities();
    sauceCapabilities.setCapability("build", "Build Name");
    sauceCapabilities.setCapability("username", SystemManager.get("SAUCE_USERNAME"));
    sauceCapabilities.setCapability("accessKey", SystemManager.get("SAUCE_ACCESS_KEY"));
    expectedCapabilities.setCapability("sauce:options", sauceCapabilities);
    MutableCapabilities actualCapabilities = sauceOptions.toCapabilities();

    Assertions.assertEquals(
        expectedCapabilities.asMap().toString(), actualCapabilities.asMap().toString());
  }

  @Test
  public void parsesW3CAndSauceAndSeleniumSettings() {
    MutableCapabilities expectedCapabilities = new MutableCapabilities();
    MutableCapabilities sauceCapabilities = new MutableCapabilities();

    FirefoxOptions firefoxOptions = new FirefoxOptions();
    firefoxOptions.addArguments("--foo");
    firefoxOptions.setUnhandledPromptBehaviour(UnexpectedAlertBehaviour.DISMISS);

    sauceOptions = SauceOptions.firefox(firefoxOptions).build();

    expectedCapabilities = expectedCapabilities.merge(firefoxOptions);
    expectedCapabilities.setCapability("browserVersion", "latest");
    expectedCapabilities.setCapability("platformName", "Windows 10");
    expectedCapabilities.setCapability("acceptInsecureCerts", true);

    sauceOptions.sauce().setBuild("CUSTOM BUILD: 12");
    sauceCapabilities.setCapability("build", "CUSTOM BUILD: 12");

    sauceOptions.setPageLoadStrategy(PageLoadStrategy.EAGER);
    expectedCapabilities.setCapability("pageLoadStrategy", PageLoadStrategy.EAGER);
    sauceOptions.setAcceptInsecureCerts(true);
    expectedCapabilities.setCapability("acceptInsecureCerts", true);
    sauceOptions.timeout.setImplicitWait(1000).setPageLoad(100000).setScript(10000);
    Map<Timeouts, Integer> timeouts = new HashMap<>();
    timeouts.put(Timeouts.IMPLICIT, 1000);
    timeouts.put(Timeouts.SCRIPT, 10000);
    timeouts.put(Timeouts.PAGE_LOAD, 100000);
    expectedCapabilities.setCapability("timeouts", timeouts);
    sauceOptions.setUnhandledPromptBehavior(UnhandledPromptBehavior.IGNORE);
    expectedCapabilities.setCapability("unhandledPromptBehavior", UnhandledPromptBehavior.IGNORE);

    sauceOptions.sauce().setExtendedDebugging(true);
    sauceCapabilities.setCapability("extendedDebugging", true);
    sauceOptions.sauce().setName("Test name");
    sauceCapabilities.setCapability("name", "Test name");
    sauceOptions.sauce().setParentTunnel("Mommy");
    sauceCapabilities.setCapability("parentTunnel", "Mommy");

    sauceOptions.sauce().setJobVisibility(JobVisibility.SHARE);
    sauceCapabilities.setCapability("public", JobVisibility.SHARE);
    sauceCapabilities.setCapability("username", SystemManager.get("SAUCE_USERNAME"));
    sauceCapabilities.setCapability("accessKey", SystemManager.get("SAUCE_ACCESS_KEY"));

    expectedCapabilities.setCapability("sauce:options", sauceCapabilities);
    MutableCapabilities actualCapabilities = sauceOptions.toCapabilities();

    Assertions.assertEquals(
        expectedCapabilities.asMap().toString(), actualCapabilities.asMap().toString());
  }
}
