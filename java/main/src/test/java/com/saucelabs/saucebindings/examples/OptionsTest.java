package com.saucelabs.saucebindings.examples;

import com.saucelabs.saucebindings.SaucePlatform;
import com.saucelabs.saucebindings.SauceSession;
import com.saucelabs.saucebindings.UnhandledPromptBehavior;
import com.saucelabs.saucebindings.options.SauceOptions;
import org.junit.Test;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.time.Duration;

public class OptionsTest {

    @Test
    public void defaultBrowserOptions() {
        SauceOptions sauceOptions = SauceOptions.firefox().build();

        SauceSession session = new SauceSession(sauceOptions);
        RemoteWebDriver driver = session.start();

        driver.get("https://www.saucedemo.com/");

        session.stop(true);
    }

    @Test
    public void customOptions() {
        SauceOptions sauceOptions = SauceOptions.firefox()
                .setBrowserVersion("88.0")
                .setPlatformName(SaucePlatform.WINDOWS_8)
                .setUnhandledPromptBehavior(UnhandledPromptBehavior.IGNORE)
                .setIdleTimeout(Duration.ofSeconds(45))
                .setTimeZone("Alaska")
                .build();

        SauceSession session = new SauceSession(sauceOptions);
        RemoteWebDriver driver = session.start();

        driver.get("https://www.saucedemo.com/");

        session.stop(true);
    }

    @Test
    public void customAndBrowserVendorOptions() {
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.addArguments("--start-fullscreen");

        SauceOptions sauceOptions = SauceOptions.chrome(chromeOptions)
                .setBrowserVersion("88.0")
                .setPlatformName(SaucePlatform.WINDOWS_8)
                .setUnhandledPromptBehavior(UnhandledPromptBehavior.IGNORE)
                .setIdleTimeout(Duration.ofSeconds(45))
                .setTimeZone("Alaska")
                .build();

        SauceSession session = new SauceSession(sauceOptions);
        RemoteWebDriver driver = session.start();

        driver.get("https://www.saucedemo.com/");

        session.stop(true);
    }
}
