package com.saucelabs.saucebindings;

import lombok.Setter;
import lombok.experimental.Accessors;
import org.openqa.selenium.chrome.ChromeOptions;

@Accessors(chain = true) @Setter
public class SauceOptionsFactory {
    public static SauceChromeBuilder chrome() {
        ChromeOptions chromeOptions = new ChromeOptions();
        return chrome(chromeOptions);
    }

    public static SauceChromeBuilder chrome(ChromeOptions chromeOptions) {
        return new SauceChromeBuilder(chromeOptions);
    }
}
