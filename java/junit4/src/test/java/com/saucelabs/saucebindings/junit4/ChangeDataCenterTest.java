package com.saucelabs.saucebindings.junit4;

import com.saucelabs.saucebindings.DataCenter;
import org.junit.Assert;
import org.junit.Test;

public class ChangeDataCenterTest extends SauceBaseTest {
  @Override
  public DataCenter getDataCenter() {
    return DataCenter.EU_CENTRAL;
  }

  @Test
  public void setDataCenter() {
    Assert.assertNotNull(driver);
    Assert.assertTrue(session.getSauceUrl().toString().contains("eu-central-1"));
  }
}
