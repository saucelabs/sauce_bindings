using System;
using System.Collections.Generic;
using FluentAssertions;
using Microsoft.VisualStudio.TestTools.UnitTesting;
using OpenQA.Selenium;
using Sauce.Bindings;

namespace SauceBindings.Test
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
            SauceOptions.ConfiguredOptions.BrowserName.Should().BeEquivalentTo(Browser.Chrome.Value);
            SauceOptions.ConfiguredOptions.BrowserVersion.Should().Be("latest");
            SauceOptions.ConfiguredOptions.PlatformName.Should().BeEquivalentTo(Platforms.Windows10.Value);
        }

        [TestMethod]
        public void UpdatesBrowserAndBrowserVersionAndPlatformVersion()
        {
            SauceOptions.ConfiguredOptions.BrowserVersion = "68";
            SauceOptions.ConfiguredOptions.PlatformName = Platforms.MacOsHighSierra.Value;

            SauceOptions.ConfiguredOptions.BrowserVersion.Should().Be("68");
            SauceOptions.ConfiguredOptions.PlatformName.Should().BeEquivalentTo("macOS 10.13");
        }

        [TestMethod]
        public void AcceptsAllW3CValues()
        {
            SauceOptions.ConfiguredOptions.PageLoadStrategy = PageLoadStrategy.Eager;
            SauceOptions.ConfiguredOptions.AcceptInsecureCertificates = true;
            SauceOptions.SetWindowRect = true;
            SauceOptions.Timeout.Implicit = 4;
            SauceOptions.Timeout.PageLoad = 44;
            SauceOptions.Timeout.Script = 33;

            var proxy = new Proxy();
            SauceOptions.ConfiguredOptions.Proxy = proxy;
            SauceOptions.ConfiguredOptions.UseStrictFileInteractability = true;
            SauceOptions.ConfiguredOptions.UnhandledPromptBehavior = UnhandledPromptBehavior.Dismiss;

            var impl = new KeyValuePair<string, int>("implicit", 4);
            var page = new KeyValuePair<string, int>("pageLoad", 44);
            var script = new KeyValuePair<string, int>("script", 33);

            SauceOptions.ConfiguredOptions.AcceptInsecureCertificates.Should().BeTrue();
            SauceOptions.ConfiguredOptions.PageLoadStrategy.Should().Be(PageLoadStrategy.Eager);
            SauceOptions.ConfiguredOptions.Proxy.Should().Be(proxy);
            SauceOptions.SetWindowRect.Should().BeTrue();
            SauceOptions.Timeout.ToDictionary().Should().Contain(impl, page, script);
            SauceOptions.ConfiguredOptions.UseStrictFileInteractability.Should().BeTrue();
            SauceOptions.ConfiguredOptions.UnhandledPromptBehavior.Should().Be(UnhandledPromptBehavior.Dismiss);
        }

        [TestMethod]
        public void AcceptsAllSauceLabsValues()
        {
            var customData = new Dictionary<string, string> { { "foo", "foo" }, { "bar", "bar" } };

            Prerun prerun = new Prerun
            {
                Executable = "http://url.to/your/executable.exe",
                Args = new List<string> { "--silent", "-a", "-q" },
                Background = false,
                Timeout = 120
            };

            var tags = new List<string> { "foo", "bar" };

            SauceOptions.AvoidProxy = true;
            SauceOptions.BuildName = "Sample Build Name";
            SauceOptions.CapturePerformance = true;
            SauceOptions.Timeout.CommandTimeout = 2;
            SauceOptions.CustomData = customData;
            SauceOptions.ExtendedDebugging = true;
            SauceOptions.Timeout.IdleTimeout = 3;
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
            SauceOptions.Timeout.CommandTimeout.Should().Be(2);
            SauceOptions.CustomData.Should().BeEquivalentTo(customData);
            SauceOptions.ExtendedDebugging.Should().BeTrue();
            SauceOptions.Timeout.IdleTimeout.Should().Be(3);
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
    }
}