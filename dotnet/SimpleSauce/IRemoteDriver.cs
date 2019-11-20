using OpenQA.Selenium;

namespace SimpleSauce
{
    public interface IRemoteDriver
    {
        IWebDriver CreateRemoteWebDriver(DriverOptions options);
    }
}