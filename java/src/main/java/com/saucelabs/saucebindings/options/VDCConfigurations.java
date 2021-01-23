package com.saucelabs.saucebindings.options;

import com.saucelabs.saucebindings.PageLoadStrategy;
import com.saucelabs.saucebindings.Prerun;
import com.saucelabs.saucebindings.UnhandledPromptBehavior;
import org.openqa.selenium.Proxy;

import java.net.URL;
import java.time.Duration;
import java.util.Map;

public abstract class VDCConfigurations<T extends VDCConfigurations<T>> extends BaseConfigurations<T> {

    // W3C Values

    // TODO: Set restrictions on allowed versions in subclass
    public T setBrowserVersion(String browserVersion) {
        sauceOptions.setBrowserVersion(browserVersion);
        return (T) this;
    }

    public T setPageLoadStrategy(PageLoadStrategy pageLoadStrategy) {
        sauceOptions.setPageLoadStrategy(pageLoadStrategy);
        return (T) this;
    }

    public T setAcceptInsecureCerts() {
        sauceOptions.setAcceptInsecureCerts(true);
        return (T) this;
    }

    public T setProxy(Proxy proxy) {
        sauceOptions.setProxy(proxy);
        return (T) this;
    }

    public T setStrictFileInteractability() {
        sauceOptions.setStrictFileInteractability(true);
        return (T) this;
    }

    public T setUnhandledPromptBehavior(UnhandledPromptBehavior behavior) {
        sauceOptions.setUnhandledPromptBehavior(behavior);
        return (T) this;
    }

    // Sauce Values common to all browser sessions:
    public T disableRecordVideo() {
        sauceOptions.sauce().setRecordVideo(false);
        return (T) this;
    }

    public T disableVideoUploadOnPass() {
        sauceOptions.sauce().setVideoUploadOnPass(false);
        return (T) this;
    }

    public T disableRecordScreenshots() {
        sauceOptions.sauce().setRecordScreenshots(false);
        return (T) this;
    }

    public T disableRecordLogs() {
        sauceOptions.sauce().setRecordLogs(false);
        return (T) this;
    }

    public T setMaxDuration(Duration duration) {
        sauceOptions.sauce().setMaxDuration((int) duration.getSeconds());
        return (T) this;
    }
    public T setCommandTimeout(Duration duration) {
        sauceOptions.sauce().setCommandTimeout((int) duration.getSeconds());
        return (T) this;
    }

    public T setIdleTimeout(Duration duration) {
        sauceOptions.sauce().setIdleTimeout((int) duration.getSeconds());
        return (T) this;
    }

    public T setPrerun(Map<Prerun, Object> prerun) {
        sauceOptions.sauce().setPrerun(prerun);
        return (T) this;
    }

    public T setPrerunUrl(URL url) {
        sauceOptions.sauce().setPrerunUrl(url);
        return (T) this;
    }

    public T setPriority(Integer priority) {
        sauceOptions.sauce().setPriority(priority);
        return (T) this;
    }

    public T setScreenResolution(String resolution) {
        sauceOptions.sauce().setScreenResolution(resolution);
        return (T) this;
    }

    public T setTimeZone(String timeZone) {
        sauceOptions.sauce().setTimeZone(timeZone);
        return (T) this;
    }

    public T setImplicitWaitTimeout(Duration timeout) {
        sauceOptions.timeout.setImplicitWait((int) timeout.getSeconds());
        return (T) this;
    }

    public T setPageLoadTimeout(Duration timeout) {
        sauceOptions.timeout.setPageLoad((int) timeout.getSeconds());
        return (T) this;
    }

    public T setScriptTimeout(Duration timeout) {
        sauceOptions.timeout.setScript((int) timeout.getSeconds());
        return (T) this;
    }
}
