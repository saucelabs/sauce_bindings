using Microsoft.VisualStudio.TestTools.UnitTesting;
using Simple.Sauce;

namespace DotnetFramework.Test
{
    [TestClass]
    public class IntegrationTests
    {
        [TestMethod]
        public void ShouldCompile()
        {
            var sauce = new SauceSession();
            Assert.IsNotNull(sauce);
        }
    }
}
