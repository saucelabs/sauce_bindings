package com.saucelabs.saucebindings;

import lombok.Getter;
import lombok.Setter;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.MutableCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.URL;

public class SauceSession {
    @Getter @Setter private DataCenter dataCenter = DataCenter.US_WEST;
    @Getter private final SauceOptions sauceOptions;

    @Getter private RemoteWebDriver driver;
    @Getter APIStrategy sauceAPIStrategy;
    @Getter
    private String sauceUrl;

    private JavascriptExecutor getJavascriptExecutor(){
        return driver;
    }

    public SauceSession() {
        this(new SauceOptions());
    }

    public SauceSession(SauceOptions options) {
        sauceOptions = options;
        if(sauceOptions.getVisualCapabilities() != null){
            sauceAPIStrategy = new VisualTestingStrategy(sauceOptions);
        }
        else{
            sauceAPIStrategy = new BrowserTestingStrategy(sauceOptions);
        }
    }

    public RemoteWebDriver start() {
        driver = sauceAPIStrategy.createRemoteWebDriver();
        return driver;
	}

    protected RemoteWebDriver createRemoteWebDriver(URL url, MutableCapabilities capabilities) {
        return new RemoteWebDriver(url, capabilities);
    }

    public void stop(Boolean passed) {
        String result = passed ? "passed" : "failed";
        stop(result);
    }

    public void stop(String result) {
        sauceAPIStrategy.updateResult(result);
        stop();
    }

    private void stop() {
        if(driver !=null) {
            driver.quit();
        }
    }

    public void takeSnapshot(String snapshotName) {
        getJavascriptExecutor().executeScript("/*@visual.snapshot*/", snapshotName);
    }

    public URL getSauceUrl() {
        return sauceAPIStrategy.getSauceUrl();
    }
    public void setSauceUrl(URL url){
        sauceAPIStrategy.setSauceUrl(url);
    }
}
