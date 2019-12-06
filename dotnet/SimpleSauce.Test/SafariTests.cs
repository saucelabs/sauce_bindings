using System.Collections.Generic;
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
        [TestMethod]
        [DynamicData(nameof(SafariAndMacConfigurations), typeof(SafariTests))]
        public void WithSafari_SpecificVersion_SetsCorrectBrowser(string safariVersion, Platforms expectedPlatform)
        {
            SauceOptions.WithSafari(safariVersion);
            SauceOptions.ConfiguredSafariOptions.PlatformName.Should().Be(expectedPlatform.Value);
        }
        public static IEnumerable<object[]> SafariAndMacConfigurations => new[]
        {
            new object[] {"12.0", Platforms.MacOsMojave },
            new object[] {"13.0", Platforms.MacOsHighSierra },
            new object[] {"12.1", Platforms.MacOsHighSierra },
            new object[] {"11.1", Platforms.MacOsHighSierra },
            new object[] {"11.0", Platforms.MacOsSierra },
            new object[] { "10.1", Platforms.MacOsSierra },
            new object[] {"9.0", Platforms.MacOsxElCapitan },
            new object[] { "10.0", Platforms.MacOsxElCapitan },
            new object[] { "8.0", Platforms.MacOsxYosemite },
        };
    }
}
