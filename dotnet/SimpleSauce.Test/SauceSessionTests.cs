using Microsoft.VisualStudio.TestTools.UnitTesting;
using FluentAssertions;
using SimpleSauce;

namespace SimpleSauce.Test
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
    }
}
