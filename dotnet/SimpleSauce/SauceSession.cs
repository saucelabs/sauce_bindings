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

        public RemoteWebDriver Start()
        {
            //TODO please supply your Sauce Labs user name in an environment variable
            sauceUserName = Environment.GetEnvironmentVariable("SAUCE_USERNAME", EnvironmentVariableTarget.User);
            //TODO please supply your own Sauce Labs access Key in an environment variable
            sauceAccessKey = Environment.GetEnvironmentVariable("SAUCE_ACCESS_KEY", EnvironmentVariableTarget.User);
            sauceOptions = new Dictionary<string, object>
            {
                ["username"] = sauceUserName,
                ["accessKey"] = sauceAccessKey
            };

            var chromeOptions = new ChromeOptions
            {
                BrowserVersion = "latest",
                PlatformName = "Windows 10",
                UseSpecCompliantProtocol = true
            };
            chromeOptions.AddAdditionalCapability("sauce:options", sauceOptions, true);
            _driver = remoteDriverManager.CreateRemoteWebDriver(chromeOptions);
            return _driver;
        }
    }
}