using Microsoft.VisualStudio.TestTools.UnitTesting;
using OpenQA.Selenium;
using OpenQA.Selenium.Chrome;

namespace Sauce.Bindings.Test
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
