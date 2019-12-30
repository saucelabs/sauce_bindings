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
        }
        [TestMethod]
        public void SauceSession_OptionsPassedIn_SetsConcreteDriver()
        {
            SauceOptions = new SauceOptions();
            SauceSession = new SauceSession(SauceOptions);
            SauceSession.DriverFactory.Should().BeOfType(typeof(DriverFactory));
        }
        [TestMethod]
        public void SauceSession_NoConstructorParam_OptionsInitialized()
        {
            SauceSession = new SauceSession();
            Assert.IsNotNull(SauceSession.Options);
        }
        [TestMethod]
        public void GetDataCenter_Default_IsWest()
        {
            SauceSession = new SauceSession();
            SauceSession.DataCenter.Should().BeEquivalentTo(DataCenter.UsWest);
        }
        [TestMethod]
        public void Start_Default_IsChrome()
        {
            SauceSession = new SauceSession(_driverFactory.Object);

            SauceSession.Start();

            SauceSession.Options.ConfiguredChromeOptions.Should().NotBeNull();
        }
        [TestMethod]
        public void Start_Default_SetsSauceOptionsTag()
        {
            SauceSession = new SauceSession(_driverFactory.Object);

            SauceSession.Start();

            var browserOptionsSetInSauceJson = SauceSession.Options.ConfiguredChromeOptions.ToString();
            var browserOptionsSetInSauce = DeserializeToObject(browserOptionsSetInSauceJson);
            AssertUsernameAndAccessKeyExist(browserOptionsSetInSauce);
        }
        [TestMethod]
        public void Start_WithEdge_SetsUsernameAndAccessKey()
        {
            SauceOptions = new SauceOptions();
            SauceOptions.WithEdge();
            SauceSession = new SauceSession(SauceOptions, _driverFactory.Object);

            SauceSession.Start();

            var browserOptionsSetInSauceJson = SauceSession.Options.ConfiguredEdgeOptions.ToString();
            var browserOptionsSetInSauce = DeserializeToObject(browserOptionsSetInSauceJson);
            AssertUsernameAndAccessKeyExist(browserOptionsSetInSauce);
        }
        [TestMethod]
        public void Start_WithChrome_SetsUsernameAndAccessKey()
        {
            SauceOptions = new SauceOptions();
            SauceOptions.WithChrome();
            SauceSession = new SauceSession(SauceOptions, _driverFactory.Object);

            SauceSession.Start();

            var browserOptionsSetInSauceJson = SauceSession.Options.ConfiguredChromeOptions.ToString();
            var browserOptionsSetInSauce = DeserializeToObject(browserOptionsSetInSauceJson);
            AssertUsernameAndAccessKeyExist(browserOptionsSetInSauce);
        }
        [TestMethod]
        public void Start_WithChromeVersionSet_CreatesCorrectDriver()
        {
            SauceOptions = new SauceOptions();
            SauceOptions.WithChrome("72");
            SauceSession = new SauceSession(SauceOptions, _driverFactory.Object);

            SauceSession.Start();

            SauceSession.Options.ConfiguredChromeOptions.BrowserVersion.Should().Be("72");
        }
        [TestMethod]
        public void Start_WithSafari_SetsUsernameAndAccessKey()
        {
            SauceOptions = new SauceOptions();
            SauceOptions.WithSafari();
            SauceSession = new SauceSession(SauceOptions, _driverFactory.Object);

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
            SauceOptions = new SauceOptions(_driverFactory.Object);
            SauceOptions.WithSafari();
            SauceSession = new SauceSession(SauceOptions);

            SauceSession.Start();

            _driverFactory.Verify(f => f.CreateRemoteWebDriver(It.IsAny<SafariOptions>()));
        }
        [TestMethod]
        public void Start_WithEdge_CreatesDriverWithEdgeOptions()
        {
            SauceOptions = new SauceOptions(_driverFactory.Object);
            SauceOptions.WithEdge();
            SauceSession = new SauceSession(SauceOptions);

            SauceSession.Start();

            _driverFactory.Verify(f => f.CreateRemoteWebDriver(It.IsAny<EdgeOptions>()));
        }
        [TestMethod]
        public void Start_WithChrome_CreatesDriverWithChromeOptions()
        {
            SauceOptions = new SauceOptions(_driverFactory.Object);
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
            driver.Setup(d => d.Quit());
            //_driverFactory.Setup(
            //    d => d.CreateRemoteWebDriver(new SauceOptions().ConfiguredChromeOptions)).Returns(It.IsAny<IWebDriver>());
            _driverFactory.Setup(
                d => d.CreateRemoteWebDriver(It.IsAny<DriverOptions>())).Returns(It.IsAny<IWebDriver>());
            SauceSession = new SauceSession(driver.Object);
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
