using FluentAssertions;
using Microsoft.VisualStudio.TestTools.UnitTesting;
using Simple.Sauce;
using System;
using System.Collections.Generic;

namespace SimpleSauce.Test.Browsers
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
        [DynamicData(nameof(PossibleEdgeConfigurations), typeof(EdgeTests))]
        public void WithEdge_SetVersion_SetsCorrectVersion(string expectedVersion, EdgeVersion edgeVersion)
        {
            SauceOptions.BrowserVersion = edgeVersion.Value;
            SauceOptions.BrowserVersion.Should().Be(expectedVersion);
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
    }
}
