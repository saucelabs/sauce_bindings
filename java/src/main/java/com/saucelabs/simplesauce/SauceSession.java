package com.saucelabs.simplesauce;

import lombok.Getter;
import lombok.Setter;
import org.openqa.selenium.InvalidArgumentException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URL;

public class SauceSession {
    @Getter private final DataCenter dataCenter = DataCenter.US_WEST;
    @Getter private final SauceOptions sauceOptions;
    @Getter @Setter private String sauceUsername = System.getenv("SAUCE_USERNAME");
    @Getter @Setter private String sauceAccessKey = System.getenv("SAUCE_ACCESS_KEY");
    @Setter private URL sauceUrl;

    @Getter private WebDriver driver;

    public SauceSession() {
        this(new SauceOptions());
    }

    public SauceSession(SauceOptions options) {
        sauceOptions = options;
    }

    public WebDriver start() {
        if (sauceUsername == null) {
            throw new SauceEnvironmentVariablesNotSetException("Sauce Username was not provided");
        } else if (sauceAccessKey == null) {
            throw new SauceEnvironmentVariablesNotSetException("Sauce Access Key was not provided");
        } else {
            driver = createRemoteWebDriver();
            return driver;
        }
	}

    public void stop() {
        if(driver !=null)
            driver.quit();
    }

    RemoteWebDriver createRemoteWebDriver() {
        try {
            return new RemoteWebDriver(getSauceUrl(), sauceOptions.toCapabilities());
        }
        catch (MalformedURLException e) {
            throw new InvalidArgumentException("Invalid URL" + e.getMessage());
        }
    }

    public URL getSauceUrl() throws MalformedURLException {
        if (sauceUrl != null) {
            return sauceUrl;
        } else {
            String url = "https://" + sauceUsername + ":" + sauceAccessKey + "@" +
                    dataCenter.getEndpoint() + ":443/wd/hub";
            return new URL(url);
        }
    }
}
