using FluentAssertions;
using Microsoft.VisualStudio.TestTools.UnitTesting;
using Moq;
using SimpleSauce;
[assembly: Parallelize(Workers = 100, Scope = ExecutionScope.MethodLevel)]

namespace SimpleSauceTests
{
    [TestClass]
    public class SauceSessionTests : BaseTest
    {

        [TestMethod]
        public void ShouldTakeSauceOptions()
        {
            sauceOptions = new SauceOptions();
            sauceSession = new SauceSession(sauceOptions);
            sauceSession.Should().NotBeNull();
        }
        [TestMethod]
        public void SauceSession_NoConstructorParam_OptionsInitialized()
        {
            sauceSession = new SauceSession();
            Assert.IsNotNull(sauceSession.Options);
        }
        [TestMethod]
        public void GetDataCenter_Default_IsWest()
        {
            sauceSession = new SauceSession();
            sauceSession.DataCenter.Should().BeEquivalentTo(DataCenter.UsWest);
        }
        [TestMethod]
        public void Start_Default_IsChrome()
        {
            var dummyManager = new Mock<IRemoteDriver>();
            sauceSession = new SauceSession(dummyManager.Object);

            sauceSession.Start();

            sauceSession.ChromeOptions.Should().NotBeNull();
        }
        [TestMethod]
        public void Start_WithEdge_SetsEdgeBrowser()
        {
            sauceOptions = new SauceOptions();
            sauceOptions.WithEdge();
            var dummyManager = new Mock<IRemoteDriver>();
            sauceSession = new SauceSession(sauceOptions, dummyManager.Object);

            sauceSession.Start();

            sauceSession.Options.EdgeOptions.Should().NotBeNull();
        }
    }
}
