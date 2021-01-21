package com.saucelabs.saucebindings.options;

import com.saucelabs.saucebindings.options.capabilities.PageLoadStrategy;
import com.saucelabs.saucebindings.options.capabilities.Prerun;
import com.saucelabs.saucebindings.options.capabilities.ScreenResolution;
import com.saucelabs.saucebindings.options.capabilities.UnhandledPromptBehavior;
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
        sauceOptions.setRecordVideo(false);
        return (T) this;
    }


    public T disableVideoUploadOnPass() {
        sauceOptions.setVideoUploadOnPass(false);
        return (T) this;
    }

    public T disableRecordScreenshots() {
        sauceOptions.setRecordScreenshots(false);
        return (T) this;
    }

    public T disableRecordLogs() {
        sauceOptions.setRecordLogs(false);
        return (T) this;
    }

    public T setMaxDuration(Duration duration) {
        sauceOptions.setMaxDuration((int) duration.getSeconds());
        return (T) this;
    }
    public T setCommandTimeout(Duration duration) {
        sauceOptions.setCommandTimeout((int) duration.getSeconds());
        return (T) this;
    }

    public T setIdleTimeout(Duration duration) {
        sauceOptions.setIdleTimeout((int) duration.getSeconds());
        return (T) this;
    }

    public T setPrerun(Map<Prerun, Object> prerun) {
        sauceOptions.setPrerun(prerun);
        return (T) this;
    }

    public T setPrerunUrl(URL url) {
        sauceOptions.setPrerunUrl(url);
        return (T) this;
    }

    public T setPriority(Integer priority) {
        sauceOptions.setPriority(priority);
        return (T) this;
    }

    public T setScreenResolution(ScreenResolution resolution) {
        sauceOptions.setScreenResolution(resolution.getValue());
        return (T) this;

    }

    public T setTimeZone(String timeZone) {
        sauceOptions.setTimeZone(timeZone);
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
