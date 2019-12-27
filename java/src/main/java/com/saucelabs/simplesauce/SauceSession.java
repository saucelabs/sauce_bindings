package com.saucelabs.simplesauce;

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
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.safari.SafariOptions;

import java.net.MalformedURLException;
import java.net.URL;

public class SauceSession {
    @Getter @Setter private DataCenter dataCenter = DataCenter.US_WEST;
    @Getter private final SauceOptions sauceOptions;
    @Getter private final SauceTimeout timeouts = new SauceTimeout();
    @Getter @Setter private String username;
    @Getter @Setter private String accessKey;
    @Setter private URL sauceUrl;
    private final String sauceOptionsTag = "sauce:options";

    //TODO 2 same variables being used differently
    private MutableCapabilities mutableCapabilities;
    @Getter private MutableCapabilities currentSessionCapabilities;
    @Getter private WebDriver driver;

    public MutableCapabilities getSauceOptionsCapability(){
        return ((MutableCapabilities) currentSessionCapabilities.getCapability(sauceOptionsTag));
    }

    public SauceSession() {
        this(new SauceOptions());
    }

    public SauceSession(SauceOptions options) {
        sauceOptions = options;
        currentSessionCapabilities = new MutableCapabilities();
    }

    public WebDriver start() {
        mutableCapabilities = appendSauceCapabilities();
        setBrowserSpecificCapabilities(sauceOptions.getBrowserName());
        currentSessionCapabilities = setRemoteDriverCapabilities(mutableCapabilities);
        sauceUrl = getSauceUrl();
        driver = createRemoteWebDriver();
        return driver;
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

    public URL getSauceUrl() {
        if (sauceUrl != null) {
            return sauceUrl;
        } else {
            String url = "https://" + getSauceUsername() + ":" + getSauceAccessKey() + "@" + dataCenter.getEndpoint() + "/wd/hub";
            try {
                return new URL(url);
            } catch (MalformedURLException e) {
                throw new InvalidArgumentException("Invalid URL");
            }
        }
    }

    protected RemoteWebDriver createRemoteWebDriver() {
        return new RemoteWebDriver(getSauceUrl(), currentSessionCapabilities);
    }

    public void stop() {
        if(driver !=null)
            driver.quit();
    }

    protected String getEnvironmentVariable(String key) {
        return System.getenv(key);
    }

    private String getSauceUsername() {
        if (username != null) {
            return username;
        } else if (getEnvironmentVariable("SAUCE_USERNAME") != null) {
            return getEnvironmentVariable("SAUCE_USERNAME");
        } else {
            throw new SauceEnvironmentVariablesNotSetException("Sauce Username was not provided");
        }
    }

    private String getSauceAccessKey() {
        if (accessKey != null) {
            return accessKey;
        } else if (getEnvironmentVariable("SAUCE_ACCESS_KEY") != null) {
            return getEnvironmentVariable("SAUCE_ACCESS_KEY");
        } else {
            throw new SauceEnvironmentVariablesNotSetException("Sauce Access Key was not provided");
        }
    }
}
