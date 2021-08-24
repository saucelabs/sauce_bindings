package com.saucelabs.saucebindings.testng;

import com.saucelabs.saucebindings.SaucePlatform;
import com.saucelabs.saucebindings.options.SauceOptions;
import com.saucelabs.saucebindings.options.VDCConfigurations;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.lang.reflect.Method;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class ParameterizedOptionsTest extends SauceParameterizedBaseTest {

    @DataProvider(name = "sauceBrowsers", parallel = true)
    public static Object[][] sauceBrowserDataProvider() {
        return new Object[][]{
                new Object[]{SauceOptions.chrome(), "90.0", SaucePlatform.MAC_BIG_SUR},
                new Object[]{SauceOptions.firefox(), "89.0", SaucePlatform.MAC_BIG_SUR},
                new Object[]{SauceOptions.chrome(), "91.0", SaucePlatform.WINDOWS_10}
        };
    }

    @Override
    protected SauceOptions createSauceOptions(Method method, Object[] parameters) {
        return ((VDCConfigurations) parameters[0])
                .setBrowserVersion((String) parameters[1])
                .setPlatformName((SaucePlatform) parameters[2])
                .build();
    }

    @Test(dataProvider = "sauceBrowsers")
    public void useParameters(VDCConfigurations browser, String browserVersion, SaucePlatform saucePlatform) {
        assertEquals(getDriver().getCapabilities().getBrowserName(), browser.build().getBrowserName().getValue());
        String version = (String) getDriver().getCapabilities().getCapability("browserVersion");
        assertTrue(version.contains(browserVersion));
    }
}
