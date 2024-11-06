package com.saucelabs.saucebindings;

import com.google.common.collect.ImmutableList;
import com.saucelabs.saucebindings.options.SauceOptions;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.condition.EnabledOnOs;
import org.junit.jupiter.api.condition.OS;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.openqa.selenium.InvalidArgumentException;
import org.openqa.selenium.MutableCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.remote.SessionId;

@ExtendWith(MockitoExtension.class)
public class SauceSessionTest {
  private SauceOptions sauceOptions;
  private SauceSession sauceSession;
  private SauceSession safariSession;

  @Mock private RemoteWebDriver dummyRemoteDriver;
  @Mock private MutableCapabilities dummyMutableCapabilities;
  @Mock private SauceRest dummyRestClient;

  @BeforeEach
  public void setUp() {
    sauceOptions = Mockito.spy(SauceOptions.safari().build());
    sauceSession = Mockito.spy(new SauceSession());
    safariSession = Mockito.spy(new SauceSession(sauceOptions));
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
        .when(safariSession)
        .createRemoteWebDriver(
            Mockito.any(URL.class), ArgumentMatchers.eq(dummyMutableCapabilities));
    setRestMocks(safariSession);

    safariSession.start();

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
    setMocks();
    sauceSession.start();
    sauceSession.stop(true);

    Mockito.verify(dummyRemoteDriver).quit();
  }

  @Test
  public void stopWithBooleanTrue() {
    setMocks();
    sauceSession.start();
    sauceSession.stop(true);
    Mockito.verify(dummyRestClient).setResult(true);
  }

  @Test
  public void stopWithBooleanFalse() {
    setMocks();
    sauceSession.start();
    sauceSession.stop(false);
    Mockito.verify(dummyRestClient).setResult(false);
  }

  @Test
  public void annotateRequiresStart() {
    Assertions.assertThrows(
        SauceSessionNotStartedException.class,
        () -> sauceSession.annotate("Comment Causes Failure"));
  }

  @Test
  public void annotates() {
    setMocks();
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
    setMocks();
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
    setMocks();
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
    setMocks();
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
    Mockito.doReturn(dummyRemoteDriver)
        .when(safariSession)
        .createRemoteWebDriver(Mockito.any(URL.class), Mockito.any(MutableCapabilities.class));
    setRestMocks(safariSession);

    safariSession.start();
    safariSession.stopNetwork();
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
    Mockito.doReturn(dummyMutableCapabilities).when(sauceOptions).toCapabilities();
    Mockito.doReturn(dummyRemoteDriver)
        .when(safariSession)
        .createRemoteWebDriver(Mockito.any(URL.class), Mockito.eq(dummyMutableCapabilities));
    setRestMocks(safariSession);

    safariSession.start();
    safariSession.startNetwork();
    Mockito.verify(dummyRemoteDriver).executeScript("sauce: start network");
  }

  @Test
  public void changeNameRequiresStart() {
    Assertions.assertThrows(
        SauceSessionNotStartedException.class,
        () -> sauceSession.changeName("New Name Causes Failure"));
  }

  @Test
  public void changesName() {
    setMocks();
    sauceSession.start();
    sauceSession.changeName("New Name");
    Mockito.verify(dummyRestClient).changeName("New Name");
  }

  @Test
  public void addTagsRequiresStart() {
    Assertions.assertThrows(
        SauceSessionNotStartedException.class,
        () -> sauceSession.addTags(ImmutableList.of("foo", "bar")));
  }

  @Test
  public void addTags() {
    setMocks();
    sauceSession.start();
    List<String> tags = List.of("foo", "bar");
    sauceSession.addTags(tags);
    Mockito.verify(dummyRestClient).addTags(tags);
  }

  private void setMocks() {
    Mockito.doReturn(dummyRemoteDriver)
        .when(sauceSession)
        .createRemoteWebDriver(Mockito.any(URL.class), Mockito.any(MutableCapabilities.class));
    setRestMocks(sauceSession);
  }

  private void setRestMocks(SauceSession session) {
    Mockito.doReturn(new SessionId("id")).when(dummyRemoteDriver).getSessionId();

    Mockito.doReturn(dummyRestClient)
        .when(session)
        .sauceRest(Mockito.any(DataCenter.class), Mockito.any(SessionId.class));
  }
}
