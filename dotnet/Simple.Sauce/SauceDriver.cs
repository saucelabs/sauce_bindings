using System;
using System.Collections.ObjectModel;
using OpenQA.Selenium;
using OpenQA.Selenium.Remote;

namespace Sauce.Bindings
{
    public class SauceDriver : ISauceRemoteDriver
    {
        private IWebDriver _driver;

        public IWebDriver CreateRemoteWebDriver(DriverOptions options)
        {
            return CreateRemoteWebDriver(DataCenter.UsWest, options, 600);
        }

        public IWebDriver CreateRemoteWebDriver(DriverOptions options, int commandTimeout)
        {
            return CreateRemoteWebDriver(DataCenter.UsWest, options, commandTimeout);
        }

        public IWebDriver CreateRemoteWebDriver(DataCenter dataCenter, DriverOptions options)
        {
            return CreateRemoteWebDriver(dataCenter, options, 600);
        }

        public IWebDriver CreateRemoteWebDriver(DataCenter dataCenter, DriverOptions options, int commandTimeout)
        {
            _driver = new RemoteWebDriver(new Uri(dataCenter.Value),
                options.ToCapabilities(), TimeSpan.FromSeconds(commandTimeout));
            return _driver;
        }

        public object ExecuteScript(string script, params object[] args)
        {
            return ((IJavaScriptExecutor)_driver).ExecuteScript(script, args);
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
            _driver?.Dispose();
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