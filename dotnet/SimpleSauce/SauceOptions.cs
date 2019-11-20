using OpenQA.Selenium.Edge;

namespace SimpleSauce
{
    public class SauceOptions
    {
        public EdgeOptions EdgeOptions { get; set; }

        public void WithEdge() => EdgeOptions = new EdgeOptions
        {
            BrowserVersion = "latest",
            PlatformName = "Windows 10"
        };
    }
}