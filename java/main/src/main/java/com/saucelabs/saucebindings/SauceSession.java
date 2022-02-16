package com.saucelabs.saucebindings;

import com.deque.html.axecore.results.Results;
import com.deque.html.axecore.selenium.AxeBuilder;
import com.saucelabs.saucebindings.options.BaseConfigurations;
import com.saucelabs.saucebindings.options.SauceOptions;
import lombok.Getter;
import lombok.Setter;
import org.openqa.selenium.InvalidArgumentException;
import org.openqa.selenium.MutableCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URL;

public class SauceSession {
    @Getter @Setter private DataCenter dataCenter = DataCenter.US_WEST;
    @Getter private final SauceOptions sauceOptions;
    @Setter private URL sauceUrl;
    @Getter private String result;

    @Getter private RemoteWebDriver driver;

    public SauceSession() {
        this(new SauceOptions());
    }

    /**
     * Ideally the end user calls build() on Configurations instance
     * this constructor is being accommodating in case they do not
     *
     * @param configs the instance of Configuration used to create the Options
     */
    public SauceSession(BaseConfigurations configs) {
        this(configs.build());
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
            try {
                return new URL(dataCenter.getValue());
            } catch (MalformedURLException e) {
                throw new InvalidArgumentException("Invalid URL");
            }
        }
    }

    protected RemoteWebDriver createRemoteWebDriver(URL url, MutableCapabilities capabilities) {
        return new RemoteWebDriver(url, capabilities);
    }

    public Results getAccessibilityResults() {
        return getAccessibilityResults(true);
    }

    public Results getAccessibilityResults(boolean frames) {
        AxeBuilder axeBuilder = new AxeBuilder();
        if (!frames) {
            axeBuilder.disableIframeTesting();
        }
        return getAccessibilityResults(axeBuilder);
    }

    public Results getAccessibilityResults(AxeBuilder builder) {
        return builder.analyze(driver);
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
        this.result = result;
        getDriver().executeScript("sauce:job-result=" + result);
        // Add output for the Sauce OnDemand Jenkins plugin
        // The first print statement will automatically populate links on Jenkins to Sauce
        // The second print statement will output the job link to logging/console
        if (this.driver != null) {
            String sauceReporter = String.format("SauceOnDemandSessionID=%s job-name=%s", this.driver.getSessionId(), this.sauceOptions.sauce().getName());
            String sauceTestLink = String.format("Test Job Link: https://app.saucelabs.com/tests/%s", this.driver.getSessionId());
            System.out.print(sauceReporter + "\n" + sauceTestLink + "\n");
        }
    }

    private void stop() {
        if(driver !=null) {
            driver.quit();
            driver = null;
        }
    }
}
