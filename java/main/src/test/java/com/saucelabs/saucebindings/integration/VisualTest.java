package com.saucelabs.saucebindings.integration;

import com.saucelabs.saucebindings.VisualSession;
import com.saucelabs.saucebindings.options.VisualOptions;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.remote.RemoteWebDriver;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

public class VisualTest {
    private VisualSession session;
    private RemoteWebDriver driver;
    private final String PROJECT_NAME = "VisualTest.java";

    @Before
    public void setup() {
        System.setProperty("BUILD_NAME", "Sauce Bindings");
    }

    @After
    public void cleanUp() {
        if (session != null) {
            session.stop();
        }
    }

    @Test
    public void startsSession() {
        session = new VisualSession("VisualTest startsSession", PROJECT_NAME);
        driver = session.start();

        assertNotNull(driver);
        assertTrue(session.getSauceUrl().toString().contains("screener.io"));
    }

    @Test
    public void takeMultipleSnapshots() {
        VisualOptions vo = new VisualOptions("VisualTest takeMultipleSnapshots", PROJECT_NAME);
        vo.setFailOnNewStates(false);
        session = new VisualSession(vo);
        driver = session.start();

        session.takeSnapshot("Blank1");
        session.takeSnapshot("Blank2");
    }

    @Test
    public void stopSendsVisualResultToSauce() {
        session = new VisualSession("VisualTest stopSendsVisualResultToSauce", PROJECT_NAME);
        driver = session.start();
        session.stop();
        assertEquals("true", session.getResult());
    }

    @Test
    public void stopParameterOverridesVisualResult() {
        session = new VisualSession("VisualTest stopParameterOverridesVisualResult", PROJECT_NAME);
        driver = session.start();

        session.stop(false);
        assertEquals("false", session.getResult());
    }
}
