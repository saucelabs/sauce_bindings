package com.saucelabs.saucebindings.integration;

import com.saucelabs.saucebindings.Browser;
import com.saucelabs.saucebindings.SauceOptions;
import com.saucelabs.saucebindings.SaucePlatform;
import com.saucelabs.saucebindings.SauceSession;
import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.assertNotNull;

@RunWith(Parameterized.class)
public class VisualAcceptanceTests {
    private RemoteWebDriver webDriver;
    private SauceSession session;
    private SauceOptions sauceOptions;

    @After
    public void cleanUp() {
        session.stop(true);
    }

    @Test
    public void defaultStart() {
        sauceOptions = SauceOptions.visual("defaultStart");
        session = new SauceSession(sauceOptions);
        session.start();
        assertNotNull(session.getDriver());
    }

    @Test
    public void settingCommonOptions()  {
        sauceOptions = SauceOptions.visual("settingCommonOptions");
        sauceOptions.setName("testName");
        sauceOptions.visual().setViewportSize("1280x1024");

        session = new SauceSession(sauceOptions);
        webDriver = session.start();
        assertNotNull(session.getDriver());
    }

    @Test
    public void settingUniqueOptions() {
        sauceOptions = SauceOptions.visual("settingUniqueOptions");

        sauceOptions.visual().setViewportSize("1280x1024");

        Map<String, Object> diffOptions = new HashMap<>();
        diffOptions.put("structure", true);
        diffOptions.put("layout", true);
        diffOptions.put("style", true);
        diffOptions.put("content", true);
        diffOptions.put("minLayoutPosition", 4);
        diffOptions.put("minLayoutDimension", 10);

        sauceOptions.visual().setDiffOptions(diffOptions);

        sauceOptions.visual().setIgnore("#foo, .bar");
        sauceOptions.visual().setFailOnNewStates(true);
        sauceOptions.visual().setScrollAndStitchScreenshots(true);
        sauceOptions.visual().setDisableCORS(true);

        session = new SauceSession(sauceOptions);
        webDriver = session.start();
        webDriver.get("https://www.saucedemo.com/");
        session.takeSnapshot("Snapshot name");
        assertNotNull(session.getDriver());
    }

    @Test
    public void multiScreenshotTest() {
        sauceOptions = SauceOptions.visual("multiScreenshotTest");
        session = new SauceSession(sauceOptions);
        webDriver = session.start();
        webDriver.get("https://www.saucedemo.com/");
        session.takeSnapshot("Login page");

        webDriver.get("https://www.saucedemo.com/inventory.html");
        session.takeSnapshot("Inventory page");

        assertNotNull(session.getDriver());
    }

    /*
     * Configure our data driven parameters
     * */
    @Parameterized.Parameter
    public String browserName;
    @Parameterized.Parameter(2)
    public String browserVersion;
    @Parameterized.Parameter(1)
    public String platform;
    @Parameterized.Parameter(3)
    public String viewportSize;
    @Parameterized.Parameter(4)
    public String deviceName;

    @Parameterized.Parameters()
    public static Collection<Object[]> crossBrowserData() {
        return Arrays.asList(new Object[][] {
                { "Chrome", "WINDOWS_10", "latest", "412x732", "Pixel XL" },
                { "Safari", "MAC_MOJAVE", "latest", "375x812", "iPhone X" }
        });
    }

    //This is an important use case as it demonstrates
    //if we have the ability to run multiple tests in parallel
    @Test
    @Parameterized.Parameters()
    public void crossPlatformTest() {
        sauceOptions = SauceOptions.visual("crossPlatformTest");
        sauceOptions.setPlatformName(SaucePlatform.valueOf(platform.toUpperCase()));
        sauceOptions.setBrowserName(Browser.valueOf(browserName.toUpperCase()));
        sauceOptions.setBrowserVersion(browserVersion);
        sauceOptions.visual().setViewportSize(viewportSize);

        session = new SauceSession(sauceOptions);
        webDriver = session.start();
        webDriver.get("https://www.saucedemo.com/");
        session.takeSnapshot("Login page");

        assertNotNull(session.getDriver());
    }
}
