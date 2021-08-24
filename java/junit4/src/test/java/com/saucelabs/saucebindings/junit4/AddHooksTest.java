package com.saucelabs.saucebindings.junit4;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertTrue;

public class AddHooksTest extends SauceBaseTest {
    Boolean working = null;

    @Before
    public void setup() {
        working = true;
        super.setup();
    }

    @After
    public void teardown() {
        System.out.println("Can do something in teardown before test watcher methods");
    }

    @Test
    public void useCustomOptions() {
        assertTrue(working);
    }
}
