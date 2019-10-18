package com.saucelabs.simplesauce;

import org.hamcrest.text.IsEqualIgnoringCase;
import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;

public class DataCenterTest
{
    @Test
    public void usWestDataCenterUrl_isCorrect()
    {
        assertThat(DataCenter.USWest,
                IsEqualIgnoringCase.equalToIgnoringCase("https://ondemand.saucelabs.com/wd/hub"));
    }
    @Test
    public void usEastDataCenterUrl_isCorrect()
    {
        assertThat(DataCenter.US_EAST,
                IsEqualIgnoringCase.equalToIgnoringCase("https://ondemand.us-east-1.saucelabs.com/wd/hub"));
    }
    @Test
    public void euCentralDataCenterUrl_isCorrect()
    {
        assertThat(DataCenter.EU_CENTRAL,
                IsEqualIgnoringCase.equalToIgnoringCase("https://ondemand.eu-central-1.saucelabs.com/wd/hub"));
    }
}
