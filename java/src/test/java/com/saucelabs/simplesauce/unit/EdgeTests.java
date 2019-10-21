package com.saucelabs.simplesauce.unit;

import com.saucelabs.simplesauce.SauceSession;
import org.junit.Test;

import java.net.MalformedURLException;
import static org.junit.Assert.assertEquals;

public class EdgeTests extends BaseConfigurationTest{
    @Test
    public void withEdgeReturnsCorrectBrowserVersion() {
        for(SauceSession.EdgeVersion version : SauceSession.EdgeVersion.values()) {
            fakeSauceSession.withEdge(version);
            fakeSauceSession.start();
            String actualBrowserSetInConfig = fakeSauceSession.sauceSessionCapabilities.getVersion();
            String expectedVersionString = version.name().substring(1);
            assertEquals(expectedVersionString, actualBrowserSetInConfig);
        }
    }
}
