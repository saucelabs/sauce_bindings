package com.saucelabs.saucebindings.junit4;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.Description;
import org.openqa.selenium.chrome.ChromeDriver;

import static org.junit.Assert.assertEquals;

public class SubclassTest extends SauceBaseTest {
    @Rule
    public MyTestWatcher watcher = new MyTestWatcher();

    @BeforeClass
    public static void demoPurposes() {
        System.setProperty("SELENIUM_TARGET", "SAUCE_LABS");
    }

    @Before
    public void setup() {
        if (System.getProperty("SELENIUM_TARGET").equals("SAUCE_LABS")) {
            super.setup();
        } else {
            driver = new ChromeDriver();
        }
    }

    @Test
    public void subclassedExample() {
        driver.navigate().to("https://www.saucedemo.com");
        assertEquals(driver.getTitle(), "Swag Labs");
    }

    public class MyTestWatcher extends SauceTestWatcher {
        @Override
        protected void succeeded(Description description) {
            if (session == null) {
                driver.quit();
            } else {
                super.succeeded(description);
            }
        }

        @Override
        protected void failed(Throwable e, Description description) {
            if (session == null) {
                driver.quit();
            } else {
                super.failed(e, description);
            }
        }
    }
}
