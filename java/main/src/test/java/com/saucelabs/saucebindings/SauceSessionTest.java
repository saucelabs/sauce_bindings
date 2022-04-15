package com.saucelabs.saucebindings;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import com.saucelabs.saucebindings.options.ChromeConfigurations;
import com.saucelabs.saucebindings.options.InvalidSauceOptionsArgumentException;
import com.saucelabs.saucebindings.options.SauceOptions;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.mockito.Matchers;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;
import org.openqa.selenium.InvalidArgumentException;
import org.openqa.selenium.MutableCapabilities;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;

public class SauceSessionTest {
    private SauceOptions sauceOptions = Mockito.spy(SauceOptions.chrome().build());
    private SauceSession sauceSession = Mockito.spy(new SauceSession());
    private final SauceSession sauceOptsSession = Mockito.spy(new SauceSession(sauceOptions));
    private final RemoteWebDriver dummyRemoteDriver = Mockito.mock(RemoteWebDriver.class);
    private final MutableCapabilities dummyMutableCapabilities = Mockito.mock(MutableCapabilities.class);

    @Rule
    public MockitoRule initRule = MockitoJUnit.rule();

    @Before
    public void setUp() {
        Mockito.doReturn(dummyRemoteDriver).when(sauceSession)
                .createRemoteWebDriver(Mockito.any(URL.class), Mockito.any(MutableCapabilities.class));
    }

    @Test
    public void defaultsToLatestChromeOnWindows() {
        Browser actualBrowser = sauceSession.getSauceOptions().getBrowserName();
        String actualBrowserVersion = sauceSession.getSauceOptions().getBrowserVersion();
        SaucePlatform actualPlatformName = sauceSession.getSauceOptions().getPlatformName();

        Assert.assertEquals(Browser.CHROME, actualBrowser);
        Assert.assertEquals(SaucePlatform.WINDOWS_10, actualPlatformName);
        Assert.assertEquals("latest", actualBrowserVersion);
    }

    @Test
    public void usesProvidedSauceOptions() {
        Mockito.doReturn(dummyMutableCapabilities).when(sauceOptions).toCapabilities();
        Mockito.doReturn(dummyRemoteDriver).when(sauceOptsSession)
                .createRemoteWebDriver(Mockito.any(URL.class), Matchers.eq(dummyMutableCapabilities));

        sauceOptsSession.start();

        Mockito.verify(sauceOptions).toCapabilities();
    }

    @Test
    public void usesProvidedSeleniumOptions() {
        FirefoxOptions seOpts = new FirefoxOptions();
        seOpts.setCapability("sauce:options",
                ImmutableMap.of("username", System.getenv("SAUCE_USERNAME"),
                        "accessKey", System.getenv("SAUCE_ACCESS_KEY"),
                        "build", "Build Name",
                        "maxDuration", 300));

        sauceSession = new SauceSession(seOpts);
        MutableCapabilities actualCapabilities = sauceSession.getSauceOptions().toCapabilities();

        MutableCapabilities expectedCapabilities = new MutableCapabilities();
        expectedCapabilities.setCapability("browserName", "firefox");
        expectedCapabilities.setCapability("browserVersion", "latest");
        expectedCapabilities.setCapability("platformName", "Windows 10");
        expectedCapabilities.setCapability("acceptInsecureCerts", true);

        MutableCapabilities sauceCapabilities = new MutableCapabilities();
        sauceCapabilities.setCapability("build", "Build Name");
        sauceCapabilities.setCapability("maxDuration", 300);
        sauceCapabilities.setCapability("username", SystemManager.get("SAUCE_USERNAME"));
        sauceCapabilities.setCapability("accessKey", SystemManager.get("SAUCE_ACCESS_KEY"));
        expectedCapabilities.setCapability("sauce:options", sauceCapabilities);
        expectedCapabilities.setCapability("moz:firefoxOptions", new HashMap<>());
        expectedCapabilities.setCapability("moz:debuggerAddress", true);

        Assert.assertEquals(expectedCapabilities.asMap().toString(), actualCapabilities.asMap().toString());
    }

    @Test(expected = InvalidSauceOptionsArgumentException.class)
    public void doesNotUseDesiredCapabilities() {
        DesiredCapabilities caps = new DesiredCapabilities();

        sauceSession = new SauceSession(caps);
    }

    @Test
    public void usesProvidedSauceConfigs() {
        SauceSession sauceSession = new SauceSession(SauceOptions.chrome()
                .setPlatformName(SaucePlatform.MAC_MOJAVE));
        SauceOptions sauceOptions = sauceSession.getSauceOptions();

        Assert.assertEquals(Browser.CHROME, sauceOptions.getBrowserName());
        Assert.assertEquals(SaucePlatform.MAC_MOJAVE, sauceOptions.getPlatformName());
        Assert.assertEquals("latest", sauceOptions.getBrowserVersion());
    }

    @Test
    public void defaultsToUSWestDataCenter() {
        String expectedDataCenterEndpoint = DataCenter.US_WEST.getValue();
        Assert.assertEquals(expectedDataCenterEndpoint, sauceSession.getDataCenter().getValue());
    }

