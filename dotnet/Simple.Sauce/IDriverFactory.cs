using OpenQA.Selenium;

namespace Simple.Sauce
{
    public interface IDriverFactory : IJavaScriptExecutor, IWebDriver
    {
        IWebDriver CreateRemoteWebDriver(DriverOptions options);
    }
}