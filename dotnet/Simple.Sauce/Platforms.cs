namespace Simple.Sauce
{
    public class Platforms
    {
        private Platforms(string value)
        {
            Value = value;
        }

        public string Value { get; set; }

        public static Platforms MacOsMojave => new Platforms("macOS 10.14");
        public static Platforms MacOsHighSierra => new Platforms("macOS 10.13");
        public static Platforms MacOsSierra => new Platforms("macOS 10.12");
        public static Platforms MacOsxElCapitan => new Platforms("OS X 10.11");
        public static Platforms MacOsxYosemite => new Platforms("OS X 10.10");
        public static Platforms Windows10 => new Platforms("Windows 10");
    }
}