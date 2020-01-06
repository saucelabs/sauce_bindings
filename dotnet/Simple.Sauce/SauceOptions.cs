using System;
using System.Collections.Generic;
using OpenQA.Selenium;
using OpenQA.Selenium.Chrome;
using OpenQA.Selenium.Edge;
using OpenQA.Selenium.Firefox;
using OpenQA.Selenium.Safari;
// ReSharper disable InconsistentNaming

namespace Simple.Sauce
{
    public class SauceOptions
    {
        private readonly string _sauceUserName = Environment.GetEnvironmentVariable("SAUCE_USERNAME");
        private readonly string _sauceAccessKey = Environment.GetEnvironmentVariable("SAUCE_ACCESS_KEY");
        private readonly Dictionary<string, object> _sauceConfiguration;
        private const string DEFAULT_BROWSER_VERSION = "latest";
        private const string DEFAULT_PLATFORM = "Windows 10";

        public SauceOptions() : this(new DriverFactory())
        {
        }

        public SauceOptions(IDriverFactory driverFactory)
        {
            DriverFactory = driverFactory;
            _sauceConfiguration = new Dictionary<string, object>
            {
                ["username"] = _sauceUserName,
                ["accessKey"] = _sauceAccessKey
            };
            WithChrome();
        }

        public IDriverFactory DriverFactory { get; }
        public DataCenter DataCenter { get; set; } = DataCenter.UsWest;
        public EdgeOptions ConfiguredEdgeOptions { get; set; } = new EdgeOptions();
        public ChromeOptions ConfiguredChromeOptions { get; private set; } = new ChromeOptions();
        public SafariOptions ConfiguredSafariOptions { get; set; } = new SafariOptions();
        public FirefoxOptions ConfiguredFirefoxOptions { get; set; } = new FirefoxOptions();

        public void WithEdge()
        {
            WithEdge(EdgeVersion.Latest);
        }

        public void WithEdge(EdgeVersion edgeVersion)
        {
            if (edgeVersion == null)
                throw new ArgumentNullException("Please supply a valid EdgeVersion. You suplied an invalid value=>" +
                                                edgeVersion);
            ConfiguredEdgeOptions.BrowserVersion = edgeVersion.Value;
            ConfiguredEdgeOptions.PlatformName = DEFAULT_PLATFORM;
        }

        public void WithChrome()
        {
            ConfiguredChromeOptions.BrowserVersion = DEFAULT_BROWSER_VERSION;
            ConfiguredChromeOptions.PlatformName = DEFAULT_PLATFORM;
        }

        public void WithChrome(string chromeVersion)
        {
            ConfiguredChromeOptions.BrowserVersion = chromeVersion;
        }

        public IWebDriver CreateEdgeBrowser()
        {
            ConfiguredEdgeOptions.AddAdditionalOption("sauce:options", _sauceConfiguration);
            return DriverFactory.CreateRemoteWebDriver(ConfiguredEdgeOptions);
        }

        public IWebDriver CreateSafariDriver()
        {
            ConfiguredSafariOptions.AddAdditionalOption("sauce:options", _sauceConfiguration);
            return DriverFactory.CreateRemoteWebDriver(ConfiguredSafariOptions);
        }

        public IWebDriver CreateChromeDriver()
        {
            ConfiguredChromeOptions.AddAdditionalOption("sauce:options", _sauceConfiguration);
            return DriverFactory.CreateRemoteWebDriver(ConfiguredChromeOptions);
        }
        public IWebDriver CreateFirefoxDriver()
        {
            ConfiguredFirefoxOptions.AddAdditionalOption("sauce:options", _sauceConfiguration);
            return DriverFactory.CreateRemoteWebDriver(ConfiguredFirefoxOptions);
        }
        public void WithSafari()
        {
            WithSafari(DEFAULT_BROWSER_VERSION);
        }

        public void WithSafari(string safariVersion)
        {
            SetPlatformBasedOnBrowserVersion(safariVersion);
        }

        private void SetPlatformBasedOnBrowserVersion(string safariBrowserVersion)
        {
            ConfiguredSafariOptions.BrowserVersion = safariBrowserVersion;
            ConfiguredSafariOptions.PlatformName = MatchCorrectPlatformToBrowserVersion(safariBrowserVersion);
        }

        public string MatchCorrectPlatformToBrowserVersion(string safariBrowserVersion)
        {
            switch (safariBrowserVersion)
            {
                case "latest":
                    return Platforms.MacOsMojave.Value;
                case "12.0":
                    return Platforms.MacOsMojave.Value;
                case "13.0":
                    return Platforms.MacOsHighSierra.Value;
                case "12.1":
                    return Platforms.MacOsHighSierra.Value;
                case "11.1":
                    return Platforms.MacOsHighSierra.Value;
                case "11.0":
                    return Platforms.MacOsSierra.Value;
                case "10.1":
                    return Platforms.MacOsSierra.Value;
                case "9.0":
                    return Platforms.MacOsxElCapitan.Value;
                case "10.0":
                    return Platforms.MacOsxElCapitan.Value;
                case "8.0":
                    return Platforms.MacOsxYosemite.Value;
                default:
                    throw new IncorrectSafariVersionException();
            }
        }

        public void WithFirefox()
        {
            WithFirefox(DEFAULT_BROWSER_VERSION);
        }

        public void WithFirefox(string version)
        {
            ConfiguredFirefoxOptions.BrowserVersion = version;
            ConfiguredFirefoxOptions.PlatformName = DEFAULT_PLATFORM;
        }


    }
}