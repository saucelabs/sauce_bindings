using OpenQA.Selenium;

namespace SimpleSauce
{
    public interface ISauceRemoteDriver
    {
        IWebDriver CreateRemoteWebDriver(DriverOptions options);
    }
}