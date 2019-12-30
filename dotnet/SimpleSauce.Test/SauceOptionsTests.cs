using FluentAssertions;
using Microsoft.VisualStudio.TestTools.UnitTesting;
using OpenQA.Selenium.Chrome;
using OpenQA.Selenium.Edge;
using Simple.Sauce;

namespace SimpleSauce.Test
{
    [TestClass]
    public class SauceOptionsTests : BaseTest
    {
        [TestInitialize]
        public void Setup()
        {
            SauceOptions = new SauceOptions();
        }
        [TestMethod]
        public void DefaultConstructor_SetsDriverFactory()
        {
            SauceOptions.DriverFactory.Should().BeOfType(typeof(DriverFactory));
        }
        [TestMethod]
        public void WithEdge_SetsEdgeOptions()
        {
            SauceOptions.WithEdge();
            SauceOptions.ConfiguredEdgeOptions.Should().NotBeNull();
            SauceOptions.ConfiguredEdgeOptions.Should().BeOfType(typeof(EdgeOptions));
        }
        [TestMethod]
        public void WithChrome_SetsChromeOptions()
        {
            SauceOptions.WithChrome();
            SauceOptions.ConfiguredChromeOptions.Should().NotBeNull();
            SauceOptions.ConfiguredChromeOptions.Should().BeOfType(typeof(ChromeOptions));
        }
        [TestMethod]
        public void WithChrome_DefaultBrowserVersion_Latest()
        {
            SauceOptions.WithChrome();
            SauceOptions.ConfiguredChromeOptions.BrowserVersion.Should().Be("latest");
        }
        [TestMethod]
        public void WithChrome_DefaultPlatform_Win10()
        {
            SauceOptions.WithChrome();
            SauceOptions.ConfiguredChromeOptions.PlatformName.Should().Be("Windows 10");
        }
        [TestMethod]
        public void WithChrome_VersionChanged_SetsVersion()
        {
            SauceOptions.WithChrome("72");
            SauceOptions.ConfiguredChromeOptions.BrowserVersion.Should().
                Be("72", "we set a specific chrome version and this version should be passed to ChromeOptions");
        }
    }
}
