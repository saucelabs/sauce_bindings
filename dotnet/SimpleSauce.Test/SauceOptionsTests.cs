using System;
using System.Collections.Generic;
using System.Globalization;
using System.Runtime.InteropServices.ComTypes;
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
            SauceOptions.BrowserName.Should().Be(Browser.Chrome);
            SauceOptions.BrowserVersion.Should().Be("latest");
            SauceOptions.PlatformName.Should().BeEquivalentTo(Platforms.Windows10);
        }
        [TestMethod]
        public void UpdatesBrowserBrowserVersionPlatformVersion()
        {
            SauceOptions.BrowserVersion = "68";
            SauceOptions.BrowserName = Browser.Firefox;
            SauceOptions.PlatformName = Platforms.MacOsHighSierra;

            SauceOptions.BrowserName.Should().Be(Browser.Firefox);
            SauceOptions.BrowserVersion.Should().Be("68");
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

            var impl = new KeyValuePair<string, int>("implicit", 4);
            var page = new KeyValuePair<string, int>("pageload", 44);
            var script = new KeyValuePair<string, int>("script", 33);

            SauceOptions.AcceptInsecureCerts.Should().BeTrue();
            SauceOptions.PageLoadStrategy.Should().Be(PageLoadStrategy.Eager);
            SauceOptions.Proxy.Should().Be(proxy);
            SauceOptions.SetWindowRect.Should().BeTrue();
            SauceOptions.Timeout.ToDictionary().Should().Contain(impl, page, script);
            SauceOptions.StrictFileInteractability.Should().BeTrue();
            SauceOptions.UnhandledPromptBehavior.Should().Be(UnhandledPromptBehavior.Dismiss);
        }
        [TestMethod]
        public void AcceptsAllSauceLabsValues()
        {
            var customData = new Dictionary<string, string> { { "foo", "foo" }, { "bar", "bar" } };

            var args = new List<string> { "--silent", "-a", "-q" };

            var prerun = new Dictionary<string, object>
            {
                {"executable", "http://url.to/your/executable.exe"},
                {"args", args},
                {"background", false},
                {"timeout", new TimeSpan(120)}
            };

            var tags = new List<string> { "foo", "bar" };

            SauceOptions.AvoidProxy = true;
            SauceOptions.BuildName = "Sample Build Name";
            SauceOptions.CapturePerformance = true;
            SauceOptions.ChromedriverVersion = "71";
            SauceOptions.Timeout.CommandTimeout = 2;
            SauceOptions.CustomData = customData;
            SauceOptions.ExtendedDebugging = true;
            SauceOptions.Timeout.IdleTimeout = 3;
            SauceOptions.IeDriverVersion = "3.141.0";
            SauceOptions.Timeout.MaxDuration = 300;
            SauceOptions.TestName = "Sample Test Name";
            SauceOptions.ParentTunnel = "Mommy";
            SauceOptions.Prerun = prerun;
            SauceOptions.Priority = 0;
            SauceOptions.TestVisibility = TestVisibility.Public;
            SauceOptions.RecordLogs = false;
            SauceOptions.RecordScreenshots = false;
            SauceOptions.RecordVideo = false;
            SauceOptions.ScreenResolution = "10x10";
            SauceOptions.SeleniumVersion = "3.141.59";
            SauceOptions.Tags = tags;
            SauceOptions.TimeZone = "San Francisco";
            SauceOptions.TunnelIdentifier = "My Tunnel ID";
            SauceOptions.VideoUploadOnPass = false;

            SauceOptions.AvoidProxy.Should().BeTrue();
            SauceOptions.BuildName.Should().Be("Sample Build Name");
            SauceOptions.CapturePerformance.Should().BeTrue();
            SauceOptions.ChromedriverVersion.Should().Be("71");
            SauceOptions.Timeout.CommandTimeout.Should().Be(2);
            SauceOptions.CustomData.Should().BeEquivalentTo(customData);
            SauceOptions.ExtendedDebugging.Should().BeTrue();
            SauceOptions.Timeout.IdleTimeout.Should().Be(3);
            SauceOptions.IeDriverVersion.Should().Be("3.141.0");
            SauceOptions.Timeout.MaxDuration.Should().Be(300);
            SauceOptions.TestName.Should().Be("Sample Test Name");
            SauceOptions.ParentTunnel.Should().Be("Mommy");
            SauceOptions.Prerun.Should().BeEquivalentTo(prerun);
            SauceOptions.Priority.Should().Be(0);
            SauceOptions.TestVisibility.Should().Be(TestVisibility.Public);
            SauceOptions.RecordLogs.Should().BeFalse();
            SauceOptions.RecordScreenshots.Should().BeFalse();
            SauceOptions.RecordVideo.Should().BeFalse();
            SauceOptions.ScreenResolution.Should().Be("10x10");
            SauceOptions.SeleniumVersion.Should().Be("3.141.59");
            SauceOptions.Tags.Should().BeEquivalentTo(tags);
            SauceOptions.TimeZone.Should().Be("San Francisco");
            SauceOptions.TunnelIdentifier.Should().Be("My Tunnel ID");
            SauceOptions.VideoUploadOnPass.Should().BeFalse();
        }

        [TestMethod]
        public void AcceptsChromeOptionsClass()
        {
            var options = new ChromeOptions();
            SauceOptions = new SauceOptions(options);

            SauceOptions.BrowserName.ToString().ToLowerInvariant().Should().BeEquivalentTo("chrome");
            SauceOptions.SeleniumOptions.Should().BeEquivalentTo(options);
        }
        [TestMethod]
        public void AcceptsEdgeOptionsClass()
        {
            var options = new EdgeOptions();
            SauceOptions = new SauceOptions(options);

            SauceOptions.BrowserName.ToString().ToLowerInvariant().Should().BeEquivalentTo("MicrosoftEdge");
            SauceOptions.SeleniumOptions.Should().BeEquivalentTo(options);
        }

        [TestMethod]
        public void AcceptsFirefoxOptionsClass()
        {
            var options = new FirefoxOptions();
            SauceOptions = new SauceOptions(options);

            SauceOptions.BrowserName.ToString().ToLowerInvariant().Should().BeEquivalentTo("firefox");
            SauceOptions.SeleniumOptions.Should().BeEquivalentTo(options);
        }

        [TestMethod]
        public void AcceptsInternetExplorerOptionsClass()
        {
            var options = new InternetExplorerOptions();
            SauceOptions = new SauceOptions(options);

            SauceOptions.BrowserName.ToString().ToLowerInvariant().Should().BeEquivalentTo("internet explorer");
            SauceOptions.SeleniumOptions.Should().BeEquivalentTo(options);
        }

        [TestMethod]
        public void AcceptsSafariOptionsClass()
        {
            var options = new SafariOptions();
            SauceOptions = new SauceOptions(options);

            SauceOptions.BrowserName.ToString().ToLowerInvariant().Should().BeEquivalentTo("safari");
            SauceOptions.SeleniumOptions.Should().BeEquivalentTo(options);
        }

        [TestMethod]
        public void CreatesDefaultBuildName()
        {
            Environment.SetEnvironmentVariable("BUILD_TAG", "Not Empty");
            Environment.SetEnvironmentVariable("BUILD_NAME", "TEMP BUILD");
            Environment.SetEnvironmentVariable("BUILD_NUMBER", "11");

            SauceOptions.BuildName.Should().BeEquivalentTo("TEMP BUILD: 11");
        }
    }
}
