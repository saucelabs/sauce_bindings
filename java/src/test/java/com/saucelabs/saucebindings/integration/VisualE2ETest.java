package com.saucelabs.saucebindings.integration;

import com.saucelabs.saucebindings.SauceVisualSession;
import com.saucelabs.saucebindings.visual.SauceVisualOptions;
import lombok.var;
import org.junit.Test;
import org.openqa.selenium.remote.RemoteWebDriver;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

public class VisualE2ETest {
    private RemoteWebDriver webDriver;

    @Test
    public void defaultStartWorks() {
        SauceVisualSession visualSession = new SauceVisualSession();
        webDriver = visualSession.start();
        assertNotNull(webDriver);
    }

    @Test
    public void pointsToVisualHub() {
        SauceVisualSession visualSession = new SauceVisualSession();
        webDriver = visualSession.start();
        assertNotNull(visualSession.getHubUrl().toString().contains("hub.screener.io/wd/hub"));
    }

    @Test
    public void simpleTest() {
        SauceVisualSession visualSession = new SauceVisualSession();
        //Not sure if you always need to set test name,
        //but if we do then maybe we can just do start(testName);
        visualSession.start();
        visualSession.setTestName("Visual Test");
        visualSession.takeSnapshot("Snapshot name 1");
        Boolean isPassed = visualSession.stop();
        assertTrue(isPassed);
    }

    @Test
    public void withVisualOptions() {
        var visualOptions = new SauceVisualOptions();
        visualOptions.setProjectName("Sauce Bindings");
        visualOptions.setViewportSize("1280x1024");

        SauceVisualSession visualSession = new SauceVisualSession(visualOptions);
        //Not sure if you always need to set test name,
        //but if we do then maybe we can just do start(testName);
        visualSession.start();
        visualSession.setTestName("Visual Test");
        visualSession.takeSnapshot("Snapshot name 1");
        Boolean isPassed = visualSession.stop();
        assertTrue(isPassed);
    }
}
