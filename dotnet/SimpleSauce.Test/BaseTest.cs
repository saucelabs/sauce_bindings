using Microsoft.VisualStudio.TestTools.UnitTesting;
using SimpleSauce;
[assembly: Parallelize(Workers = 100, Scope = ExecutionScope.MethodLevel)]

namespace SimpleSauceTests
{
    public class BaseTest
    {
        public SauceOptions SauceOptions { get; set; }
        public SauceSession SauceSession { get; set; }
    }
}