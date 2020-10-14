using System;

namespace Sauce.Bindings
{
    public class IncorrectSafariVersionException : Exception
    {
        public IncorrectSafariVersionException() { }
        public IncorrectSafariVersionException(string invalidVersion) : base("Incorrect version specified => " + invalidVersion) { }
    }
}