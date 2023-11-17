package com.saucelabs.saucebindings.junit4;

import org.junit.After;
import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;

public class DefaultTest {
  @Rule public SauceBindingsExtension sauceExtension = new SauceBindingsExtension();

  @Test
  public void useAllDefaults() {
    try {
      sauceExtension.getSession().annotate("Annotating test");
      sauceExtension.getDriver().get("https://www.saucedemo.com/");
    } catch (Exception e) {
      Assert.fail("Driver and Session should still be available");
    }
  }

  @After
  public void teardownWorks() {
    try {
      sauceExtension.getDriver().getTitle();
    } catch (Exception e) {
      Assert.fail("Session should not be stopped, yet");
    }
  }
}
