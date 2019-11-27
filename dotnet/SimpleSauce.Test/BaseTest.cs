using Microsoft.VisualStudio.TestTools.UnitTesting;

[assembly: Parallelize(Workers = 100, Scope = ExecutionScope.MethodLevel)]

namespace SimpleSauce.Test
{
    public class BaseTest
    {
        public SauceOptions SauceOptions { get; set; }
        public SauceSession SauceSession { get; set; }
    }
}