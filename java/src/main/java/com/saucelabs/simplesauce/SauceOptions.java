package com.saucelabs.simplesauce;

import lombok.Getter;
import lombok.Setter;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.ie.InternetExplorerOptions;

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
        //TODO no longer needed with Chrome 75+
        chromeOptions.setExperimentalOption("w3c", true);
        browser = "Chrome";
        return this;
    }
    public SauceOptions withSafari()
    {
        return withMacOsMojave();
    }
    public SauceOptions withSafari(String version)
    {
        browser = "safari";
        //TODO: I did this but I hate it :(
        //I wish I could just have a default value set for the version param
        if(!version.equalsIgnoreCase(""))
        {
            browserVersion = version;
        }
        return this;
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
    public SauceOptions withLinux() {
        operatingSystem = "Linux";
        return this;
    }

    public SauceOptions withWindows10() {
        operatingSystem = "Windows 10";
        return this;
    }
    public SauceOptions withWindows8_1() {
        operatingSystem = "Windows 8.1";
        return this;
    }
    public SauceOptions withWindows8() {
        operatingSystem = "Windows 8";
        return this;
    }

    public SauceOptions withWindows7() {
        operatingSystem = "Windows 7";
        return this;
    }

    public SauceOptions withEdge() {
        browser = "Edge";
        EdgeOptions edgeOptions = new EdgeOptions();
        return this;
    }
    //TODO notice the duplication below with edge.
    //Maybe could be moved to a separate class so we can do withEdge().16_16299();
    //Or withEdge().version(EdgeVersion.16_16299);
    public SauceOptions withEdge18() {
        withEdge();
        browserVersion = "18.17763";
        return this;
    }
    public SauceOptions withEdge17() {
        withEdge();
        browserVersion = "17.17134";
        return this;
    }
    public SauceOptions withEdge16() {
        withEdge();
        browserVersion = "16.16299";
        return this;
    }

    public SauceOptions withEdge15() {
        withEdge();
        browserVersion = "15.15063";
        return this;
    }

    public SauceOptions withEdge14() {
        withEdge();
        browserVersion = "14.14393";
        return this;
    }

    public SauceOptions withEdge13() {
        withEdge();
        browserVersion = "13.10586";
        return this;
    }

    public SauceOptions withFirefox()
    {
        browser = "Firefox";
        return this;
    }

    public SauceOptions withIE(String version) {
        browser = "IE";
        browserVersion = version;
        InternetExplorerOptions ieOptions = new InternetExplorerOptions();
        return this;
    }

}
