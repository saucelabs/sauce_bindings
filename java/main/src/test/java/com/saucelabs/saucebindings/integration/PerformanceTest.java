package com.saucelabs.saucebindings.integration;

import com.saucelabs.saucebindings.SauceSession;
import com.saucelabs.saucebindings.options.SauceOptions;
import com.saucelabs.saucebindings.performance.PerformanceMetrics;
import com.saucelabs.saucebindings.performance.PerformanceResults;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TestName;
import org.openqa.selenium.By;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class PerformanceTest {
    private SauceSession session;
    private RemoteWebDriver driver;

    @After
    public void cleanUp() {
        if (session != null) {
            session.stop(true);
        }
    }

    @Rule
    public TestName testName = new TestName();

    @Before
    public void setup() {
        SauceOptions sauceOptions = SauceOptions.chrome()
                .setBrowserVersion("85.0")
                .setName(testName.getMethodName())
                .setCapturePerformance()
                .build();
        session = new SauceSession(sauceOptions);
        driver = session.start();
    }

    @Test
    public void performanceResults() {
        driver.get("https://www.saucedemo.com");

        PerformanceResults performanceResults = session.performance().getResults();
        // We don't actually care if this "passes" or "fails" just that these methods are returning correct values
        if (performanceResults.isPassed()) {
            Assert.assertTrue(performanceResults.getDetails().isEmpty());
            Assert.assertNull(performanceResults.getReason());
        } else {
            Assert.assertTrue(performanceResults.getReason().contains("Metrics"));
            Assert.assertFalse(performanceResults.getDetails().isEmpty());
        }
    }

    @Test
    public void performanceMetricResults() {
        driver.get("https://www.saucedemo.com");

        PerformanceResults performanceResults = session.performance().getResults("firstInteractive");
        if (performanceResults.isPassed()) {
            Assert.assertTrue(performanceResults.getDetails().isEmpty());
            Assert.assertNull(performanceResults.getReason());
        } else {
            Map<String, Object> firstInteractive = (Map<String, Object>) performanceResults.getDetails().get("firstInteractive");
            Assert.assertTrue((Long) firstInteractive.get("actual") < 5000);
        }
    }

    @Test
    public void performanceMetricsResults() {
        driver.get("https://www.saucedemo.com");

        List<String> metrics = new ArrayList<String>();
        metrics.add("firstInteractive");
        metrics.add("load");

        PerformanceResults performanceResults = session.performance().getResults(metrics);
        if (performanceResults.isPassed()) {
            Assert.assertTrue(performanceResults.getDetails().isEmpty());
            Assert.assertNull(performanceResults.getReason());
        } else if (performanceResults.getDetails().get("firstInteractive") == null) {
            Assert.assertNotNull(performanceResults.getDetails().get("load"));
        } else {
            Map<String, Object> firstInteractive = (Map<String, Object>) performanceResults.getDetails().get("firstInteractive");
            Assert.assertTrue((Long) firstInteractive.get("actual") < 5000);
        }
    }

    @Test
    public void performanceDetails() {
        driver.get("https://www.saucedemo.com");

        PerformanceMetrics performanceMetrics = session.performance().getMetrics();
        Assert.assertTrue(performanceMetrics.getLoad() < 1500);
    }

    @Test
    public void disablePerformance() {
        driver.get("https://www.saucedemo.com");

        PerformanceMetrics performanceMetrics1 = session.performance().getMetrics();

        // Disabling performance prevents previous metrics from being overridden
        session.performance().disable();

        driver.findElement(By.id("user-name")).sendKeys("standard_user");
        driver.findElement(By.id("password")).sendKeys("secret_sauce");
        driver.findElement(By.id("login-button")).click();

        PerformanceMetrics performanceMetrics2 = session.performance().getMetrics();

        // Disabled means the metrics haven't changed
        // If metrics were captured for filling the form, load would be null
        Assert.assertEquals(performanceMetrics1.getLoad(), performanceMetrics2.getLoad());
    }

    @Test
    public void enablePerformance() {
        driver.get("https://www.saucedemo.com");

        session.performance().disable();
        session.performance().enable();

        driver.findElement(By.id("user-name")).sendKeys("standard_user");
        driver.findElement(By.id("password")).sendKeys("secret_sauce");
        driver.findElement(By.id("login-button")).click();

        PerformanceMetrics performanceMetrics = session.performance().getMetrics();
        Assert.assertNull(performanceMetrics.getLoad());
    }
}
