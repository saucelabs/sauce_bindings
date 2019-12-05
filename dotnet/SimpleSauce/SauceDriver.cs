using System;
using OpenQA.Selenium;
using OpenQA.Selenium.Remote;

namespace SimpleSauce
{
    public class SauceDriver : ISauceRemoteDriver
    {
        public IWebDriver CreateRemoteWebDriver(DriverOptions browserOptions)
        {
            return new RemoteWebDriver(new Uri("https://ondemand.saucelabs.com/wd/hub"),
                browserOptions.ToCapabilities(), TimeSpan.FromSeconds(600));
        }
    }
}