    @Test
    public void setsDataCenter() {
        String expectedDataCenterEndpoint = DataCenter.US_EAST.getValue();
        sauceSession.setDataCenter(DataCenter.US_EAST);
        Assert.assertEquals(expectedDataCenterEndpoint, sauceSession.getDataCenter().getValue());
    }

    @Test
    public void setsSauceURLDirectly() throws MalformedURLException {
        sauceSession.setSauceUrl(new URL("http://example.com"));
        String expectedSauceUrl = "http://example.com";
        Assert.assertEquals(expectedSauceUrl, sauceSession.getSauceUrl().toString());
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

    @Deprecated
    @Test
    public void stopWithStringPassed() {
        sauceSession.start();
        sauceSession.stop("passed");
        Mockito.verify(dummyRemoteDriver).executeScript("sauce:job-result=passed");
    }

    @Deprecated
    @Test
    public void stopWithStringFailed() {
        sauceSession.start();
        sauceSession.stop("failed");
        Mockito.verify(dummyRemoteDriver).executeScript("sauce:job-result=failed");
    }

    @Test(expected = SauceSessionNotStartedException.class)
    public void annotateRequiresStart() {
        sauceSession.annotate("Comment Causes Failure");
    }

    @Test
    public void annotates() {
        sauceSession.start();
        sauceSession.annotate("Comment in Command List");
        Mockito.verify(dummyRemoteDriver).executeScript("sauce:context=Comment in Command List");
    }

    @Test(expected = SauceSessionNotStartedException.class)
    public void pauseRequiresStart() {
        sauceSession.pause();
    }

    @Test
    public void pauses() {
        sauceSession.start();
        sauceSession.pause();
        Mockito.verify(dummyRemoteDriver).executeScript("sauce: break");
    }

    @Test(expected = SauceSessionNotStartedException.class)
    public void disableLoggingRequiresStart() {
        sauceSession.disableLogging();
    }

    @Test
    public void disablesLogs() {
        sauceSession.start();
        sauceSession.disableLogging();
        Mockito.verify(dummyRemoteDriver).executeScript("sauce: disable log");
    }

    @Test(expected = SauceSessionNotStartedException.class)
    public void enableLoggingRequiresStart() {
        sauceSession.enableLogging();
    }

    @Test
    public void enablesLogs() {
        sauceSession.start();
        sauceSession.enableLogging();
        Mockito.verify(dummyRemoteDriver).executeScript("sauce: enable log");
    }

    @Test(expected = SauceSessionNotStartedException.class)
    public void stopNetworkRequiresStart() {
        sauceSession.stopNetwork();
    }

    @Test(expected = InvalidArgumentException.class)
    public void stopNetworkRequiresMac() {
        sauceSession.start();
        sauceSession.stopNetwork();
    }

    @Test
    public void stopsNetwork() {
        // Only works on Mac
        sauceOptions = Mockito.spy(SauceOptions.safari().build());
        sauceSession = Mockito.spy(new SauceSession(sauceOptions));
        Mockito.doReturn(dummyMutableCapabilities).when(sauceOptions).toCapabilities();
        Mockito.doReturn(dummyRemoteDriver).when(sauceSession)
                .createRemoteWebDriver(Mockito.any(URL.class), Mockito.eq(dummyMutableCapabilities));

        sauceSession.start();
        sauceSession.stopNetwork();
        Mockito.verify(dummyRemoteDriver).executeScript("sauce: stop network");
    }

    @Test(expected = SauceSessionNotStartedException.class)
    public void startNetworkRequiresStart() {
        sauceSession.startNetwork();
    }

    @Test(expected = InvalidArgumentException.class)
    public void startNetworkRequiresMac() {
        sauceSession.start();
        sauceSession.startNetwork();
    }

    @Test
    public void startsNetwork() {
        // Only works on Mac
        sauceOptions = Mockito.spy(SauceOptions.safari().build());
        sauceSession = Mockito.spy(new SauceSession(sauceOptions));
        Mockito.doReturn(dummyMutableCapabilities).when(sauceOptions).toCapabilities();
        Mockito.doReturn(dummyRemoteDriver).when(sauceSession)
                .createRemoteWebDriver(Mockito.any(URL.class), Mockito.eq(dummyMutableCapabilities));

        sauceSession.start();
        sauceSession.startNetwork();
        Mockito.verify(dummyRemoteDriver).executeScript("sauce: start network");
    }

    @Test(expected = SauceSessionNotStartedException.class)
    public void changeNameRequiresStart() {
        sauceSession.changeTestName("New Name Causes Failure");
    }

    @Test
    public void changesName() {
        sauceSession.start();
        sauceSession.changeTestName("New Name");
        Mockito.verify(dummyRemoteDriver).executeScript("sauce:job-name=New Name");
    }

    @Test(expected = SauceSessionNotStartedException.class)
    public void addTagsRequiresStart() {
        sauceSession.addTags(ImmutableList.of("foo", "bar"));
    }

    @Test
    public void addTags() {
        sauceSession.start();
        sauceSession.addTags(ImmutableList.of("foo", "bar"));
        Mockito.verify(dummyRemoteDriver).executeScript("sauce:job-tags=foo,bar");
    }
}
