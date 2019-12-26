package com.saucelabs.simplesauce;

import com.google.common.annotations.VisibleForTesting;
import lombok.Getter;
import lombok.Setter;
import org.openqa.selenium.InvalidArgumentException;
import org.openqa.selenium.MutableCapabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.ie.InternetExplorerOptions;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.safari.SafariOptions;

import java.net.MalformedURLException;
import java.net.URL;

public class SauceSession {
    @Getter private final String sauceDataCenter = DataCenter.US_WEST.getEndpoint();
    @Getter private final EnvironmentManager environmentManager;
    @Getter private final SauceOptions sauceOptions;
    @Getter private final SauceTimeout timeouts = new SauceTimeout();
    @Getter @Setter private URL sauceUrl;

    private final String sauceOptionsTag = "sauce:options";

    //TODO 2 same variables being used differently
    private MutableCapabilities mutableCapabilities;
    @Getter private MutableCapabilities currentSessionCapabilities;
    @Getter private final SauceRemoteDriver sauceDriver;
    @Getter private WebDriver webDriver;

    public MutableCapabilities getSauceOptionsCapability(){
        return ((MutableCapabilities) currentSessionCapabilities.getCapability(sauceOptionsTag));
    }

    public SauceSession() {
        currentSessionCapabilities = new MutableCapabilities();
        sauceDriver = new SauceDriverImpl();
        environmentManager = new EnvironmentManagerImpl();
        sauceOptions = new SauceOptions();
    }

    public SauceSession(SauceRemoteDriver remoteManager, EnvironmentManager environmentManager) {
        sauceDriver = remoteManager;
        currentSessionCapabilities = new MutableCapabilities();
        this.environmentManager = environmentManager;
        sauceOptions = new SauceOptions();
    }

    public SauceSession(SauceOptions options) {
        sauceOptions = options;
        currentSessionCapabilities = new MutableCapabilities();
        environmentManager = new EnvironmentManagerImpl();
        sauceDriver = new SauceDriverImpl();
    }

    public SauceSession(SauceOptions options, SauceRemoteDriver remoteManager, EnvironmentManager environmentManager) {
        sauceOptions = options;
        sauceDriver = remoteManager;
        currentSessionCapabilities = new MutableCapabilities();
        this.environmentManager = environmentManager;
    }

    public WebDriver start() {
        mutableCapabilities = appendSauceCapabilities();
        setBrowserSpecificCapabilities(sauceOptions.getBrowserName());
        currentSessionCapabilities = setRemoteDriverCapabilities(mutableCapabilities);
        tryToCreateRemoteWebDriver();
        return webDriver;
	}

    private MutableCapabilities appendSauceCapabilities() {
        mutableCapabilities = new MutableCapabilities();
        if (timeouts.getCommandTimeout() != 0) {
            mutableCapabilities.setCapability("commandTimeout", timeouts.getCommandTimeout());
        }
        if (timeouts.getIdleTimeout() != 0) {
            mutableCapabilities.setCapability("idleTimeout", timeouts.getIdleTimeout());
        }
        if (timeouts.getMaxTestDurationTimeout() != 0) {
            mutableCapabilities.setCapability("maxDuration", timeouts.getMaxTestDurationTimeout());
        }
        return mutableCapabilities;
    }

    private void setBrowserSpecificCapabilities(String browserName) {
        if (browserName.equalsIgnoreCase("Chrome")) {
            ChromeOptions chromeOptions = new ChromeOptions();
            currentSessionCapabilities.setCapability(ChromeOptions.CAPABILITY, chromeOptions);
        }
        else if (browserName.equalsIgnoreCase("Firefox")) {
            FirefoxOptions firefoxOptions = new FirefoxOptions();
            currentSessionCapabilities.setCapability(FirefoxOptions.FIREFOX_OPTIONS, firefoxOptions);
        }
        else if(browserName.equalsIgnoreCase("Safari")) {
            SafariOptions safariOptions = new SafariOptions();
            currentSessionCapabilities.setCapability(SafariOptions.CAPABILITY, safariOptions);
        }
        else if(browserName.equalsIgnoreCase("Edge")) {
            EdgeOptions edgeOptions = new EdgeOptions();
            currentSessionCapabilities.setCapability("Edge", edgeOptions);
        }
        else if(browserName.equalsIgnoreCase("IE")) {
            InternetExplorerOptions ieOptions = new InternetExplorerOptions();
            currentSessionCapabilities.setCapability("se:ieOptions", ieOptions);
        }
        else {
            throw new IllegalArgumentException("The browserName=>" + browserName + " that you passed in is not a valid option.");
        }
    }

    private MutableCapabilities setRemoteDriverCapabilities(MutableCapabilities sauceOptions) {
        currentSessionCapabilities.setCapability(sauceOptionsTag, sauceOptions);
        currentSessionCapabilities.setCapability(CapabilityType.BROWSER_NAME, this.sauceOptions.getBrowserName());
        currentSessionCapabilities.setCapability(CapabilityType.PLATFORM_NAME, this.sauceOptions.getOperatingSystem());
        currentSessionCapabilities.setCapability(CapabilityType.BROWSER_VERSION, this.sauceOptions.getBrowserVersion());
        return currentSessionCapabilities;
    }

    public URL getSauceUrl() throws MalformedURLException {
        if (sauceUrl != null) {
            return sauceUrl;
        } else {
            String url = "https://" + getUserName() + ":" + getAccessKey() + "@" + sauceDataCenter + "/wd/hub";
            return new URL(url);
        }
    }

    private void tryToCreateRemoteWebDriver() {
        try {
            webDriver = this.sauceDriver.createRemoteWebDriver(getSauceUrl(), currentSessionCapabilities);
        }
        catch (MalformedURLException e) {
            throw new InvalidArgumentException("Invalid URL");
        }
    }

    public void stop() {
        if(webDriver !=null)
            webDriver.quit();
    }

    @VisibleForTesting
    String getUserName() throws SauceEnvironmentVariablesNotSetException{
        String userName = environmentManager.getEnvironmentVariable("SAUCE_USERNAME");
        return checkIfEmpty(userName);
    }

    @VisibleForTesting
    String getAccessKey() throws SauceEnvironmentVariablesNotSetException {
        String accessKey = environmentManager.getEnvironmentVariable("SAUCE_ACCESS_KEY");
        return checkIfEmpty(accessKey);
    }

    private String checkIfEmpty(String variableToCheck) {
        if (variableToCheck == null) {
            throw new SauceEnvironmentVariablesNotSetException();
        }
        return variableToCheck;
    }
}
