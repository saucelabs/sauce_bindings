using System;
using System.Collections.Generic;
using Newtonsoft.Json;
using OpenQA.Selenium;
using OpenQA.Selenium.Chrome;
using OpenQA.Selenium.Edge;
using OpenQA.Selenium.Firefox;
using OpenQA.Selenium.Safari;

// ReSharper disable InconsistentNaming

namespace Sauce.Bindings
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

        [JsonIgnore]
        public DriverOptions ConfiguredOptions { get; set; }

        [JsonProperty("name")]
        public string TestName { get; set; }

        [JsonProperty("custom-data")]
        public Dictionary<string, string> CustomData { get; set; }

        public Timeout Timeout { get; set; }
        public bool SetWindowRect { get; set; }
        public bool AvoidProxy { get; set; }
        public string BuildName { get; set; }
        public bool CapturePerformance { get; set; }
        public bool ExtendedDebugging { get; set; }
        public string ParentTunnel { get; set; }
        public string TunnelIdentifier { get; set; }
        public Prerun Prerun { get; set; }
        public int Priority { get; set; }
        public TestVisibility TestVisibility { get; set; }
        public bool RecordLogs { get; set; }
        public bool RecordScreenshots { get; set; }
        public bool RecordVideo { get; set; }
        public string ScreenResolution { get; set; }
        public string SeleniumVersion { get; set; }
        public IList<string> Tags { get; set; }
        public string TimeZone { get; set; }
        public bool VideoUploadOnPass { get; set; }

        public Dictionary<string, object> ToDictionary()
        {
            var options = new Dictionary<string, object>();

            var addAuth = AddAuthentication();
            foreach (var option in addAuth)
                options.Add(option.Key, option.Value);

            var addOptions = AddOptions();
            foreach (var option in addOptions)
                options.Add(option.Key, option.Value);

            return options;
        }

        private Dictionary<string, object> AddAuthentication()
        {
            var sauceUsername = Environment.GetEnvironmentVariable("SAUCE_USERNAME");
            var sauceAccessKey = Environment.GetEnvironmentVariable("SAUCE_ACCESS_KEY");

            return new Dictionary<string, object>
            {
                ["username"] = sauceUsername,
                ["accessKey"] = sauceAccessKey
            };
        }

        private Dictionary<string, object> AddOptions()
        {
            var options = JsonConvert.SerializeObject(this, JsonUtils.SerializerSettings());
            var sauceOptions = JsonConvert.DeserializeObject<Dictionary<string, object>>(options, JsonUtils.SerializerSettings());

            return sauceOptions;
        }

        public SauceOptions WithChrome() => WithChrome(DEFAULT_BROWSER_VERSION, DEFAULT_PLATFORM);
        public SauceOptions WithChrome(string version) => WithChrome(version, DEFAULT_PLATFORM);
        public SauceOptions WithChrome(string version, string platform)
        {
            ConfiguredOptions = new ChromeOptions
            {
                BrowserVersion = version,
                PlatformName = platform
            };

            return this;
        }

        public SauceOptions WithEdge() => WithEdge(EdgeVersion.Latest, DEFAULT_PLATFORM);
        public SauceOptions WithEdge(EdgeVersion version) => WithEdge(version, DEFAULT_PLATFORM);
        public SauceOptions WithEdge(EdgeVersion edgeVersion, string platform)
        {
            if (edgeVersion is null)
                throw new ArgumentNullException("Please supply a valid Edge Version. You supplied an invalid value => " +
                                                edgeVersion);

            ConfiguredOptions = new EdgeOptions
            {
                BrowserVersion = edgeVersion.Value,
                PlatformName = platform
            };

            return this;
        }

        public SauceOptions WithFirefox() => WithFirefox(DEFAULT_BROWSER_VERSION, DEFAULT_PLATFORM);
        public SauceOptions WithFirefox(string version) => WithFirefox(version, DEFAULT_PLATFORM);
        public SauceOptions WithFirefox(string version, string platform)
        {
            ConfiguredOptions = new FirefoxOptions
            {
                BrowserVersion = version,
                PlatformName = platform
            };

            return this;
        }

        public SauceOptions WithSafari() => WithSafari(DEFAULT_BROWSER_VERSION);

        public SauceOptions WithSafari(string version)
        {
            ConfiguredOptions = new SafariOptions
            {
                BrowserVersion = version,
                PlatformName = MatchCorrectPlatformToBrowserVersion(version)
            };

            return this;
        }

        private string MatchCorrectPlatformToBrowserVersion(string safariBrowserVersion)
        {
            switch (safariBrowserVersion)
            {
                case "latest":
                case "15.0":
                    return Platforms.MacOsCatalina.Value;
                case "14.0":
                case "12.0":
                    return Platforms.MacOsMojave.Value;
                case "13.0":
                case "12.1":
                case "11.1":
                    return Platforms.MacOsHighSierra.Value;
                case "11.0":
                case "10.1":
                    return Platforms.MacOsSierra.Value;
                case "10.0":
                case "9.0":
                    return Platforms.MacOsxElCapitan.Value;
                case "8.0":
                    return Platforms.MacOsxYosemite.Value;
                default:
                    throw new IncorrectSafariVersionException(safariBrowserVersion);
            }
        }
    }
}