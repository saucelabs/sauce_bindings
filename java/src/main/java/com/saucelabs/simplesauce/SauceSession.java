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
    private final SauceOptions sauceCapabilities;
    public SauceTimeout timeouts = new SauceTimeout();

    private final String sauceOptionsTag = "sauce:options";
    private ChromeOptions chromeOptions;
    private FirefoxOptions firefoxOptions;
    private MutableCapabilities sauceOptions;
    public MutableCapabilities sauceSessionCapabilities;
    private final RemoteDriverInterface remoteDriverImplementation;

    public WebDriver webDriver;
    private EdgeOptions edgeOptions;
    private InternetExplorerOptions ieOptions;
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
        sauceCapabilities = new SauceOptions();
    }

    public SauceSession(RemoteDriverInterface remoteManager, EnvironmentManager environmentManager) {
        remoteDriverImplementation = remoteManager;
        sauceSessionCapabilities = new MutableCapabilities();
        this.environmentManager = environmentManager;
        sauceCapabilities = new SauceOptions();
    }

    public SauceSession(SauceOptions options) {
        sauceCapabilities = options;
        sauceSessionCapabilities = new MutableCapabilities();
        environmentManager = new ConcreteSystemManager();
        remoteDriverImplementation = new ConcreteRemoteDriver();
    }

    public SauceSession(SauceOptions options, RemoteDriverInterface remoteManager, EnvironmentManager environmentManager) {
        sauceCapabilities = options;
        remoteDriverImplementation = remoteManager;
        sauceSessionCapabilities = new MutableCapabilities();
        this.environmentManager = environmentManager;
    }

    public WebDriver start() {
        //TODO this might be the same as sauceCapabilities
        sauceOptions = setSauceOptions();
        setBrowserSpecificCapabilities(sauceCapabilities.browser);
        sauceSessionCapabilities = setRemoteDriverCapabilities(sauceOptions);
        sauceLabsUrl = sauceDataCenter;
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
    private MutableCapabilities setSauceOptions() {
        sauceOptions = new MutableCapabilities();
        sauceOptions.setCapability("username", getUserName());
        sauceOptions.setCapability("accessKey", getAccessKey());
        if(timeouts.getCommandTimeout() != 0)
            sauceOptions.setCapability("commandTimeout", timeouts.getCommandTimeout());
        if(timeouts.getIdleTimeout() != 0)
            sauceOptions.setCapability("idleTimeout", timeouts.getIdleTimeout());
        return sauceOptions;
    }
    //TODO this needs to be moved to it's own class because it keeps changing
    private void setBrowserSpecificCapabilities(String browserName)
    {
        if (browserName.equalsIgnoreCase("Chrome"))
        {
            sauceSessionCapabilities.setCapability(ChromeOptions.CAPABILITY, chromeOptions);
        }
        else if (browserName.equalsIgnoreCase("Firefox"))
        {
            sauceSessionCapabilities.setCapability(FirefoxOptions.FIREFOX_OPTIONS, firefoxOptions);
        }
        else if(browserName.equalsIgnoreCase("Safari"))
        {
            SafariOptions safariOptions = new SafariOptions();
            sauceSessionCapabilities.setCapability(SafariOptions.CAPABILITY, safariOptions);
        }
        else if(browserName.equalsIgnoreCase("Edge"))
        {
            sauceSessionCapabilities.setCapability("Edge", edgeOptions);
        }
        else if(browserName.equalsIgnoreCase("IE"))
        {
            sauceSessionCapabilities.setCapability("se:ieOptions", ieOptions);
        }
        else {
            throw new IllegalArgumentException("The browser=>" + browserName + " that you passed in is not a valid option.");
        }
    }
    private MutableCapabilities setRemoteDriverCapabilities(MutableCapabilities sauceOptions) {
        sauceSessionCapabilities.setCapability(sauceOptionsTag, sauceOptions);
        sauceSessionCapabilities.setCapability(CapabilityType.BROWSER_NAME, sauceCapabilities.browser);
        sauceSessionCapabilities.setCapability(CapabilityType.PLATFORM_NAME, sauceCapabilities.operatingSystem);
        sauceSessionCapabilities.setCapability(CapabilityType.BROWSER_VERSION, sauceCapabilities.browserVersion);
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
