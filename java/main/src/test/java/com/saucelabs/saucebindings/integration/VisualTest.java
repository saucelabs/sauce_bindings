package com.saucelabs.saucebindings.integration;

import com.saucelabs.saucebindings.SauceVisualException;
import com.saucelabs.saucebindings.VisualSession;
import com.saucelabs.saucebindings.options.VisualOptions;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.remote.RemoteWebDriver;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

public class VisualTest {
    private VisualSession session;
    private RemoteWebDriver driver;

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
        session = new VisualSession("VisualTest startsSession");
        driver = session.start();

        assertNotNull(driver);
        assertTrue(session.getSauceUrl().toString().contains("screener.io"));
    }

    @Test
    public void takeMultipleSnapshots() {
        VisualOptions vo = new VisualOptions("VisualTest takeMultipleSnapshots");
        vo.setFailOnNewStates(false);
        session = new VisualSession(vo);
        driver = session.start();

        session.takeSnapshot("Blank1");
        // this is wrong
        // session.newVisualTest("VisualTest runMultipleTests 2");
        session.takeSnapshot("Blank2");
        session.stop();
    }

    @Test
    public void stopSendsVisualResultToSauce() {
        session = new VisualSession("VisualTest stopSendsVisualResultToSauce");
        driver = session.start();

        session.stop();
        assertEquals("true", session.getResult());
    }

    @Test
    public void stopParameterOverridesVisualResult() {
        session = new VisualSession("VisualTest stopParameterOverridesVisualResult");
        driver = session.start();

        session.stop(false);
        assertEquals("false", session.getResult());
    }
}
