using System;
using System.Collections.Generic;
using FluentAssertions;
using Microsoft.VisualStudio.TestTools.UnitTesting;
using OpenQA.Selenium;
using OpenQA.Selenium.Chrome;
using OpenQA.Selenium.Remote;
using Sauce.Bindings;

namespace SauceBindings.Test
{
    [TestClass]
    [TestCategory("Acceptance")]
    public class BaselineSeleniumAcceptanceTests
    {
        public TestContext TestContext { get; set; }
        public RemoteWebDriver _driver { get; set; }

        [TestInitialize]
        public void Setup()
        {
            var sauceUserName = Environment.GetEnvironmentVariable("SAUCE_USERNAME");
            var sauceAccessKey = Environment.GetEnvironmentVariable("SAUCE_ACCESS_KEY");
            var sauceOptions = new Dictionary<string, object>
            {
                ["username"] = sauceUserName,
                ["accessKey"] = sauceAccessKey,
                ["name"] = nameof(WithoutSauceSession_ShouldWork)
            };

            var chromeOptions = new ChromeOptions
            {
                BrowserVersion = "latest",
                PlatformName = "Windows 10",
                UseSpecCompliantProtocol = true
            };

            chromeOptions.AddAdditionalOption("sauce:options", sauceOptions);

            _driver = new RemoteWebDriver(new Uri("https://ondemand.us-west-1.saucelabs.com/wd/hub"),
                chromeOptions.ToCapabilities(), TimeSpan.FromSeconds(30));
        }

        [TestCleanup]
        public void Cleanup()
        {
            bool passed = TestContext.CurrentTestOutcome == UnitTestOutcome.Passed;

            ((IJavaScriptExecutor)_driver).ExecuteScript("sauce:job-result=" + (passed ? "passed" : "failed"));
            _driver?.Quit();
        }

        [TestMethod]
        public void WithoutSauceSession_ShouldWork()
        {
            _driver.Navigate().GoToUrl(new Uri("https://www.google.com"));
            _driver.SessionId.Should().NotBeNull();
        }
    }
}
