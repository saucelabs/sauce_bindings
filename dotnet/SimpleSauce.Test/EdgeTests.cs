using System;
using System.Collections.Generic;
using FluentAssertions;
using Microsoft.VisualStudio.TestTools.UnitTesting;

namespace SimpleSauce.Test
{
    [TestClass]
    public class EdgeTests : BaseTest
    {
        [TestInitialize]
        public void Setup()
        {
            SauceOptions = new SauceOptions();
        }
        [TestMethod]
        public void WithEdge_DefaultPlatform_Windows10()
        {
            SauceOptions.WithEdge();
            SauceOptions.ConfiguredEdgeOptions.PlatformName.Should().Be("Windows 10");
        }
        [TestMethod]
        public void WithEdge_DefaultBrowserVersion_latest()
        {
            SauceOptions.WithEdge();
            SauceOptions.ConfiguredEdgeOptions.BrowserVersion.Should().Be("latest");
        }
        [TestMethod]
        [DynamicData(nameof(PossibleEdgeConfigurations), typeof(EdgeTests))]
        public void WithEdge_SetVersion_SetsCorrectVersion(string expectedVersion, EdgeVersion edgeVersion)
        {
            SauceOptions.WithEdge(edgeVersion);
            SauceOptions.ConfiguredEdgeOptions.BrowserVersion.Should().Be(expectedVersion);
        }
        public static IEnumerable<object[]> PossibleEdgeConfigurations => new[]
        {
            new object[] {"18.17763", EdgeVersion._18 },
            new object[] {"17.17134", EdgeVersion._17 },
            new object[] {"16.16299", EdgeVersion._16 },
            new object[] {"15.15063", EdgeVersion._15 },
            new object[] {"14.14393", EdgeVersion._14 },
            new object[] { "13.10586", EdgeVersion._13 }
        };

        [TestMethod]
        public void WithEdge_NullBrowserVersion_ThrowsException()
        {
            Assert.ThrowsException<ArgumentNullException>(() => SauceOptions.WithEdge(null));
        }
    }
}
