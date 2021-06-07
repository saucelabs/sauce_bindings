package com.saucelabs.saucebindings.junit4;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class QuickstartTest extends SauceBaseTest {
    @Test
    public void useAllDefaults() {
        driver.navigate().to("https://www.saucedemo.com");
        assertEquals(driver.getTitle(), "Swag Labs");
    }
}
