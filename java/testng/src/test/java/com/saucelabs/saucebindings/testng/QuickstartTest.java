package com.saucelabs.saucebindings.testng;

import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

public class QuickstartTest extends SauceBaseTest {
    @Test
    public void useAllDefaults() {
        getDriver().navigate().to("https://www.saucedemo.com");
        assertEquals(getDriver().getTitle(), "Swag Labs");
    }
}
