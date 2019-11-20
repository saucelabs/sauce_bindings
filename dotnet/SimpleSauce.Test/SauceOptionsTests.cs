using FluentAssertions;
using Microsoft.VisualStudio.TestTools.UnitTesting;
using Moq;
using OpenQA.Selenium.Edge;
using SimpleSauce;

namespace SimpleSauceTests
{
    [TestClass]
    public class SauceOptionsTests
    {
        [TestMethod]
        public void WithEdge_SetsEdgeOptions()
        {
            SauceOptions options = new SauceOptions();
            options.WithEdge();
            options.EdgeOptions.Should().NotBeNull();
            options.EdgeOptions.Should().BeOfType(typeof(EdgeOptions));
        }
    }
}
