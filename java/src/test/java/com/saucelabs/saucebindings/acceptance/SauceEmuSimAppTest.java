package com.saucelabs.saucebindings.acceptance;

import com.saucelabs.saucebindings.DataCenter;
import com.saucelabs.saucebindings.SauceMobileOptions;
import com.saucelabs.saucebindings.SauceMobileSession;
import io.appium.java_client.AppiumDriver;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.MutableCapabilities;

import static org.junit.Assert.assertTrue;

// TODO: Convert these into unit tests; Integration tests are not unique
public class SauceEmuSimAppTest {
    private SauceMobileSession session;
    private AppiumDriver webDriver;

    @Before
    public void setUp() {
        MutableCapabilities appiumOptions = new MutableCapabilities();
        appiumOptions.setCapability("appWaitActivity", "com.swaglabsmobileapp.MainActivity");

        SauceMobileOptions options = new SauceMobileOptions(appiumOptions);
        String app = "https://github.com/saucelabs/sample-app-mobile/releases/download/2.3.0/Android.SauceLabs.Mobile.Sample.app.2.3.0.apk";
        options.setApp(app);

        session = new SauceMobileSession(options);
    }

    @After
    public void tearDown() {
        session.stop(true);
    }

    @Test
    public void defaultsToLegacy() {
        webDriver = session.start();

        assertTrue(webDriver.getRemoteAddress().toString().contains("ondemand.saucelabs"));
    }

    @Test
    public void runsUSWest() {
        session.setDataCenter(DataCenter.US_WEST);
        webDriver = session.start();

        assertTrue(webDriver.getRemoteAddress().toString().contains("us-west-1"));
    }

    @Test
    public void runsEUCentral() {
        session.setDataCenter(DataCenter.EU_CENTRAL);
        webDriver = session.start();

        assertTrue(webDriver.getRemoteAddress().toString().contains("eu-central-1"));
    }
}
