package com.saucelabs.simplesauce.examples;

import com.saucelabs.simplesauce.SauceSession;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.Assert;

public class UsingJUnit {

    private SauceSession sauceSession;

    @Before
    public void setUp(){
        sauceSession = new SauceSession();
        sauceSession.start();
    }

    @After
    public void tearDown(){
        sauceSession.stop();
    }

    @Test
    public void passingTest() {
        sauceSession.getDriver().get("https://www.saucedemo.com");

        Assert.assertTrue(sauceSession.getDriver().getTitle().contains("Swag Labs"));
    }

    @Test
    public void failingTest(){
        sauceSession.getDriver().get("https://www.saucedemo.com");

        Assert.assertTrue(sauceSession.getDriver().getCurrentUrl().contains("Swag Labs"));
    }
}
