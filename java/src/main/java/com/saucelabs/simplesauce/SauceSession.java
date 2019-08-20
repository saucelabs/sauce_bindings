package com.saucelabs.simplesauce;

import com.saucelabs.simplesauce.interfaces.EnvironmentManager;
import com.saucelabs.simplesauce.interfaces.RemoteDriverInterface;
import lombok.Getter;
import lombok.Setter;
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
    private EnvironmentManager environmentManager;

    //todo there is some weird bug when this is set to Linux, the session can't be started
	private String operatingSystem = "Windows 10";
	private String browserName = "Chrome";
	private String testName;
    private String sauceOptionsTag = "sauce:options";
    private ChromeOptions chromeOptions;
    private FirefoxOptions firefoxOptions;
    private MutableCapabilities sauceOptions;
    private String browserVersion = "latest";
    public MutableCapabilities sauceSessionCapabilities;
    private RemoteDriverInterface remoteDriverImplementation;

    private WebDriver webDriver;
    private SafariOptions safariOptions;
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

    public WebDriver start() throws MalformedURLException
	{
        sauceOptions = setSauceOptions();
        setBrowserSpecificCapabilities(browserName);
        sauceSessionCapabilities = setRemoteDriverCapabilities(sauceOptions);
        sauceLabsUrl = sauceDataCenter;
        webDriver = remoteDriverImplementation.createRemoteWebDriver(sauceLabsUrl, sauceSessionCapabilities);
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
            safariOptions = new SafariOptions();
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

    public SauceSession withSafari()
    {
        safariOptions = new SafariOptions();
        browserName = "safari";
        return this;
    }

    public SauceSession withFirefox()
	{
		firefoxOptions = new FirefoxOptions();
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

    //TODO How do we want to handle this?
    //1. withMacOsMojave(OperatingSystem.MacOs1014), aka, force the user to pass in a mac version
    //2. throw an exception for withMacOsMojave() used without withMac();
    //3. this is the method I chose below: withMacOsMojave(String browserVersion)
    public SauceSession withMacOsMojave() {
        operatingSystem = "macOS 10.14";
        browserName = "safari";
        return this;
    }
    public SauceSession withMacOsHighSierra()
    {
        this.operatingSystem = "macOS 10.13";
        browserName = "Safari";
        return this;
    }
    public SauceSession withBrowserVersion(String browserVersion){
        this.browserVersion = browserVersion;
        return this;
    }

    public SauceSession withEdge() {
        this.browserName = "Edge";
        edgeOptions = new EdgeOptions();
        return this;
    }

    public SauceSession withIE() {
        this.browserName = "IE";
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

    public void withMacOsSierra() {
        this.operatingSystem = "macOS 10.12";
        browserName = "Safari";
    }

    public void withMacOsXElCapitan() {
        this.operatingSystem = "OS X 10.11";
        browserName = "Safari";
    }

    public void withMacOsXYosemite() {
        this.operatingSystem = "OS X 10.10";
        browserName = "Safari";
    }
    //TODO notice the duplication below with edge.
    //Maybe could be moved to a separate class so we can do withEdge().16_16299();
    //Or withEdge().version(EdgeVersion.16_16299);
    public void withEdge16_16299() {
        browserName = "Edge";
        browserVersion = "16.16299";
    }

    public void withEdge15_15063() {
        browserName = "Edge";
        browserVersion = "15.15063";
    }

    public void withEdge14_14393() {
        browserName = "Edge";
        browserVersion = "14.14393";
    }

    public void withEdge13_10586() {
        browserName = "Edge";
        browserVersion = "13.10586";
    }

    public void withEdge17_17134() {
        browserName = "Edge";
        browserVersion = "17.17134";
    }

    public void withEdge18_17763() {
        browserName = "Edge";
        browserVersion = "18.17763";
    }

    public SauceSession withWindows10() {
        operatingSystem = "Windows 10";
        return this;
    }
}
