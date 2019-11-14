using FluentAssertions;
using Microsoft.VisualStudio.TestTools.UnitTesting;
using SimpleSauce;

namespace SimpleSauceTests
{
    [TestClass]
    public class DataCenterTests
    {
        [TestMethod]
        public void ShouldContainUsWest()
        {
            DataCenter.UsWest.Should().NotBeNull();
        }
        [TestMethod]
        public void ShouldContainUsEast()
        {
            DataCenter.UsEast.Should().NotBeNull();
        }
        [TestMethod]
        public void ShouldContainEuCentral()
        {
            DataCenter.EuCental.Should().NotBeNull();
        }
    }
}
