using System;
using System.Collections.Generic;
using FluentAssertions;
using Microsoft.VisualStudio.TestTools.UnitTesting;
using OpenQA.Selenium;
using OpenQA.Selenium.Chrome;
using OpenQA.Selenium.Edge;
using OpenQA.Selenium.Firefox;
using OpenQA.Selenium.IE;
using OpenQA.Selenium.Safari;
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
            SauceOptions.BrowserName.Should().BeEquivalentTo("chrome");
            SauceOptions.BrowserVersion.Should().BeEquivalentTo("latest");
            SauceOptions.PlatformName.Should().BeEquivalentTo("Windows 10");
        }

        [TestMethod]
        public void UpdatesBrowserBrowserVersionPlatformVersionValues()
        {
            SauceOptions.BrowserName = "firefox";
            SauceOptions.BrowserVersion = "68";
            SauceOptions.PlatformName = "macOS 10.13";
            
            SauceOptions.BrowserName.Should().BeEquivalentTo("firefox");
            SauceOptions.BrowserVersion.Should().BeEquivalentTo("68");
            SauceOptions.PlatformName.Should().BeEquivalentTo("macOS 10.13");
        }
        
        [TestMethod]
        public void AcceptsOtherW3CValues()
        {
            SauceOptions.PageLoadStrategy = "eager";
            SauceOptions.AcceptInsecureCerts = true;
            SauceOptions.SetWindowRect = true;
            var timeouts = new Dictionary<string, TimeSpan>();
            timeouts.Add("implicit", new TimeSpan(4));
            timeouts.Add("pageLoad", new TimeSpan(44));
            timeouts.Add("script", new TimeSpan(33));
            SauceOptions.Timeouts = timeouts;
            var proxy = new Proxy();
            SauceOptions.Proxy = proxy;
            SauceOptions.StrictFileInteractability = true;
            SauceOptions.UnhandledPromptBehavior = "dismiss";
            
            SauceOptions.PageLoadStrategy.Should().BeEquivalentTo("eager");
            SauceOptions.AcceptInsecureCerts.Should().BeTrue();
            SauceOptions.SetWindowRect.Should().BeTrue();
            SauceOptions.Proxy.Should().BeEquivalentTo(proxy);
            SauceOptions.Timeouts.Should().BeEquivalentTo(timeouts);
            SauceOptions.StrictFileInteractability.Should().BeTrue();
            SauceOptions.UnhandledPromptBehavior.Should().BeEquivalentTo("dismiss");
        }

        [TestMethod]
        public void AcceptsSauceLabsSettings()
        {
        }

        [TestMethod]
        public void AcceptsEdgeOptionsClass()
        {
            var options = new EdgeOptions();
            SauceOptions = new SauceOptions(options);
            
            SauceOptions.BrowserName.Should().BeEquivalentTo("MicrosoftEdge");
        }

        [TestMethod]
        public void AcceptsFirefoxOptionsClass()
        {
            var options = new FirefoxOptions();
            SauceOptions = new SauceOptions(options);
            
            SauceOptions.BrowserName.Should().BeEquivalentTo("firefox");
        }

        [TestMethod]
        public void AcceptsIEOptionsClass()
        {
            var options = new InternetExplorerOptions();
            SauceOptions = new SauceOptions(options);
            
            SauceOptions.BrowserName.Should().BeEquivalentTo("internet explorer");
        }

        [TestMethod]
        public void AcceptsSafariOptionsClass()
        {
            var options = new SafariOptions();
            SauceOptions = new SauceOptions(options);
            
            SauceOptions.BrowserName.Should().BeEquivalentTo("safari");
        }

        [TestMethod]
        public void AcceptsChromeOptionsClass()
        {
            var options = new ChromeOptions();
            SauceOptions = new SauceOptions(options);
            
            SauceOptions.BrowserName.Should().BeEquivalentTo("chrome");
        }

        [TestMethod]
        public void AllowsBuildToBeSet()
        {
        }

        [TestMethod]
        public void CreatesDefaultBuildName()
        {
        }

        [TestMethod]
        public void ParsesW3CAndSauceAndSeleniumSettings()
        {
            var firefoxOptions = new FirefoxOptions();
            firefoxOptions.AddArgument("--foo");
            firefoxOptions.SetPreference("foo", "bar");
            firefoxOptions.UnhandledPromptBehavior = UnhandledPromptBehavior.Dismiss;
            
            var sauceOptions = new SauceOptions(firefoxOptions);

            var expectedOptions = new FirefoxOptions();
            expectedOptions.AddArgument("--foo");
            expectedOptions.SetPreference("foo", "bar");
            expectedOptions.UnhandledPromptBehavior = UnhandledPromptBehavior.Dismiss;
            expectedOptions.BrowserVersion = "latest";
            expectedOptions.PlatformName = "Windows 10";
            expectedOptions.AddAdditionalOption("sauce:options", new Dictionary<string, object>());

            sauceOptions.ToString().Should().BeEquivalentTo(expectedOptions.ToString());
        }
        
    }
}
