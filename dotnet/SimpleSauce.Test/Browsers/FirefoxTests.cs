using FluentAssertions;
using Microsoft.VisualStudio.TestTools.UnitTesting;
using OpenQA.Selenium.Firefox;
using Sauce.Bindings;

namespace SauceBindings.Test.Browsers
{
    [TestClass]
    public class FirefoxTests : BaseTest
    {
        [TestInitialize]
        public void Setup()
        {
            SauceOptions = new SauceOptions();
        }
        [TestMethod]
        public void WithFirefox_SetsFirefoxOptions()
        {
            SauceOptions.WithFirefox();
            SauceOptions.ConfiguredFirefoxOptions.Should().NotBeNull();
            SauceOptions.ConfiguredFirefoxOptions.Should().BeOfType(typeof(FirefoxOptions));
        }
        [TestMethod]
        public void WithFirefox_DefaultBrowserVersion_Latest()
        {
            SauceOptions.WithFirefox();
            SauceOptions.ConfiguredChromeOptions.BrowserVersion.Should().Be("latest");
        }
        [TestMethod]
        public void WithFirefox_DefaultPlatform_Win10()
        {
            SauceOptions.WithFirefox();
            SauceOptions.ConfiguredChromeOptions.PlatformName.Should().Be("Windows 10");
        }
        [TestMethod]
        public void WithFirefox_VersionChanged_SetsVersion()
        {
            SauceOptions.WithFirefox("72");
            SauceOptions.ConfiguredFirefoxOptions.BrowserVersion.Should().Be("72");
        }
    }
}
