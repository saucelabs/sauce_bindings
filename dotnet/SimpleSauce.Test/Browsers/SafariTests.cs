using System.Collections.Generic;
using FluentAssertions;
using Microsoft.VisualStudio.TestTools.UnitTesting;
using Sauce.Bindings;

namespace SauceBindings.Test.Browsers
{
    [TestClass]
    public class SafariTests : BaseTest
    {
        private static IEnumerable<object[]> SafariAndMacConfigurations => new[]
        {
            new object[] { "latest", Platforms.MacOsCatalina },
            new object[] { "15.0", Platforms.MacOsCatalina },
            new object[] { "14.0", Platforms.MacOsMojave },
            new object[] { "12.0", Platforms.MacOsMojave },
            new object[] { "13.0", Platforms.MacOsHighSierra },
            new object[] { "12.1", Platforms.MacOsHighSierra },
            new object[] { "11.1", Platforms.MacOsHighSierra },
            new object[] { "11.0", Platforms.MacOsSierra },
            new object[] { "10.1", Platforms.MacOsSierra },
            new object[] { "10.0", Platforms.MacOsxElCapitan },
            new object[] { "9.0", Platforms.MacOsxElCapitan },
            new object[] { "8.0", Platforms.MacOsxYosemite },
        };

        [TestInitialize]
        public void Setup()
        {
            SauceOptions = new SauceOptions();
        }

        [TestMethod]
        public void WithSafari_DefaultPlatform_Catalina()
        {
            SauceOptions.WithSafari();
            SauceOptions.ConfiguredOptions.PlatformName.Should().Be(Platforms.MacOsCatalina.Value);
        }

        [TestMethod]
        public void WithSafari_DefaultBrowserVersion_latest()
        {
            SauceOptions.WithSafari();
            SauceOptions.ConfiguredOptions.BrowserVersion.Should().Be("latest");
        }

        [TestMethod]
        public void WithSafari_InvalidVersion_ThrowsException()
        {
            var invalidSafariVersion = "BadVersion";
            Assert.ThrowsException<IncorrectSafariVersionException>(
                () => SauceOptions.WithSafari(invalidSafariVersion));
        }

        [TestMethod]
        [DynamicData(nameof(SafariAndMacConfigurations), typeof(SafariTests))]
        public void WithSafari_SpecificVersion_SetsCorrectPlatform(string safariVersion, Platforms expectedPlatform)
        {
            SauceOptions.WithSafari(safariVersion);
            SauceOptions.ConfiguredOptions.PlatformName.Should().Be(expectedPlatform.Value);
        }
    }
}