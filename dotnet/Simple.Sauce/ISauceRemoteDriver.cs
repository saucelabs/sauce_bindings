using OpenQA.Selenium;

namespace Simple.Sauce
{
    public interface ISauceRemoteDriver
    {
        IWebDriver CreateRemoteWebDriver(DriverOptions options);
    }
}