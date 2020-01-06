using System;
using System.Collections.Generic;
using OpenQA.Selenium;

namespace Simple.Sauce
{
    public class SauceSession
    {
        private IWebDriver _driver;
        private IDriverFactory _driverFactory;

        public SauceSession()
        {
            Options = new SauceOptions();
            _driverFactory = new DriverFactory();
        }

        public SauceSession(SauceOptions options)
        {
            Options = options;
            _driverFactory = new DriverFactory();
        }

        public SauceSession(SauceOptions options, IWebDriver driver)
        {
            _driver = driver;
            Options = options;
        }

        public SauceSession(SauceOptions sauceOptions, IDriverFactory driverFactory)
        {
            Options = sauceOptions;
            _driverFactory = driverFactory;
        }

        public SauceOptions Options { get; }

        public IWebDriver Start()
        {
            return _driverFactory.CreateRemoteWebDriver(Options);
        }
      

        public void Stop(bool isPassed)
        {
            if (_driver is null)
                return;
            var script = "sauce:job-result=" + (isPassed ? "passed" : "failed");
            ((IJavaScriptExecutor) _driver).ExecuteScript(script);
            _driver.Quit();
        }
    }
}