using System;
using System.Collections.Generic;
using OpenQA.Selenium;
using OpenQA.Selenium.Chrome;
using OpenQA.Selenium.Edge;
using OpenQA.Selenium.Safari;

namespace Simple.Sauce
{
    public class SauceOptions
    {
        private const string DefaultBrowserVersion = "latest";
        private const string DefaultPlatform = "Windows 10";
        private readonly string _sauceUserName = Environment.GetEnvironmentVariable("SAUCE_USERNAME");
        private readonly string _sauceAccessKey = Environment.GetEnvironmentVariable("SAUCE_ACCESS_KEY");
        private readonly Dictionary<string, object> _sauceConfiguration;

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
        public EdgeOptions ConfiguredEdgeOptions { get; set; }
        public ChromeOptions ConfiguredChromeOptions { get; private set; }
        public SafariOptions ConfiguredSafariOptions { get; set; }
        public DataCenter DataCenter { get; set; } = DataCenter.UsWest;


        public void WithEdge()
        {
            WithEdge(EdgeVersion.Latest);
        }

        public void WithEdge(EdgeVersion edgeVersion)
        {
            if (edgeVersion == null)
                throw new ArgumentNullException("Please supply a valid EdgeVersion. You suplied an invalid value=>" +
                                                edgeVersion);
            ConfiguredEdgeOptions = new EdgeOptions
            {
                BrowserVersion = edgeVersion.Value,
                PlatformName = DefaultPlatform
            };
        }

        public void WithChrome()
        {
            ConfiguredChromeOptions = new ChromeOptions
            {
                BrowserVersion = DefaultBrowserVersion,
                PlatformName = DefaultPlatform
            };
        }

        public void WithChrome(string chromeVersion)
        {
            ConfiguredChromeOptions = new ChromeOptions
            {
                BrowserVersion = chromeVersion,
                PlatformName = DefaultPlatform
            };
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

        public void WithSafari()
        {
            ConfiguredSafariOptions = new SafariOptions
            {
                BrowserVersion = DefaultBrowserVersion,
                PlatformName = Platforms.MacOsMojave.Value
            };
        }

        public void WithSafari(string safariVersion)
        {
            ConfiguredSafariOptions = new SafariOptions
            {
                BrowserVersion = safariVersion,
                PlatformName = SetCorrectPlatformVersion(safariVersion)
            };
        }

        private string SetCorrectPlatformVersion(string safariBrowserVersion)
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
    }
}