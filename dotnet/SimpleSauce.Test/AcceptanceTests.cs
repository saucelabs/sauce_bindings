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
            _session.Stop(TestContext.CurrentTestOutcome == UnitTestOutcome.Passed);
        }

        [TestMethod]
        public void Start_Default_IsChrome()
        {
            _session = new SauceSession();
            _driver = _session.Start();

            var capabilities = ((RemoteWebDriver)_driver).Capabilities;
            capabilities.GetCapability("browserName").Should().Be("chrome");
        }

        [TestMethod]
        public void RunTestWithChrome()
        {
            _sauceOptions = new SauceOptions().WithChrome();
            _sauceOptions.TestName = nameof(RunTestWithChrome);
            _session = new SauceSession(_sauceOptions);
            _driver = _session.Start();

            var capabilities = ((RemoteWebDriver)_driver).Capabilities;
            capabilities.GetCapability("browserName").Should().Be("chrome");
        }

        [TestMethod]
        public void RunTestWithEdge()
        {
            _sauceOptions = new SauceOptions().WithEdge();
            _sauceOptions.TestName = nameof(RunTestWithEdge);
            _session = new SauceSession(_sauceOptions);
            _driver = _session.Start();

            var capabilities = ((RemoteWebDriver)_driver).Capabilities;
            capabilities.GetCapability("browserName").Should().Be("msedge");
        }

        [TestMethod]
        public void RunTestWithFirefox()
        {
            _sauceOptions = new SauceOptions().WithFirefox();
            _sauceOptions.TestName = nameof(RunTestWithFirefox);
            _session = new SauceSession(_sauceOptions);
            _driver = _session.Start();

            var capabilities = ((RemoteWebDriver)_driver).Capabilities;
            capabilities.GetCapability("browserName").Should().Be("firefox");
        }

        [TestMethod]
        public void RunTestWithSafariDefault()
        {
            _sauceOptions = new SauceOptions().WithSafari();
            _sauceOptions.TestName = nameof(RunTestWithSafariDefault);
            _session = new SauceSession(_sauceOptions);
            _driver = _session.Start();

            var capabilities = ((RemoteWebDriver)_driver).Capabilities;
            capabilities.GetCapability("browserName").Should().Be("Safari");
        }
    }
}