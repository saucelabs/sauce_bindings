package com.saucelabs.saucebindings.junit4;

import com.saucelabs.saucebindings.SauceSession;
import java.util.List;
import org.junit.Assert;
import org.junit.Assume;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.openqa.selenium.WebDriver;

public class ToggleTest {
  private SauceSession session;
  private WebDriver driver;

  @Rule public SauceBindingsWatcher sauceWatcher = new SauceBindingsWatcher();

  static {
    System.setProperty("sauce.disabled", "true");
  }

  @Before
  public void storeVariables() {
    this.session = sauceWatcher.getSession();
    this.driver = sauceWatcher.getDriver();
  }

  @Test
  public void disableSauce() {
    Assume.assumeTrue("true".equals(System.getProperty("sauce.disabled")));

    Assert.assertNull(driver);

    try {
      session.annotate("This gets ignored");
      session.addTags(List.of("ignored"));
      session.stopNetwork();
      session.enableLogging();
      session.getAccessibilityResults();
    } catch (Exception e) {
      Assert.fail("Exception should not be thrown.");
    }
  }
}
