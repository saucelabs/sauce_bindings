package com.saucelabs.saucebindings.integration;

import com.saucelabs.saucebindings.SauceOptions;
import com.saucelabs.saucebindings.SauceSession;
import org.junit.After;
import org.junit.Test;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.assertNotNull;

public class VisualTest {
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
        sauceOptions.setViewportSize("1280x1024");

        session = new SauceSession(sauceOptions);
        webDriver = session.start();
        assertNotNull(session.getDriver());
    }

    @Test
    public void settingUniqueOptions() {
        sauceOptions = SauceOptions.visual("settingUniqueOptions");

        sauceOptions.setViewportSize("1280x1024");

        Map<String, Object> diffOptions = new HashMap<>();
        diffOptions.put("structure", true);
        diffOptions.put("layout", true);
        diffOptions.put("style", true);
        diffOptions.put("content", true);
        diffOptions.put("minLayoutPosition", 4);
        diffOptions.put("minLayoutDimension", 10);

        sauceOptions.setDiffOptions(diffOptions);

        sauceOptions.setIgnore("#foo, .bar");
        sauceOptions.setFailOnNewStates(true);
        sauceOptions.setScrollAndStitchScreenshots(true);
        sauceOptions.setDisableCORS(true);

        session = new SauceSession(sauceOptions);
        webDriver = session.start();
        webDriver.get("https://www.saucedemo.com/");
        session.takeSnapshot("Snapshot name");
        assertNotNull(session.getDriver());
    }
}