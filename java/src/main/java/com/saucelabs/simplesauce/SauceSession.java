package com.saucelabs.simplesauce;

import lombok.Getter;
import lombok.Setter;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URL;

public class SauceSession {

    @Getter @Setter private SauceDataCenter sauceDataCenter = SauceDataCenter.US_WEST;
    @Setter private String sauceUsername = System.getenv("SAUCE_USERNAME");
    @Setter private String sauceAccessKey = System.getenv("SAUCE_ACCESS_KEY");
    @Getter private SauceOptions sauceOptions;
    @Getter private WebDriver webDriver;
    @Setter private URL sauceUrl;

    public SauceSession() {
        sauceOptions = new SauceOptions();
    }

    public SauceSession(SauceOptions options) {
        sauceOptions = options;
    }

    public WebDriver start() throws MalformedURLException {
        if(sauceUsername == null) {
            throw new SauceEnvironmentVariablesNotSetException("Sauce Username was not provided");
        } else if(sauceAccessKey == null) {
            throw new SauceEnvironmentVariablesNotSetException("Sauce Access Key was not provided");
        } else {
            webDriver = new RemoteWebDriver(getSauceUrl(), sauceOptions.toCapabilities());
            return webDriver;
        }
    }

    public void stop(TestResult result) {
        JavascriptExecutor js = (JavascriptExecutor) webDriver;
        js.executeScript("sauce:job-result=" + result.getResult());

        if(webDriver !=null)
            webDriver.quit();
    }

    public URL getSauceUrl() throws MalformedURLException {
        if(sauceUrl != null) {
            return sauceUrl;
        } else {
            String url = "https://" + sauceUsername + ":" + sauceAccessKey + "@" +
                    sauceDataCenter.getValue() + ":443/wd/hub";
            return new URL(url);
        }
    }
}
