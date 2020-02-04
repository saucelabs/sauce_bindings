namespace Simple.Sauce
{
    public class Browser
    {
        private Browser(string browserName)
        {
            Value = browserName;
        }

        private string Value { get; set; }

        public static Browser Chrome => new Browser("chrome");
        public static Browser Firefox => new Browser("firefox");
        public static Browser Safari => new Browser("safari");
        public static Browser Edge => new Browser("MicrosoftEdge");
        public static Browser IE => new Browser("internet explorer");


        public override string ToString()
        {
            return Value;
        }
    }
}