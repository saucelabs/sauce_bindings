using Microsoft.VisualStudio.TestTools.UnitTesting;
using Simple.Sauce;

namespace DotnetCore.Test
{
    [TestClass]
    public class PackageIntegration
    {
        [TestMethod]
        public void ShouldCompile()
        {
            var sauce = new SauceSession();
            Assert.IsNotNull(sauce);
        }
    }
}
