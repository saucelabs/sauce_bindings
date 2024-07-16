namespace Sauce.Bindings
{
    public class DataCenter
    {
        private DataCenter(string value)
        {
            Value = value;
        }

        public string Value { get; set; }

        public static DataCenter UsWest => new DataCenter("https://ondemand.us-west-1.saucelabs.com/wd/hub");

        public static DataCenter UsEast => new DataCenter("https://ondemand.us-east-4.saucelabs.com/wd/hub");

        public static object EuCental => new DataCenter("https://ondemand.eu-central-1.saucelabs.com/wd/hub");
    }
}
