package com.saucelabs.simplesauce;

import com.saucelabs.simplesauce.interfaces.EnvironmentManager;
import com.saucelabs.simplesauce.interfaces.RemoteDriverInterface;
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

public class SauceSession {
    @Getter @Setter public final String sauceDataCenter = DataCenter.USWest;
    private final EnvironmentManager environmentManager;
    private final SauceOptions sauceOptions;
    public final SauceTimeout timeouts = new SauceTimeout();

    private final String sauceOptionsTag = "sauce:options";
    //TODO 2 same variables being used differently
    //definitely should be fixed
    private MutableCapabilities mutableCapabilities;
    public MutableCapabilities currentSessionCapabilities;
    private final RemoteDriverInterface remoteDriverImplementation;
    private WebDriver webDriver;

    public RemoteDriverInterface getDriverManager() {
        return remoteDriverImplementation;
    }

    public MutableCapabilities getSauceOptionsCapability(){
        return ((MutableCapabilities) currentSessionCapabilities.getCapability(sauceOptionsTag));
    }
    public SauceSession() {
        currentSessionCapabilities = new MutableCapabilities();
        remoteDriverImplementation = new ConcreteRemoteDriver();
        environmentManager = new ConcreteSystemManager();
        sauceOptions = new SauceOptions();
    }

    public SauceSession(RemoteDriverInterface remoteManager, EnvironmentManager environmentManager) {
        remoteDriverImplementation = remoteManager;
        currentSessionCapabilities = new MutableCapabilities();
        this.environmentManager = environmentManager;
        sauceOptions = new SauceOptions();
    }

    public SauceSession(SauceOptions options) {
        sauceOptions = options;
        currentSessionCapabilities = new MutableCapabilities();
        environmentManager = new ConcreteSystemManager();
        remoteDriverImplementation = new ConcreteRemoteDriver();
    }

    public SauceSession(SauceOptions options, RemoteDriverInterface remoteManager, EnvironmentManager environmentManager) {
        sauceOptions = options;
        remoteDriverImplementation = remoteManager;
        currentSessionCapabilities = new MutableCapabilities();
        this.environmentManager = environmentManager;
    }

    public WebDriver start() {
        //TODO this might be the same as sauceCapabilities
        mutableCapabilities = appendSauceCapabilities();
        setBrowserSpecificCapabilities(sauceOptions.browser);
        currentSessionCapabilities = setRemoteDriverCapabilities(mutableCapabilities);
        String sauceLabsUrl = sauceDataCenter;
        //TODO move to a separate method
        try
        {
            webDriver = remoteDriverImplementation.createRemoteWebDriver(sauceLabsUrl, currentSessionCapabilities);
        }
        catch (MalformedURLException e)
        {
            throw new InvalidArgumentException("Invalid URL");
        }
        return webDriver;
	}
    private MutableCapabilities appendSauceCapabilities() {
        mutableCapabilities = new MutableCapabilities();
        mutableCapabilities.setCapability("username", getUserName());
        mutableCapabilities.setCapability("accessKey", getAccessKey());
        if(timeouts.getCommandTimeout() != 0)
            mutableCapabilities.setCapability("commandTimeout", timeouts.getCommandTimeout());
        if(timeouts.getIdleTimeout() != 0)
            mutableCapabilities.setCapability("idleTimeout", timeouts.getIdleTimeout());
        if(timeouts.getMaxTestDurationTimeout() != 0)
            mutableCapabilities.setCapability("maxDuration", timeouts.getMaxTestDurationTimeout());
        return mutableCapabilities;
    }
    private void setBrowserSpecificCapabilities(String browserName)
    {
        if (browserName.equalsIgnoreCase("Chrome"))
        {
            ChromeOptions chromeOptions = new ChromeOptions();
            currentSessionCapabilities.setCapability(ChromeOptions.CAPABILITY, chromeOptions);
        }
        else if (browserName.equalsIgnoreCase("Firefox"))
        {
            FirefoxOptions firefoxOptions = new FirefoxOptions();
            currentSessionCapabilities.setCapability(FirefoxOptions.FIREFOX_OPTIONS, firefoxOptions);
        }
        else if(browserName.equalsIgnoreCase("Safari"))
        {
            SafariOptions safariOptions = new SafariOptions();
            currentSessionCapabilities.setCapability(SafariOptions.CAPABILITY, safariOptions);
        }
        else if(browserName.equalsIgnoreCase("Edge"))
        {
            EdgeOptions edgeOptions = new EdgeOptions();
            currentSessionCapabilities.setCapability("Edge", edgeOptions);
        }
        else if(browserName.equalsIgnoreCase("IE"))
        {
            InternetExplorerOptions ieOptions = new InternetExplorerOptions();
            currentSessionCapabilities.setCapability("se:ieOptions", ieOptions);
        }
        else {
            throw new IllegalArgumentException("The browser=>" + browserName + " that you passed in is not a valid option.");
        }
    }
    private MutableCapabilities setRemoteDriverCapabilities(MutableCapabilities sauceOptions) {
        currentSessionCapabilities.setCapability(sauceOptionsTag, sauceOptions);
        currentSessionCapabilities.setCapability(CapabilityType.BROWSER_NAME, this.sauceOptions.browser);
        currentSessionCapabilities.setCapability(CapabilityType.PLATFORM_NAME, this.sauceOptions.operatingSystem);
        currentSessionCapabilities.setCapability(CapabilityType.BROWSER_VERSION, this.sauceOptions.browserVersion);
        return currentSessionCapabilities;
    }
    public void stop()
    {
        if(webDriver != null)
            webDriver.quit();
    }

    public String getUserName() throws SauceEnvironmentVariablesNotSetException{
        String userName = environmentManager.getEnvironmentVariable("SAUCE_USERNAME");
        return checkIfEmpty(userName);
    }

    private String checkIfEmpty(String variableToCheck) {
        if (variableToCheck == null)
            throw new SauceEnvironmentVariablesNotSetException();
        return variableToCheck;
    }

    public String getAccessKey() throws SauceEnvironmentVariablesNotSetException {
        String accessKey = environmentManager.getEnvironmentVariable("SAUCE_ACCESS_KEY");
        return checkIfEmpty(accessKey);
    }
}
