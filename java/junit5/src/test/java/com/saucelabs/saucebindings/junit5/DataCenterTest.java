package com.saucelabs.saucebindings.junit5;

import com.saucelabs.saucebindings.DataCenter;
import com.saucelabs.saucebindings.SauceSession;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.RegisterExtension;
import org.openqa.selenium.WebDriver;

public class DataCenterTest {
  @RegisterExtension
  static SauceBindingsExtension sauceExtension =
      new SauceBindingsExtension.Builder().withDataCenter(DataCenter.EU_CENTRAL).build();

  @Test
  public void setDataCenter(SauceSession session, WebDriver driver) {
    DataCenter dataCenter = session.getDataCenter();
    Assertions.assertEquals(DataCenter.EU_CENTRAL, dataCenter);

    Assertions.assertDoesNotThrow(
        () -> {
          session.annotate("Annotating test");
          driver.get("https://www.saucedemo.com/");
        },
        "Driver and Session should be available");
  }
}
