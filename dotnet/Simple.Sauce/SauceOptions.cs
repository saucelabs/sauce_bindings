using OpenQA.Selenium;
using OpenQA.Selenium.Chrome;
using OpenQA.Selenium.Edge;
using OpenQA.Selenium.Firefox;
using OpenQA.Selenium.Safari;
using System;
using System.Collections.Generic;
using System.Reflection;
// ReSharper disable InconsistentNaming

namespace Simple.Sauce
{
    public class SauceOptions
    {
        private const string DEFAULT_BROWSER_VERSION = "latest";
        private const string DEFAULT_PLATFORM = "Windows 10";
        private string _buildName;

        public SauceOptions()
        {
            Timeout = new Timeout();
        }

        public SauceOptions(DriverOptions options)
        {
            SeleniumOptions = options;
            if (options.BrowserName != null)
            {
                BrowserName = ToBrowserType(options.BrowserName);
            }
        }

        public EdgeOptions ConfiguredEdgeOptions { get; set; } = new EdgeOptions();
        public ChromeOptions ConfiguredChromeOptions { get; private set; } = new ChromeOptions();
        public SafariOptions ConfiguredSafariOptions { get; set; } = new SafariOptions();
        public FirefoxOptions ConfiguredFirefoxOptions { get; set; } = new FirefoxOptions();
        //public Browser BrowserName
        //{
        //    get
        //    {
        //        if (_browserName == null)
        //            return Browser.Chrome;
        //        return ToBrowserType(SeleniumOptions.BrowserName);
        //    }
        //    set
        //    {
        //        _browserName = value;
        //    }
        //}
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
        public string BuildName
        {
            get
            {
                if (_buildName != null)
                    return _buildName;
                else if (GetEnvironmentVariable(knownCITools["Jenkins"]) != null)
                {
                    return GetEnvironmentVariable("BUILD_NAME") + ": " + GetEnvironmentVariable("BUILD_NUMBER");
                }
                else if (GetEnvironmentVariable(knownCITools["Bamboo"]) != null)
                {
                    return GetEnvironmentVariable("bamboo_shortJobName") + ": " + GetEnvironmentVariable("bamboo_buildNumber");
                }
                else if (GetEnvironmentVariable(knownCITools["Travis"]) != null)
                {
                    return GetEnvironmentVariable("TRAVIS_JOB_NAME") + ": " + GetEnvironmentVariable("TRAVIS_JOB_NUMBER");
                }
                else if (GetEnvironmentVariable(knownCITools["Circle"]) != null)
                {
                    return GetEnvironmentVariable("CIRCLE_JOB") + ": " + GetEnvironmentVariable("CIRCLE_BUILD_NUM");
                }
                else if (GetEnvironmentVariable(knownCITools["GitLab"]) != null)
                {
                    return GetEnvironmentVariable("CI_JOB_NAME") + ": " + GetEnvironmentVariable("CI_JOB_ID");
                }
                else if (GetEnvironmentVariable(knownCITools["TeamCity"]) != null)
                {
                    return GetEnvironmentVariable("TEAMCITY_PROJECT_NAME") + ": " + GetEnvironmentVariable("BUILD_NUMBER");
                }
                else
                {
                    return "Build Time: " + DateTime.Now;
                }
            }
            set { _buildName = value; }
        }

        internal DriverOptions ToDriverOptions()
        {
            var w3cCapabilities = SeleniumOptions;
            //TODO temporary solution to get the code working
            var sauceConfiguration = new Dictionary<string, object>
            {
                ["username"] = Environment.GetEnvironmentVariable("SAUCE_USERNAME"),
                ["accessKey"] = Environment.GetEnvironmentVariable("SAUCE_ACCESS_KEY")
            };
            //TODO add if logic from the toCapabilities() in Java

            if (BrowserName == Browser.Edge)
                w3cCapabilities = new EdgeOptions();

            w3cOptions.ForEach(capability => AppendCapabilityIfDefined(sauceConfiguration, capability));

            w3cCapabilities.AddAdditionalOption("sauce:options", sauceConfiguration);
            return w3cCapabilities;
        }

        private void AppendCapabilityIfDefined(Dictionary<string, object> sauceConfiguration, string capability)
        {
            var capabilityValue = TryToGetCapabilityValue(capability);
            if (capabilityValue != null)
                sauceConfiguration.Add(capability, capabilityValue);
        }

        private object TryToGetCapabilityValue(string capability)
        {
            try
            {
                return GetType().GetProperty(capability).GetValue(this);
            }
            catch (NullReferenceException)
            {
                return null;
            }
            //PropertyInfo pinfo = typeof(SauceOptions).GetProperty(capability);
            //return  pinfo.GetValue(YourInstantiatedObject, null);
        }

        public bool CapturePerformance { get; set; }
        public string ChromedriverVersion { get; set; }
        public Dictionary<string, string> CustomData { get; set; }
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
        public DriverOptions SeleniumOptions { get; set; }
        public Browser BrowserName { get; set; } = Browser.Chrome;
        public static List<string> w3cOptions = new List<string>(new string[]
            {
                "browserName",
                "browserVersion",
                "platformName",
                "pageLoadStrategy",
                "acceptInsecureCerts",
                "proxy",
                "setWindowRect",
                "timeouts",
                "strictFileInteractability",
                "unhandledPromptBehavior"
            }
        );
        public static List<string> sauceOptions = new List<string>(new string[]
        {
            "avoidProxy",
            "build",
            "capturePerformance",
            "chromedriverVersion",
            "commandTimeout",
            "customData",
            "extendedDebugging",
            "idleTimeout",
            "iedriverVersion",
            "maxDuration",
            "name",
            "parentTunnel",
            "prerun",
            "priority",
            // public, do not use, reserved keyword, using jobVisibility
            "recordLogs",
            "recordScreenshots",
            "recordVideo",
            "screenResolution",
            "seleniumVersion",
            "tags",
            "timeZone",
            "tunnelIdentifier",
            "videoUploadOnPass"
        }
        );


        protected string GetEnvironmentVariable(string key)
        {
            return Environment.GetEnvironmentVariable(key);
        }
        //TODO could probably store this into an enum
        private readonly Dictionary<string, string> knownCITools = new Dictionary<string, string>()
        {
            { "Jenkins", "BUILD_TAG" },
            { "Bamboo", "bamboo_agentId" },
            { "Travis", "TRAVIS_JOB_ID" },
            { "Circle", "CIRCLE_JOB" },
            { "GitLab", "CI" },
            { "TeamCity", "TEAMCITY_PROJECT_NAME" },
            { "ADO", "NEEDS_DEFINITION" },
        };
        private Browser ToBrowserType(string browserName)
        {
            Browser browser;
            switch (browserName)
            {
                case "chrome":
                    browser = Browser.Chrome;
                    break;
                case "MicrosoftEdge":
                    browser = Browser.Edge;
                    break;
                case "firefox":
                    browser = Browser.Firefox;
                    break;
                case "safari":
                    browser = Browser.Safari;
                    break;
                case "internet explorer":
                    browser = Browser.IE;
                    break;
                default:
                    throw new ArgumentException("No such browser exists.");
            }
            return browser;
        }
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