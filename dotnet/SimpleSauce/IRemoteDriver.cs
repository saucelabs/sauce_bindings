using OpenQA.Selenium.Chrome;
using OpenQA.Selenium.Remote;

namespace SimpleSauce
{
    public interface IRemoteDriver
    {
        RemoteWebDriver CreateRemoteWebDriver(ChromeOptions chromeOptions);
    }
}