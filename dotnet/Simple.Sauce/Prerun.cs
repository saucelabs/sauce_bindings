using System;
using System.Collections.Generic;
using System.Text;

namespace Sauce.Bindings
{
    public class Prerun
    {
        public string Executable { get; set; }
        public List<string> Args { get; set; }
        public bool Background { get; set; }
        public int Timeout { get; set; }
    }
}
