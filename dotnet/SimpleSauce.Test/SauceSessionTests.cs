using System;
using FluentAssertions;
using Microsoft.VisualStudio.TestTools.UnitTesting;
using Moq;
using OpenQA.Selenium;
using OpenQA.Selenium.Remote;
using Simple.Sauce;

namespace SimpleSauce.Test
{
    [TestClass]
    public class SauceSessionTests : BaseTest
    {

        [TestInitialize]
        public void Setup()
        {
            SauceSession = new SauceSession();
        }

        [TestMethod]
        public void SauceSessionDefaultsToLatestChromeOnWindows()
        {
            var sauceOptions = SauceSession.Options;
            sauceOptions.BrowserName.Should().BeEquivalentTo("chrome");
            sauceOptions.BrowserVersion.Should().BeEquivalentTo("latest");
            sauceOptions.PlatformName.Should().BeEquivalentTo("Windows 10");
        }

        [TestMethod]
        public void SauceSessionUsesProvidedSauceOptions()
        {    
            var sauceOptions = new SauceOptions();
            sauceOptions.BrowserName = "firefox";
            
            SauceSession = new SauceSession(sauceOptions);
            
            sauceOptions.Should().BeEquivalentTo(SauceSession.Options);
        }

        [TestMethod]
        public void StartReturnsDriver()
        {
            var returnValue = SauceSession.Start();

            returnValue.Should().BeOfType<RemoteWebDriver>();
            
            SauceSession.Stop(true);
        }

        [TestMethod]
        public void DefaultsToUsWestDataCenter()
        {
            SauceSession.DataCenter.Should().BeEquivalentTo(SauceDataCenter.UsWest);
        }

        [TestMethod]
        public void SetsDataCenter()
        {
            SauceSession.DataCenter = SauceDataCenter.UsEast;
            SauceSession.DataCenter.Should().BeEquivalentTo(SauceDataCenter.UsEast);
        }

        [TestMethod]
        public void SauceUrlUsesEnvironmentVariables()
        {
            // ENV variables need to be mocked
        }

        [TestMethod]
        public void SetsSauceUrlDirectly()
        {
            var url = new Uri("http://example.com");
            SauceSession.SauceUrl = url;
            SauceSession.GenerateUrl().Should().BeEquivalentTo(url);
        }

        [TestMethod]
        public void StartErrorsWithoutUsername()
        {
            // ENV variables need to be mocked
        }

        [TestMethod]
        public void StartErrorsWithoutAccessKey()
        {
            // ENV variables need to be mocked
        }

        [TestMethod]
        public void StopTestFailedUpdatesTestStatus()
        {
        }

        [TestMethod]
        public void StopTestPassedUpdatesTestStatus()
        {
        }

    }
}
