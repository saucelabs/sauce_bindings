package com.saucelabs.saucebindings.testng;

import org.testng.Assert;
import org.testng.annotations.Test;

public class QuickstartTest extends SauceBaseTest {
    @Test
    public void useAllDefaults() {
        getDriver().navigate().to("https://www.saucedemo.com");
        Assert.assertEquals(getDriver().getTitle(), "Swag Labs");
    }
}
