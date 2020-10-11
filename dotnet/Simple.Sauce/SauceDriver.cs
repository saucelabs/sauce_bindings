using System;
using System.Collections.ObjectModel;
using OpenQA.Selenium;
using OpenQA.Selenium.Chrome;
using OpenQA.Selenium.DevTools.Debugger;
using OpenQA.Selenium.Edge;
using OpenQA.Selenium.Firefox;
using OpenQA.Selenium.Remote;
using OpenQA.Selenium.Safari;

namespace Sauce.Bindings
{
    public class SauceDriver : ISauceRemoteDriver
    {
        private IWebDriver _driver;

        public IWebDriver CreateRemoteWebDriver(DriverOptions options)
        {
            return CreateRemoteWebDriver(DataCenter.UsWest, options);
        }

        public IWebDriver CreateRemoteWebDriver(DataCenter dataCenter, DriverOptions options)
        {
            _driver = new RemoteWebDriver(new Uri(dataCenter.Value),
                options.ToCapabilities(), TimeSpan.FromSeconds(600));

            return _driver;
        }

        public object ExecuteScript(string script, params object[] args)
            => ((IJavaScriptExecutor)_driver).ExecuteScript(script, args);


        public object ExecuteAsyncScript(string script, params object[] args)
            => ((IJavaScriptExecutor)_driver).ExecuteAsyncScript(script, args);

        public ReadOnlyCollection<IWebElement> FindElements(By by)
                    => _driver.FindElements(by);

        public IWebElement FindElement(By by) => _driver.FindElement(by);
        public IOptions Manage() => _driver.Manage();
        public INavigation Navigate() => _driver.Navigate();
        public ITargetLocator SwitchTo() => _driver.SwitchTo();
        public string Title => _driver.Title;
        public string PageSource => _driver.PageSource;
        public string CurrentWindowHandle => _driver.CurrentWindowHandle;
        public ReadOnlyCollection<string> WindowHandles => _driver.WindowHandles;

        public string Url
        {
            get { return _driver.Url; }
            set { _driver.Url = value; }
        }

        public void Dispose()
        {
            _driver?.Dispose();
        }

        public void Close()
        {
            _driver?.Close();
        }

        public void Quit()
        {
            _driver?.Quit();
        }
    }
}