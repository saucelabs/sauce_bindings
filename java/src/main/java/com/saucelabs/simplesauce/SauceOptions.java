package com.saucelabs.simplesauce;

import lombok.Getter;
import lombok.Setter;
import org.openqa.selenium.chrome.ChromeOptions;

public class SauceOptions {
    //TODO can probably use BrowserType enum from Selenium to do BrowserType.CHROME
    @Getter
    @Setter
    public String browser = "Chrome";

    @Getter @Setter public String browserVersion = "latest";
    @Getter @Setter public String operatingSystem = "Windows 10";
    @Getter public  ChromeOptions chromeOptions;

    public SauceOptions withChrome()
    {
        chromeOptions = new ChromeOptions();
        chromeOptions.setExperimentalOption("w3c", true);
        browser = "Chrome";
        return this;
    }
    public SauceOptions withSafari()
    {
        return withMacOsMojave();
    }
    public SauceOptions withMacOsMojave() {
        operatingSystem = "macOS 10.14";
        browser = "safari";
        browserVersion = "12.0";
        return this;
    }
    public SauceOptions withMacOsHighSierra()
    {
        operatingSystem = "macOS 10.13";
        browser = "Safari";
        return this;
    }
    public SauceOptions withMacOsSierra() {
        operatingSystem = "macOS 10.12";
        browser = "Safari";
        return this;
    }

    public SauceOptions withMacOsXElCapitan() {
        operatingSystem = "OS X 10.11";
        browser = "Safari";
        return this;
    }

    public SauceOptions withMacOsXYosemite() {
        operatingSystem = "OS X 10.10";
        browser = "Safari";
        return this;
    }


}
