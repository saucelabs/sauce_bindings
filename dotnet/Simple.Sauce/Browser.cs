namespace Simple.Sauce
{
    public class Browser
    {
        private Browser(string browserName)
        {
            Value = browserName;
        }

        private string Value { get; set; }

        public static Browser Chrome { get; } = new Browser("chrome");
        public static Browser Firefox { get; } = new Browser("firefox");
        public static Browser Safari { get; } = new Browser("safari");
        public static Browser Edge { get; } = new Browser("MicrosoftEdge");
        public static Browser IE { get; } = new Browser("internet explorer");


        public override string ToString()
        {
            return Value;
        }
    }
}