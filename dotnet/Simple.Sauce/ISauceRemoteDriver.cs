using OpenQA.Selenium;

namespace Simple.Sauce
{
    public interface ISauceRemoteDriver : IJavaScriptExecutor, IWebDriver
    {
        IWebDriver CreateRemoteWebDriver(DriverOptions options);
    }
}