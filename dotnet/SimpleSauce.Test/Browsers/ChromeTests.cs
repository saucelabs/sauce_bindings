using FluentAssertions;
using Microsoft.VisualStudio.TestTools.UnitTesting;
using OpenQA.Selenium.Chrome;
using Sauce.Bindings;

namespace SauceBindings.Test.Browsers
{
    [TestClass]
    public class ChromeTests : BaseTest
    {
        [TestInitialize]
        public void Setup()
        {
            SauceOptions = new SauceOptions();
        }

        [TestMethod]
        public void WithChrome_SetsChromeOptions()
        {
            SauceOptions.WithChrome();
            SauceOptions.ConfiguredOptions.Should().NotBeNull();
            SauceOptions.ConfiguredOptions.Should().BeOfType(typeof(ChromeOptions));
        }

        [TestMethod]
        public void WithChrome_DefaultBrowserVersion_Latest()
        {
            SauceOptions.WithChrome();
            SauceOptions.ConfiguredOptions.BrowserVersion.Should().Be("latest");
        }

        [TestMethod]
        public void WithChrome_DefaultPlatform_Win10()
        {
            SauceOptions.WithChrome();
            SauceOptions.ConfiguredOptions.PlatformName.Should().Be(Platforms.Windows10.Value);
        }

        [TestMethod]
        public void WithChrome_VersionChanged_SetsVersion()
        {
            SauceOptions.WithChrome("72");
            SauceOptions.ConfiguredOptions.BrowserVersion.Should().
                Be("72", "We set a specific chrome version and this version should be passed to ChromeOptions.");
        }
    }
}
