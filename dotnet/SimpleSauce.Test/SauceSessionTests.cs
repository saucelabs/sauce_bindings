using FluentAssertions;
using Microsoft.VisualStudio.TestTools.UnitTesting;
using Moq;
using Newtonsoft.Json;

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
            SauceSession.DriverImplementation.Should().BeOfType(typeof(SauceDriver));
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

            var optionsString = SauceSession.Options.ConfiguredChromeOptions.ToString();
            var configuredOptions = JsonConvert.DeserializeObject<Root>(optionsString);
            configuredOptions.SauceOptions.Username.Should().NotBeNullOrEmpty();
            configuredOptions.SauceOptions.AccessKey.Should().NotBeNullOrEmpty();
        }
        [TestMethod]
        public void Start_WithEdge_SetsEdgeOptions()
        {
            SauceOptions = new SauceOptions();
            SauceOptions.WithEdge();
            SauceSession = new SauceSession(SauceOptions, _dummyDriver.Object);

            SauceSession.Start();

            SauceSession.Options.ConfiguredEdgeOptions.Should().NotBeNull("because we set SauceSession to run withEdge()");

            var edgeOptionsString = SauceSession.Options.ConfiguredEdgeOptions.ToString();
            var configuredOptions = JsonConvert.DeserializeObject<Root>(edgeOptionsString);
            configuredOptions.SauceOptions.Username.Should().NotBeNullOrEmpty();
            configuredOptions.SauceOptions.AccessKey.Should().NotBeNullOrEmpty();
        }
        [TestMethod]
        public void Start_WithEdge_SetsUsernameAndAccessKey()
        {
            SauceOptions = new SauceOptions();
            SauceOptions.WithEdge();
            SauceSession = new SauceSession(SauceOptions, _dummyDriver.Object);

            SauceSession.Start();

            var edgeOptionsString = SauceSession.Options.ConfiguredEdgeOptions.ToString();
            var configuredOptions = JsonConvert.DeserializeObject<Root>(edgeOptionsString);
            configuredOptions.SauceOptions.Username.Should().NotBeNullOrEmpty();
            configuredOptions.SauceOptions.AccessKey.Should().NotBeNullOrEmpty();
        }
        [TestMethod]
        public void Start_WithChrome_SetsAdditionalSauceOptions()
        {
            SauceOptions = new SauceOptions();
            SauceOptions.WithChrome();
            SauceSession = new SauceSession(SauceOptions, _dummyDriver.Object);

            SauceSession.Start();

            var browserOptions = SauceSession.Options.ConfiguredChromeOptions.ToString();
            var configuredSauceOptions = JsonConvert.DeserializeObject<Root>(browserOptions);
            configuredSauceOptions.SauceOptions.Username.Should().NotBeNullOrEmpty();
            configuredSauceOptions.SauceOptions.AccessKey.Should().NotBeNullOrEmpty();
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
        public void Start_WithSafari_SetsSafariBrowser()
        {
            SauceOptions = new SauceOptions();
            SauceOptions.WithSafari();
            SauceSession = new SauceSession(SauceOptions, _dummyDriver.Object);

            SauceSession.Start();

            var browserOptions = SauceSession.Options.ConfiguredSafariOptions.ToString();
            var configuredSauceOptions = JsonConvert.DeserializeObject<Root>(browserOptions);
            //TODO see if I can figure out a way to just check that configuredSauceOptions has the right amount
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
