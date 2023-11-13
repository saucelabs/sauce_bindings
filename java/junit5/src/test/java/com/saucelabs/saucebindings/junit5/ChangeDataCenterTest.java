package com.saucelabs.saucebindings.junit5;

import com.saucelabs.saucebindings.DataCenter;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class ChangeDataCenterTest extends SauceBaseTest {
  @Override
  public DataCenter getDataCenter() {
    return DataCenter.EU_CENTRAL;
  }

  @Test
  public void setDataCenter() {
    Assertions.assertNotNull(driver);
    Assertions.assertTrue(session.getSauceUrl().toString().contains("eu-central-1"));
  }
}
