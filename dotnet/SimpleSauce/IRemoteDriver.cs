using OpenQA.Selenium;
using OpenQA.Selenium.Chrome;

namespace SimpleSauce
{
    public interface IRemoteDriver
    {
        IWebDriver CreateRemoteWebDriver(ChromeOptions chromeOptions);
    }
}