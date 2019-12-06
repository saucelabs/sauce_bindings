namespace SimpleSauce.Test
{
    public class Platforms
    {
        private Platforms(string value)
        {
            Value = value;
        }

        public string Value { get; set; }

        public static Platforms MacOsMojave => new Platforms("macOS 10.14");
    }
}