package com.saucelabs.saucebindings.integration;

import com.saucelabs.saucebindings.SystemManager;
import com.saucelabs.saucebindings.VisualSession;
import com.saucelabs.saucebindings.options.InvalidSauceOptionsArgumentException;
import com.saucelabs.saucebindings.options.SauceOptions;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.List;
import java.util.Map;

import static org.junit.Assert.*;

public class VisualTest {
    private VisualSession session;

    @Before
    public void setup() {
        System.setProperty("BUILD_NAME", "Sauce Bindings");
    }

    @After
    public void cleanUp() {
        if (session != null) {
            session.stop(true);
        }
    }

    @Test
    public void usesVisualWhenOptionSet() {
        SauceOptions options = SauceOptions.chrome().setName("Visual uses this name").build();
        session = new VisualSession(options);
        session.start();
        session.takeSnapshot("Snapshot necessary to for Visual test to pass");
        assertEquals("Visual uses this name", getName());
    }

    @Test
    public void takesSnapshot() {
        SauceOptions options = SauceOptions.chrome().setName("Takes Sauce Snapshot").build();
        session = new VisualSession(options);
        session.start();
        session.takeSnapshot("Name of Snapshot");
        assertEquals("Name of Snapshot", getName());
    }

    @Test
    public void providesTestResults() {
        SauceOptions options = SauceOptions.chrome().setName("Provides Test Results").build();
        session = new VisualSession(options);
        session.start();
        session.takeSnapshot("Name-of-Snapshot");
        Map results = session.getResults();
        assertTrue((Boolean) results.get("passed"));
        assertEquals("success", results.get("status"));
        assertNull(results.get("message"));
        assertEquals(1, ((List) results.get("states")).size());
        Map totals = (Map) results.get("totals");
        assertEquals(0L, totals.get("new"));
        assertEquals(0L, totals.get("changed"));
        assertEquals(1L, totals.get("accepted"));
        assertEquals(0L, totals.get("rejected"));
        assertEquals(1L, totals.get("all"));
        Map snapshot = (Map) ((List) results.get("states")).get(0);
        assertEquals("Name-of-Snapshot", snapshot.get("name"));
        assertEquals("Provides Test Results", snapshot.get("groupName"));
        assertEquals("accepted", snapshot.get("status"));
        String url = (String) snapshot.get("url");
        assertTrue(url.contains(SystemManager.getCurrentGitBranch()));
        assertTrue(url.contains("Name-of-Snapshot"));
        assertTrue(url.contains("Windows%2010"));
    }

    @Test
    public void errorsWithoutTestName() {
        try {
            new VisualSession(new SauceOptions());
            fail("Expected InvalidSauceOptionsArgumentException exception");
        } catch (InvalidSauceOptionsArgumentException exception) {
            String msg = "Visual Tests Require setting a name in options: SauceOptions#setName(Name)";
            assertEquals(msg, exception.getMessage());
        }
    }

    public String getName() {
        Map snapshot = (Map) ((List) session.getResults().get("states")).get(0);
        return (String) snapshot.get("groupName");
    }
}
