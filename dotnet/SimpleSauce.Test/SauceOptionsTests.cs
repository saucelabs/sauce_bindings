using FluentAssertions;
using Microsoft.VisualStudio.TestTools.UnitTesting;
using OpenQA.Selenium.Chrome;
using OpenQA.Selenium.Edge;
using SimpleSauce;

namespace SimpleSauceTests
{
    [TestClass]
    public class SauceOptionsTests : BaseTest
    {
        [TestMethod]
        public void WithEdge_SetsEdgeOptions()
        {
            SauceOptions = new SauceOptions();
            SauceOptions.WithEdge();
            SauceOptions.EdgeOptions.Should().NotBeNull();
            SauceOptions.EdgeOptions.Should().BeOfType(typeof(EdgeOptions));
        }
        [TestMethod]
        public void WithChrome_SetsChromeOptions()
        {
            SauceOptions = new SauceOptions();
            SauceOptions.WithChrome();
            SauceOptions.SauceChromeOptions.Should().NotBeNull();
            SauceOptions.SauceChromeOptions.Should().BeOfType(typeof(ChromeOptions));
        }
        [TestMethod]
        public void WithChrome_DefaultBrowserVersion_Latest()
        {
            SauceOptions = new SauceOptions();
            SauceOptions.WithChrome();
            SauceOptions.SauceChromeOptions.BrowserVersion.Should().Be("latest");
        }
        [TestMethod]
        public void WithChrome_Platform_Win10()
        {
            SauceOptions = new SauceOptions();
            SauceOptions.WithChrome();
            SauceOptions.SauceChromeOptions.PlatformName.Should().Be("Windows 10");
        }
    }
}
