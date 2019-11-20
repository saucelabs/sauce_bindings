using OpenQA.Selenium;
using OpenQA.Selenium.Chrome;
using System;
using System.Collections.Generic;

namespace SimpleSauce
{
    public class SauceSession
    {
        public ChromeOptions ChromeOptions { get; private set; }
        public DataCenter DataCenter { get; set; } = DataCenter.UsWest;
        public SauceOptions Options { get; }

        public ISauceRemoteDriver DriverImplementation { get; set; }

        public SauceSession()
        {
            DriverImplementation = new SauceDriver();
            Options = new SauceOptions();
        }
        public SauceSession(ISauceRemoteDriver driver)
        {
            DriverImplementation = driver;
            Options = new SauceOptions();
        }

        public SauceSession(SauceOptions options)
        {
            this.Options = options;
            DriverImplementation = new SauceDriver();
        }

        public SauceSession(SauceOptions options, ISauceRemoteDriver driver)
        {
            this.Options = options;
            DriverImplementation = driver;
        }

        public IWebDriver Start()
        {
            if (Options.EdgeOptions != null)
                return CreateEdgeBrowser();

            var sauceUserName = Environment.GetEnvironmentVariable("SAUCE_USERNAME");
            var sauceAccessKey = Environment.GetEnvironmentVariable("SAUCE_ACCESS_KEY");
            var sauceOptions = new Dictionary<string, object>
            {
                ["username"] = sauceUserName,
                ["accessKey"] = sauceAccessKey
            };

            ChromeOptions = new ChromeOptions
            {
                BrowserVersion = "latest",
                PlatformName = "Windows 10",
                UseSpecCompliantProtocol = true
            };
            ChromeOptions.AddAdditionalCapability("sauce:options", sauceOptions, true);
            return DriverImplementation.CreateRemoteWebDriver(ChromeOptions);
        }

        private IWebDriver CreateEdgeBrowser()
        {
            var sauceUserName = Environment.GetEnvironmentVariable("SAUCE_USERNAME");
            var sauceAccessKey = Environment.GetEnvironmentVariable("SAUCE_ACCESS_KEY");
            var sauceConfiguration = new Dictionary<string, object>
            {
                ["username"] = sauceUserName,
                ["accessKey"] = sauceAccessKey
            };

            Options.EdgeOptions.AddAdditionalCapability("sauce:options", sauceConfiguration);
            return DriverImplementation.CreateRemoteWebDriver(Options.EdgeOptions);
        }
    }
}