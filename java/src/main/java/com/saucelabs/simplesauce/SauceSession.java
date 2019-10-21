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
    public SauceTimeout timeouts = new SauceTimeout();

    //todo there is some weird bug when this is set to Linux, the session can't be started
	private String operatingSystem = "Windows 10";
	private String browserName = "Chrome";
    private final String sauceOptionsTag = "sauce:options";
    private ChromeOptions chromeOptions;
    private FirefoxOptions firefoxOptions;
    private MutableCapabilities sauceOptions;
    private String browserVersion = "latest";
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
    }

    public SauceSession(RemoteDriverInterface remoteManager, EnvironmentManager environmentManager) {
        remoteDriverImplementation = remoteManager;
        sauceSessionCapabilities = new MutableCapabilities();
        this.environmentManager = environmentManager;
    }

    public WebDriver start() {
        sauceOptions = setSauceOptions();
        setBrowserSpecificCapabilities(browserName);
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

    private MutableCapabilities setRemoteDriverCapabilities(MutableCapabilities sauceOptions) {
        sauceSessionCapabilities.setCapability(sauceOptionsTag, sauceOptions);
        sauceSessionCapabilities.setCapability(CapabilityType.BROWSER_NAME, browserName);
        sauceSessionCapabilities.setCapability(CapabilityType.PLATFORM_NAME, operatingSystem);
        sauceSessionCapabilities.setCapability(CapabilityType.BROWSER_VERSION, browserVersion);
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

	public SauceSession withChrome()
	{
	    chromeOptions = new ChromeOptions();
		chromeOptions.setExperimentalOption("w3c", true);
		browserName = "Chrome";
		return this;
	}

    public SauceSession withSafari(String version)
    {
        //TODO: I did this but I hate it :(
        //I wish I could just have a default value set for the version param
        if(version.equalsIgnoreCase(""))
            version = "latest";
        browserName = "safari";
        this.browserVersion = version;
        return this;
    }
    public SauceSession withSafari()
    {
        return withMacOsMojave();
    }

    public SauceSession withFirefox()
	{
		browserName = "Firefox";
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
        operatingSystem = "macOS 10.14";
        browserName = "safari";
        browserVersion = "12.0";
        return this;
    }
    public SauceSession withMacOsHighSierra()
    {
        this.operatingSystem = "macOS 10.13";
        browserName = "Safari";
        return this;
    }

    public SauceSession withEdge() {
        this.browserName = "Edge";
        edgeOptions = new EdgeOptions();
        return this;
    }

    public SauceSession withIE(String version) {
        this.browserName = "IE";
        this.browserVersion = version;
        ieOptions = new InternetExplorerOptions();
        return this;
    }

    public SauceSession withPlatform(String operatingSystem) {
        this.operatingSystem = operatingSystem;
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
        this.operatingSystem = "macOS 10.12";
        browserName = "Safari";
        return this;
    }

    public SauceSession withMacOsXElCapitan() {
        this.operatingSystem = "OS X 10.11";
        browserName = "Safari";
        return this;
    }

    public SauceSession withMacOsXYosemite() {
        this.operatingSystem = "OS X 10.10";
        browserName = "Safari";
        return this;
    }
    //FIXED notice the duplication below with edge. Used enumeration
    //Maybe could be moved to a separate class so we can do withEdge().16_16299();
    //Or withEdge().version(EdgeVersion.16_16299);
    public SauceSession withEdge(EdgeVersion version) {
        browserName = "Edge";
        browserVersion = version.name().substring(1);
        return this;
    }

    public SauceSession withWindows10() {
        operatingSystem = "Windows 10";
        return this;
    }

    public SauceSession withWindows8_1() {
        operatingSystem = "Windows 8.1";
        return this;
    }

    public SauceSession withWindows8() {
        operatingSystem = "Windows 8";
        return this;
    }

    public SauceSession withWindows7() {
        operatingSystem = "Windows 7";
        return this;
    }

    public SauceSession withLinux() {
        operatingSystem = "Linux";
        return this;
    }

    public enum EdgeVersion {
        E16_16299,
        E15_15063,
        E14_14393,
        E13_10586,
        E17_17134,
        E18_17763,

    }
}
