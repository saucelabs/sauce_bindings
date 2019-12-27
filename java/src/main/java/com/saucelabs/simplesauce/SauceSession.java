package com.saucelabs.simplesauce;

import lombok.Getter;
import org.openqa.selenium.JavascriptExecutor;
import lombok.Setter;
import org.openqa.selenium.InvalidArgumentException;
import org.openqa.selenium.MutableCapabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URL;

public class SauceSession {
    @Getter @Setter private DataCenter dataCenter = DataCenter.US_WEST;
    @Getter private final SauceOptions sauceOptions;
    @Getter @Setter private String username;
    @Getter @Setter private String accessKey;
    @Setter private URL sauceUrl;

    @Getter private MutableCapabilities currentSessionCapabilities;
    @Getter private RemoteWebDriver driver;

    public SauceSession() {
        this(new SauceOptions());
    }

    public SauceSession(SauceOptions options) {
        sauceOptions = options;
    }

    public RemoteWebDriver start() {
        sauceUrl = getSauceUrl();
        currentSessionCapabilities = sauceOptions.toCapabilities();
        driver = createRemoteWebDriver();
        return driver;
	}

    public URL getSauceUrl() {
        if (sauceUrl != null) {
            return sauceUrl;
        } else {
            String url = "https://" + getSauceUsername() + ":" + getSauceAccessKey() + "@" + dataCenter.getEndpoint() + "/wd/hub";
            try {
                return new URL(url);
            } catch (MalformedURLException e) {
                throw new InvalidArgumentException("Invalid URL");
            }
        }
    }

    protected RemoteWebDriver createRemoteWebDriver() {
        return new RemoteWebDriver(sauceUrl, currentSessionCapabilities);
    }

    protected JavascriptExecutor getJSExecutor() {
        return driver;
    }

    public void stop() {
        if(driver !=null) {
            driver.quit();
        }
    }

    public void stop(String result) {
        updateResults(result);
        stop();
    }

    public void stop(Boolean passed) {
        String result = passed ? "passed" : "failed";
        stop(result);
    }

    private void updateResults(String result) {
        getJSExecutor().executeScript("sauce:job-result=" + result);
    }

    protected String getEnvironmentVariable(String key) {
        return System.getenv(key);
    }

    private String getSauceUsername() {
        if (username != null) {
            return username;
        } else if (getEnvironmentVariable("SAUCE_USERNAME") != null) {
            return getEnvironmentVariable("SAUCE_USERNAME");
        } else {
            throw new SauceEnvironmentVariablesNotSetException("Sauce Username was not provided");
        }
    }

    private String getSauceAccessKey() {
        if (accessKey != null) {
            return accessKey;
        } else if (getEnvironmentVariable("SAUCE_ACCESS_KEY") != null) {
            return getEnvironmentVariable("SAUCE_ACCESS_KEY");
        } else {
            throw new SauceEnvironmentVariablesNotSetException("Sauce Access Key was not provided");
        }
    }
}
