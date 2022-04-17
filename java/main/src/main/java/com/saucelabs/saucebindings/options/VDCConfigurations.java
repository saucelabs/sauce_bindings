package com.saucelabs.saucebindings.options;

import com.saucelabs.saucebindings.PageLoadStrategy;
import com.saucelabs.saucebindings.Prerun;
import com.saucelabs.saucebindings.UnhandledPromptBehavior;
import org.openqa.selenium.Proxy;

import java.net.URL;
import java.time.Duration;
import java.util.Map;
import java.util.Optional;

public abstract class VDCConfigurations<T extends VDCConfigurations<T>> extends BaseConfigurations<T> {

    // W3C Values

    /**
     * TODO: Set restrictions on allowed versions in subclass
     *
     * @param version The version of the browser you want to use in your test
     * @return instance of configuration
     */
    public T setBrowserVersion(String version) {
        sauceOptions.setBrowserVersion(version);
        return (T) this;
    }

    /**
     * Note that this only applies on navigation commands, not as a result of clicking links
     *
     * @param pageLoadStrategy the job's page load strategy
     * @return instance of configuration
     */
    public T setPageLoadStrategy(PageLoadStrategy pageLoadStrategy) {
        sauceOptions.setPageLoadStrategy(pageLoadStrategy);
        return (T) this;
    }

    /**
     * Indicates whether untrusted and self-signed TLS certificates are implicitly trusted on navigation
     *
     * @return instance of configuration
     */
    public T setAcceptInsecureCerts() {
        sauceOptions.setAcceptInsecureCerts(true);
        return (T) this;
    }

    /**
     * @param proxy an instance of Selenium's Proxy class specifying the proxy to use
     * @return instance of configuration
     */
    public T setProxy(Proxy proxy) {
        sauceOptions.setProxy(proxy);
        return (T) this;
    }

    /**
     * Toggle on requirement that input elements with type=file need to be displayed and enabled in order to use
     * Keep the default if the input fields on your site are never displayed
     * Set with this method if you have an automated synchronization strategy that the default incorrectly bypasses
     *
     * @return instance of configuration
     */
    public T setStrictFileInteractability() {
        sauceOptions.setStrictFileInteractability(true);
        return (T) this;
    }

    /**
     * @param behavior enum for desired response when sending non-alert commands with an alert displayed
     * @return instance of configuration
     */
    public T setUnhandledPromptBehavior(UnhandledPromptBehavior behavior) {
        sauceOptions.setUnhandledPromptBehavior(behavior);
        return (T) this;
    }

    /**
     * @param timeout the amount of time to wait for the element location command to complete
     * @return instance of configuration
     */
    public T setImplicitWaitTimeout(Duration timeout) {
        sauceOptions.setImplicitWaitTimeout(timeout);
        return (T) this;
    }

    /**
     * @param timeout the amount of time to wait for an explicit navigation attempt
     * @return instance of configuration
     */
    public T setPageLoadTimeout(Duration timeout) {
        sauceOptions.setPageLoadTimeout(timeout);
        return (T) this;
    }

    /**
     * A null value implies that scripts should never be interrupted, but instead run indefinitely
     *
     * @param timeout the amount of time to wait for an JavaScript execution command to complete
     * @return instance of configuration
     */
    public T setScriptTimeout(Duration timeout) {
        sauceOptions.setScriptTimeout(timeout);
        return (T) this;
    }

    // Sauce Values common to all browser sessions:

    /**
     * Toggles off Sauce Labs recording video of the session.
     * Not recommended since videos are very useful for debugging failures and visual reproduction of actions taken.
     * If performance is the only concern, there is a (small) time savings during the test by disabling this
     *
     * @return instance of configuration
     */
    public T disableRecordVideo() {
        sauceOptions.sauce().setRecordVideo(false);
        return (T) this;
    }

    /**
     * Toggles off Sauce Labs video post-processing and uploading when test is reported passing
     * If performance is the only concern, there is a (small) time savings after the test by disabling this
     *
     * @return instance of configuration
     */
    public T disableVideoUploadOnPass() {
        sauceOptions.sauce().setVideoUploadOnPass(false);
        return (T) this;
    }

    /**
     * Toggles off the step-by-step screenshots Sauce Labs takes during a test run
     * If performance is the only concern, there is a (small) time savings during the test by disabling this
     *
     * @return instance of configuration
     */
    public T disableRecordScreenshots() {
        sauceOptions.sauce().setRecordScreenshots(false);
        return (T) this;
    }

