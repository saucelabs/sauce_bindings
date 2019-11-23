using OpenQA.Selenium;
using OpenQA.Selenium.Chrome;
using OpenQA.Selenium.Edge;
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
        public EdgeOptions EdgeOptions { get; set; }

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
            if (Options.ConfiguredEdgeOptions != null)
                return CreateEdgeBrowser();
            else if (Options.ConfiguredChromeOptions != null)
                return CreateChromeDriver();
            return DriverImplementation.CreateRemoteWebDriver(ChromeOptions);
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