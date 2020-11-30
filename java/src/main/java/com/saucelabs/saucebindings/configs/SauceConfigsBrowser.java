package com.saucelabs.saucebindings.configs;

import com.saucelabs.saucebindings.PageLoadStrategy;
import com.saucelabs.saucebindings.Prerun;
import com.saucelabs.saucebindings.UnhandledPromptBehavior;
import org.openqa.selenium.Proxy;

import java.net.URL;
import java.time.Duration;
import java.util.Map;

public class SauceConfigsBrowser<T extends SauceConfigsBrowser<T>> extends SauceConfigs<T> {

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

    public T setAcceptInsecureCerts(Boolean bool) {
        sauceOptions.setAcceptInsecureCerts(bool);
        return (T) this;
    }

    public T setProxy(Proxy proxy) {
        sauceOptions.setProxy(proxy);
        return (T) this;
    }

    public T setSetWindowRect(Boolean bool) {
        sauceOptions.setSetWindowRect(bool);
        return (T) this;
    }

    public T setStrictFileInteractability(Boolean bool) {
        sauceOptions.setStrictFileInteractability(bool);
        return (T) this;
    }

    public T setUnhandledPromptBehavior(UnhandledPromptBehavior behavior) {
        sauceOptions.setUnhandledPromptBehavior(behavior);
        return (T) this;
    }

    // Sauce Values common to all browser sessions:
    public T setRecordVideo(Boolean bool) {
        sauceOptions.setRecordVideo(bool);
        return (T) this;
    }


    public T setVideoUploadOnPass(Boolean bool) {
        sauceOptions.setVideoUploadOnPass(bool);
        return (T) this;
    }

    public T setRecordScreenshots(Boolean bool) {
        sauceOptions.setRecordScreenshots(bool);
        return (T) this;
    }

    public T setRecordLogs(Boolean bool) {
        sauceOptions.setRecordLogs(bool);
        return (T) this;
    }

    public T setMaxDuration(Integer bool) {
        sauceOptions.setMaxDuration(bool);
        return (T) this;
    }
    public T setCommandTimeout(Integer bool) {
        sauceOptions.setCommandTimeout(bool);
        return (T) this;
    }

    public T setIdleTimeout(Integer bool) {
        sauceOptions.setIdleTimeout(bool);
        return (T) this;
    }

    public T setPrerun(Map<Prerun, Object> bool) {
        sauceOptions.setPrerun(bool);
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

    public T setScreenResolution(String resolution) {
        sauceOptions.setScreenResolution(resolution);
        return (T) this;
    }

    public T setTimeZone(String timeZone) {
        sauceOptions.setTimeZone(timeZone);
        return (T) this;
    }

    public T setImplicitWaitTimeout(Duration implicitWaitTimeout) {
        sauceOptions.timeout.setImplicitWait((int) implicitWaitTimeout.getSeconds());
        return (T) this;
    }

    public T setPageLoadTimeout(Duration pageLoadTimeout) {
        sauceOptions.timeout.setPageLoad((int) pageLoadTimeout.getSeconds());
        return (T) this;
    }

    public T setScriptTimeout(Duration scriptTimeout) {
        sauceOptions.timeout.setScript((int) scriptTimeout.getSeconds());
        return (T) this;
    }
}
