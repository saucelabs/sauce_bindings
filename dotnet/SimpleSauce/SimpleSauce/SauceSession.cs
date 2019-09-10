using OpenQA.Selenium.Chrome;
using OpenQA.Selenium.Remote;

namespace SimpleSauce.Tests
{
    public class SauceSession
    {
        public SauceSession()
        {
        }
        public RemoteWebDriver Start()
        {
            var options = new ChromeOptions();
            return new RemoteWebDriver(options);
        }
    }
}