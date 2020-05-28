package com.saucelabs.saucebindings;

import lombok.Getter;
import lombok.Setter;
import org.openqa.selenium.InvalidArgumentException;
import org.openqa.selenium.MutableCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URL;

public class SauceSession<T extends RemoteWebDriver>  {
    @Getter @Setter private DataCenter dataCenter = DataCenter.US_WEST;
    @Getter @Setter protected SauceOptions sauceOptions;
    @Setter private URL sauceUrl;

    @Getter private RemoteWebDriver driver;

    public SauceSession() {
        this(new SauceOptions());
    }

    public SauceSession(SauceOptions options) {
        sauceOptions = options;
    }

    public T start() {
        this.driver = createDriver(getSauceUrl(), sauceOptions.toCapabilities());
        return (T) driver;
	}

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

    protected T createDriver(URL url, MutableCapabilities capabilities) {
        return (T) new RemoteWebDriver(url, capabilities);
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
        getDriver().executeScript("sauce:job-result=" + result);
        // Add output for the Sauce OnDemand Jenkins plugin
        // The first print statement will automatically populate links on Jenkins to Sauce
        // The second print statement will output the job link to logging/console
        if (getDriver() != null) {
            String sauceReporter = String.format("SauceOnDemandSessionID=%s job-name=%s", getDriver().getSessionId(), this.getSauceOptions().getName());
            String sauceTestLink = String.format("Test Job Link: https://app.saucelabs.com/tests/%s", getDriver().getSessionId());
            System.out.print(sauceReporter + "\n" + sauceTestLink + "\n");
        }
    }

    private void stop() {
        if(getDriver() !=null) {
            getDriver().quit();
        }
    }
}
