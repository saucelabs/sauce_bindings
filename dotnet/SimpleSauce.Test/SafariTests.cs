using FluentAssertions;
using Microsoft.DotNet.PlatformAbstractions;
using Microsoft.VisualStudio.TestTools.UnitTesting;

namespace SimpleSauce.Test
{
    [TestClass]
    public class SafariTests : BaseTest
    {
        [TestInitialize]
        public void Setup()
        {
            SauceOptions = new SauceOptions();
        }
        [TestMethod]
        public void WithSafari_DefaultPlatform_Mojave()
        {
            SauceOptions.WithSafari();
            SauceOptions.ConfiguredSafariOptions.PlatformName.Should().Be(Platforms.MacOsMojave.Value);
        }
        [TestMethod]
        public void WithSafari_DefaultBrowserVersion_latest()
        {
            SauceOptions.WithSafari();
            SauceOptions.ConfiguredSafariOptions.BrowserVersion.Should().Be("latest");
        }
        [TestMethod]
        public void WithSafari_UpdatedVersion_SetsCorrectVersion()
        {
            var expectedSafariVersion = "12";
            SauceOptions.WithSafari(expectedSafariVersion);
            SauceOptions.ConfiguredSafariOptions.BrowserVersion.Should().Be(expectedSafariVersion);
        }
        [TestMethod]
        public void WithSafari_UpdatedVersion_SetsCorrectPlatform()
        {
            var expectedSafariVersion = "latest";
            SauceOptions.WithSafari(expectedSafariVersion);
            SauceOptions.ConfiguredSafariOptions.PlatformName.Should().Be(Platforms.MacOsMojave.Value);
        }
        //TODO need tests that will validate that different version of Safari
        //set the corresponding PlatformName
    }
}
