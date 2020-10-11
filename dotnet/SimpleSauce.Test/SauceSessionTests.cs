using FluentAssertions;
using Microsoft.VisualStudio.TestTools.UnitTesting;
using Moq;
using Newtonsoft.Json;
using OpenQA.Selenium;
using OpenQA.Selenium.Chrome;
using OpenQA.Selenium.Edge;
using OpenQA.Selenium.Firefox;
using OpenQA.Selenium.Safari;
using Sauce.Bindings;
using System;
using System.Collections.Generic;

namespace SauceBindings.Test
{
    [TestClass]
    public class SauceSessionTests : BaseTest
    {
        private Mock<ISauceRemoteDriver> _dummyDriver;

        private static IEnumerable<object[]> DataCenterEndpoints => new[]
        {
            new object[] { DataCenter.UsEast, "https://ondemand.us-east-1.saucelabs.com/wd/hub" },
            new object[] { DataCenter.UsWest, "https://ondemand.us-west-1.saucelabs.com/wd/hub" },
            new object[] { DataCenter.EuCental, "https://ondemand.eu-central-1.saucelabs.com/wd/hub" }
        };

        private static IEnumerable<object[]> BrowserOptions => new[]
       {
            new object[] { new SauceOptions().WithChrome(), typeof(ChromeOptions) },
            new object[] { new SauceOptions().WithEdge(), typeof(EdgeOptions) },
            new object[] { new SauceOptions().WithFirefox(), typeof(FirefoxOptions) },
            new object[] { new SauceOptions().WithSafari(), typeof(SafariOptions) }
        };

        [TestInitialize]
        public void Setup()
        {
            _dummyDriver = new Mock<ISauceRemoteDriver>();
        }

        #region Empty Constructor Tests
        // Calls the Empty Constructor and validates that the Options are intialized
        [TestMethod]
        public void SauceSession_NoConstructorParam_OptionsInitialized()
        {
            SauceSession = new SauceSession();
            Assert.IsNotNull(SauceSession.Options);
        }

        // Calls the Empty Constructor and validates that the Driver is intialized
        [TestMethod]
        public void SauceSession_NoConstructorParam_DriverInitialized()
        {
            SauceSession = new SauceSession();
            Assert.IsNotNull(SauceSession.Driver);
        }

        // Calls the Empty Constructor and validates that the Data Center is intialized
        [TestMethod]
        public void SauceSession_NoConstructorParam_DataCenterInitialized()
        {
            SauceSession = new SauceSession();
            Assert.IsNotNull(SauceSession.DataCenter);
        }
        #endregion

        #region Constructor Tests with single Parameter passed
        // Calls the Options Constuctor and validates that the driver is intialized
        [TestMethod]
        public void SauceSession_OptionsPassedIn_SetsConcreteDriver()
        {
            SauceOptions = new SauceOptions();
            SauceSession = new SauceSession(SauceOptions);
            SauceSession.Driver.Should().BeOfType(typeof(SauceDriver));
        }

        [TestMethod]
        [DynamicData(nameof(DataCenterEndpoints))]
        // Calls the Data Cemter Constuctor and validates that the endpoint is valid for each data center
        public void GetDataCenter_DataCenterPassedIn(DataCenter dataCenter, string expectedEndpoint)
        {
            SauceSession = new SauceSession(dataCenter);
            SauceSession.DataCenter.Value.Should().BeEquivalentTo(expectedEndpoint);
        }

        // Calls the Driver Constuctor and validates that the driver is intialized
        [TestMethod]
        public void SauceSession_DriverPassedIn_SetsConcreteDriver()
        {
            SauceSession = new SauceSession(_dummyDriver.Object);
            SauceSession.Driver.Should().NotBeNull();
        }
        #endregion

        #region Default Tests
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
            SauceSession.Options.ConfiguredOptions.Should().BeOfType(typeof(ChromeOptions));
        }

        [TestMethod]
        public void Start_Default_SetsSauceOptionsTag()
        {
            SauceSession = new SauceSession(_dummyDriver.Object);
            SauceSession.Start();

            var browserOptionsSetInSauceJson = SauceSession.Options.ConfiguredOptions.ToString();
            var browserOptionsSetInSauce = DeserializeToObject(browserOptionsSetInSauceJson);

            AssertUsernameAndAccessKeyExist(browserOptionsSetInSauce);
        }
        #endregion

        [TestMethod]
        public void Start_WithEdge_SetsUsernameAndAccessKey()
        {
            SauceOptions = new SauceOptions().WithEdge();
            SauceSession = new SauceSession(SauceOptions, _dummyDriver.Object);

            SauceSession.Start();

            var browserOptionsSetInSauceJson = SauceSession.Options.ConfiguredOptions.ToString();
            var browserOptionsSetInSauce = DeserializeToObject(browserOptionsSetInSauceJson);
            AssertUsernameAndAccessKeyExist(browserOptionsSetInSauce);
        }

        [TestMethod]
        public void Start_WithChrome_SetsUsernameAndAccessKey()
        {
            SauceOptions = new SauceOptions().WithChrome();
            SauceSession = new SauceSession(SauceOptions, _dummyDriver.Object);

            SauceSession.Start();

            var browserOptionsSetInSauceJson = SauceSession.Options.ConfiguredOptions.ToString();
            var browserOptionsSetInSauce = DeserializeToObject(browserOptionsSetInSauceJson);

            AssertUsernameAndAccessKeyExist(browserOptionsSetInSauce);
        }

        [TestMethod]
        public void Start_WithChromeVersionSet_CreatesCorrectDriver()
        {
            SauceOptions = new SauceOptions().WithChrome("72");
            SauceSession = new SauceSession(SauceOptions, _dummyDriver.Object);

            SauceSession.Start();

            SauceSession.Options.ConfiguredOptions.BrowserVersion.Should().Be("72");
        }

        [TestMethod]
        public void Start_WithSafari_SetsUsernameAndAccessKey()
        {
            SauceOptions = new SauceOptions().WithSafari();
            SauceSession = new SauceSession(SauceOptions, _dummyDriver.Object);

            SauceSession.Start();

            var browserOptionsSetInSauceJson = SauceSession.Options.ConfiguredOptions.ToString();
            var browserOptionsSetInSauce = DeserializeToObject(browserOptionsSetInSauceJson);
            AssertUsernameAndAccessKeyExist(browserOptionsSetInSauce);
        }

        [TestMethod]
        [DynamicData(nameof(BrowserOptions))]
        public void Start_WithBrowser_CallsCorrectOptions(SauceOptions sauceOptions, Type optionsType)
        {
            SauceSession = new SauceSession(sauceOptions, _dummyDriver.Object);
            SauceSession.Start();
            SauceSession.Stop(true);
            SauceSession.Options.ConfiguredOptions.Should().BeOfType(optionsType);
        }

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

        [TestMethod]
        public void Start_WithFirefox_SetsUsernameAndAccessKey()
        {
            SauceOptions = new SauceOptions();
            SauceOptions.WithFirefox();
            SauceSession = new SauceSession(SauceOptions, _dummyDriver.Object);

            SauceSession.Start();

            var browserOptionsSetInSauceJson = SauceSession.Options.ConfiguredOptions.ToString();
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
