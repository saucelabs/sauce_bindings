using System;
using System.Collections.Generic;
using FluentAssertions;
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
        public void WithSafari_DefaultPlatform_Windows10()
        {
            SauceOptions.WithSafari();
            SauceOptions.ConfiguredSafariOptions.PlatformName.Should().Be("Windows 10");
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
        public void WithEdge_NullBrowserVersion_ThrowsException()
        {
            Assert.ThrowsException<ArgumentNullException>(() => SauceOptions.WithEdge(null));
        }
    }
}
