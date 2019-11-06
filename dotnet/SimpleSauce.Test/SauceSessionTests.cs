using Microsoft.VisualStudio.TestTools.UnitTesting;
using FluentAssertions;
using SimpleSauce;
using Moq;
using OpenQA.Selenium;
using OpenQA.Selenium.Remote;
using OpenQA.Selenium.Chrome;

[assembly: Parallelize(Workers = 100, Scope = ExecutionScope.MethodLevel)]

namespace SimpleSauceTests
{
    [TestClass]
    public class SauceSessionTests
    {
        [TestMethod]
        public void ShouldReturnObject()
        {
            var session = new SauceSession();
            session.Should().NotBeNull();
        }
        [TestMethod]
        public void GetDataCenter_Default_IsWest()
        {
            var session = new SauceSession();
            session.DataCenter.Should().BeEquivalentTo(DataCenter.UsWest);
        }
        [TestMethod]
        public void Start_Default_IsChrome()
        {
            var session = new SauceSession();
            var driver = session.Start();
            var capabilities = ((RemoteWebDriver)driver).Capabilities;
            capabilities.GetCapability("browserName").Should().Be("chrome");
        }
    }
}
