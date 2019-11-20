using FluentAssertions;
using Microsoft.VisualStudio.TestTools.UnitTesting;
using Moq;
using OpenQA.Selenium.Edge;
using SimpleSauce;

namespace SimpleSauceTests
{
    [TestClass]
    public class EdgeTests : BaseTest
    {
        [TestMethod]
        public void WithEdge_DefaultPlatform_Windows10()
        {
            SauceOptions = new SauceOptions();
            SauceOptions.WithEdge();
            SauceOptions.EdgeOptions.PlatformName.Should().Be("Windows 10");
        }
        [TestMethod]
        public void WithEdge_DefaultBrowserVersion_latest()
        {
            SauceOptions = new SauceOptions();
            SauceOptions.WithEdge();
            SauceOptions.EdgeOptions.BrowserVersion.Should().Be("latest");
        }
    }
}
