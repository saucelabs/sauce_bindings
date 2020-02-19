namespace Simple.Sauce
{
    public class DataCenter
    {
        private DataCenter(string value)
        {
            Value = value;
        }

        public string Value { get; set; }

        public static DataCenter UsWest => new DataCenter("ondemand.us-west-1.saucelabs.com");

        public static DataCenter UsEast => new DataCenter("ondemand.us-east-1.saucelabs.com");

        public static object EuCental => new DataCenter("ondemand.eu-central-1.saucelabs.com");
    }
}