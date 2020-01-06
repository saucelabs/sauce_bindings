using FluentAssertions;
using Microsoft.VisualStudio.TestTools.UnitTesting;
using Moq;
using Newtonsoft.Json;
using OpenQA.Selenium;
using OpenQA.Selenium.Chrome;
using OpenQA.Selenium.Edge;
using OpenQA.Selenium.Safari;
using Simple.Sauce;

namespace SimpleSauce.Test
{
    [TestClass]
    public class SauceSessionTests : BaseTest
    {
        private Mock<IDriverFactory> _driverFactory;

        [TestInitialize]
        public void Setup()
        {
            _driverFactory = new Mock<IDriverFactory>();
            SauceOptions = new SauceOptions(_driverFactory.Object);
            SauceSession = new SauceSession(SauceOptions);
        }
        [TestMethod]
        public void SauceSession_DefaultConstructor_OptionsInitialized()
        {
            Assert.IsNotNull(SauceSession.Options);
        }

        [TestMethod]
        public void Start_Default_IsChrome()
        {
            SauceSession.Start();

            SauceSession.Options.ConfiguredChromeOptions.Should().NotBeNull();
        }
        [TestMethod]
        public void Start_Default_SetsSauceOptionsTag()
        {
            SauceSession.Start();

            var browserOptionsSetInSauceJson = SauceSession.Options.ConfiguredChromeOptions.ToString();
            var browserOptionsSetInSauce = DeserializeToObject(browserOptionsSetInSauceJson);
            AssertUsernameAndAccessKeyExist(browserOptionsSetInSauce);
        }
        [TestMethod]
        public void Start_WithEdge_SetsUsernameAndAccessKey()
        {
            SauceOptions.WithEdge();
            SauceSession = new SauceSession(SauceOptions);

            SauceSession.Start();

            var browserOptionsSetInSauceJson = SauceSession.Options.ConfiguredEdgeOptions.ToString();
            var browserOptionsSetInSauce = DeserializeToObject(browserOptionsSetInSauceJson);
            AssertUsernameAndAccessKeyExist(browserOptionsSetInSauce);
        }
        [TestMethod]
        public void Start_WithChrome_SetsUsernameAndAccessKey()
        {
            SauceOptions.WithChrome();
            SauceSession = new SauceSession(SauceOptions);

            SauceSession.Start();

            var browserOptionsSetInSauceJson = SauceSession.Options.ConfiguredChromeOptions.ToString();
            var browserOptionsSetInSauce = DeserializeToObject(browserOptionsSetInSauceJson);
            AssertUsernameAndAccessKeyExist(browserOptionsSetInSauce);
        }
        [TestMethod]
        public void Start_WithChromeVersionSet_CreatesCorrectDriver()
        {
            SauceOptions.WithChrome("72");
            SauceSession = new SauceSession(SauceOptions);

            SauceSession.Start();

            SauceSession.Options.ConfiguredChromeOptions.BrowserVersion.Should().Be("72");
        }
        [TestMethod]
        public void Start_WithSafari_SetsUsernameAndAccessKey()
        {
            SauceOptions.WithSafari();
            SauceSession = new SauceSession(SauceOptions);

            SauceSession.Start();

            var browserOptionsSetInSauceJson = SauceSession.Options.ConfiguredSafariOptions.ToString();
            var browserOptionsSetInSauce = DeserializeToObject(browserOptionsSetInSauceJson);
            AssertUsernameAndAccessKeyExist(browserOptionsSetInSauce);
        }

        private static Root DeserializeToObject(string browserOptions)
        {
            return JsonConvert.DeserializeObject<Root>(browserOptions);
        }
        private static void AssertUsernameAndAccessKeyExist(Root configuredSauceOptions)
        {
            configuredSauceOptions.SauceOptions.Username.Should().NotBeNullOrEmpty();
            configuredSauceOptions.SauceOptions.AccessKey.Should().NotBeNullOrEmpty();
        }
        [TestMethod]
        public void Start_WithSafari_CreatesDriverWithSafariOptions()
        {
            SauceOptions.WithSafari();
            SauceSession = new SauceSession(SauceOptions);

            SauceSession.Start();

            _driverFactory.Verify(f => f.CreateRemoteWebDriver(It.IsAny<SafariOptions>()));
        }
        [TestMethod]
        public void Start_WithEdge_CreatesDriverWithEdgeOptions()
        {
            SauceOptions.WithEdge();
            SauceSession = new SauceSession(SauceOptions);

            SauceSession.Start();

            _driverFactory.Verify(f => f.CreateRemoteWebDriver(It.IsAny<EdgeOptions>()));
        }
        [TestMethod]
        public void Start_WithChrome_CreatesDriverWithChromeOptions()
        {
            SauceOptions.WithChrome();
            SauceSession = new SauceSession(SauceOptions);

            SauceSession.Start();

            _driverFactory.Verify(f => f.CreateRemoteWebDriver(It.IsAny<ChromeOptions>()));
        }
        [TestMethod]
        [Ignore("need to fix")]
        public void Stop_CallsQuit()
        {
            var driver = new Mock<IWebDriver>();
            var factory = new Mock<IDriverFactory>();
            factory.Setup(f => f.CreateRemoteWebDriver(
                It.IsAny<DriverOptions>())).Returns(It.IsAny<ChromeDriver>());
            var options = new SauceOptions(factory.Object);

            SauceSession = new SauceSession(options, driver.Object);
            SauceSession.Start();
            SauceSession.Stop(true);
            driver.Verify(d => d.Quit(), Times.Once);
        }
        [TestMethod]
        [Ignore("need to fix")]

        public void Stop_TestPassed_UpdatesTestStatus()
        {
            SauceSession = new SauceSession();
            SauceSession.Start();
            SauceSession.Stop(true);
            _driverFactory.Verify(driver => ((IJavaScriptExecutor)driver).ExecuteScript("sauce:job-result=passed"), Times.Exactly(1));
        }
        [TestMethod]
        [Ignore("need to fix")]

        public void Stop_TestFailed_UpdatesTestStatus()
        {
            SauceSession = new SauceSession();
            SauceSession.Start();
            SauceSession.Stop(false);
            _driverFactory.Verify(driver => ((IJavaScriptExecutor)driver).ExecuteScript("sauce:job-result=failed"), Times.Exactly(1));
        }
        [TestMethod]
        public void Start_WithFirefox_SetsUsernameAndAccessKey()
        {
            SauceOptions = new SauceOptions();
            SauceOptions.WithFirefox();
            SauceSession = new SauceSession(SauceOptions);

            SauceSession.Start();

            var browserOptionsSetInSauceJson = SauceSession.Options.ConfiguredFirefoxOptions.ToString();
            var browserOptionsSetInSauce = DeserializeToObject(browserOptionsSetInSauceJson);
            AssertUsernameAndAccessKeyExist(browserOptionsSetInSauce);
        }
    }
    public class Root
    {
        public string BrowserName { get; set; }
        public string BrowserVersion { get; set; }
        public string PlatformName { get; set; }

        [JsonProperty("sauce:options")]
        public Options SauceOptions { get; set; }
    }

    public class Options
    {
        public string Username { get; set; }
        public string AccessKey { get; set; }
    }
}
