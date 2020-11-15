package com.saucelabs.saucebindings.options;

import com.saucelabs.saucebindings.options.builders.*;
import lombok.Setter;
import lombok.experimental.Accessors;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.ie.InternetExplorerOptions;
import org.openqa.selenium.safari.SafariOptions;

@Accessors(chain = true) @Setter
public class SauceOptionsFactory {
    public static SauceChromeBuilder chrome() {
        ChromeOptions chromeOptions = new ChromeOptions();
        return chrome(chromeOptions);
    }

    public static SauceChromeBuilder chrome(ChromeOptions chromeOptions) {
        return new SauceChromeBuilder(chromeOptions);
    }

    public static SauceEdgeBuilder edge() {
        EdgeOptions edgeOptions = new EdgeOptions();
        return edge(edgeOptions);
    }

    public static SauceEdgeBuilder edge(EdgeOptions edgeOptions) {
        return new SauceEdgeBuilder(edgeOptions);
    }

    public static SauceFirefoxBuilder firefox() {
        FirefoxOptions firefoxOptions = new FirefoxOptions();
        return firefox(firefoxOptions);
    }

    public static SauceFirefoxBuilder firefox(FirefoxOptions firefoxOptions) {
        return new SauceFirefoxBuilder(firefoxOptions);
    }

    public static SauceIEBuilder internetExplorer() {
        InternetExplorerOptions internetExplorerOptions = new InternetExplorerOptions();
        return internetExplorer(internetExplorerOptions);
    }

    public static SauceIEBuilder internetExplorer(InternetExplorerOptions internetExplorerOptions) {
        return new SauceIEBuilder(internetExplorerOptions);
    }

    public static SauceSafariBuilder safari() {
        SafariOptions safariOptions = new SafariOptions();
        return safari(safariOptions);
    }

    public static SauceSafariBuilder safari(SafariOptions safariOptions) {
        return new SauceSafariBuilder(safariOptions);
    }

}
