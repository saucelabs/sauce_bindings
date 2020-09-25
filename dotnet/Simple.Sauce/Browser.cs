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
        public static Browser Firefox => new Browser("firefox");
    }
}