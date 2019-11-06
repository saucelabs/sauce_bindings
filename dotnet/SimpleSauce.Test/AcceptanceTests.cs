using Microsoft.VisualStudio.TestTools.UnitTesting;
using FluentAssertions;
using SimpleSauce;
using Moq;
using OpenQA.Selenium;
using OpenQA.Selenium.Remote;
using OpenQA.Selenium.Chrome;
using System;
using System.Collections.Generic;

namespace SimpleSauceTests
{
    [TestClass]
    public class AcceptanceTests
    {
        private string sauceUserName;
        private string sauceAccessKey;
        private Dictionary<string, object> sauceOptions;
        private IWebDriver _driver;

        public TestContext TestContext { get; set; }

        [TestInitialize]
        public void SetupTests()
        {
            //TODO please supply your Sauce Labs user name in an environment variable
            sauceUserName = Environment.GetEnvironmentVariable("SAUCE_USERNAME", EnvironmentVariableTarget.User);
            //TODO please supply your own Sauce Labs access Key in an environment variable
            sauceAccessKey = Environment.GetEnvironmentVariable("SAUCE_ACCESS_KEY", EnvironmentVariableTarget.User);
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
        public void ShouldReturnObject()
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
    }
}
