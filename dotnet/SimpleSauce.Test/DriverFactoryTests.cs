using Microsoft.VisualStudio.TestTools.UnitTesting;
using OpenQA.Selenium;
using OpenQA.Selenium.Chrome;
using Simple.Sauce;

namespace SimpleSauce.Test
{
    [TestClass]
    public class DriverFactoryTests : BaseTest
    {
        [TestMethod]
        public void CreateRemoteWebDriver_WithEmptyChromeOptions_ThrowsException()
        {
            var browserOptions = new ChromeOptions();
            var driverFactory = new DriverFactory();
            Assert.ThrowsException<WebDriverException>(
                () => driverFactory.CreateRemoteWebDriver(browserOptions));
        }
    }
}
