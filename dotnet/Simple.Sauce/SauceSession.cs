﻿using OpenQA.Selenium;
using System;
using System.Collections.Generic;

namespace Sauce.Bindings
{
    public class SauceSession
    {
        private Uri _sauceUrl;

        public SauceSession()
        {
            Driver = new SauceDriver();
            Options = new SauceOptions();
        }

        public SauceSession(ISauceRemoteDriver driver)
        {
            Driver = driver;
            Options = new SauceOptions();
        }

        public SauceSession(SauceOptions options)
        {
            Options = options;
            Driver = new SauceDriver();
        }

        public SauceSession(SauceOptions options, ISauceRemoteDriver driver)
        {
            Options = options;
            Driver = driver;
        }

        public DataCenter DataCenter { get; set; } = DataCenter.UsWest;
        public SauceOptions Options { get; }

        public ISauceRemoteDriver Driver { get; set; }

        public IWebDriver Start()
        {
            if (Options.BrowserName.Value == Browser.Firefox.Value)
                return CreateFirefoxDriver();
            if (!string.IsNullOrEmpty(Options.ConfiguredSafariOptions.BrowserVersion))
                return CreateSafariDriver();
            if (!string.IsNullOrEmpty(Options.ConfiguredFirefoxOptions.BrowserVersion))
                return CreateFirefoxDriver();
            return CreateChromeDriver();
        }

        private IWebDriver CreateFirefoxDriver()
        {
            var sauceUserName = Environment.GetEnvironmentVariable("SAUCE_USERNAME");
            var sauceAccessKey = Environment.GetEnvironmentVariable("SAUCE_ACCESS_KEY");
            var sauceConfiguration = new Dictionary<string, object>
            {
                ["username"] = sauceUserName,
                ["accessKey"] = sauceAccessKey
            };

            Options.ConfiguredFirefoxOptions.AddAdditionalOption("sauce:options", sauceConfiguration);
            return Driver.CreateRemoteWebDriver(Options.ConfiguredFirefoxOptions);
        }

        private IWebDriver CreateSafariDriver()
        {
            var sauceUserName = Environment.GetEnvironmentVariable("SAUCE_USERNAME");
            var sauceAccessKey = Environment.GetEnvironmentVariable("SAUCE_ACCESS_KEY");
            var sauceConfiguration = new Dictionary<string, object>
            {
                ["username"] = sauceUserName,
                ["accessKey"] = sauceAccessKey
            };

            Options.ConfiguredSafariOptions.AddAdditionalOption("sauce:options", sauceConfiguration);
            return Driver.CreateRemoteWebDriver(Options.ConfiguredSafariOptions);
        }

        private IWebDriver CreateChromeDriver()
        {
            var sauceUserName = Environment.GetEnvironmentVariable("SAUCE_USERNAME");
            var sauceAccessKey = Environment.GetEnvironmentVariable("SAUCE_ACCESS_KEY");
            var sauceConfiguration = new Dictionary<string, object>
            {
                ["username"] = sauceUserName,
                ["accessKey"] = sauceAccessKey
            };

            Options.ConfiguredChromeOptions.AddAdditionalOption("sauce:options", sauceConfiguration);
            return Driver.CreateRemoteWebDriver(Options.ConfiguredChromeOptions);
        }

        private IWebDriver CreateEdgeBrowser()
        {
            var sauceUserName = Environment.GetEnvironmentVariable("SAUCE_USERNAME");
            var sauceAccessKey = Environment.GetEnvironmentVariable("SAUCE_ACCESS_KEY");
            var sauceConfiguration = new Dictionary<string, object>
            {
                ["username"] = sauceUserName,
                ["accessKey"] = sauceAccessKey
            };

            Options.ConfiguredEdgeOptions.AddAdditionalOption("sauce:options", sauceConfiguration);
            return Driver.CreateRemoteWebDriver(Options.ConfiguredEdgeOptions);
        }

        public void Stop(bool isPassed)
        {
            if (Driver is null)
                return;
            var script = "sauce:job-result=" + (isPassed ? "passed" : "failed");
            Driver.ExecuteScript(script);
            Driver.Quit();
        }

        public Uri GetSauceUrl()
        {
            return _sauceUrl ?? new Uri(DataCenter.Value);
        }
    }
}