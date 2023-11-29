package com.saucelabs.saucebindings.junit5;

import com.saucelabs.saucebindings.DataCenter;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.RegisterExtension;

public class DataCenterTest {
  @RegisterExtension
  static SauceBindingsExtension sauceExtension = new SauceBindingsExtension(DataCenter.EU_CENTRAL);

  @Test
  public void setDataCenter() {
    DataCenter dataCenter = sauceExtension.getSession().getDataCenter();
    Assertions.assertEquals(DataCenter.EU_CENTRAL, dataCenter);

    Assertions.assertDoesNotThrow(
        () -> {
          sauceExtension.getSession().annotate("Annotating test");
          sauceExtension.getDriver().get("https://www.saucedemo.com/");
        },
        "Driver and Session should be available");
  }
}
