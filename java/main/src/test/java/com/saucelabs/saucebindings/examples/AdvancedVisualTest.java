package com.saucelabs.saucebindings.examples;

import com.saucelabs.saucebindings.SaucePlatform;
import com.saucelabs.saucebindings.VisualSession;
import com.saucelabs.saucebindings.options.VisualOptions;
import com.saucelabs.saucebindings.options.SauceOptions;
import org.junit.Test;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.util.Arrays;
import java.util.Collection;

import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

@RunWith(Parameterized.class)
public class AdvancedVisualTest {
    @Parameterized.Parameter
    public String browserName;
    @Parameterized.Parameter(2)
    public String browserVersion;
    @Parameterized.Parameter(1)
    public String platform;
    @Parameterized.Parameter(3)
    public String viewportSize;
    // Device name is a property added to know which device resolution is configured
    @Parameterized.Parameter(4)
    public String deviceName;

    @Parameterized.Parameters()
    public static Collection<Object[]> crossBrowserData() {
        return Arrays.asList(new Object[][] {
                { "Chrome", "Windows 10", "latest", "412x732", "Pixel XL" },
                { "Safari", "macOS 10.15", "latest", "375x812", "iPhone X" },
        });
    }

    @Test
    public void dataDrivenSession() {

        SauceOptions sauceOptions = SauceOptions.firefox()
                .setPlatformName(SaucePlatform.MAC_HIGH_SIERRA)
                .setBrowserVersion("99")
                .setName("dataDrivenSession()")
                .build();

        VisualOptions visualOptions = new VisualOptions(sauceOptions, "Data Driven Project");
        VisualSession session = new VisualSession(visualOptions);

        // 2. Start Session to get the driver
        RemoteWebDriver driver = session.start();

        // 3. Use the driver in your tests just like normal
        driver.get("https://www.saucedemo.com/");

        // 4. Take snapshot
        session.takeSnapshot("snapshotName1");

        // 5. Go to another URL
        driver.get("https://www.google.com/");

        // 6. Take 2nd snapshot
        session.takeSnapshot("snapshotName2");

        // 5. Stop the Session
        session.stop();
    }
}
