using FluentAssertions;
using Microsoft.VisualStudio.TestTools.UnitTesting;
using OpenQA.Selenium;
using OpenQA.Selenium.Chrome;
using OpenQA.Selenium.Remote;
using SimpleSauce;
using System;
using System.Collections.Generic;

namespace SimpleSauceTests
{
    [TestClass]
    public class AcceptanceTests
    {
        private Dictionary<string, object> sauceOptions;
        private IWebDriver _driver;

        public TestContext TestContext { get; set; }

        [TestInitialize]
        public void SetupTests()
        {
            var sauceUserName = Environment.GetEnvironmentVariable("SAUCE_USERNAME");
            var sauceAccessKey = Environment.GetEnvironmentVariable("SAUCE_ACCESS_KEY");
            sauceOptions = new Dictionary<string, object>
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
            chromeOptions.AddAdditionalCapability("sauce:options", sauceOptions, true);

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
        [Ignore("Throwing Sauce infrastructure error, not sure why")]
        public void RunTestWithEdge15()
        {
            sauceOptions.Add("name", TestContext.TestName);
            var options = new SauceOptions();
            options.WithEdge(EdgeVersion._15);
            var session = new SauceSession(options);
            var driver = session.Start();
            var capabilities = ((RemoteWebDriver)driver).Capabilities;
            capabilities.GetCapability("browserName").Should().Be("MicrosoftEdge");
        }
    }
}
