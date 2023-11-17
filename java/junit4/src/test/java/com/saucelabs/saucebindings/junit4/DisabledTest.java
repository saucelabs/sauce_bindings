package com.saucelabs.saucebindings.junit4;

import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;

public class DisabledTest {

  @Rule
  public SauceBindingsExtension sauceExtension =
      SauceBindingsExtension.builder().enabled(false).build();

  @Test
  public void ignoresExtension() {
    Assert.assertNull(sauceExtension.getDriver());
    Assert.assertNull(sauceExtension.getSession());
  }
}
