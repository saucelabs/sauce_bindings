namespace Simple.Sauce
{
    public class SauceDataCenter
    {
        private SauceDataCenter(string value)
        {
            Value = value;
        }

        public string Value { get; set; }

        public static SauceDataCenter UsWest => new SauceDataCenter("ondemand.us-west-1.saucelabs.com/wd/hub");

        public static SauceDataCenter UsEast => new SauceDataCenter("ondemand.us-east-1.saucelabs.com/wd/hub");

        public static object EuCental => new SauceDataCenter("ondemand.eu-central-1.saucelabs.com/wd/hub");
    }
}