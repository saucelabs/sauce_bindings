using System;
using System.Collections.Generic;
using FluentAssertions;
using Microsoft.VisualStudio.TestTools.UnitTesting;
using Sauce.Bindings;

namespace SauceBindings.Test.Browsers
{
    [TestClass]
    public class EdgeTests : BaseTest
    {
        private static IEnumerable<object[]> PossibleEdgeConfigurations => new[]
        {
            new object[] { "latest", EdgeVersion.Latest },
            new object[] { "85.0", EdgeVersion._85 },
            new object[] { "84.0", EdgeVersion._84 },
            new object[] { "83.0", EdgeVersion._83 },
            new object[] { "81.0", EdgeVersion._81 },
            new object[] { "80.0", EdgeVersion._80 },
            new object[] { "79.0", EdgeVersion._79 },
            new object[] { "17.17134", EdgeVersion._17 },
            new object[] { "16.16299", EdgeVersion._16 },
            new object[] { "15.15063", EdgeVersion._15 },
            new object[] { "14.14393", EdgeVersion._14 },
            new object[] { "13.10586", EdgeVersion._13 }
        };

        [TestInitialize]
        public void Setup()
        {
            SauceOptions = new SauceOptions();
        }

        [TestMethod]
        public void WithEdge_DefaultPlatform_Windows10()
        {
            SauceOptions.WithEdge();
            SauceOptions.ConfiguredOptions.PlatformName.Should().Be(Platforms.Windows10.Value);
        }

        [TestMethod]
        public void WithEdge_DefaultBrowserVersion_latest()
        {
            SauceOptions.WithEdge();
            SauceOptions.ConfiguredOptions.BrowserVersion.Should().Be("latest");
        }

        [TestMethod]
        [DynamicData(nameof(PossibleEdgeConfigurations), typeof(EdgeTests))]
        public void WithEdge_SetVersion_SetsCorrectVersion(string expectedVersion, EdgeVersion edgeVersion)
        {
            SauceOptions.WithEdge(edgeVersion);
            SauceOptions.ConfiguredOptions.BrowserVersion.Should().Be(expectedVersion);
        }

        [TestMethod]
        public void WithEdge_NullBrowserVersion_ThrowsException()
        {
            Assert.ThrowsException<ArgumentNullException>(() => SauceOptions.WithEdge(null));
        }
    }
}