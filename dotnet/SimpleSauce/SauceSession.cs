using System;
using System.Collections.Generic;
using OpenQA.Selenium;
using OpenQA.Selenium.Chrome;

namespace SimpleSauce
{
    public class SauceSession
    {
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
            Options = options;
            DriverImplementation = new SauceDriver();
        }

        public SauceSession(SauceOptions options, ISauceRemoteDriver driver)
        {
            Options = options;
            DriverImplementation = driver;
        }

        public ChromeOptions ChromeOptions { get; private set; }
        public DataCenter DataCenter { get; set; } = DataCenter.UsWest;
        public SauceOptions Options { get; }

        public ISauceRemoteDriver DriverImplementation { get; set; }

        public IWebDriver Start()
        {
            if (Options.ConfiguredEdgeOptions != null)
                return CreateEdgeBrowser();
            if (Options.ConfiguredSafariOptions != null)
                return CreateSafariDriver();
            return CreateChromeDriver();
        }

        private IWebDriver CreateSafariDriver()
        {
            var sauceUserName = Environment.GetEnvironmentVariable("SAUCE_USERNAME");
            var sauceAccessKey = Environment.GetEnvironmentVariable("SAUCE_ACCESS_KEY");
            var sauceConfiguration = new Dictionary<string, object>
            {
                ["username"] = sauceUserName,
                ["accessKey"] = sauceAccessKey
            };

            Options.ConfiguredSafariOptions.AddAdditionalOption("sauce:options", sauceConfiguration);
            return DriverImplementation.CreateRemoteWebDriver(Options.ConfiguredSafariOptions);
        }

        private IWebDriver CreateChromeDriver()
        {
            var sauceUserName = Environment.GetEnvironmentVariable("SAUCE_USERNAME");
            var sauceAccessKey = Environment.GetEnvironmentVariable("SAUCE_ACCESS_KEY");
            var sauceConfiguration = new Dictionary<string, object>
            {
                ["username"] = sauceUserName,
                ["accessKey"] = sauceAccessKey
            };

            Options.ConfiguredChromeOptions.AddAdditionalOption("sauce:options", sauceConfiguration);
            return DriverImplementation.CreateRemoteWebDriver(Options.ConfiguredChromeOptions);
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

            Options.ConfiguredEdgeOptions.AddAdditionalOption("sauce:options", sauceConfiguration);
            return DriverImplementation.CreateRemoteWebDriver(Options.ConfiguredEdgeOptions);
        }
    }
}