    /**
     * Toggles off Sauce Labs recording logs of user commands
     * If performance is the only concern, there is a (small) time savings during the test by disabling this
     * Note that the Selenium Logs will still be recorded and available
     *
     * @return instance of configuration
     */
    public T disableRecordLogs() {
        sauceOptions.sauce().setRecordLogs(false);
        return (T) this;
    }

    /**
     * This is a safety measure to prevent tests from running indefinitely
     * The default is 30 minutes; the maximum is 180 minutes
     *
     * @param timeout how long a test is allowed to run
     * @return instance of configuration
     */
    public T setMaxDuration(Duration timeout) {
        sauceOptions.sauce().setMaxDuration((int) timeout.getSeconds());
        return (T) this;
    }

    /**
     * This is a safety measure to prevent Selenium crashes from making your tests run indefinitely
     * The default is 300 seconds; the maximum is 600 seconds
     *
     * @param timeout how long to wait for a response from the driver after sending a command
     * @return instance of configuration
     */
    public T setCommandTimeout(Duration timeout) {
        sauceOptions.sauce().setCommandTimeout((int) timeout.getSeconds());
        return (T) this;
    }

    /**
     * As a safety measure to prevent tests from running too long after something has gone wrong
     * The default is 90 seconds; the maximum is 1000 seconds
     *
     * @param timeout how long to wait between commands from the test code
     * @return instance of configuration
     */
    public T setIdleTimeout(Duration timeout) {
        sauceOptions.sauce().setIdleTimeout((int) timeout.getSeconds());
        return (T) this;
    }

    /**
     * For faster performance, you may want to upload the executable to Sauce Storage, a private temporary storage space
     *
     * @param prerun a Map of the configurations for downloading and executing a file before the test begins
     * @return instance of configuration
     * @see #setPrerunUrl(URL) you can use this instead if you are not configuring anything
     */
    public T setPrerun(Map<Prerun, Object> prerun) {
        sauceOptions.sauce().setPrerun(prerun);
        return (T) this;
    }

    /**
     * For faster performance, you may want to upload the executable to Sauce Storage, a private temporary storage space
     *
     * @param url the location of the executable file to be downloaded and run before the test begins
     * @return instance of configuration
     * @see #setPrerun(Map) if you need additional configurations
     */
    public T setPrerunUrl(URL url) {
        sauceOptions.sauce().setPrerunUrl(url);
        return (T) this;
    }

    /**
     * If you have multiple new jobs waiting to start, jobs with a lower priority number take precedence over jobs with a higher number
     * Sauce will attempt to find resources for Jobs with priority 0 before priority 1, etc.
     * This can be useful if you have a test that runs as part of a build, and you don't want it to wait for a daily run to complete
     *
     * @param priority how important a given test is
     * @return instance of configuration
     */
    public T setPriority(Integer priority) {
        sauceOptions.sauce().setPriority(priority);
        return (T) this;
    }

    /**
     * The default screen resolution for Sauce tests when not specified is 1024x768
     *
     * @param resolution The screen resolution to be used
     * @return instance of configuration
     */
    public T setScreenResolution(String resolution) {
        sauceOptions.sauce().setScreenResolution(resolution);
        return (T) this;
    }

    /**
     * @param timeZone the name of a city in the desired time zone to be set on the Operating System before the test starts
     * @return instance of configuration
     */
    public T setTimeZone(String timeZone) {
        sauceOptions.sauce().setTimeZone(timeZone);
        return (T) this;
    }

    protected void validateBrowserName(String expected, String actual) {
        String message = "Selenium class contains incorrect browser name; \"" + actual + "\" can not be set for: " + this.getClass();
        if (!expected.equals(actual)) {
            throw new InvalidSauceOptionsArgumentException(message);
        }
    }

    protected void validatePrefix(String prefix, Map<String, Object> map) {
        Optional<String> first = map.keySet().stream()
                .filter(k -> k.contains(":"))
                .map(k -> k.substring(0, k.indexOf(":")))
                .filter(substring -> !prefix.equals(substring) && !"sauce".equals(substring))
                .findFirst();
        if (first.isPresent()) {
            String message = "\"" + first.get() + ":\" is not a valid prefix for a " + map.get("browserName") + " option.";
            throw new InvalidSauceOptionsArgumentException(message);
        }
    }
}
