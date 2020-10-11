using FluentAssertions;
using Microsoft.VisualStudio.TestTools.UnitTesting;
using Newtonsoft.Json;
using Sauce.Bindings;
using System;
using System.Collections.Generic;
using System.Text;

namespace SauceBindings.Test
{
    [TestClass]
    public class PreRunTests : BaseTest
    {
        [TestInitialize]
        public void Setup()
        {
            SauceOptions = new SauceOptions();
        }

        [TestMethod]
        public void PreRunTest()
        {
            SauceOptions.Prerun = new PreRun
            {
                Executable = "http://url.to/your/executable.exe",
                Args = new List<string> { "--silent", "-a", "-q" },
                Background = false,
                Timeout = 120
            };

            var sauceOptions = SauceOptions.ToDictionary();
            sauceOptions.Should().ContainKey("prerun");

            var saucePrerun = JsonConvert.DeserializeObject<PreRun>(sauceOptions["prerun"].ToString(), JsonUtils.SerializerSettings());
            saucePrerun.Executable.Should().Be("http://url.to/your/executable.exe");
            saucePrerun.Args.Should().Contain(new List<string> { "--silent", "-a", "-q" });
            saucePrerun.Background.Should().BeFalse();
            saucePrerun.Timeout.Should().Be(120);
        }
    }
}
