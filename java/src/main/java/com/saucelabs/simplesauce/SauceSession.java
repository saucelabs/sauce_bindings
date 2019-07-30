package com.saucelabs.simplesauce;

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
    public static final String SAUCE_URL = "https://ondemand.saucelabs.com:443/wd/hub";

	@Setter private static String SAUCE_USERNAME = System.getenv("SAUCE_USERNAME");
	@Setter private static String SAUCE_ACCESS_KEY = System.getenv("SAUCE_ACCESS_KEY");

	private String BUILD_TAG = System.getenv("BUILD_TAG");

    public SauceApi test;

    //todo there is some weird bug when this is set to Linux, the session can't be started
	private String operatingSystem = "Windows 10";
	private String browserName = "Chrome";
	private String testName;
	private Boolean useSauce = true;
    private String sauceOptionsTag = "sauce:options";

    private ChromeOptions chromeOptions;
    private FirefoxOptions firefoxOptions;
    private MutableCapabilities sauceOptions;
    private String browserVersion = "latest";

    private MutableCapabilities capabilities;
    private RemoteDriverInterface remoteDriverManager;
    private WebDriver webDriver;
    private SafariOptions safariOptions;
    private EdgeOptions edgeOptions;
    private InternetExplorerOptions ieOptions;

    public SauceSession() {
        capabilities = new MutableCapabilities();
        remoteDriverManager = new ConcreteRemoteDriver();
    }

    public SauceSession(RemoteDriverInterface remoteManager) {
        remoteDriverManager = remoteManager;
        capabilities = new MutableCapabilities();
    }

    public WebDriver start() throws MalformedURLException
	{
        capabilities = setSauceOptions();
        webDriver = remoteDriverManager.getRemoteWebDriver(SAUCE_URL, capabilities);

        return this.webDriver;
	}

    public MutableCapabilities setSauceOptions() {
        sauceOptions = getSauceOptions();
        setBrowserOptions(browserName);

        capabilities.setCapability(sauceOptionsTag, sauceOptions);
        capabilities.setCapability(CapabilityType.BROWSER_NAME, browserName);
        capabilities.setCapability(CapabilityType.PLATFORM_NAME, operatingSystem);
        capabilities.setCapability(CapabilityType.BROWSER_VERSION, browserVersion);

        return capabilities;
    }

    public MutableCapabilities getSauceOptions()
    {
        //TODO no longer required
        if (useSauce)
        {
            sauceOptions = new MutableCapabilities();
            sauceOptions.setCapability("username", SAUCE_USERNAME);
            sauceOptions.setCapability("accessKey", SAUCE_ACCESS_KEY);

            if (testName != null)
            {
                sauceOptions.setCapability("name", testName);
            }

            if (BUILD_TAG != null)
            {
                sauceOptions.setCapability("build", BUILD_TAG);
            }
        }

        return sauceOptions;
    }
    //TODO this needs to be moved to it's own class because it keeps changing
    public void setBrowserOptions(String browserName)
    {
        if (browserName.equalsIgnoreCase("Chrome"))
        {
            capabilities.setCapability(ChromeOptions.CAPABILITY, chromeOptions);
        }
        else if (browserName.equalsIgnoreCase("Firefox"))
        {
            capabilities.setCapability(FirefoxOptions.FIREFOX_OPTIONS, firefoxOptions);
        }
        else if(browserName.equalsIgnoreCase("Safari"))
        {
            safariOptions = new SafariOptions();
            capabilities.setCapability(SafariOptions.CAPABILITY, safariOptions);
        }
        else if(browserName.equalsIgnoreCase("Edge"))
        {
            capabilities.setCapability("Edge", edgeOptions);
        }
        else if(browserName.equalsIgnoreCase("IE"))
        {
            capabilities.setCapability("se:ieOptions", ieOptions);
        }
        else {
            //TODO why is this so annoying??
            //throw new NoSuchBrowserExistsException();
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
        return remoteDriverManager;
    }


    public MutableCapabilities getSauceOptionsCapability(){
        return ((MutableCapabilities) capabilities.getCapability(sauceOptionsTag));
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
}
