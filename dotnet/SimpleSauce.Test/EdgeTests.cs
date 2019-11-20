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
            sauceOptions = new SauceOptions();
            sauceOptions.WithEdge();
            sauceOptions.EdgeOptions.PlatformName.Should().Be("Windows 10");
        }
        [TestMethod]
        public void WithEdge_DefaultBrowserVersion_latest()
        {
            sauceOptions = new SauceOptions();
            sauceOptions.WithEdge();
            sauceOptions.EdgeOptions.BrowserVersion.Should().Be("latest");
        }
    }
}
