namespace SimpleSauce
{
    public class DataCenter
    {
        private DataCenter(string value) { Value = value; }

        public string Value { get; set; }

        public static DataCenter UsWest { get { return new DataCenter("https://ondemand.saucelabs.com/wd/hub"); } }

        public static DataCenter UsEast { get { return new DataCenter("https://ondemand.us-east-1.saucelabs.com/wd/hub"); } }

        public static object EuCental { get { return new DataCenter("https://ondemand.eu-central-1.saucelabs.com/wd/hub"); } }
    }
}

