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
            SauceOptions.ConfiguredOptions.Should().NotBeNull();
            SauceOptions.ConfiguredOptions.Should().BeOfType(typeof(FirefoxOptions));
        }

        [TestMethod]
        public void WithFirefox_DefaultBrowserVersion_Latest()
        {
            SauceOptions.WithFirefox();
            SauceOptions.ConfiguredOptions.BrowserVersion.Should().Be("latest");
        }

        [TestMethod]
        public void WithFirefox_DefaultPlatform_Win10()
        {
            SauceOptions.WithFirefox();
            SauceOptions.ConfiguredOptions.PlatformName.Should().Be(Platforms.Windows10.Value);
        }

        [TestMethod]
        public void WithFirefox_VersionChanged_SetsVersion()
        {
            SauceOptions.WithFirefox("72");
            SauceOptions.ConfiguredOptions.BrowserVersion.Should().
                Be("72", "We set a specific firefox version and this version should be passed to FirefoxOptions.");
        }
    }
}
