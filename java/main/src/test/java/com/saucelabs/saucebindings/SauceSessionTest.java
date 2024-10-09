package com.saucelabs.saucebindings;

import com.google.common.collect.ImmutableList;
import com.saucelabs.saucebindings.options.SauceOptions;
import java.net.MalformedURLException;
import java.net.URL;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.condition.EnabledOnOs;
import org.junit.jupiter.api.condition.OS;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.openqa.selenium.InvalidArgumentException;
import org.openqa.selenium.MutableCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

@ExtendWith(MockitoExtension.class)
public class SauceSessionTest {
  private SauceOptions sauceOptions = Mockito.spy(new SauceOptions());
  private SauceSession sauceSession = Mockito.spy(new SauceSession());
  private final SauceSession sauceOptsSession = Mockito.spy(new SauceSession(sauceOptions));
  private final RemoteWebDriver dummyRemoteDriver = Mockito.mock(RemoteWebDriver.class);
  private final MutableCapabilities dummyMutableCapabilities =
      Mockito.mock(MutableCapabilities.class);

  @BeforeEach
  public void setUp() {
    Mockito.doReturn(dummyRemoteDriver)
        .when(sauceSession)
        .createRemoteWebDriver(Mockito.any(URL.class), Mockito.any(MutableCapabilities.class));
  }

  @Test
  public void sauceSessionDefaultsToLatestChromeOnWindows() {
    Browser actualBrowser = sauceSession.getSauceOptions().getBrowserName();
    String actualBrowserVersion = sauceSession.getSauceOptions().getBrowserVersion();
    SaucePlatform actualPlatformName = sauceSession.getSauceOptions().getPlatformName();

    Assertions.assertEquals(Browser.CHROME, actualBrowser);
    Assertions.assertEquals(SaucePlatform.WINDOWS_10, actualPlatformName);
    Assertions.assertEquals("latest", actualBrowserVersion);
  }

  @Test
  public void sauceSessionUsesProvidedSauceOptions() {
    Mockito.doReturn(dummyMutableCapabilities).when(sauceOptions).toCapabilities();
    Mockito.doReturn(dummyRemoteDriver)
        .when(sauceOptsSession)
        .createRemoteWebDriver(
            Mockito.any(URL.class), ArgumentMatchers.eq(dummyMutableCapabilities));

    sauceOptsSession.start();

    Mockito.verify(sauceOptions).toCapabilities();
  }

  @Test
  public void sauceSessionUsesProvidedSauceConfigs() {
    SauceSession sauceSession =
        new SauceSession(SauceOptions.chrome().setPlatformName(SaucePlatform.MAC_MOJAVE));
    SauceOptions sauceOptions = sauceSession.getSauceOptions();

    Assertions.assertEquals(Browser.CHROME, sauceOptions.getBrowserName());
    Assertions.assertEquals(SaucePlatform.MAC_MOJAVE, sauceOptions.getPlatformName());
    Assertions.assertEquals("latest", sauceOptions.getBrowserVersion());
  }

  @Test
  public void defaultsToUSWestDataCenter() {
    String expectedDataCenterEndpoint = DataCenter.US_WEST.getEndpoint();
    Assertions.assertEquals(expectedDataCenterEndpoint, sauceSession.getDataCenter().getEndpoint());
  }

  @Test
  public void setsDataCenter() {
    String expectedDataCenterEndpoint = DataCenter.US_EAST.getEndpoint();
    sauceSession.setDataCenter(DataCenter.US_EAST);
    Assertions.assertEquals(expectedDataCenterEndpoint, sauceSession.getDataCenter().getEndpoint());
  }

  @Test
  public void setsSauceURLDirectly() throws MalformedURLException {
    sauceSession.setSauceUrl(new URL("https://example.com"));
    String expectedSauceUrl = "https://example.com";
    Assertions.assertEquals(expectedSauceUrl, sauceSession.getSauceUrl().toString());
  }

  @Test
  public void stopCallsDriverQuitPassing() {
    sauceSession.start();
    sauceSession.stop(true);

    Mockito.verify(dummyRemoteDriver).quit();
  }

  @Test
  public void stopWithBooleanTrue() {
    sauceSession.start();
    sauceSession.stop(true);
    Mockito.verify(dummyRemoteDriver).executeScript("sauce:job-result=passed");
  }

  @Test
  public void stopWithBooleanFalse() {
    sauceSession.start();
    sauceSession.stop(false);
    Mockito.verify(dummyRemoteDriver).executeScript("sauce:job-result=failed");
  }

