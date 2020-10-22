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
        public static Browser Edge => new Browser("edge");
        public static Browser Safari => new Browser("safari");
        public static Browser IE => new Browser("internet explorer");
    }
}