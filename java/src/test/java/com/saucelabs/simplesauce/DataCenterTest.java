package com.saucelabs.simplesauce;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class DataCenterTest
{
    @Test
    public void usWestDataCenterUrl_isCorrect()
    {
        assertEquals("ondemand.us-west-1.saucelabs.com",
                DataCenter.US_WEST.getEndpoint());
    }
    @Test
    public void usEastDataCenterUrl_isCorrect()
    {
        assertEquals("ondemand.us-east-1.saucelabs.com",
                DataCenter.US_EAST.getEndpoint());
    }
    @Test
    public void euCentralDataCenterUrl_isCorrect()
    {
        assertEquals("ondemand.eu-central-1.saucelabs.com",
                DataCenter.EU_CENTRAL.getEndpoint());
    }
}
