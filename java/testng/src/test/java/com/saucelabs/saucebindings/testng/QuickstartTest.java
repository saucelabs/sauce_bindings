package com.saucelabs.saucebindings.testng;

import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

public class QuickstartTest extends SauceBaseTest {
    @Test
    public void useAllDefaults() {
        driver.navigate().to("https://www.saucedemo.com");
        assertEquals(driver.getTitle(), "Swag Labs");
    }
}
