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
            sauceOptions = new SauceOptions();
            sauceOptions.WithEdge();
            sauceOptions.EdgeOptions.Should().NotBeNull();
            sauceOptions.EdgeOptions.Should().BeOfType(typeof(EdgeOptions));
        }
    }
}
