package com.saucelabs.saucebindings.examples;

import com.saucelabs.saucebindings.SauceSession;
import com.saucelabs.saucebindings.UnhandledPromptBehavior;
import com.saucelabs.saucebindings.options.SauceOptions;
import org.junit.Test;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

public class ConversionTest {

    public ChromeOptions optionsFromPlatformConfigurator() {
        ChromeOptions browserOptions = new ChromeOptions();
        browserOptions.setPlatformName("Windows 10");
        browserOptions.setBrowserVersion("latest");
        Map<String, Object> sauceOptions = new HashMap<>();
        browserOptions.setCapability("sauce:options", sauceOptions);

        return browserOptions;
    }

    @Test
    public void noSauceBindingsExampleForComparison() throws MalformedURLException {
        String username = System.getenv("SAUCE_USERNAME");
        String accessKey = System.getenv("SAUCE_ACCESS_KEY");
        String url = "https://" + username + ":" + accessKey + "@ondemand.us-west-1.saucelabs.com/wd/hub";

        RemoteWebDriver driver = new RemoteWebDriver(new URL(url), optionsFromPlatformConfigurator());

        driver.get("https://www.saucedemo.com/");

        driver.executeScript("sauce:job-result=passed");
        driver.quit();
    }

    @Test
    public void seleniumOptionsWithSauceSession() {
        SauceSession session = new SauceSession(optionsFromPlatformConfigurator());
        RemoteWebDriver driver = session.start();

        driver.get("https://www.saucedemo.com/");

        session.stop(true);
    }

    @Test
    public void addSauceOptionsToSeleniumOptions() {
        SauceOptions sauceBindingsOptions = SauceOptions.chrome(optionsFromPlatformConfigurator())
                .setUnhandledPromptBehavior(UnhandledPromptBehavior.IGNORE)
                .setTimeZone("Alaska")
                .build();

        SauceSession session = new SauceSession(sauceBindingsOptions);
        RemoteWebDriver driver = session.start();

        driver.get("https://www.saucedemo.com/");

        session.stop(true);
    }
}
