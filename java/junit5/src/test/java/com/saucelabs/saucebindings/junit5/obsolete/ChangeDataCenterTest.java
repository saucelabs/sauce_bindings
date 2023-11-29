package com.saucelabs.saucebindings.junit5.obsolete;

import com.saucelabs.saucebindings.DataCenter;
import com.saucelabs.saucebindings.junit5.SauceBaseTest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class ChangeDataCenterTest extends SauceBaseTest {
  @Override
  public DataCenter getDataCenter() {
    return DataCenter.EU_CENTRAL;
  }

  @Test
  public void setDataCenterObsolete() {
    Assertions.assertNotNull(driver);
    Assertions.assertTrue(session.getSauceUrl().toString().contains("eu-central-1"));
  }
}
