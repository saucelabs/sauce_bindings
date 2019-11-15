using FluentAssertions;
using Microsoft.VisualStudio.TestTools.UnitTesting;
using Moq;
using SimpleSauce;

namespace SimpleSauceTests
{
    [TestClass]
    public class SauceOptionsTests
    {
        [TestMethod]
        public void WithEdge_Possible()
        {
            SauceOptions options = new SauceOptions();
            options.WithEdge();
            options.EdgeOptions.Should().NotBeNull();
        }
    }
}
