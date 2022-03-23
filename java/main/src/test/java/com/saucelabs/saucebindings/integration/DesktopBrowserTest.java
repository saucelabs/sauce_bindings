package com.saucelabs.saucebindings.integration;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import com.saucelabs.saucebindings.DataCenter;
import com.saucelabs.saucebindings.JobVisibility;
import com.saucelabs.saucebindings.SaucePlatform;
import com.saucelabs.saucebindings.SauceSession;
import com.saucelabs.saucebindings.options.SauceOptions;
import org.junit.After;
import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.io.IOException;
import java.util.List;
import java.util.Map;

public class DesktopBrowserTest {
    private SauceSession session = new SauceSession();
    private RemoteWebDriver driver;

    @After
    public void cleanUp() {
        if (session != null) {
            session.stop(true);
        }
    }

    @Test
    public void defaultsToUSWest() {
        driver = session.start();
        Assert.assertNotNull(driver);
        Assert.assertTrue(session.getSauceUrl().toString().contains("us-west-"));
    }

    @Test
    public void runsUSEast() {
        SauceOptions options = new SauceOptions();
        options.setPlatformName(SaucePlatform.LINUX);
        session = new SauceSession(options);
        session.setDataCenter(DataCenter.US_EAST);
        driver = session.start();

        Assert.assertNotNull(driver);
        Assert.assertTrue(session.getSauceUrl().toString().contains("us-east-1"));
    }

    @Test
    public void runsAPACSoutheast() {
        session.setDataCenter(DataCenter.APAC_SOUTHEAST);
        driver = session.start();

        Assert.assertNotNull(driver);
        Assert.assertTrue(session.getSauceUrl().toString().contains("apac-southeast"));
    }

    @Test
    public void runsEUCentral() {
        session.setDataCenter(DataCenter.EU_CENTRAL);
        driver = session.start();

        Assert.assertNotNull(driver);
        Assert.assertTrue(session.getSauceUrl().toString().contains("eu-central-1"));
    }

    @Test
    public void storesResultOfFirstStopPassing() {
        session.start();

        session.stop(true);
        session.stop(false);

        Assert.assertEquals("passed", session.getResult());
        Assert.assertTrue(session.getPassed());
    }

    @Test
    public void storesResultOfFirstStopFailing() {
        session.start();

        session.stop(false);
        session.stop(true);

        Assert.assertFalse(session.getPassed());
    }

    @Test
    public void nullsDriver() {
        driver = session.start();
        session.stop(true);

        Assert.assertNull(session.getDriver());
    }

    @Test
    public void stopsNetwork() {
        session = new SauceSession(SauceOptions.safari());
        driver = session.start();

        session.stopNetwork();

        driver.get("https://www.saucedemo.com");

        Assert.assertEquals("Failed to open page", driver.getTitle());

        session.startNetwork();
        driver.get("https://www.saucedemo.com");

        Assert.assertEquals("Swag Labs", driver.getTitle());
    }

    @Test
    public void getTestDetails() {
        driver = session.start();
        Map<String, Object> testDetails = session.getTestDetails();

        Assert.assertEquals("googlechrome", testDetails.get("browser"));
        Assert.assertEquals("Windows 10", testDetails.get("os"));
        Assert.assertEquals("webdriver", testDetails.get("automation_backend"));
        Assert.assertEquals("Windows 10", testDetails.get("os"));
        Assert.assertEquals("team", testDetails.get("public"));
        Assert.assertTrue((Boolean) testDetails.get("record_screenshots"));
        Assert.assertTrue((Boolean) testDetails.get("record_video"));
        Assert.assertTrue(((List<String>) testDetails.get("tags")).isEmpty());
        Assert.assertNull(testDetails.get("error"));
        Assert.assertNull(testDetails.get("selenium_version"));
        Assert.assertNull(testDetails.get("name"));
        Assert.assertNull(testDetails.get("assigned_tunnel_id"));
        Assert.assertNull(testDetails.get("passed"));
        Assert.assertNull(testDetails.get("custom-data"));
    }

    @Test
    public void changeTestName() {
        String newName = "New Test Name";
        driver = session.start();
        session.changeTestName(newName);

        Map<String, Object> testDetails = session.getTestDetails();
        Assert.assertEquals(newName, testDetails.get("name"));
    }

    @Test
    public void addTags() {
        driver = session.start();
        List<String> tags = ImmutableList.of("tag1", "tag2");
        session.addTags(tags);

        Map<String, Object> testDetails = session.getTestDetails();
        Assert.assertEquals(tags, testDetails.get("tags"));
    }

    @Test
    public void addCustomData() {
        driver = session.start();
        Map<String, Object> data = ImmutableMap.of("data1", "value1",
                "data2", "value2");
        session.addCustomData(data);

        Map<String, Object> testDetails = session.getTestDetails();
        Assert.assertEquals(data, testDetails.get("custom-data"));
    }

    @Test
    public void deleteTest() {
        driver = session.start();
        session.deleteTest();

        Assert.assertThrows(RuntimeException.class,  ()-> {session.getTestDetails();});
    }

    @Test
    public void changeTestVisibility() {
        driver = session.start();
        session.changeTestVisibility(JobVisibility.PRIVATE);

        Map<String, Object> testDetails = session.getTestDetails();
        Assert.assertEquals(JobVisibility.PRIVATE.toString(), testDetails.get("public"));
    }
}
