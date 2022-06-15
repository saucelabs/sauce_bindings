package com.saucelabs.saucebindings.junit4;

import org.junit.Assert;
import org.junit.Test;

public class QuickstartTest extends SauceBaseTest {
    @Test
    public void useAllDefaults() {
        driver.navigate().to("https://www.saucedemo.com");
        Assert.assertEquals(driver.getTitle(), "Swag Labs");
    }
}
