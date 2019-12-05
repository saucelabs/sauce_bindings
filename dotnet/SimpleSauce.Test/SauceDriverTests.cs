using FluentAssertions;
using Microsoft.VisualStudio.TestTools.UnitTesting;
using Moq;
using OpenQA.Selenium;
using OpenQA.Selenium.Chrome;
using OpenQA.Selenium.Edge;

namespace SimpleSauce.Test
{
    [TestClass]
    public class SauceDriverTests : BaseTest
    {
        [TestMethod]
        public void CreateRemoteWebDriver_WithEmptyChromeOptions_ThrowsException()
        {
            var browserOptions = new ChromeOptions();
            var sauceDriver = new SauceDriver();
            Assert.ThrowsException<WebDriverException>(
                () => sauceDriver.CreateRemoteWebDriver(browserOptions));
        }
    }
}
