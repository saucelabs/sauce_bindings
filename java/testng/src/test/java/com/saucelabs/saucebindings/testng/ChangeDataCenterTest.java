package com.saucelabs.saucebindings.testng;

import com.saucelabs.saucebindings.DataCenter;
import org.testng.Assert;
import org.testng.annotations.Test;

public class ChangeDataCenterTest extends SauceBaseTest {

    @Override
    public DataCenter getDataCenter() {
        return DataCenter.EU_CENTRAL;
    }

    @Test
    public void setDataCenter() {
        Assert.assertNotNull(getDriver());
        Assert.assertTrue(getSession().getSauceUrl().toString().contains("eu-central-1"));
    }
}
