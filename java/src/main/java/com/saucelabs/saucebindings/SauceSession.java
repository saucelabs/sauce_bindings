package com.saucelabs.saucebindings;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;
import lombok.Getter;
import lombok.Setter;
import org.openqa.selenium.InvalidArgumentException;
import org.openqa.selenium.MutableCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URL;

public class SauceSession {
    @Getter @Setter protected DataCenter dataCenter = DataCenter.US_LEGACY;
    @Getter private SauceOptions sauceOptions;
    @Setter private URL sauceUrl;
    @Getter private RemoteWebDriver webDriver;
    @Getter private AppiumDriver appDriver;

    public SauceSession() {
        this(new SauceOptions());
    }

    public SauceSession(SauceOptions options) {
        this.sauceOptions = options;
    }

    public RemoteWebDriver start() {
        String environment = sauceOptions.toCapabilities().getCapability("platformName").toString();
        String browserName = sauceOptions.toCapabilities().getBrowserName();

        if (browserName.equals("")){
            if (environment.toLowerCase().equals("android")){
                return createAndroidDriver(getSauceUrl(), sauceOptions.toCapabilities());
            }
            else if (environment.toLowerCase().equals("ios")) {
                return createIOSDriver(getSauceUrl(), sauceOptions.toCapabilities());
            }
            else {
                throw new InvalidArgumentException("Invalid Sauce Labs capabilities. Please set a browser name or set platformName as \"Android\" or \"IOS\".");
            }
        }
        else {
           return createRemoteWebDriver(getSauceUrl(), sauceOptions.toCapabilities());
        }
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

    protected AppiumDriver createIOSDriver(URL url, MutableCapabilities capabilities) {
       return new IOSDriver<>(url, capabilities);
    }

    protected AppiumDriver createAndroidDriver(URL url, MutableCapabilities capabilities) {
        return new AndroidDriver<>(url, capabilities);
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
        if (webDriver != null)
            getWebDriver().executeScript("sauce:job-result=" + result);
        else {
            System.out.println("use API for mobile case");
        }

    }

    private void stop() {
        if(getWebDriver() !=null) {
            getWebDriver().quit();
        }
        if(getAppDriver() != null){
            getAppDriver().quit();
        }
    }
}
