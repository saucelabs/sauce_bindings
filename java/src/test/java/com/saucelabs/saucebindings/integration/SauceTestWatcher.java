package com.saucelabs.saucebindings.integration;

import com.saucelabs.saucebindings.SauceSession;
import lombok.Setter;
import org.junit.rules.TestWatcher;
import org.junit.runner.Description;

public class SauceTestWatcher extends TestWatcher {
    @Setter private SauceSession sauceSession;

    @Override
    protected void succeeded(Description description) {
        sauceSession.stop("passed");
    }

    @Override
    protected void failed(Throwable e, Description description) {
        sauceSession.stop("failed");
    }
}
