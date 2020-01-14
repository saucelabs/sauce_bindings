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
        private const string DEFAULT_BROWSER_VERSION = "latest";
        private const string DEFAULT_PLATFORM = "Windows 10";

        public SauceOptions()
        {
            WithChrome();
            Timeout = new Timeout();
        }

        public EdgeOptions ConfiguredEdgeOptions { get; set; } = new EdgeOptions();
        public ChromeOptions ConfiguredChromeOptions { get; private set; } = new ChromeOptions();
        public SafariOptions ConfiguredSafariOptions { get; set; } = new SafariOptions();
        public FirefoxOptions ConfiguredFirefoxOptions { get; set; } = new FirefoxOptions();
        public Browser BrowserName { get; set; } = Browser.Chrome;
        public String BrowserVersion { get; set; } = DEFAULT_BROWSER_VERSION;
        public Platforms PlatformName { get; set; } = Platforms.Windows10;
        public PageLoadStrategy PageLoadStrategy { get; set; }
        public bool AcceptInsecureCerts { get; set; }
        public bool SetWindowRect { get; set; }
        public Timeout Timeout { get; set; }

        public Proxy Proxy { get; set; }
        public bool StrictFileInteractability { get; set; }
        public UnhandledPromptBehavior UnhandledPromptBehavior { get; set; }
        public bool AvoidProxy { get; set; }
        public string BuildName { get; set; }
        public bool CapturePerformance { get; set; }
        public string ChromedriverVersion { get; set; }
        public Dictionary<string,string> CustomData { get; set; }
        public bool ExtendedDebugging { get; set; }
        public string IeDriverVersion { get; set; }
        public string TestName { get; set; }
        public string ParentTunnel { get; set; }
        public Dictionary<string, object> Prerun { get; set; }
        public int Priority { get; set; }
        public TestVisibility TestVisibility { get; set; }
        public bool RecordLogs { get; set; }
        public bool RecordScreenshots { get; set; }
        public bool RecordVideo { get; set; }
        public string ScreenResolution { get; set; }
        public string SeleniumVersion { get; set; }
        public IList<string> Tags { get; set; }
        public string TimeZone { get; set; }
        public string TunnelIdentifier { get; set; }
        public bool VideoUploadOnPass { get; set; }

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