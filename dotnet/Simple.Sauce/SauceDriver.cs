using System;
using System.Collections.ObjectModel;
using OpenQA.Selenium;
using OpenQA.Selenium.Remote;

namespace Simple.Sauce
{
    public class SauceDriver : ISauceRemoteDriver
    {
        private IWebDriver _driver; 
        public IWebDriver CreateRemoteWebDriver(DriverOptions browserOptions)
        {
            _driver = new RemoteWebDriver(new Uri("https://ondemand.saucelabs.com/wd/hub"),
                browserOptions.ToCapabilities(), TimeSpan.FromSeconds(600));
            return _driver;
        }

        public object ExecuteScript(string script, params object[] args)
        {
            return ((IJavaScriptExecutor) _driver).ExecuteScript(script, args);
        }

        public object ExecuteAsyncScript(string script, params object[] args)
        {
            throw new NotImplementedException();
        }

        public IWebElement FindElement(By @by)
        {
            throw new NotImplementedException();
        }

        public ReadOnlyCollection<IWebElement> FindElements(By @by)
        {
            throw new NotImplementedException();
        }

        public void Dispose()
        {
            throw new NotImplementedException();
        }

        public void Close()
        {
            throw new NotImplementedException();
        }

        public void Quit()
        {
            _driver.Quit();
        }

        public IOptions Manage()
        {
            throw new NotImplementedException();
        }

        public INavigation Navigate()
        {
            throw new NotImplementedException();
        }

        public ITargetLocator SwitchTo()
        {
            throw new NotImplementedException();
        }

        public string Url { get; set; }
        public string Title { get; }
        public string PageSource { get; }
        public string CurrentWindowHandle { get; }
        public ReadOnlyCollection<string> WindowHandles { get; }
    }
}