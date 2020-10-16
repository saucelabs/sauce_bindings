using FluentAssertions;
using Microsoft.VisualStudio.TestTools.UnitTesting;
using OpenQA.Selenium;
using OpenQA.Selenium.Remote;
using Sauce.Bindings;

namespace SauceBindings.Test
{
    [TestClass]
    [TestCategory("Acceptance")]
    public class AcceptanceTests
    {
        private SauceOptions _sauceOptions;
        private IWebDriver _driver;
        private SauceSession _session;

        public TestContext TestContext { get; set; }
        [TestCleanup]
        public void Cleanup()
        {
            _session.Stop(true);
        }

        [TestMethod]
        public void ChromeIsDefaultBrowser()
        {
            _session = new SauceSession();
            _driver = _session.Start();
            var capabilities = ((RemoteWebDriver)_driver).Capabilities;
            capabilities.GetCapability("browserName").Should().Be("chrome");
        }
        [TestMethod]
        public void RunTestWithFirefox()
        {
            _sauceOptions = new SauceOptions
            {
                BrowserName = Browser.Firefox
            };
            _session = new SauceSession(_sauceOptions);
            _driver = _session.Start();
            ((IJavaScriptExecutor)_driver).ExecuteScript("sauce:job-name=" + TestContext.TestName);
            var capabilities = ((RemoteWebDriver)_driver).Capabilities;
            capabilities.GetCapability("browserName").Should().Be("firefox");
        }
    }
}
