package com.saucelabs.saucebindings.options;

import com.google.common.collect.ImmutableMap;
import com.saucelabs.saucebindings.SystemManager;
import com.saucelabs.saucebindings.Timeouts;
import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.MutableCapabilities;

import java.time.Duration;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class SauceOptionsDeprecatedTimeoutTest {
    private final SauceOptions sauceOptions = SauceOptions.chrome().build();

    @Test
    public void setsTimeoutStoreValues() {
        sauceOptions.timeout.setImplicitWait(1000);
        sauceOptions.timeout.setPageLoad(100000);
        sauceOptions.timeout.setScript(10000);

        Assert.assertEquals(Duration.ofSeconds(1), sauceOptions.getImplicitWaitTimeout());
        Assert.assertEquals(Duration.ofSeconds(100), sauceOptions.getPageLoadTimeout());
        Assert.assertEquals(Duration.ofSeconds(10), sauceOptions.getScriptTimeout());
    }

    @Test
    public void parsesTimeoutStoreCapabilities() {
        sauceOptions.timeout.setImplicitWait(1000);
        sauceOptions.timeout.setPageLoad(100000);
        sauceOptions.timeout.setScript(10000);
        sauceOptions.sauce().setBuild("Build Name");

        Map<Timeouts, Integer> timeouts = new HashMap<>();
        timeouts.put(Timeouts.IMPLICIT, 1000);
        timeouts.put(Timeouts.SCRIPT, 10000);
        timeouts.put(Timeouts.PAGE_LOAD, 100000);

        MutableCapabilities expectedCapabilities = new MutableCapabilities();
        expectedCapabilities.setCapability("browserName", "chrome");
        expectedCapabilities.setCapability("browserVersion", "latest");
        expectedCapabilities.setCapability("platformName", "Windows 10");
        expectedCapabilities.setCapability("timeouts", timeouts);

        MutableCapabilities sauceCapabilities = new MutableCapabilities();
        sauceCapabilities.setCapability("build", "Build Name");
        sauceCapabilities.setCapability("username", SystemManager.get("SAUCE_USERNAME"));
        sauceCapabilities.setCapability("accessKey", SystemManager.get("SAUCE_ACCESS_KEY"));
        expectedCapabilities.setCapability("sauce:options", sauceCapabilities);
        expectedCapabilities.setCapability("goog:chromeOptions",
                ImmutableMap.of("args", new ArrayList<>(),
                        "extensions", new ArrayList<>()));
        MutableCapabilities actualCapabilities = sauceOptions.toCapabilities();

        Assert.assertEquals(expectedCapabilities.asMap().toString(), actualCapabilities.asMap().toString());
    }
}
