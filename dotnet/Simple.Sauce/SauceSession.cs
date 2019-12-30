using System;
using System.Collections.Generic;
using OpenQA.Selenium;
using OpenQA.Selenium.Chrome;

namespace Simple.Sauce
{
    public class SauceSession
    {
        public SauceSession()
        {
            DriverFactory = new DriverFactory();
            Options = new SauceOptions();
        }

        public SauceSession(IDriverFactory driverFactory)
        {
            DriverFactory = driverFactory;
            Options = new SauceOptions();
        }

        public SauceSession(SauceOptions options)
        {
            Options = options;
            DriverFactory = new DriverFactory();
        }

        public SauceSession(SauceOptions options, IDriverFactory driverFactory)
        {
            Options = options;
            DriverFactory = driverFactory;
        }

        public ChromeOptions ChromeOptions { get; private set; }
        public DataCenter DataCenter { get; set; } = DataCenter.UsWest;
        public SauceOptions Options { get; }

        public IDriverFactory DriverFactory { get; set; }
        private IWebDriver _driver;

        public SauceSession(IDriverFactory factory, IWebDriver driver)
        {
            DriverFactory = factory;
            _driver = driver;
            Options = new SauceOptions();
        }

        public IWebDriver Start()
        {
            if (Options.ConfiguredEdgeOptions != null)
            {
                _driver = Options.CreateEdgeBrowser();
                return _driver;
            }
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
            _driver = DriverFactory.CreateRemoteWebDriver(Options.ConfiguredSafariOptions);
            return _driver;
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
            _driver = DriverFactory.CreateRemoteWebDriver(Options.ConfiguredChromeOptions);
            return _driver;
        }



        public void Stop(bool isPassed)
        {
            if (_driver is null)
                return;
            var script = "sauce:job-result=" + (isPassed ? "passed" : "failed");
            ((IJavaScriptExecutor)_driver).ExecuteScript(script);
            _driver.Quit();
        }
    }
}