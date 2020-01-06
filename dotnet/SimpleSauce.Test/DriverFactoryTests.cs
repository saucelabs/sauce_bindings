using Microsoft.VisualStudio.TestTools.UnitTesting;
using Newtonsoft.Json;
using OpenQA.Selenium;
using OpenQA.Selenium.Chrome;
using Simple.Sauce;

namespace SimpleSauce.Test
{
    [TestClass]
    public class DriverFactoryTests : BaseTest
    {
        private SauceOptions _browserOptions;
        [TestInitialize]
        public void Setup()
        {
            _browserOptions = new SauceOptions();
        }

        [TestMethod]
        public void CreateRemoteWebDriver_WithEmptyChromeOptions_ThrowsException()
        {
            var driverFactory = new DriverFactory();
            Assert.ThrowsException<WebDriverException>(
                () => driverFactory.CreateRemoteWebDriver(_browserOptions));
        }
        [TestMethod]
        public void CreateRemoteWebDriver_DefaultOptions_SetsSauceOptionsTag()
        {
            var driverFactory = new DriverFactory();
            driverFactory.CreateRemoteWebDriver(_browserOptions);

            var browserOptionsSetInSauceJson = _browserOptions.ConfiguredChromeOptions.ToString();
            var browserOptionsSetInSauce = DeserializeToObject(browserOptionsSetInSauceJson);
            AssertUsernameAndAccessKeyExist(browserOptionsSetInSauce);
        }
        private static Root DeserializeToObject(string browserOptions)
        {
            return JsonConvert.DeserializeObject<Root>(browserOptions);
        }
        private static void AssertUsernameAndAccessKeyExist(Root configuredSauceOptions)
        {
            Assert.IsNotNull(configuredSauceOptions.SauceOptions.Username);
            Assert.IsNotNull(configuredSauceOptions.SauceOptions.AccessKey);
        }
    }
}
