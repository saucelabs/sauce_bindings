using System;
using OpenQA.Selenium;
using OpenQA.Selenium.Chrome;
using OpenQA.Selenium.Remote;

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
        
        public SauceOptions Options { get; }
        public Uri SauceUrl { get; set; }
        public string Username { get; set; }
        public string AccessKey { get; set; }
        public SauceDataCenter DataCenter { get; set; } = SauceDataCenter.UsWest;

        public IWebDriver Start()
        {
            _driver = new RemoteWebDriver(GenerateUrl(), Options.ToCapabilities().ToCapabilities());
            return _driver;
        }

        public Uri GenerateUrl()
        {
            return SauceUrl != null ? SauceUrl : new Uri("https://" + SauceUsername() + ":" + SauceAccessKey() + "@" + DataCenter.Value);
        }

        public void Stop(bool isPassed)
        {
            if (_driver is null)
                return;
            var script = "sauce:job-result=" + (isPassed ? "passed" : "failed");
            ((IJavaScriptExecutor) _driver).ExecuteScript(script);
            _driver.Quit();
        }
        
        private string SauceUsername() {
            if (Username != null) {
                return Username;
            } else if (EnvironmentVariable("SAUCE_USERNAME") != null) {
                return EnvironmentVariable("SAUCE_USERNAME");
            } else {
                throw new Exception();
            }
        }

        private string SauceAccessKey() {
            if (AccessKey != null) {
                return AccessKey;
            } else if (EnvironmentVariable("SAUCE_ACCESS_KEY") != null) {
                return EnvironmentVariable("SAUCE_ACCESS_KEY");
            } else {
                throw new Exception();
            }
        }

        private static string EnvironmentVariable(string key) {
            return Environment.GetEnvironmentVariable(key);
        }

    }
}