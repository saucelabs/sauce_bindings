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
    public SauceTimeout timeouts = new SauceTimeout();

    private final String sauceOptionsTag = "sauce:options";
    //TODO 2 same variables being used differently
    //definitely should be fixed
    private MutableCapabilities mutableCapabilities;
    public MutableCapabilities sauceSessionCapabilities;
    private final RemoteDriverInterface remoteDriverImplementation;
    //TODO turn into a @Getter and then I can get rid of getDriver() below
    public WebDriver webDriver;

    @Getter @Setter public String sauceLabsUrl;
    public RemoteDriverInterface getDriverManager() {
        return remoteDriverImplementation;
    }
    public MutableCapabilities getSauceOptionsCapability(){
        return ((MutableCapabilities) sauceSessionCapabilities.getCapability(sauceOptionsTag));
    }
    public WebDriver getDriver() {
        return webDriver;
    }

    public SauceSession() {
        sauceSessionCapabilities = new MutableCapabilities();
        remoteDriverImplementation = new ConcreteRemoteDriver();
        environmentManager = new ConcreteSystemManager();
        sauceOptions = new SauceOptions();
    }

    public SauceSession(RemoteDriverInterface remoteManager, EnvironmentManager environmentManager) {
        remoteDriverImplementation = remoteManager;
        sauceSessionCapabilities = new MutableCapabilities();
        this.environmentManager = environmentManager;
        sauceOptions = new SauceOptions();
    }

    public SauceSession(SauceOptions options) {
        sauceOptions = options;
        sauceSessionCapabilities = new MutableCapabilities();
        environmentManager = new ConcreteSystemManager();
        remoteDriverImplementation = new ConcreteRemoteDriver();
    }

    public SauceSession(SauceOptions options, RemoteDriverInterface remoteManager, EnvironmentManager environmentManager) {
        sauceOptions = options;
        remoteDriverImplementation = remoteManager;
        sauceSessionCapabilities = new MutableCapabilities();
        this.environmentManager = environmentManager;
    }

    public WebDriver start() {
        //TODO this might be the same as sauceCapabilities
        mutableCapabilities = appendSauceCapabilities();
        setBrowserSpecificCapabilities(sauceOptions.browser);
        sauceSessionCapabilities = setRemoteDriverCapabilities(mutableCapabilities);
        sauceLabsUrl = sauceDataCenter;
        //TODO move to a separate method
        try
        {
            webDriver = remoteDriverImplementation.createRemoteWebDriver(sauceLabsUrl, sauceSessionCapabilities);
        }
        catch (MalformedURLException e)
        {
            throw new InvalidArgumentException("Invalid URL");
        }
        return this.webDriver;
	}
    private MutableCapabilities appendSauceCapabilities() {
        mutableCapabilities = new MutableCapabilities();
        mutableCapabilities.setCapability("username", getUserName());
        mutableCapabilities.setCapability("accessKey", getAccessKey());
        if(timeouts.getCommandTimeout() != 0)
            mutableCapabilities.setCapability("commandTimeout", timeouts.getCommandTimeout());
        if(timeouts.getIdleTimeout() != 0)
            mutableCapabilities.setCapability("idleTimeout", timeouts.getIdleTimeout());
        return mutableCapabilities;
    }
    private void setBrowserSpecificCapabilities(String browserName)
    {
        if (browserName.equalsIgnoreCase("Chrome"))
        {
            ChromeOptions chromeOptions = new ChromeOptions();
            sauceSessionCapabilities.setCapability(ChromeOptions.CAPABILITY, chromeOptions);
        }
        else if (browserName.equalsIgnoreCase("Firefox"))
        {
            FirefoxOptions firefoxOptions = new FirefoxOptions();
            sauceSessionCapabilities.setCapability(FirefoxOptions.FIREFOX_OPTIONS, firefoxOptions);
        }
        else if(browserName.equalsIgnoreCase("Safari"))
        {
            SafariOptions safariOptions = new SafariOptions();
            sauceSessionCapabilities.setCapability(SafariOptions.CAPABILITY, safariOptions);
        }
        else if(browserName.equalsIgnoreCase("Edge"))
        {
            EdgeOptions edgeOptions = new EdgeOptions();
            sauceSessionCapabilities.setCapability("Edge", edgeOptions);
        }
        else if(browserName.equalsIgnoreCase("IE"))
        {
            InternetExplorerOptions ieOptions = new InternetExplorerOptions();
            sauceSessionCapabilities.setCapability("se:ieOptions", ieOptions);
        }
        else {
            throw new IllegalArgumentException("The browser=>" + browserName + " that you passed in is not a valid option.");
        }
    }
    private MutableCapabilities setRemoteDriverCapabilities(MutableCapabilities sauceOptions) {
        sauceSessionCapabilities.setCapability(sauceOptionsTag, sauceOptions);
        sauceSessionCapabilities.setCapability(CapabilityType.BROWSER_NAME, this.sauceOptions.browser);
        sauceSessionCapabilities.setCapability(CapabilityType.PLATFORM_NAME, this.sauceOptions.operatingSystem);
        sauceSessionCapabilities.setCapability(CapabilityType.BROWSER_VERSION, this.sauceOptions.browserVersion);
        return sauceSessionCapabilities;
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
