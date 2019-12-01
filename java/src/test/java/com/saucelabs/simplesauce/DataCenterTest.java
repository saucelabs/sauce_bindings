package com.saucelabs.simplesauce;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class DataCenterTest
{
    @Test
    public void usWestDataCenterUrl_isCorrect()
    {
        assertEquals("https://ondemand.saucelabs.com/wd/hub",
                DataCenter.US_WEST.getEndpoint());
    }
    @Test
    public void usEastDataCenterUrl_isCorrect()
    {
        assertEquals("https://ondemand.us-east-1.saucelabs.com/wd/hub",
                DataCenter.US_EAST.getEndpoint());
    }
    @Test
    public void euCentralDataCenterUrl_isCorrect()
    {
        assertEquals("https://ondemand.eu-central-1.saucelabs.com/wd/hub",
                DataCenter.EU_CENTRAL.getEndpoint());
    }
}
