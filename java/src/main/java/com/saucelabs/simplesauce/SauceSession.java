package com.saucelabs.simplesauce;

import lombok.Getter;
import org.openqa.selenium.JavascriptExecutor;
import lombok.Setter;
import org.openqa.selenium.InvalidArgumentException;
import org.openqa.selenium.MutableCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URL;

public class SauceSession {

    /**
     * Defaults for username and access key are environment variables
     */
    private String username = System.getenv("SAUCE_USERNAME");
    private String accessKey = System.getenv("SAUCE_ACCESS_KEY");


    @Getter @Setter private DataCenter dataCenter = DataCenter.US_WEST;
    @Getter private final SauceOptions sauceOptions;
    @Setter private URL sauceUrl;

    @Getter private RemoteWebDriver driver;

    public SauceSession() {
        this(new SauceOptions());
    }

    public SauceSession(SauceOptions options) {
        sauceOptions = options;
    }

    public RemoteWebDriver start() {
        driver = createRemoteWebDriver(getSauceUrl(), sauceOptions.toCapabilities());
        return driver;
	}

    public URL getSauceUrl() {
        if (sauceUrl != null) {
            return sauceUrl;
        } else {
            String url = "https://" + getUsername() + ":" + getAccessKey() + "@" + dataCenter + "/wd/hub";
            try {
                return new URL(url);
            } catch (MalformedURLException e) {
                throw new InvalidArgumentException("Invalid URL");
            }
        }
    }

    protected RemoteWebDriver createRemoteWebDriver(URL url, MutableCapabilities capabilities) {
        return new RemoteWebDriver(url, capabilities);
    }

    protected JavascriptExecutor getJSExecutor() {
        return driver;
    }

    public void stop(Boolean passed) {
        String result = passed ? "passed" : "failed";
        stop(result);
    }

    public void stop(String result) {
        updateResult(result);
        stop();
    }

    private void updateResult(String result) {
        getJSExecutor().executeScript("sauce:job-result=" + result);
    }

    private void stop() {
        if(driver !=null) {
            driver.quit();
        }
    }

    private String getUsername() {
        if (username != null) {
            return username;
        } else if (System.getProperty("SAUCE_USERNAME") != null) {
            return System.getProperty("SAUCE_USERNAME");
        } else {
            throw new SauceEnvironmentVariablesNotSetException("Sauce Username was not provided");
        }
    }

    private String getAccessKey() {
        if (accessKey != null) {
            return accessKey;
        } else if (System.getProperty("SAUCE_ACCESS_KEY") != null) {
            return System.getProperty("SAUCE_ACCESS_KEY");
        } else {
            throw new SauceEnvironmentVariablesNotSetException("Sauce Access Key was not provided");
        }
    }
}
