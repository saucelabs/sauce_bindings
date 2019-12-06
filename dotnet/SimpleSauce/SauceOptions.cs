using System;
using OpenQA.Selenium.Chrome;
using OpenQA.Selenium.Edge;
using OpenQA.Selenium.Safari;

namespace SimpleSauce
{
    public class SauceOptions
    {
        private const string DefaultBrowserVersion = "latest";
        private const string DefaultPlatform = "Windows 10";

        public SauceOptions()
        {
            WithChrome();
        }

        public EdgeOptions ConfiguredEdgeOptions { get; set; }
        public ChromeOptions ConfiguredChromeOptions { get; private set; }
        public SafariOptions ConfiguredSafariOptions { get; set; }

        public void WithEdge()
        {
            ConfiguredEdgeOptions = new EdgeOptions
            {
                BrowserVersion = DefaultBrowserVersion,
                PlatformName = DefaultPlatform
            };
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

        public void WithSafari()
        {
            ConfiguredSafariOptions = new SafariOptions
            {
                BrowserVersion = DefaultBrowserVersion,
                PlatformName = DefaultPlatform
            };
        }

        public void WithSafari(string safariVersion)
        {
            ConfiguredSafariOptions = new SafariOptions
            {
                BrowserVersion = safariVersion,
                PlatformName = DefaultPlatform
            };
        }
    }
}