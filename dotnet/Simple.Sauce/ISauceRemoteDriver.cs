using OpenQA.Selenium;

namespace Sauce.Bindings
{
    public interface ISauceRemoteDriver : IJavaScriptExecutor, IWebDriver
    {
        IWebDriver CreateRemoteWebDriver(DriverOptions options);
        IWebDriver CreateRemoteWebDriver(DriverOptions options, int commandTimeout);
        IWebDriver CreateRemoteWebDriver(DataCenter dataCenter, DriverOptions options);
        IWebDriver CreateRemoteWebDriver(DataCenter dataCenter, DriverOptions options, int commandTimeout);
    }
}