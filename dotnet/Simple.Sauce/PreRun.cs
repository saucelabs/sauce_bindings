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
        public TimeSpan Timeout { get; set; }

        public Dictionary<string, int> ToDictionary()
        {
            var json = JsonConvert.SerializeObject(this, JsonUtils.SerializerSettings());
            var dictionary = JsonConvert.DeserializeObject<Dictionary<string, int>>(json, JsonUtils.SerializerSettings());
            return dictionary;
        }
    }
}
