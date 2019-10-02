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

    private WebDriver webDriver;
    private EdgeOptions edgeOptions;
    private InternetExplorerOptions ieOptions;
    @Getter @Setter public String sauceLabsUrl;

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





    public SauceSession withSafari(String version)
    {
        //TODO: I did this but I hate it :(
        //I wish I could just have a default value set for the version param
        if(version.equalsIgnoreCase(""))
            version = "latest";
        sauceCapabilities.browser = "safari";
        sauceCapabilities.browserVersion = version;
        return this;
    }
    public SauceSession withSafari()
    {
        return withMacOsMojave();
    }

    public SauceSession withFirefox()
	{
        sauceCapabilities.browser = "Firefox";
		return this;
	}

    public RemoteDriverInterface getDriverManager() {
        return remoteDriverImplementation;
    }
    public MutableCapabilities getSauceOptionsCapability(){
        return ((MutableCapabilities) sauceSessionCapabilities.getCapability(sauceOptionsTag));
    }
    public WebDriver getDriver() {
        return webDriver;
    }

    public SauceSession withMacOsMojave() {
        sauceCapabilities.operatingSystem = "macOS 10.14";
        sauceCapabilities.browser = "safari";
        sauceCapabilities.browserVersion = "12.0";
        return this;
    }
    public SauceSession withMacOsHighSierra()
    {
        sauceCapabilities.operatingSystem = "macOS 10.13";
        sauceCapabilities.browser = "Safari";
        return this;
    }

    public SauceSession withEdge() {
        sauceCapabilities.browser = "Edge";
        edgeOptions = new EdgeOptions();
        return this;
    }

    public SauceSession withIE(String version) {
        sauceCapabilities.browser = "IE";
        sauceCapabilities.browserVersion = version;
        ieOptions = new InternetExplorerOptions();
        return this;
    }

    public SauceSession withPlatform(String operatingSystem) {
        sauceCapabilities.operatingSystem = operatingSystem;
        return this;
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

    public SauceSession withMacOsSierra() {
        sauceCapabilities.operatingSystem = "macOS 10.12";
        sauceCapabilities.browser = "Safari";
        return this;
    }

    public SauceSession withMacOsXElCapitan() {
        sauceCapabilities.operatingSystem = "OS X 10.11";
        sauceCapabilities.browser = "Safari";
        return this;
    }

    public SauceSession withMacOsXYosemite() {
        sauceCapabilities.operatingSystem = "OS X 10.10";
        sauceCapabilities.browser = "Safari";
        return this;
    }
    //TODO notice the duplication below with edge.
    //Maybe could be moved to a separate class so we can do withEdge().16_16299();
    //Or withEdge().version(EdgeVersion.16_16299);
    public SauceSession withEdge16_16299() {
        withEdge();
        sauceCapabilities.browserVersion = "16.16299";
        return this;
    }

    public SauceSession withEdge15_15063() {
        withEdge();
        sauceCapabilities.browserVersion = "15.15063";
        return this;
    }

    public SauceSession withEdge14_14393() {
        withEdge();
        sauceCapabilities.browserVersion = "14.14393";
        return this;
    }

    public SauceSession withEdge13_10586() {
        withEdge();
        sauceCapabilities.browserVersion = "13.10586";
        return this;
    }

    public SauceSession withEdge17_17134() {
        withEdge();
        sauceCapabilities.browserVersion = "17.17134";
        return this;
    }

    public SauceSession withEdge18_17763() {
        withEdge();
        sauceCapabilities.browserVersion = "18.17763";
        return this;
    }

    public SauceSession withWindows10() {
        sauceCapabilities.operatingSystem = "Windows 10";
        return this;
    }

    public SauceSession withWindows8_1() {
        sauceCapabilities.operatingSystem = "Windows 8.1";
        return this;
    }

    public SauceSession withWindows8() {
        sauceCapabilities.operatingSystem = "Windows 8";
        return this;
    }

    public SauceSession withWindows7() {
        sauceCapabilities.operatingSystem = "Windows 7";
        return this;
    }

    public SauceSession withLinux() {
        sauceCapabilities.operatingSystem = "Linux";
        return this;
    }
}
