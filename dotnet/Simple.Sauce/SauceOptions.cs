using System;
using OpenQA.Selenium.Chrome;
using OpenQA.Selenium.Edge;
using OpenQA.Selenium.Firefox;
using OpenQA.Selenium.Safari;
// ReSharper disable InconsistentNaming

namespace Simple.Sauce
{
    public class SauceOptions
    {
        private const string DEFAULT_BROWSER_VERSION = "latest";
        private const string DEFAULT_PLATFORM = "Windows 10";

        public SauceOptions()
        {
            WithChrome();
        }

        public EdgeOptions ConfiguredEdgeOptions { get; set; } = new EdgeOptions();
        public ChromeOptions ConfiguredChromeOptions { get; private set; } = new ChromeOptions();
        public SafariOptions ConfiguredSafariOptions { get; set; } = new SafariOptions();
        public FirefoxOptions ConfiguredFirefoxOptions { get; set; } = new FirefoxOptions();
        public String BrowserName { get; set; } = "chrome";
        public String BrowserVersion { get; set; } = DEFAULT_BROWSER_VERSION;
        public String PlatformName { get; set; } = DEFAULT_PLATFORM;

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

        public void WithSafari()
        {
            WithSafari(DEFAULT_BROWSER_VERSION);
        }

        public void WithSafari(string safariVersion)
        {
            ConfiguredSafariOptions.BrowserVersion = safariVersion;
            ConfiguredSafariOptions.PlatformName = MatchCorrectPlatformToBrowserVersion(safariVersion);
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