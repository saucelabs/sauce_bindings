namespace SimpleSauce
{
    public class SafariVersion
    {
        private SafariVersion(string value)
        {
            Value = value;
        }

        public string Value { get; set; }

        public static SafariVersion _18 => new SafariVersion("18.17763");
        public static SafariVersion _17 => new SafariVersion("17.17134");

        public static SafariVersion _16 => new SafariVersion("16.16299");

        public static SafariVersion _15 => new SafariVersion("15.15063");

        public static SafariVersion _14 => new SafariVersion("14.14393");
        public static SafariVersion _13 => new SafariVersion("13.10586");
    }
}