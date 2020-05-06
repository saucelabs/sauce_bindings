using OpenQA.Selenium;
using System;

namespace Simple.Sauce
{
    public interface ISauceRemoteDriver : IJavaScriptExecutor, IWebDriver
    {
        IWebDriver CreateRemoteWebDriver(DriverOptions options);
        IWebDriver CreateRemoteWebDriver(Uri sauceUri, DriverOptions browserOptions);
    }
}