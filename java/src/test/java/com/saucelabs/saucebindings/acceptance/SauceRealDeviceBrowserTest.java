package com.saucelabs.saucebindings.acceptance;

import com.saucelabs.saucebindings.DataCenter;
import com.saucelabs.saucebindings.SauceMobileOptions;
import com.saucelabs.saucebindings.SauceMobileSession;
import com.saucelabs.saucebindings.SauceSession;
import io.appium.java_client.AppiumDriver;
import org.junit.After;
import org.junit.Ignore;
import org.junit.Test;
import org.openqa.selenium.remote.RemoteWebDriver;

import static org.junit.Assert.assertTrue;

public class SauceRealDeviceBrowserTest {
    private SauceSession session = new SauceMobileSession();

    @After
    public void tearDown() {
        session.stop(true);
    }

    @Test
    public void runsUSMobile() {
        session.setDataCenter(DataCenter.US_MOBILE);

        assertTrue(session.getSauceUrl().toString().contains("us1.appium.testobject"));
    }

    @Test
    public void runsEUMobile() {
        session.setDataCenter(DataCenter.EU_MOBILE);

        assertTrue(session.getSauceUrl().toString().contains("eu1.appium.testobject"));
    }

    @Test
    public void runsUSTestObject() {
        session.setDataCenter(DataCenter.US_TEST_OBJECT);

        assertTrue(session.getSauceUrl().toString().contains("us1-manual.app.testobject"));
    }

    @Test
    public void runsEUTestObject() {
        session.setDataCenter(DataCenter.EU_TEST_OBJECT);

        assertTrue(session.getSauceUrl().toString().contains("//appium.testobject"));
    }

    @Ignore
    public void runsUnifiedPlatform() {
        SauceMobileOptions sauceMobileOptions = new SauceMobileOptions();
        sauceMobileOptions.setDeviceName("Google Pixel XL Real");
        session = new SauceMobileSession(sauceMobileOptions);
        session.setDataCenter(DataCenter.US_WEST);

        assertTrue(session.getSauceUrl().toString().contains("ondemand.saucelabs"));
    }
}
