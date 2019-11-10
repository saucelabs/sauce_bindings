using OpenQA.Selenium;
using OpenQA.Selenium.Chrome;
using OpenQA.Selenium.Remote;

namespace SimpleSauce
{
    public interface IRemoteDriver
    {
        IWebDriver CreateRemoteWebDriver(ChromeOptions chromeOptions);
    }
}