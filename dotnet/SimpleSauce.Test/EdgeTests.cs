using FluentAssertions;
using Microsoft.VisualStudio.TestTools.UnitTesting;
using Moq;
using OpenQA.Selenium.Edge;
using SimpleSauce;

namespace SimpleSauceTests
{
    [TestClass]
    public class EdgeTests
    {
        [TestMethod]
        public void WithEdge_DefaultPlatform_Windows10()
        {
            SauceOptions options = new SauceOptions();
            options.WithEdge();
            options.EdgeOptions.PlatformName.Should().Be("Windows 10");
        }
        [TestMethod]
        public void WithEdge_DefaultBrowserVersion_latest()
        {
            SauceOptions options = new SauceOptions();
            options.WithEdge();
            options.EdgeOptions.BrowserVersion.Should().Be("latest");
        }
    }
}
