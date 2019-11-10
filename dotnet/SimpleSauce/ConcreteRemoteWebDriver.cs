using OpenQA.Selenium;
using OpenQA.Selenium.Chrome;
using OpenQA.Selenium.Remote;
using System;
using System.Collections.Generic;
using System.Text;

namespace SimpleSauce
{
    class ConcreteRemoteWebDriver : IRemoteDriver
    {
        public IWebDriver CreateRemoteWebDriver(ChromeOptions chromeOptions)
        {
            return new RemoteWebDriver(new Uri("https://ondemand.saucelabs.com/wd/hub"),
                chromeOptions.ToCapabilities(), TimeSpan.FromSeconds(600));
        }
    }
}
