using Newtonsoft.Json;
using System;
using System.Collections.Generic;
using System.Text;

namespace Sauce.Bindings
{
    public class PreRun
    {
        [JsonProperty("executable")]
        public string Executable { get; set; }

        [JsonProperty("args")]
        public List<string> Args { get; set; } = new List<string>();

        [JsonProperty("background")]
        public bool Background { get; set; }

        [JsonProperty("timeout")]
        public int Timeout { get; set; }
    }
}
