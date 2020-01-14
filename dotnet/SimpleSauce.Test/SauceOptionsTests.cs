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
        public void UsesLatestChromeWindowsVersionsByDefault()
        {
            SauceOptions.BrowserName.Should().BeEquivalentTo(Browser.Chrome);
            SauceOptions.BrowserVersion.Should().BeEquivalentTo("latest");
            SauceOptions.PlatformName.Should().BeEquivalentTo(Platforms.Windows10);
        }
        [TestMethod]
        public void UpdatesBrowserBrowserVersionPlatformVersion()
        {
            SauceOptions.BrowserVersion = "68";
            SauceOptions.BrowserName = Browser.Firefox;
            SauceOptions.PlatformName = Platforms.MacOsHighSierra;

            SauceOptions.BrowserName.Should().BeEquivalentTo(Browser.Firefox);
            SauceOptions.BrowserVersion.Should().BeEquivalentTo("68");
            SauceOptions.PlatformName.Should().BeEquivalentTo(Platforms.MacOsHighSierra);
        }
    }
}
