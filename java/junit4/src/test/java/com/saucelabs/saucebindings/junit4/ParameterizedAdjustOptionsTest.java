package com.saucelabs.saucebindings.junit4;

import com.saucelabs.saucebindings.SaucePlatform;
import com.saucelabs.saucebindings.options.SauceOptions;
import com.saucelabs.saucebindings.options.VDCConfigurations;
import java.util.Arrays;
import java.util.Collection;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

@RunWith(Parameterized.class)
public class ParameterizedAdjustOptionsTest extends SauceBaseTest {
  @Parameterized.Parameter public VDCConfigurations browser;

  @Parameterized.Parameter(1)
  public String browserVersion;

  @Parameterized.Parameter(2)
  public SaucePlatform saucePlatform;

  @Parameterized.Parameters()
  public static Collection<Object[]> crossBrowserData() {
    return Arrays.asList(
        new Object[][] {
          {SauceOptions.chrome(), "90.0", SaucePlatform.MAC_BIG_SUR},
          {SauceOptions.firefox(), "89.0", SaucePlatform.MAC_BIG_SUR},
          {SauceOptions.chrome(), "91.0", SaucePlatform.WINDOWS_10}
        });
  }

  @Override
  public SauceOptions createSauceOptions() {
    return browser.setBrowserVersion(browserVersion).setPlatformName(saucePlatform).build();
  }

  @Test
  public void useParameterizedOptions() {
    Assert.assertEquals(
        driver.getCapabilities().getBrowserName(), browser.build().getBrowserName().getValue());
    String version = (String) driver.getCapabilities().getCapability("browserVersion");
    Assert.assertTrue(version.contains(browserVersion));
  }
}
