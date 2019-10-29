package com.saucelabs.simplesauce;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class IETest extends BaseConfigurationTest{

    @Test
    public void withIE_validIeVersion() {
        sauceOptions.withIE(IEVersion._11);
        sauce = instantiateSauceSession();

        sauce.start();
        String actualBrowserSetInConfig = sauce.currentSessionCapabilities.getVersion();
        assertEquals("11.285", actualBrowserSetInConfig);
    }

    @Test
    public void withIE_default(){
        sauceOptions.withIE();
        sauce = instantiateSauceSession();

        sauce.start();
        String actualBrowserSetInConfig = sauce.currentSessionCapabilities.getVersion();
        assertEquals("latest", actualBrowserSetInConfig);
    }
}
