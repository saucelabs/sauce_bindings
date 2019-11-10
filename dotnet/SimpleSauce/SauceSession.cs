using OpenQA.Selenium;
using OpenQA.Selenium.Chrome;
using OpenQA.Selenium.Remote;
using System;
using System.Collections.Generic;

namespace SimpleSauce
{
    public class SauceSession
    {
        private RemoteWebDriver _driver;
        private string sauceUserName;
        private string sauceAccessKey;
        private Dictionary<string, object> sauceOptions;

        public ChromeOptions ChromeOptions { get; private set; }

        private IRemoteDriver remoteDriverManager;

        public SauceSession()
        {
            remoteDriverManager = new ConcreteRemoteWebDriver();
        }
        public SauceSession(IRemoteDriver driverManager)
        {
            remoteDriverManager = driverManager;
        }
        public DataCenter DataCenter { get; set; } = DataCenter.UsWest;

        public IWebDriver Start()
        {
            sauceUserName = Environment.GetEnvironmentVariable("SAUCE_USERNAME", EnvironmentVariableTarget.User);
            sauceAccessKey = Environment.GetEnvironmentVariable("SAUCE_ACCESS_KEY", EnvironmentVariableTarget.User);
            sauceOptions = new Dictionary<string, object>
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
            return remoteDriverManager.CreateRemoteWebDriver(ChromeOptions);
        }
    }
}