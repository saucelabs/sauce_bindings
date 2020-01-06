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
                return CreateEdgeBrowser();
            if (!string.IsNullOrEmpty(Options.ConfiguredSafariOptions.BrowserVersion))
                return CreateSafariDriver();
            if (!string.IsNullOrEmpty(Options.ConfiguredFirefoxOptions.BrowserVersion))
                return CreateFirefoxDriver();
            return CreateChromeDriver();
        }
         private IWebDriver CreateEdgeBrowser()
        {
            var sauceUserName = Environment.GetEnvironmentVariable("SAUCE_USERNAME");
            var sauceAccessKey = Environment.GetEnvironmentVariable("SAUCE_ACCESS_KEY");
            var sauceConfiguration = new Dictionary<string, object>
            {
                ["username"] = sauceUserName,
                ["accessKey"] = sauceAccessKey
            };

            Options.ConfiguredEdgeOptions.AddAdditionalOption("sauce:options", sauceConfiguration);
            return Driver.CreateRemoteWebDriver(Options.ConfiguredEdgeOptions);
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
            return Driver.CreateRemoteWebDriver(Options.ConfiguredSafariOptions);
        }
        private IWebDriver CreateFirefoxDriver()
        {
            var sauceUserName = Environment.GetEnvironmentVariable("SAUCE_USERNAME");
            var sauceAccessKey = Environment.GetEnvironmentVariable("SAUCE_ACCESS_KEY");
            var sauceConfiguration = new Dictionary<string, object>
            {
                ["username"] = sauceUserName,
                ["accessKey"] = sauceAccessKey
            };

            Options.ConfiguredFirefoxOptions.AddAdditionalOption("sauce:options", sauceConfiguration);
            return Driver.CreateRemoteWebDriver(Options.ConfiguredFirefoxOptions);
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
            return Driver.CreateRemoteWebDriver(Options.ConfiguredChromeOptions);
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