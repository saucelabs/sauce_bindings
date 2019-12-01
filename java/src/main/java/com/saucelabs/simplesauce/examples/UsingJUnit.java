package com.saucelabs.simplesauce.examples;

import com.saucelabs.simplesauce.SauceSession;
import org.junit.*;
import org.openqa.selenium.WebDriver;

public class UsingJUnit {

    private WebDriver driver;

    @Before
    public void setUp(){
        SauceSession sauceSession = new SauceSession();
        driver = sauceSession.start();
    }

    @After
    public void tearDown(){
        driver.quit();
    }

    @Test
    public void passingTest() {
        driver.get("https://www.saucedemo.com");

        Assert.assertTrue(driver.getTitle().contains("Swag Labs"));
    }

    @Test
    @Ignore
    public void failingTest() {
        driver.get("https://www.saucedemo.com");

        Assert.assertTrue(driver.getCurrentUrl().contains("Swag Labs"));
    }
}
