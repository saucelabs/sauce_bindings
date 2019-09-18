using Microsoft.VisualStudio.TestTools.UnitTesting;
using FluentAssertions;
using SimpleSauce;

namespace SimpleSauceTests
{
    [TestClass]
    public class SauceSessionTests
    {
        [TestMethod]
        public void ShouldReturnObject()
        {
            var session = new SauceSession();
            session.Should().NotBeNull();
        }
        [TestMethod]
        public void GetDataCenter_Default_IsWest()
        {
            var session = new SauceSession();
            session.DataCenter.Should().BeEquivalentTo(DataCenter.UsWest);
        }
    }
}
