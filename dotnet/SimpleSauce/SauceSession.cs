using OpenQA.Selenium;
using OpenQA.Selenium.Chrome;
using System;
using System.Collections.Generic;

namespace SimpleSauce
{
    public class SauceSession
    {
        public ChromeOptions ChromeOptions { get; private set; }

        private readonly IRemoteDriver _remoteDriverManager;
        private SauceOptions options;

        public SauceSession()
        {
            _remoteDriverManager = new ConcreteRemoteWebDriver();
        }
        public SauceSession(IRemoteDriver driverManager)
        {
            _remoteDriverManager = driverManager;
        }

        public SauceSession(SauceOptions options)
        {
            this.options = options;
        }

        public DataCenter DataCenter { get; set; } = DataCenter.UsWest;

        public IWebDriver Start()
        {
            var sauceUserName = Environment.GetEnvironmentVariable("SAUCE_USERNAME", EnvironmentVariableTarget.User);
            var sauceAccessKey = Environment.GetEnvironmentVariable("SAUCE_ACCESS_KEY", EnvironmentVariableTarget.User);
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
            return _remoteDriverManager.CreateRemoteWebDriver(ChromeOptions);
        }
    }
}