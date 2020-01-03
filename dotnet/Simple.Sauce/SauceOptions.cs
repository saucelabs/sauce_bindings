using System;
using System.Collections.Generic;
using System.Numerics;
using Newtonsoft.Json;
using OpenQA.Selenium;
using OpenQA.Selenium.Chrome;
using OpenQA.Selenium.Edge;
using OpenQA.Selenium.Remote;
using OpenQA.Selenium.Safari;

namespace Simple.Sauce
{
    public class SauceOptions
    {
        private readonly DriverOptions _seleniumCapabilities;

        public SauceOptions() : this(new ChromeOptions())
        {
        }

        public SauceOptions(DriverOptions options)
        {
            this.BrowserName = options.BrowserName;
            this._seleniumCapabilities = options;
        }

        public string BrowserName { get; set; } = "chrome";
        public string BrowserVersion { get; set; } = "latest";
        public string PlatformName { get; set; } = "Windows 10";
        public string PageLoadStrategy { get; set; }
        public bool AcceptInsecureCerts { get; set; }
        public Proxy Proxy { get; set; }
        public bool SetWindowRect { get; set; }
        public Dictionary<string, TimeSpan> Timeouts { get; set; }
        public bool StrictFileInteractability { get; set; }
        public string UnhandledPromptBehavior { get; set; }

        public DriverOptions ToCapabilities()
        {
            _seleniumCapabilities.BrowserVersion = this.BrowserVersion;
            _seleniumCapabilities.PlatformName = this.PlatformName;
            var sauceCapabilities = new Dictionary<string, object>();
            _seleniumCapabilities.AddAdditionalOption("sauce:options", sauceCapabilities);

            return _seleniumCapabilities;
        }
        
        public override string ToString()
        {
            return this.ToCapabilities().ToString();
        }


    }
}