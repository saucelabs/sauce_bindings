using Microsoft.VisualStudio.TestTools.UnitTesting;
using OpenQA.Selenium;
using OpenQA.Selenium.Chrome;
using Simple.Sauce;

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
