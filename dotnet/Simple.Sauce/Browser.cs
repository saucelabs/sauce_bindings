using OpenQA.Selenium;
using OpenQA.Selenium.Chrome;
using OpenQA.Selenium.Edge;
using OpenQA.Selenium.Firefox;
using OpenQA.Selenium.Safari;

namespace Sauce.Bindings
{
    public class Browser
    {
        private Browser(string browserName)
        {
            Value = browserName;
        }

        public string Value { get; set; }
        public static Browser Chrome => new Browser("chrome");
        public static Browser Edge => new Browser("edge");
        public static Browser Firefox => new Browser("firefox");
        public static Browser Safari => new Browser("safari");
    }
}