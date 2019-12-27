using System;
using System.Reflection;
using FluentAssertions;
using Microsoft.VisualStudio.TestTools.UnitTesting;
using Moq;
using Newtonsoft.Json;
using Simple.Sauce;

namespace SimpleSauce.Test
{
    [TestClass]
    public class SauceSessionTests : BaseTest
    {
        private Mock<ISauceRemoteDriver> _dummyDriver;

        [TestInitialize]
        public void Setup()
        {
            _dummyDriver = new Mock<ISauceRemoteDriver>();
        }
        [TestMethod]
        public void SauceSession_OptionsPassedIn_SetsConcreteDriver()
        {
            SauceOptions = new SauceOptions();
            SauceSession = new SauceSession(SauceOptions);
            SauceSession.Driver.Should().BeOfType(typeof(SauceDriver));
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
            SauceSession = new SauceSession(_dummyDriver.Object);

            SauceSession.Start();

            SauceSession.Options.ConfiguredChromeOptions.Should().NotBeNull();
        }
        [TestMethod]
        public void Start_Default_SetsSauceOptionsTag()
        {
            SauceSession = new SauceSession(_dummyDriver.Object);

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
            SauceSession = new SauceSession(SauceOptions, _dummyDriver.Object);

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
            SauceSession = new SauceSession(SauceOptions, _dummyDriver.Object);

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
            SauceSession = new SauceSession(SauceOptions, _dummyDriver.Object);

            SauceSession.Start();

            SauceSession.Options.ConfiguredChromeOptions.BrowserVersion.Should().Be("72");
        }
        [TestMethod]
        public void Start_WithSafari_SetsUsernameAndAccessKey()
        {
            SauceOptions = new SauceOptions();
            SauceOptions.WithSafari();
            SauceSession = new SauceSession(SauceOptions, _dummyDriver.Object);

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
        //TODO need a test that will validate that
        //Driver.CreateRemoteWebDriver(Options.ConfiguredSafariOptions);
        //Calls the correct Options property on each of the Create driver methods
        [TestMethod]
        public void Stop_CallsQuit()
        {
            SauceSession = new SauceSession(_dummyDriver.Object);
            SauceSession.Start();
            SauceSession.Stop(true);
            _dummyDriver.Verify(driver => driver.Quit(), Times.Exactly(1));
        }
        [TestMethod]
        public void Stop_TestPassed_UpdatesTestStatus()
        {
            SauceSession = new SauceSession(_dummyDriver.Object);
            SauceSession.Start();
            SauceSession.Stop(true);
            _dummyDriver.Verify(driver => driver.ExecuteScript("sauce:job-result=passed"), Times.Exactly(1));
        }
        [TestMethod]
        public void Stop_TestFailed_UpdatesTestStatus()
        {
            SauceSession = new SauceSession(_dummyDriver.Object);
            SauceSession.Start();
            SauceSession.Stop(false);
            _dummyDriver.Verify(driver => driver.ExecuteScript("sauce:job-result=failed"), Times.Exactly(1));
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
