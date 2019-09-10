using System;
using Microsoft.VisualStudio.TestTools.UnitTesting;
using SimpleSauce;
using FluentAssertions;
using OpenQA.Selenium.Remote;

namespace SimpleSauce.Tests
{
    [TestClass]
    public class SauceSessionTest
    {
        [TestMethod]
        public void isNotNull()
        {
            var sauceSession = new SauceSession();
            Assert.IsNotNull(sauceSession);
        }
        [TestMethod]
        public void Start_ShouldReturnRemoteWebDriver()
        {
            var driver = new SauceSession().Start();
            driver.Should().BeOfType(typeof(RemoteWebDriver));
        }
    }
}
