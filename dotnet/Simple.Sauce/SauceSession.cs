using OpenQA.Selenium;

namespace Simple.Sauce
{
    public class SauceSession
    {
        private IWebDriver _driver;

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

        public SauceSession(IWebDriver driver)
        {
            _driver = driver;
        }

        public DataCenter DataCenter { get; set; } = DataCenter.UsWest;
        public SauceOptions Options { get; }

        public IDriverFactory DriverFactory { get; set; }

        public IWebDriver Start()
        {
            //TODO this should probably move to the DriverFactory and just let it handle the 
            //construction of the drivers
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
            ((IJavaScriptExecutor) _driver).ExecuteScript(script);
            _driver.Quit();
        }
    }
}