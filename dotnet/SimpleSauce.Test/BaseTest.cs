using Microsoft.VisualStudio.TestTools.UnitTesting;
using Sauce.Bindings;

[assembly: Parallelize(Workers = 100, Scope = ExecutionScope.MethodLevel)]

namespace SauceBindings.Test
{
    public class BaseTest
    {
        public SauceOptions SauceOptions { get; set; }
        public SauceSession SauceSession { get; set; }
    }
}