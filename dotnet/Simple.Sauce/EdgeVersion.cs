namespace Sauce.Bindings
{
    public class EdgeVersion
    {
        private EdgeVersion(string value)
        {
            Value = value;
        }

        public string Value { get; set; }
        public static EdgeVersion Latest => new EdgeVersion("latest");
        public static EdgeVersion _85 => new EdgeVersion("85.0");
        public static EdgeVersion _84 => new EdgeVersion("84.0");
        public static EdgeVersion _83 => new EdgeVersion("83.0");
        public static EdgeVersion _81 => new EdgeVersion("81.0");
        public static EdgeVersion _80 => new EdgeVersion("80.0");
        public static EdgeVersion _79 => new EdgeVersion("79.0");
        public static EdgeVersion _18 => new EdgeVersion("18.17763");
        public static EdgeVersion _17 => new EdgeVersion("17.17134");
        public static EdgeVersion _16 => new EdgeVersion("16.16299");
        public static EdgeVersion _15 => new EdgeVersion("15.15063");
        public static EdgeVersion _14 => new EdgeVersion("14.14393");
        public static EdgeVersion _13 => new EdgeVersion("13.10586");
    }
}