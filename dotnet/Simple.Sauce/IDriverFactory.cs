using OpenQA.Selenium;

namespace Simple.Sauce
{
    public interface IDriverFactory
    {
        IWebDriver CreateRemoteWebDriver(SauceOptions options);
    }
}