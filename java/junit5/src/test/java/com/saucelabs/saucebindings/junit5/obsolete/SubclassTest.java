package com.saucelabs.saucebindings.junit5.obsolete;

import com.saucelabs.saucebindings.junit5.SauceBaseTest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInfo;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.api.extension.RegisterExtension;
import org.openqa.selenium.chrome.ChromeDriver;

public class SubclassTest extends SauceBaseTest {
  @RegisterExtension public MyTestWatcher watcher = new MyTestWatcher();

  @BeforeAll
  public static void demoPurposes() {
    System.setProperty("SELENIUM_TARGET", "SAUCE_LABS");
  }

  @BeforeEach
  public void setUp(TestInfo testinfo) {
    if (System.getProperty("SELENIUM_TARGET").equals("SAUCE_LABS")) {
      super.setUp(testinfo);
    } else {
      driver = new ChromeDriver();
    }
  }

  @Test
  public void subclassedExampleObsolete() {
    driver.navigate().to("https://www.saucedemo.com");
    Assertions.assertEquals("Swag Labs", driver.getTitle());
  }

  public class MyTestWatcher extends SauceTestWatcher {
    @Override
    public void testSuccessful(ExtensionContext context) {
      if (session == null) {
        driver.quit();
      } else {
        super.testSuccessful(context);
      }
    }

    @Override
    public void testFailed(ExtensionContext context, Throwable cause) {
      if (session == null) {
        driver.quit();
      } else {
        super.testFailed(context, cause);
      }
    }
  }
}
