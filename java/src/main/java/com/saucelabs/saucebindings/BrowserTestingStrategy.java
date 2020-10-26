package com.saucelabs.saucebindings;

import lombok.Getter;
import lombok.Setter;
import org.openqa.selenium.InvalidArgumentException;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URL;

public class BrowserTestingStrategy implements APIStrategy {
    private final SauceOptions sauceOptions;
    @Getter private RemoteWebDriver driver;
    @Getter
    @Setter private DataCenter dataCenter = DataCenter.US_WEST;
    private URL sauceUrl;
    private JavascriptExecutor getJavascriptExecutor(){
        return driver;
    }

    public BrowserTestingStrategy(SauceOptions sauceOptions) {
        this.sauceOptions = sauceOptions;
    }
    @Override
    public URL getSauceUrl() {
        if (sauceUrl != null) {
            return sauceUrl;
        } else {
            try {
                return new URL(dataCenter.getValue());
            } catch (MalformedURLException e) {
                throw new InvalidArgumentException("Invalid URL");
            }
        }
    }

    @Override
    public void setSauceUrl(URL url) {
        sauceUrl = url;
    }

    @Override
    public void updateResult(String result) {
        getJavascriptExecutor().executeScript("sauce:job-result=" + result);
        // Add output for the Sauce OnDemand Jenkins plugin
        // The first print statement will automatically populate links on Jenkins to Sauce
        // The second print statement will output the job link to logging/console
        if (this.driver != null) {
            String sauceReporter = String.format("SauceOnDemandSessionID=%s job-name=%s", this.driver.getSessionId(), this.sauceOptions.getName());
            String sauceTestLink = String.format("Test Job Link: https://app.saucelabs.com/tests/%s", this.driver.getSessionId());
            System.out.print(sauceReporter + "\n" + sauceTestLink + "\n");
        }
    }

    @Override
    public RemoteWebDriver createRemoteWebDriver() {
        return new RemoteWebDriver(getSauceUrl(), sauceOptions.toCapabilities());
    }
}
