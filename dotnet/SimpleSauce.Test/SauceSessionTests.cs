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
            SauceOptions = new SauceOptions();
            SauceSession = new SauceSession(SauceOptions);
            SauceSession.Should().NotBeNull();
        }
        [TestMethod]
        public void SauceSession_OptionsPassedIn_SetsConcreteDriver()
        {
            SauceOptions = new SauceOptions();
            SauceSession = new SauceSession(SauceOptions);
            SauceSession.driverImplementation.Should().BeOfType(typeof(ConcreteRemoteWebDriver));
        }
        [TestMethod]
        public void SauceSession_NoConstructorParam_OptionsInitialized()
        {
            SauceSession = new SauceSession();
            Assert.IsNotNull(SauceSession.Options);
        }
        [TestMethod]
        public void GetDataCenter_Default_IsWest()
        {
            SauceSession = new SauceSession();
            SauceSession.DataCenter.Should().BeEquivalentTo(DataCenter.UsWest);
        }
        [TestMethod]
        public void Start_Default_IsChrome()
        {
            var dummyManager = new Mock<IRemoteDriver>();
            SauceSession = new SauceSession(dummyManager.Object);

            SauceSession.Start();

            SauceSession.ChromeOptions.Should().NotBeNull();
        }
        [TestMethod]
        public void Start_WithEdge_SetsEdgeBrowser()
        {
            SauceOptions = new SauceOptions();
            SauceOptions.WithEdge();
            var dummyManager = new Mock<IRemoteDriver>();
            SauceSession = new SauceSession(SauceOptions, dummyManager.Object);

            SauceSession.Start();

            SauceSession.Options.EdgeOptions.Should().NotBeNull();
        }
    }
}
