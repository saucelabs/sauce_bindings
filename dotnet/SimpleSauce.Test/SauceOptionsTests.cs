using System.Collections.Generic;
using FluentAssertions;
using Microsoft.VisualStudio.TestTools.UnitTesting;
using OpenQA.Selenium;
using OpenQA.Selenium.Chrome;
using OpenQA.Selenium.Edge;
using Simple.Sauce;

namespace SimpleSauce.Test
{
    [TestClass]
    public class SauceOptionsTests : BaseTest
    {
        [TestInitialize]
        public void Setup()
        {
            SauceOptions = new SauceOptions();
        }

        [TestMethod]
        public void UsesLatestChromeWindowsVersionsByDefault()
        {
            SauceOptions.BrowserName.Should().BeEquivalentTo(Browser.Chrome);
            SauceOptions.BrowserVersion.Should().BeEquivalentTo("latest");
            SauceOptions.PlatformName.Should().BeEquivalentTo(Platforms.Windows10);
        }
        [TestMethod]
        public void UpdatesBrowserBrowserVersionPlatformVersion()
        {
            SauceOptions.BrowserVersion = "68";
            SauceOptions.BrowserName = Browser.Firefox;
            SauceOptions.PlatformName = Platforms.MacOsHighSierra;

            SauceOptions.BrowserName.Should().BeEquivalentTo(Browser.Firefox);
            SauceOptions.BrowserVersion.Should().BeEquivalentTo("68");
            SauceOptions.PlatformName.Should().BeEquivalentTo(Platforms.MacOsHighSierra);
        }
        [TestMethod]
        public void AcceptsAllW3CValues()
        {
            SauceOptions.PageLoadStrategy = PageLoadStrategy.Eager;
            SauceOptions.AcceptInsecureCerts = true;
            SauceOptions.SetWindowRect = true;
            SauceOptions.Timeout.Implicit = 4;
            SauceOptions.Timeout.PageLoad = 44;
            SauceOptions.Timeout.Script = 33;

            var proxy = new Proxy();
            SauceOptions.Proxy = proxy;
            SauceOptions.StrictFileInteractability = true;
            SauceOptions.UnhandledPromptBehavior = UnhandledPromptBehavior.Dismiss;

            var expectedTimeouts = new Dictionary<string, int> 
                {{"implicit", 4}, {"pageLoad", 44}, {"script", 33}};

            SauceOptions.AcceptInsecureCerts.Should().BeTrue();
            SauceOptions.PageLoadStrategy.ToString().Should().BeEquivalentTo("eager");
            SauceOptions.Proxy.Should().BeEquivalentTo(proxy);
            SauceOptions.SetWindowRect.Should().BeTrue();
            //SauceOptions.Timeout.Should().BeEquivalentTo(expectedTimeouts, options =>
            //    options.ExcludingMissingMembers());
            SauceOptions.StrictFileInteractability.Should().BeTrue();
            SauceOptions.UnhandledPromptBehavior.ToString().Should().BeEquivalentTo("dismiss");
        }
    }
}
