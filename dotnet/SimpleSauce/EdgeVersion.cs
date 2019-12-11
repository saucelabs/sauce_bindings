namespace SimpleSauce
{
    public class EdgeVersion
    {
        private EdgeVersion(string value)
        {
            Value = value;
        }

        public string Value { get; set; }

        public static EdgeVersion _18 => new EdgeVersion("18.17763");
        public static EdgeVersion _17 => new EdgeVersion("17.17134");

        public static EdgeVersion _16 => new EdgeVersion("16.16299");

        public static EdgeVersion _15 => new EdgeVersion("15.15063");

        public static EdgeVersion _14 => new EdgeVersion("14.14393");
        public static EdgeVersion _13 => new EdgeVersion("13.10586");
        public static EdgeVersion Latest => new EdgeVersion("latest");

    }
}