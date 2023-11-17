package com.saucelabs.saucebindings.junit4;

import com.saucelabs.saucebindings.DataCenter;
import com.saucelabs.saucebindings.SauceSession;
import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;

public class DataCenterTest {
  @Rule
  public SauceBindingsExtension sauceExtension =
          SauceBindingsExtension.builder().dataCenter(DataCenter.EU_CENTRAL).build();

  @Test
  public void setDataCenter() {
    SauceSession session = sauceExtension.getSession();
    Assert.assertEquals(DataCenter.EU_CENTRAL, session.getDataCenter());

    try {
      session.annotate("Annotating test");
      sauceExtension.getDriver().get("https://www.saucedemo.com/");
    } catch (Exception e) {
      Assert.fail("Driver and Session should still be available");
    }
  }
}
