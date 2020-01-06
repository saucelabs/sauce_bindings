using System;
using System.Collections.Generic;
using OpenQA.Selenium;

namespace Simple.Sauce
{
    public class SauceSession
    {
        private IWebDriver _driver;

        public SauceSession()
        {
            Options = new SauceOptions();
        }

        public SauceSession(SauceOptions options)
        {
            Options = options;
        }

        public SauceSession(SauceOptions options, IWebDriver driver)
        {
            _driver = driver;
            Options = options;
        }

        public SauceOptions Options { get; }

        public IWebDriver Start()
        {
            //TODO this should probably move to the DriverFactory and just let it handle the 
            //construction of the drivers
            if (!string.IsNullOrEmpty(Options.ConfiguredEdgeOptions.BrowserVersion))
                return Options.CreateEdgeBrowser();
            if (!string.IsNullOrEmpty(Options.ConfiguredSafariOptions.BrowserVersion))
                return Options.CreateSafariDriver();
            if (!string.IsNullOrEmpty(Options.ConfiguredFirefoxOptions.BrowserVersion))
                return Options.CreateFirefoxDriver();
            return Options.CreateChromeDriver();
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