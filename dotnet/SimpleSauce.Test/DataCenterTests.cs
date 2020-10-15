using FluentAssertions;
using Microsoft.VisualStudio.TestTools.UnitTesting;
using Sauce.Bindings;
using System.Collections.Generic;

namespace SauceBindings.Test
{
    [TestClass]
    public class DataCenterTests
    {
        private static IEnumerable<object[]> DataCenterEndpoints => new[]
        {
            new object[] { DataCenter.UsEast.Value, "https://ondemand.us-east-1.saucelabs.com/wd/hub" },
            new object[] { DataCenter.UsWest.Value, "https://ondemand.us-west-1.saucelabs.com/wd/hub" },
            new object[] { DataCenter.EuCental.Value, "https://ondemand.eu-central-1.saucelabs.com/wd/hub" }
        };

        [TestMethod]
        [DynamicData(nameof(DataCenterEndpoints), typeof(DataCenterTests))]
        public void WithSpecificEndpoints(string actualEndpoint, string expectedEndpoint)
        {
            actualEndpoint.Should().Be(expectedEndpoint);
        }
    }
}