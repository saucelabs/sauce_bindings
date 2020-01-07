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
        public void UpdatesBrowserBrowserVersionPlatformVersion()
        {
            SauceOptions.BrowserName = "firefox";
            SauceOptions.BrowserVersion = "68";
            SauceOptions.PlatformName = "macOS 10.13";

            SauceOptions.BrowserName.Should().BeEquivalentTo("firefox");
            SauceOptions.BrowserVersion.Should().BeEquivalentTo("68");
            SauceOptions.PlatformName.Should().BeEquivalentTo("macOS 10.13");
        }

        [TestMethod]
        public void AcceptsAllW3CValues()
        {
            SauceOptions.PageLoadStrategy = "eager";
            SauceOptions.AcceptInsecureCerts = true;
            SauceOptions.SetWindowRect = true;
            SauceOptions.Timeouts.Implicit = new TimeSpan(4);
            SauceOptions.Timeouts.PageLoad = new TimeSpan(44);
            SauceOptions.Timeouts.Script = new TimeSpan(33);
            var proxy = new Proxy();
            SauceOptions.Proxy = proxy;
            SauceOptions.StrictFileInteractability = true;
            SauceOptions.UnhandledPromptBehavior = "dismiss";

            var timeouts = new Dictionary<string, TimeSpan>();
            timeouts.Add("implicit", new TimeSpan(4));
            timeouts.Add("pageLoad", new TimeSpan(44));
            timeouts.Add("script", new TimeSpan(33));

            SauceOptions.AcceptInsecureCerts.Should().BeTrue();
            SauceOptions.PageLoadStrategy.Should().BeEquivalentTo("eager");
            SauceOptions.Proxy.Should().BeEquivalentTo(proxy);
            SauceOptions.SetWindowRect.Should().BeTrue();
            SauceOptions.Timeouts.Should().BeEquivalentTo(timeouts);
            SauceOptions.StrictFileInteractability.Should().BeTrue();
            SauceOptions.UnhandledPromptBehavior.Should().BeEquivalentTo("dismiss");
        }

        [TestMethod]
        public void AcceptsAllSauceLabsValues()
        {
            var customData = new Dictionary<string, string> {{"foo", "foo"}, {"bar", "bar"}};

            var args = new List<string> {"--silent", "-a", "-q"};

            var prerun = new Dictionary<string, object>
            {
                {"executable", "http://url.to/your/executable.exe"},
                {"args", args},
                {"background", false},
                {"timeout", new TimeSpan(120)}
            };

            var tags = new List<string> {"foo", "bar"};

            SauceOptions.AvoidProxy = true;
            SauceOptions.Build = "Sample Build Name";
            SauceOptions.CapturePerformance = true;
            SauceOptions.ChromedriverVersion = "71";
            SauceOptions.CommandTimeout = new TimeSpan(2);
            SauceOptions.CustomData = customData;
            SauceOptions.ExtendedDebugging = true;
            SauceOptions.IdleTimeout = new TimeSpan(3);
            SauceOptions.IedriverVersion = "3.141.0";
            SauceOptions.MaxDuration = new TimeSpan(300);
            SauceOptions.Name = "Sample Test Name";
            SauceOptions.ParentTunnel = "Mommy";
            SauceOptions.Prerun = prerun;
            SauceOptions.Priority = 0;
            SauceOptions.Public = "team";
            SauceOptions.RecordLogs = false;
            SauceOptions.RecordScreenshots = false;
            SauceOptions.RecordVideo = false;
            SauceOptions.ScreenResolution = "10x10";
            SauceOptions.SeleniumVersion = "3.141.59";
            SauceOptions.Tags = tags;
            SauceOptions.TimeZone = "San Francisco";
            SauceOptions.TunnelIdentifier = tags;
            SauceOptions.VideoUploadOnPass = false;

            SauceOptions.AvoidProxy.Should().BeTrue;
            SauceOptions.Build.Should().BeEquivalentTo("Sample Build Name");
            SauceOptions.CapturePerformance.Should().BeTrue;
            SauceOptions.ChromedriverVersion.Should().BeEquivalentTo("71");
            SauceOptions.CommandTimeout.Should().BeEquivalentTo(new TimeSpan(2));
            SauceOptions.CustomData.Should().BeEquivalentTo(customData);
            SauceOptions.ExtendedDebugging.Should().BeTrue;
            SauceOptions.IdleTimeout.Should().BeEquivalentTo(new TimeSpan(3));
            SauceOptions.IedriverVersion.Should().BeEquivalentTo("3.141.0");
            SauceOptions.MaxDuration.Should().BeEquivalentTo(new TimeSpan(300));
            SauceOptions.Name.Should().BeEquivalentTo("Sample Test Name");
            SauceOptions.ParentTunnel.Should().BeEquivalentTo("Mommy");
            SauceOptions.Prerun.Should().BeEquivalentTo(prerun);
            SauceOptions.Priority.Should().BeEquivalentTo(0);
            SauceOptions.Public.Should().BeEquivalentTo("team");
            SauceOptions.RecordLogs.Should().BeFalse;
            SauceOptions.RecordScreenshots.Should().BeFalse;
            SauceOptions.RecordVideo.Should().BeFalse;
            SauceOptions.ScreenResolution.Should().BeEquivalentTo("10x10");
            SauceOptions.SeleniumVersion.Should().BeEquivalentTo("3.141.59");
            SauceOptions.Tags.Should().BeEquivalentTo(tags);
            SauceOptions.TimeZone.Should().BeEquivalentTo("San Francisco");
            SauceOptions.TunnelIdentifier.Should().BeEquivalentTo(tags);
            SauceOptions.VideoUploadOnPass.Should().BeFalse;
        }

        [TestMethod]
        public void AcceptsChromeOptionsClass()
        {
            var options = new ChromeOptions();
            SauceOptions = new SauceOptions(options);

            SauceOptions.BrowserName.Should().BeEquivalentTo("chrome");
            SauceOptions.SeleniumOptions.Should().BeEquivalentTo(options);
        }

        [TestMethod]
        public void AcceptsEdgeOptionsClass()
        {
            var options = new EdgeOptions();
            SauceOptions = new SauceOptions(options);

            SauceOptions.BrowserName.Should().BeEquivalentTo("MicrosoftEdge");
            SauceOptions.SeleniumOptions.Should().BeEquivalentTo(options);
        }

        [TestMethod]
        public void AcceptsFirefoxOptionsClass()
        {
            var options = new FirefoxOptions();
            SauceOptions = new SauceOptions(options);

            SauceOptions.BrowserName.Should().BeEquivalentTo("firefox");
            SauceOptions.SeleniumOptions.Should().BeEquivalentTo(options);
        }

        [TestMethod]
        public void AcceptsInternetExplorerOptionsClass()
        {
            var options = new InternetExplorerOptions();
            SauceOptions = new SauceOptions(options);

            SauceOptions.BrowserName.Should().BeEquivalentTo("internet explorer");
            SauceOptions.SeleniumOptions.Should().BeEquivalentTo(options);
        }

        [TestMethod]
        public void AcceptsSafariOptionsClass()
        {
            var options = new SafariOptions();
            SauceOptions = new SauceOptions(options);

            SauceOptions.BrowserName.Should().BeEquivalentTo("safari");
            SauceOptions.SeleniumOptions.Should().BeEquivalentTo(options);
        }

        [TestMethod]
        public void CreatesDefaultBuildName()
        {
            Environment.SetEnvironmentVariable("BUILD_TAG", "Not Empty");
            Environment.SetEnvironmentVariable("BUILD_NAME", "TEMP BUILD");
            Environment.SetEnvironmentVariable("BUILD_NUMBER", "11");
            
            SauceOptions.Build.Should().BeEquivalentTo("TEMP BUILD: 11");
        }

        [TestMethod]
        public void MergesCapabilitiesFromDictionary()
        {
            var capabilities = new Dictionary<string, object>();
            // Add all the capabilities here
            capabilities.Add("key", "value");
            SauceOptions.SetCapabilities(capabilities);
            SauceOptions.Build.Should().BeEquivalentTo(build);
        }

        [TestMethod]
        public void ParsesCapabilitiesFromW3CValues()
        {
            SauceOptions.BrowserName = "firefox";
            SauceOptions.BrowserVersion = "68";
            SauceOptions.PlatformName = "macOS 10.13";
            SauceOptions.PageLoadStrategy = "eager";
            SauceOptions.AcceptInsecureCerts = true;
            SauceOptions.SetWindowRect = true;
            SauceOptions.Timeouts.Implicit = new TimeSpan(4);
            SauceOptions.Timeouts.PageLoad = new TimeSpan(44);
            SauceOptions.Timeouts.Script = new TimeSpan(33);
            var proxy = new Proxy();
            SauceOptions.Proxy = proxy;
            SauceOptions.StrictFileInteractability = true;
            SauceOptions.UnhandledPromptBehavior = "dismiss";
            
            var timeouts = new Dictionary<string, TimeSpan>();
            timeouts.Add("implicit", new TimeSpan(4));
            timeouts.Add("pageLoad", new TimeSpan(44));
            timeouts.Add("script", new TimeSpan(33));
            
            var expectedCapabilities = new Dictionary<string, object>();
            
            SauceOptions.ToCapabilities.Should().BeEquivalentTo(expectedCapabilities);
        }

        [TestMethod]
        public void ParsesCapabilitiesFromSauceLabsValues()
        {
            var customData = new Dictionary<string, string> {{"foo", "foo"}, {"bar", "bar"}};

            var args = new List<string> {"--silent", "-a", "-q"};

            var prerun = new Dictionary<string, object>
            {
                {"executable", "http://url.to/your/executable.exe"},
                {"args", args},
                {"background", false},
                {"timeout", new TimeSpan(120)}
            };

            var tags = new List<string> {"foo", "bar"};

            SauceOptions.AvoidProxy = true;
            SauceOptions.Build = "Sample Build Name";
            SauceOptions.CapturePerformance = true;
            SauceOptions.ChromedriverVersion = "71";
            SauceOptions.CommandTimeout = new TimeSpan(2);
            SauceOptions.CustomData = customData;
            SauceOptions.ExtendedDebugging = true;
            SauceOptions.IdleTimeout = new TimeSpan(3);
            SauceOptions.IedriverVersion = "3.141.0";
            SauceOptions.MaxDuration = new TimeSpan(300);
            SauceOptions.Name = "Sample Test Name";
            SauceOptions.ParentTunnel = "Mommy";
            SauceOptions.Prerun = prerun;
            SauceOptions.Priority = 0;
            SauceOptions.Public = "team";
            SauceOptions.RecordLogs = false;
            SauceOptions.RecordScreenshots = false;
            SauceOptions.RecordVideo = false;
            SauceOptions.ScreenResolution = "10x10";
            SauceOptions.SeleniumVersion = "3.141.59";
            SauceOptions.Tags = tags;
            SauceOptions.TimeZone = "San Francisco";
            SauceOptions.TunnelIdentifier = tags;
            SauceOptions.VideoUploadOnPass = false;

            var expectedCapabilities = new Dictionary<string, object>();
            
            SauceOptions.ToCapabilities.Should().BeEquivalentTo(expectedCapabilities);
        }

        [TestMethod]
        public void ParsesCapabilitiesFromSeleniumValues()
        {
            var options = new ChromeOptions();
            SauceOptions = new SauceOptions(options);

            var expectedCapabilities = new Dictionary<string, object>();
            
            SauceOptions.ToCapabilities.Should().BeEquivalentTo(expectedCapabilities);
        }

        [TestMethod]
        public void ParsesW3CAndSauceAndSeleniumValues()
        {
            var options = new ChromeOptions();
            SauceOptions = new SauceOptions(options);

            SauceOptions.PlatformName = "macOS 10.13";
            SauceOptions.PageLoadStrategy = "eager";
            SauceOptions.AcceptInsecureCerts = true;
            SauceOptions.AvoidProxy = true;
            SauceOptions.Build = "Sample Build Name";
            SauceOptions.CapturePerformance = true;

            var expectedCapabilities = new Dictionary<string, object>();
            
            SauceOptions.ToCapabilities.Should().BeEquivalentTo(expectedCapabilities);
        }

    }
}