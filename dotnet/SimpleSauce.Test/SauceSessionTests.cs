using FluentAssertions;
using Microsoft.VisualStudio.TestTools.UnitTesting;
using Moq;
using Newtonsoft.Json.Linq;
using OpenQA.Selenium.Edge;
using SimpleSauce;

namespace SimpleSauceTests
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

            SauceSession.ChromeOptions.Should().NotBeNull();
        }
        [TestMethod]
        public void Start_WithEdge_SetsEdgeBrowser()
        {
            SauceOptions = new SauceOptions();
            SauceOptions.WithEdge();
            SauceSession = new SauceSession(SauceOptions, _dummyDriver.Object);

            SauceSession.Start();

            SauceSession.Options.ConfiguredEdgeOptions.Should().NotBeNull();
        }
        [TestMethod]
        public void Start_WithChrome_SetsChromeBrowser()
        {
            SauceOptions = new SauceOptions();
            SauceOptions.WithChrome();
            SauceSession = new SauceSession(SauceOptions, _dummyDriver.Object);

            SauceSession.Start();

            SauceSession.Options.ConfiguredChromeOptions.Should().
                NotBeNull("we passed in options configured with Chrome, hence ChromeOptions should be set.");
        }


        //[TestMethod]
        //public void Start_WithEdge15_SetsCorrectOptions()
        //{
        //    string json = @"{
        //     'browserName': 'MicrosoftEdge',
        //        'browserVersion': '15.15063',
        //     'sauce:options': {
        //            'username': 'nikolay-a',
        //      'accessKey': '3c9c7da6-9264-4b46-8aae-8f3806a1e645'
        //        }
        //    }";
        //    JObject expectedEdgeOptions = JObject.Parse(json);
        //    SauceOptions = new SauceOptions();
        //    SauceOptions.WithEdge(EdgeVersion._15);
        //    var dummyManager = new Mock<ISauceRemoteDriver>();
        //    SauceSession = new SauceSession(SauceOptions, dummyManager.Object);

        //    SauceSession.Start();

        //    SauceSession.Options.EdgeOptions.Should().BeSameAs(expectedEdgeOptions);
        //}
    }
}
