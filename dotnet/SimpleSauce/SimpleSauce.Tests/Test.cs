using Microsoft.VisualStudio.TestTools.UnitTesting;
using SimpleSauce;

namespace SimpleSauce.Tests
{
    [TestClass]
    public class Test
    {
        [TestMethod]
        public void isNotNull()
        {
            var sauceSession = new SauceSession();
            Assert.IsNotNull(sauceSession);
        }
    }
}
