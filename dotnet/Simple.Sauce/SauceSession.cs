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

        public SauceSession(SauceOptions options)
        {
            Options = options;
            DriverFactory = new DriverFactory();
        }

        public DataCenter DataCenter { get; set; } = DataCenter.UsWest;
        public SauceOptions Options { get; }

        public IDriverFactory DriverFactory { get; set; }
        private IWebDriver _driver;

        public SauceSession(IWebDriver driver)
        {
            _driver = driver;
        }

        public IWebDriver Start()
        {
            if (Options.ConfiguredEdgeOptions != null)
            {
                _driver = Options.CreateEdgeBrowser();
                return _driver;
            }
            if (Options.ConfiguredSafariOptions != null)
            {
                _driver = Options.CreateSafariDriver();
                return _driver;
            }
            return Options.CreateChromeDriver();
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