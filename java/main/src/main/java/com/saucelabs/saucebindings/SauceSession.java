package com.saucelabs.saucebindings;

import com.deque.html.axecore.results.Results;
import com.deque.html.axecore.selenium.AxeBuilder;
import com.saucelabs.saucebindings.options.BaseConfigurations;
import com.saucelabs.saucebindings.options.SauceOptions;
import lombok.Getter;
import lombok.Setter;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.InvalidArgumentException;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URL;

public class SauceSession {
    @Getter protected RemoteWebDriver driver;
    @Getter @Setter private DataCenter dataCenter = DataCenter.US_WEST;
    @Getter private final SauceOptions sauceOptions;
    @Setter private URL sauceUrl;
    @Getter private String result;

    public SauceSession() {
        this(new SauceOptions());
    }

    /**
     * Ideally the end user calls build() on Configurations instance.
     * This constructor is being accommodating in case they do not.
     *
     * @param configs the instance of Configuration used to create the Options
     */
    public SauceSession(BaseConfigurations configs) {
        this(configs.build());
    }

    public SauceSession(SauceOptions options) {
        sauceOptions = options;
    }

    /**
     * Starts the session on Sauce Labs.
     *
     * @return the driver instance for using as normal in your tests.
     */
    public RemoteWebDriver start() {
        this.driver = createRemoteWebDriver(getSauceUrl(), sauceOptions.toCapabilities());
        return driver;
	}

    /**
     * @return the full URL for sending to Sauce Labs based on the desired data center.
     */
    public URL getSauceUrl() {
        try {
            if (sauceUrl != null) {
                return sauceUrl;
            } else {
                return new URL(getDataCenter().getEndpoint());
            }
        } catch (MalformedURLException e) {
            throw new InvalidArgumentException("Invalid URL", e);
        }
    }

    /**
     * Analyzes Accessibility for the current page.
     * User can work with the results from the Results object or see them
     * in the accessibility tab in the Sauce Labs UI.
     *
     * @return an object with the accessibility analysis
     */
    public Results getAccessibilityResults() {
        return getAccessibilityResults(true);
    }

    /**
     * Analyzes Accessibility for the current page.
     * User can work with the results from the Results object or see them
     * in the accessibility tab in the Sauce Labs UI.
     *
     * @param frames whether the page being evaluated needs to dig into frames.
     *               passing false here will slightly improve performance.
     * @return an object with the accessibility analysis
     */
    public Results getAccessibilityResults(boolean frames) {
        AxeBuilder axeBuilder = new AxeBuilder();
        if (!frames) {
            axeBuilder.disableIframeTesting();
        }
        return getAccessibilityResults(axeBuilder);
    }

    /**
     * Analyzes Accessibility for the current page.
     * User can work with the results from the Results object or see them
     * in the accessibility tab in the Sauce Labs UI.
     *
     * @param builder for advanced accessibility information provide your own
     *                instance of an AxeBuilder.
     * @return an object with the accessibility analysis
     */
    public Results getAccessibilityResults(AxeBuilder builder) {
        return builder.analyze(driver);
    }

    /**
     * Ends the session on Sauce Labs and quits the driver.
     * It requires reporting whether the test has passed or failed.
     *
     * @param passed true if the test has passed, otherwise false
     */
    public void stop(Boolean passed) {
        if (this.driver != null) {
            String update = passed ? "passed" : "failed";
            updateResult(update);
            quit();
        }
    }

    /**
     * @deprecated Do not use magic strings, pass in boolean for whether test has passed.
     */
    @Deprecated
    public void stop(String result) {
        stop(result.equals("passed"));
    }

    /**
     * Not intended for subclassing.
     * Package-private for testing.
     *
     * @param url to send session commands to
     * @param capabilities configuration for session
     * @return driver instance
     */
    RemoteWebDriver createRemoteWebDriver(URL url, Capabilities capabilities) {
        return new RemoteWebDriver(url, capabilities);
    }

    private void updateResult(String result) {
        this.result = result;
        getDriver().executeScript("sauce:job-result=" + result);

        // Add output for the Sauce OnDemand Jenkins plugin
        // The first print statement will automatically populate links on Jenkins to Sauce
        // The second print statement will output the job link to logging/console
        String sauceReporter = String.format("SauceOnDemandSessionID=%s job-name=%s",
                this.driver.getSessionId(),
                this.sauceOptions.sauce().getName());
        String sauceTestLink = String.format("Test Job Link:" + getDataCenter().getTestLink() + "%s",
                this.driver.getSessionId());
        System.out.print(sauceReporter + "\n" + sauceTestLink + "\n");
    }

    private void quit() {
        driver.quit();
        driver = null;
    }
}
