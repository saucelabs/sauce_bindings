package com.saucelabs.simplesauce;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class IETest extends BaseConfigurationTest{

    @Test
    public void withIE_validIeVersion() {
        sauceOptions.withIE(IEVersion._11);
        mockSauceSession = instantiateSauceSession();

        mockSauceSession.start();
        String actualBrowserSetInConfig = mockSauceSession.sauceSessionCapabilities.getVersion();
        assertEquals("11.285", actualBrowserSetInConfig);
    }

    @Test
    public void withIE_default(){
        sauceOptions.withIE();
        mockSauceSession = instantiateSauceSession();

        mockSauceSession.start();
        String actualBrowserSetInConfig = mockSauceSession.sauceSessionCapabilities.getVersion();
        assertEquals("latest", actualBrowserSetInConfig);
    }
}
