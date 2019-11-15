using FluentAssertions;
using Microsoft.VisualStudio.TestTools.UnitTesting;
using Moq;
using SimpleSauce;
[assembly: Parallelize(Workers = 100, Scope = ExecutionScope.MethodLevel)]

namespace SimpleSauceTests
{
    [TestClass]
    public class SauceSessionTests
    {
        [TestMethod]
        public void ShouldTakeSauceOptions()
        {
            SauceOptions options = new SauceOptions();
            var session = new SauceSession(options);
            session.Should().NotBeNull();
        }
        [TestMethod]
        public void SauceSession_NoConstructorParam_OptionsInitialized()
        {
            var session = new SauceSession();
            Assert.IsNotNull(session.Options);
        }
        [TestMethod]
        public void GetDataCenter_Default_IsWest()
        {
            var session = new SauceSession();
            session.DataCenter.Should().BeEquivalentTo(DataCenter.UsWest);
        }
        [TestMethod]
        public void Start_Default_IsChrome()
        {
            var dummyManager = new Mock<IRemoteDriver>();
            var session = new SauceSession(dummyManager.Object);

            session.Start();

            session.ChromeOptions.Should().NotBeNull();
        }
        [TestMethod]
        public void Start_WithEdge_SetsEdgeBrowser()
        {
            var options = new SauceOptions();
            options.WithEdge();
            var dummyManager = new Mock<IRemoteDriver>();
            var session = new SauceSession(options, dummyManager.Object);

            session.Start();

            session.Options.EdgeOptions.Should().NotBeNull();
        }
    }
}
