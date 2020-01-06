using System;
using OpenQA.Selenium;
using OpenQA.Selenium.Chrome;
using OpenQA.Selenium.Edge;
using OpenQA.Selenium.Firefox;
using OpenQA.Selenium.Remote;
using OpenQA.Selenium.Safari;

namespace Simple.Sauce
{
    public class DriverFactory : IDriverFactory
    {
        private SauceOptions _sauceOptions;
        private EdgeOptions _edgeOptions;
        private SafariOptions _safariOptions;
        private FirefoxOptions _firefoxOptions;
        private ChromeOptions _chromeOptions;

        private IWebDriver CreateRemoteWebDriver(DriverOptions browserOptions)
        {
            return new RemoteWebDriver(new Uri("https://ondemand.saucelabs.com/wd/hub"),
                browserOptions.ToCapabilities(), TimeSpan.FromSeconds(600));
        }

        public IWebDriver CreateRemoteWebDriver(SauceOptions sauceOptions)
        {
            _sauceOptions = sauceOptions;
            _edgeOptions = sauceOptions.ConfiguredEdgeOptions;
            _safariOptions = sauceOptions.ConfiguredSafariOptions;
            _firefoxOptions = sauceOptions.ConfiguredFirefoxOptions;
            _chromeOptions = sauceOptions.ConfiguredChromeOptions;
            if (!string.IsNullOrEmpty(_edgeOptions.BrowserVersion))
                return CreateEdgeBrowser();
            if (!string.IsNullOrEmpty(_safariOptions.BrowserVersion))
                return CreateSafariDriver();
            if (!string.IsNullOrEmpty(_firefoxOptions.BrowserVersion))
                return CreateFirefoxDriver();
            return CreateChromeDriver();
        }
        private IWebDriver CreateEdgeBrowser()
        {
            _edgeOptions.AddAdditionalOption("sauce:options", _sauceOptions._sauceConfiguration);
            return CreateRemoteWebDriver(_edgeOptions);
        }

        private IWebDriver CreateSafariDriver()
        {
            _safariOptions.AddAdditionalOption("sauce:options", _sauceOptions._sauceConfiguration);
            return CreateRemoteWebDriver(_safariOptions);
        }

        private IWebDriver CreateChromeDriver()
        {
            _chromeOptions.AddAdditionalOption("sauce:options", _sauceOptions._sauceConfiguration);
            return CreateRemoteWebDriver(_chromeOptions);
        }

        private IWebDriver CreateFirefoxDriver()
        {
            _firefoxOptions.AddAdditionalOption("sauce:options", _sauceOptions._sauceConfiguration);
            return CreateRemoteWebDriver(_firefoxOptions);
        }
    }
}