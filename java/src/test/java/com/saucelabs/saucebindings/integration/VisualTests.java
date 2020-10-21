package com.saucelabs.saucebindings.integration;

import com.saucelabs.saucebindings.OptionForVisualTestingOnlyException;
import com.saucelabs.saucebindings.SauceOptions;
import com.saucelabs.saucebindings.SauceSession;
import org.junit.After;
import org.junit.Test;
import org.openqa.selenium.remote.RemoteWebDriver;

import static org.junit.Assert.assertNotNull;

public class VisualTests {
    private RemoteWebDriver webDriver;
    private SauceSession session;

    @After
    public void cleanUp() {
        session.stop(true);
    }

    @Test
    public void defaultStart() {
        SauceOptions sauceOptions = new SauceOptions().visual();
        session = new SauceSession(sauceOptions);
        session.start();
        assertNotNull(session.getDriver());
    }

    //TODO I don't like the fact that our tests will need to throw
    //specific visual exceptions
    @Test
    public void settingCommonOptions() throws OptionForVisualTestingOnlyException {
        SauceOptions sauceOptions = new SauceOptions().visual();
        sauceOptions.setName("testName");
        //my biggest problem here is that this error will only be caught at run time
        sauceOptions.setViewportSize("1280x1024");

        session = new SauceSession(sauceOptions);
        webDriver = session.start();
        assertNotNull(session.getDriver());
    }

//    @Test
//    public void simpleTest() {
//        SauceVisualSession visualSession = new SauceVisualSession();
//        //Not sure if you always need to set test name,
//        //but if we do then maybe we can just do start(testName);
//        visualSession.start();
//        visualSession.setTestName("Visual Test");
//        visualSession.takeSnapshot("Snapshot name 1");
//        Boolean isPassed = visualSession.stop();
//        assertTrue(isPassed);
//    }

//    @Test
//    public void withVisualOptions() {
//        var visualOptions = new SauceVisualOptions();
//        visualOptions.setProjectName("Sauce Bindings");
//        visualOptions.setViewportSize("1280x1024");
//
//        SauceVisualSession visualSession = new SauceVisualSession(visualOptions);
//        //Not sure if you always need to set test name,
//        //but if we do then maybe we can just do start(testName);
//        visualSession.start();
//        visualSession.setTestName("Visual Test");
//        visualSession.takeSnapshot("Snapshot name 1");
//        Boolean isPassed = visualSession.stop();
//        assertTrue(isPassed);
//    }

//    @Test
//    public void withVisualOptions2() {
//        SauceOptions sauceOptions = new SauceOptions().visual();
//        sauceOptions.setProjectName("Sauce Bindings");
//        sauceOptions.setViewportSize("1280x1024");
//
//        //if visual then set the test name
//        sauceOptions.setName("Test name");
//
//        SauceSession sauceSession = new SauceSession(sauceOptions);
//        //Not sure if you always need to set test name,
//        //but if we do then maybe we can just do start(testName);
//        RemoteWebDriver driver = sauceSession.start();
//        sauceSession.takeSnapshot("Snapshot name 1");
//        Boolean isPassed = sauceSession.stop();
//        assertTrue(isPassed);
//    }
}
