using FluentAssertions;
using Microsoft.VisualStudio.TestTools.UnitTesting;
using Moq;
using OpenQA.Selenium.Edge;
using SimpleSauce;

namespace SimpleSauceTests
{
    [TestClass]
    public class SauceOptionsTests : BaseTest
    {
        [TestMethod]
        public void WithEdge_SetsEdgeOptions()
        {
            SauceOptions = new SauceOptions();
            SauceOptions.WithEdge();
            SauceOptions.EdgeOptions.Should().NotBeNull();
            SauceOptions.EdgeOptions.Should().BeOfType(typeof(EdgeOptions));
        }
    }
}
