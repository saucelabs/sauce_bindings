package com.saucelabs.saucebindings.integration;

import com.saucelabs.saucebindings.GitManager;
import com.saucelabs.saucebindings.SauceVisualException;
import com.saucelabs.saucebindings.VisualResults;
import com.saucelabs.saucebindings.VisualSession;
import com.saucelabs.saucebindings.VisualSnapshot;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.util.List;

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
    public void getVisualResults() {
        session = new VisualSession("VisualTest getVisualResults");
        driver = session.start();

        session.takeSnapshot("Blank");

        VisualResults results = session.getVisualResults();

        assertTrue(results.getPassed());
        assertEquals("success", results.getStatus());

        assertNull(results.getMessage());

        assertEquals(Long.valueOf(1), results.getTotal());
        assertEquals(Long.valueOf(1), results.getTotalAccepted());
        assertEquals(Long.valueOf(0), results.getTotalNew());
        assertEquals(Long.valueOf(0), results.getTotalChanged());
        assertEquals(Long.valueOf(0), results.getTotalRejected());

        List<VisualSnapshot> states = results.getSnapshots();
        assertEquals(1, states.size());

        VisualSnapshot snapshot = results.getSnapshots().get(0);
        assertEquals("Blank", snapshot.getName());
        assertEquals("VisualTest getVisualResults", snapshot.getGroupName());
        assertEquals("accepted", snapshot.getStatus());
        String url = snapshot.getUrl();
        assertTrue(url.contains(GitManager.getCurrentBranch()));
        assertTrue(url.contains("Blank"));
        assertTrue(url.contains("Windows"));
    }

    @Test
    public void runMultipleTests() {
        session = new VisualSession("VisualTest runMultipleTests 1");
        driver = session.start();

        session.takeSnapshot("Blank1");

        session.newVisualTest("VisualTest runMultipleTests 2");

        session.takeSnapshot("Blank2");

        VisualSnapshot test1 = session.getVisualResults().getSnapshots().get(0);
        VisualSnapshot test2 = session.getVisualResults().getSnapshots().get(1);
        assertEquals("Blank1", test1.getName());
        assertEquals("VisualTest runMultipleTests 1", test1.getGroupName());
        assertEquals("Blank2", test2.getName());
        assertEquals("VisualTest runMultipleTests 2", test2.getGroupName());
    }

    @Test
    public void stopSendsVisualResultToSauce() {
        session = new VisualSession("VisualTest stopSendsVisualResultToSauce");
        driver = session.start();

        session.stop();
        assertEquals("passed", session.getResult());
    }

    @Test
    public void stopParameterOverridesVisualResult() {
        session = new VisualSession("VisualTest stopParameterOverridesVisualResult");
        driver = session.start();

        session.stop(false);
        assertEquals("failed", session.getResult());
    }

    @Test
    public void canNotTakeScreenshotAfterGettingVisualResults() {
        session = new VisualSession("VisualTest stopParameterOverridesVisualResult");
        driver = session.start();

        session.getVisualResults();

        try {
            session.takeSnapshot("Blank Page");
            fail("This should throw a SauceVisualException");
        } catch (SauceVisualException ex) {
            // Expected Behavior
        }
    }
}
