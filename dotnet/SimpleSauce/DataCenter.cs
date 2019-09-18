namespace SimpleSauce
{
    public class DataCenter
    {
        private DataCenter(string value) { Value = value; }

        public string Value { get; set; }

        public static DataCenter UsWest { get { return new DataCenter("https://ondemand.saucelabs.com/wd/hub"); } }
    }
}

