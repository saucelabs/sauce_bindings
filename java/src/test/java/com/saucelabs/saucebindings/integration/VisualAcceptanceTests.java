package com.saucelabs.saucebindings.integration;

import com.saucelabs.saucebindings.SauceOptions;
import com.saucelabs.saucebindings.SauceSession;
import org.junit.After;
import org.junit.Test;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.assertNotNull;

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

    @Test
    public void sameBuildSameTestName() {
        String sameTestName = "foo";
        sauceOptions = SauceOptions.visual("sameBuildSameTestName");
        session = new SauceSession(sauceOptions);
        webDriver = session.start();
        webDriver.get("https://www.saucedemo.com/");
        session.takeSnapshot(sameTestName);

        webDriver.get("https://www.saucedemo.com/inventory.html");
        session.takeSnapshot(sameTestName);

        assertNotNull(session.getDriver());
    }
}
