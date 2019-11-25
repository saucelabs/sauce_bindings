using System;
using OpenQA.Selenium.Chrome;
using OpenQA.Selenium.Edge;

namespace SimpleSauce
{
    public class SauceOptions
    {
        private readonly string DefaultBrowserVersion = "latest";
        private readonly string DefaultPlatform = "Windows 10";
        public EdgeOptions ConfiguredEdgeOptions { get; set; }
        public ChromeOptions ConfiguredChromeOptions { get; private set; }

        public SauceOptions()
        {
            WithChrome();
        }

        public void WithEdge() => ConfiguredEdgeOptions = new EdgeOptions
        {
            BrowserVersion = DefaultBrowserVersion,
            PlatformName = DefaultPlatform
        };

        public void WithEdge(EdgeVersion edgeVersion)
        {
            if (edgeVersion == null)
                throw new ArgumentNullException("Please supply a valid EdgeVersion. You suplied an invalid value=>" + edgeVersion);
            ConfiguredEdgeOptions = new EdgeOptions
            {
                BrowserVersion = edgeVersion.Value,
                PlatformName = DefaultPlatform       
            };
        }
        public void WithChrome()
        {
            ConfiguredChromeOptions = new ChromeOptions()
            {
                BrowserVersion = DefaultBrowserVersion,
                PlatformName = DefaultPlatform
            };
        }

        public void WithChrome(string chromeVersion)
        {
            ConfiguredChromeOptions = new ChromeOptions()
            {
                BrowserVersion = chromeVersion,
                PlatformName = DefaultPlatform
            };
        }
    }
}