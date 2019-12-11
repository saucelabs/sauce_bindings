using System;
using System.Collections.Generic;
using FluentAssertions;
using Microsoft.VisualStudio.TestTools.UnitTesting;
using OpenQA.Selenium;
using OpenQA.Selenium.Chrome;
using OpenQA.Selenium.Remote;

namespace SimpleSauce.Test
{
    [TestClass]
    [TestCategory("Acceptance")]
    public class AcceptanceTests
    {
        private Dictionary<string, object> _sauceOptions;
        private IWebDriver _driver;

        public TestContext TestContext { get; set; }

        [TestInitialize]
        public void SetupTests()
        {
            var sauceUserName = Environment.GetEnvironmentVariable("SAUCE_USERNAME");
            var sauceAccessKey = Environment.GetEnvironmentVariable("SAUCE_ACCESS_KEY");
            _sauceOptions = new Dictionary<string, object>
            {
                ["username"] = sauceUserName,
                ["accessKey"] = sauceAccessKey
            };
        }
        [TestCleanup]
        public void CleanUpAfterEveryTestMethod()
        {
            if (_driver != null)
            {
                var passed = TestContext.CurrentTestOutcome == UnitTestOutcome.Passed;
                ((IJavaScriptExecutor)_driver).ExecuteScript("sauce:job-result=" + (passed ? "passed" : "failed"));
                _driver?.Quit();
            }
        }
        [TestMethod]
        public void WithoutSauceSession_ShouldWork()
        {
            var chromeOptions = new ChromeOptions
            {
                BrowserVersion = "latest",
                PlatformName = "Windows 10",
                UseSpecCompliantProtocol = true
            };
            chromeOptions.AddAdditionalOption("sauce:options", _sauceOptions);

            _driver = new RemoteWebDriver(new Uri("https://ondemand.saucelabs.com/wd/hub"),
                chromeOptions.ToCapabilities(), TimeSpan.FromSeconds(600));
            _driver.Navigate().GoToUrl("https://www.google.com");
            ((RemoteWebDriver)_driver).SessionId.Should().NotBeNull();
        }

        [TestMethod]
        public void Start_Default_IsChrome()
        {
            var session = new SauceSession();
            var driver = session.Start();
            var capabilities = ((RemoteWebDriver)driver).Capabilities;
            capabilities.GetCapability("browserName").Should().Be("chrome");
        }
        [TestMethod]
        public void RunTestWithEdge()
        {
            var options = new SauceOptions();
            options.WithEdge();
            var session = new SauceSession(options);
            var driver = session.Start();
            var capabilities = ((RemoteWebDriver)driver).Capabilities;
            capabilities.GetCapability("browserName").Should().Be("MicrosoftEdge");
        }
        [TestMethod]
        [Ignore("Getting an infrastructure error")]
        public void RunTestWithEdge15()
        {
            var options = new SauceOptions();
            options.WithEdge(EdgeVersion._15);
            var session = new SauceSession(options);
            var driver = session.Start();
            var capabilities = ((RemoteWebDriver)driver).Capabilities;
            capabilities.GetCapability("browserName").Should().Be("MicrosoftEdge");
        }
        [TestMethod]
        public void RunTestWithSafariDefault()
        {
            var options = new SauceOptions();
            options.WithSafari();
            var session = new SauceSession(options);
            var driver = session.Start();
            var capabilities = ((RemoteWebDriver)driver).Capabilities;
            capabilities.GetCapability("browserName").Should().Be("Safari");
        }
    }
}
