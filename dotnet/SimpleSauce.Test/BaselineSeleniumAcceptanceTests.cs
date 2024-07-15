using FluentAssertions;
using Microsoft.VisualStudio.TestTools.UnitTesting;
using OpenQA.Selenium;
using OpenQA.Selenium.Chrome;
using OpenQA.Selenium.Remote;
using System;
using System.Collections.Generic;

namespace SauceBindings.Test
{
    [TestClass]
    [TestCategory("Acceptance")]
    public class BaselineSeleniumAcceptanceTests
    {
        public TestContext TestContext { get; set; }

        [TestMethod]
        public void WithoutSauceSession_ShouldWork()
        {
            var sauceUserName = Environment.GetEnvironmentVariable("SAUCE_USERNAME");
            var sauceAccessKey = Environment.GetEnvironmentVariable("SAUCE_ACCESS_KEY");
            var sauceOptions = new Dictionary<string, object>
            {
                ["username"] = sauceUserName,
                ["accessKey"] = sauceAccessKey
            };
            var chromeOptions = new ChromeOptions
            {
                BrowserVersion = "latest",
                PlatformName = "Windows 10",
                UseSpecCompliantProtocol = true
            };
            chromeOptions.AddAdditionalOption("sauce:options", sauceOptions);

            var driver = new RemoteWebDriver(new Uri("https://ondemand.us-west-1.saucelabs.com/wd/hub"),
                chromeOptions.ToCapabilities(), TimeSpan.FromSeconds(30));
            driver.Navigate().GoToUrl("https://www.google.com");
            driver.SessionId.Should().NotBeNull();

            var passed = TestContext.CurrentTestOutcome == UnitTestOutcome.Passed;
            ((IJavaScriptExecutor)driver).ExecuteScript("sauce:job-result=" + (passed ? "passed" : "failed"));
            driver?.Quit();
        }
    }
}
