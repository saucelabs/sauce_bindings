using System;
using OpenQA.Selenium.Edge;

namespace SimpleSauce
{
    public class SauceOptions
    {
        private readonly string DefaultBrowserVersion = "latest";
        private readonly string DefaultPlatform = "Windows 10";
        public EdgeOptions EdgeOptions { get; set; }

        public void WithEdge() => EdgeOptions = new EdgeOptions
        {
            BrowserVersion = DefaultBrowserVersion,
            PlatformName = DefaultPlatform
        };

        public void WithEdge(EdgeVersion edgeVersion)
        {
            if (edgeVersion == null)
                throw new ArgumentNullException("Please supply a valid EdgeVersion. You suplied an invalid value=>" + edgeVersion);
            EdgeOptions = new EdgeOptions
            {
                BrowserVersion = edgeVersion.Value,
                PlatformName = DefaultPlatform       
            };
        }
    }
}