  @Test
  public void annotateRequiresStart() {
    Assertions.assertThrows(
        SauceSessionNotStartedException.class,
        () -> sauceSession.annotate("Comment Causes Failure"));
  }

  @Test
  public void annotates() {
    sauceSession.start();
    sauceSession.annotate("Comment in Command List");
    Mockito.verify(dummyRemoteDriver).executeScript("sauce:context=Comment in Command List");
  }

  @Test
  public void pauseRequiresStart() {
    Assertions.assertThrows(SauceSessionNotStartedException.class, () -> sauceSession.pause());
  }

  @Test
  public void pauses() {
    sauceSession.start();
    sauceSession.pause();
    Mockito.verify(dummyRemoteDriver).executeScript("sauce: break");
  }

  @Test
  public void disableLoggingRequiresStart() {
    Assertions.assertThrows(
        SauceSessionNotStartedException.class, () -> sauceSession.disableLogging());
  }

  @Test
  public void disablesLogs() {
    sauceSession.start();
    sauceSession.disableLogging();
    Mockito.verify(dummyRemoteDriver).executeScript("sauce: disable log");
  }

  @Test
  public void enableLoggingRequiresStart() {
    Assertions.assertThrows(
        SauceSessionNotStartedException.class, () -> sauceSession.enableLogging());
  }

  @Test
  public void enablesLogs() {
    sauceSession.start();
    sauceSession.enableLogging();
    Mockito.verify(dummyRemoteDriver).executeScript("sauce: enable log");
  }

  @Test
  public void stopNetworkRequiresStart() {
    Assertions.assertThrows(
        SauceSessionNotStartedException.class, () -> sauceSession.stopNetwork());
  }

  @Test
  public void stopNetworkRequiresMac() {
    sauceSession.start();

    Assertions.assertThrows(InvalidArgumentException.class, () -> sauceSession.stopNetwork());
  }

  @Test
  @EnabledOnOs(OS.MAC)
  public void stopsNetwork() {
    sauceOptions = Mockito.spy(SauceOptions.safari().build());
    sauceSession = Mockito.spy(new SauceSession(sauceOptions));
    Mockito.doReturn(dummyMutableCapabilities).when(sauceOptions).toCapabilities();
    Mockito.doReturn(dummyRemoteDriver)
        .when(sauceSession)
        .createRemoteWebDriver(Mockito.any(URL.class), Mockito.eq(dummyMutableCapabilities));

    sauceSession.start();
    sauceSession.stopNetwork();
    Mockito.verify(dummyRemoteDriver).executeScript("sauce: stop network");
  }

  @Test
  public void startNetworkRequiresStart() {
    Assertions.assertThrows(
        SauceSessionNotStartedException.class, () -> sauceSession.startNetwork());
  }

  @Test
  public void startNetworkRequiresMac() {
    sauceSession.start();

    Assertions.assertThrows(InvalidArgumentException.class, () -> sauceSession.startNetwork());
  }

  @Test
  public void startsNetwork() {
    // Only works on Mac
    sauceOptions = Mockito.spy(SauceOptions.safari().build());
    sauceSession = Mockito.spy(new SauceSession(sauceOptions));
    Mockito.doReturn(dummyMutableCapabilities).when(sauceOptions).toCapabilities();
    Mockito.doReturn(dummyRemoteDriver)
        .when(sauceSession)
        .createRemoteWebDriver(Mockito.any(URL.class), Mockito.eq(dummyMutableCapabilities));

    sauceSession.start();
    sauceSession.startNetwork();
    Mockito.verify(dummyRemoteDriver).executeScript("sauce: start network");
  }

  @Test
  public void changeNameRequiresStart() {
    Assertions.assertThrows(
        SauceSessionNotStartedException.class,
        () -> sauceSession.changeTestName("New Name Causes Failure"));
  }

  @Test
  public void changesName() {
    sauceSession.start();
    sauceSession.changeTestName("New Name");
    Mockito.verify(dummyRemoteDriver).executeScript("sauce:job-name=New Name");
  }

  @Test
  public void addTagsRequiresStart() {
    Assertions.assertThrows(
        SauceSessionNotStartedException.class,
        () -> sauceSession.addTags(ImmutableList.of("foo", "bar")));
  }

  @Test
  public void addTags() {
    sauceSession.start();
    sauceSession.addTags(ImmutableList.of("foo", "bar"));
    Mockito.verify(dummyRemoteDriver).executeScript("sauce:job-tags=foo,bar");
  }
}
