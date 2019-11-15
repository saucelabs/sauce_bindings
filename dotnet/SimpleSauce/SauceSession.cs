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
        public EdgeOptions EdgeOptions { get; set; }
        public SauceOptions Options => _options;

        private readonly IRemoteDriver _driverImplementation;
        private readonly SauceOptions _options;

        public SauceSession()
        {
            _driverImplementation = new ConcreteRemoteWebDriver();
            _options = new SauceOptions();
        }
        public SauceSession(IRemoteDriver driver)
        {
            _driverImplementation = driver;
            _options = new SauceOptions();
        }

        public SauceSession(SauceOptions options)
        {
            this._options = options;
        }

        public SauceSession(SauceOptions options, IRemoteDriver driver)
        {
            this._options = options;
            _driverImplementation = driver;
        }

        public IWebDriver Start()
        {
            if (_options.EdgeOptions != null)
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
            return _driverImplementation.CreateRemoteWebDriver(ChromeOptions);
        }

        private IWebDriver CreateEdgeBrowser()
        {
            var sauceUserName = Environment.GetEnvironmentVariable("SAUCE_USERNAME");
            var sauceAccessKey = Environment.GetEnvironmentVariable("SAUCE_ACCESS_KEY");
            var sauceOptions = new Dictionary<string, object>
            {
                ["username"] = sauceUserName,
                ["accessKey"] = sauceAccessKey
            };

            _options.EdgeOptions.AddAdditionalCapability("sauce:options", sauceOptions);
            return _driverImplementation.CreateRemoteWebDriver(ChromeOptions);
        }
    }
}