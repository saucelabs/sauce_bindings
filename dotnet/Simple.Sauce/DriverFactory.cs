using System;
using System.Collections.ObjectModel;
using OpenQA.Selenium;
using OpenQA.Selenium.Remote;

namespace Simple.Sauce
{
    public class DriverFactory : IDriverFactory
    {
        private IWebDriver _driver;

        public IWebDriver CreateRemoteWebDriver(DriverOptions browserOptions)
        {
            _driver = new RemoteWebDriver(new Uri("https://ondemand.saucelabs.com/wd/hub"),
                browserOptions.ToCapabilities(), TimeSpan.FromSeconds(600));
            return _driver;
        }

    }
}