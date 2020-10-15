using System;
using System.Collections.Generic;
using OpenQA.Selenium;
using OpenQA.Selenium.Chrome;
using OpenQA.Selenium.Edge;
using OpenQA.Selenium.Firefox;
using OpenQA.Selenium.Safari;

namespace Sauce.Bindings
{
    public class SauceSession
    {
        #region Constructors
        public SauceSession() : this(new SauceOptions(), new SauceDriver(), DataCenter.UsWest) { }
        public SauceSession(DataCenter dataCenter) : this(new SauceOptions(), new SauceDriver(), dataCenter) { }
        public SauceSession(SauceOptions options) : this(options, new SauceDriver(), DataCenter.UsWest) { }
        public SauceSession(ISauceRemoteDriver driver) : this(new SauceOptions(), driver, DataCenter.UsWest) { }
        public SauceSession(SauceOptions options, DataCenter dataCenter) : this(options, new SauceDriver(), dataCenter) { }
        public SauceSession(SauceOptions options, ISauceRemoteDriver driver) : this(options, driver, DataCenter.UsWest) { }
        public SauceSession(ISauceRemoteDriver driver, DataCenter dataCenter) : this(new SauceOptions(), driver, dataCenter) { }
        public SauceSession(SauceOptions options, ISauceRemoteDriver driver, DataCenter dataCenter)
        {
            Options = options;
            Driver = driver;
            DataCenter = dataCenter;
        }
        #endregion

        #region Properties
        public DataCenter DataCenter { get; set; } = DataCenter.UsWest;
        public SauceOptions Options { get; }
        public ISauceRemoteDriver Driver { get; set; }
        #endregion

        public IWebDriver Start()
        {
            Options.ConfiguredOptions.AddAdditionalOption("sauce:options", Options.ToDictionary());
            return Driver.CreateRemoteWebDriver(DataCenter, Options.ConfiguredOptions);
        }

        public void Stop(bool isPassed)
        {
            var script = "sauce:job-result=" + (isPassed ? "passed" : "failed");
            Driver?.ExecuteScript(script);
            Driver?.Quit();
        }
    }
}
