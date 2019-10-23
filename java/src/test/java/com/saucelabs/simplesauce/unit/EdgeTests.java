package com.saucelabs.simplesauce.unit;

import com.saucelabs.simplesauce.EdgeVersion;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class EdgeTests extends BaseConfigurationTest{
    @Test
    public void withEdgeReturnsCorrectBrowserVersion() {
        for(EdgeVersion version : EdgeVersion.values()) {
            fakeSauceSession.withEdge(version);
            fakeSauceSession.start();
            String actualBrowserSetInConfig = fakeSauceSession.sauceSessionCapabilities.getVersion();
            String expectedVersionString = version.label;
            assertEquals(expectedVersionString, actualBrowserSetInConfig);
        }
    }
}
