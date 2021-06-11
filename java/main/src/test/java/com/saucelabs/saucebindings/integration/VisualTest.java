package com.saucelabs.saucebindings.integration;

import com.saucelabs.saucebindings.VisualSession;
import com.saucelabs.saucebindings.options.InvalidSauceOptionsArgumentException;
import com.saucelabs.saucebindings.options.SauceOptions;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.List;
import java.util.Map;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

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

        Map snapshot = (Map) ((List) session.getResults().get("states")).get(0);
        assertEquals("Visual uses this name", (String) snapshot.get("groupName"));
    }

    @Test
    public void takesSnapshot() {
        SauceOptions options = SauceOptions.chrome().setName("Takes Sauce Snapshot").build();
        session = new VisualSession(options);
        session.start();
        session.takeSnapshot("Name of Snapshot");

        Map snapshot = (Map) ((List) session.getResults().get("states")).get(0);
        assertEquals("Name of Snapshot", (String) snapshot.get("name"));
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
}
