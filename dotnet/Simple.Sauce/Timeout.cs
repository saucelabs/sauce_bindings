using Newtonsoft.Json;
using System.Collections.Generic;

namespace Sauce.Bindings
{
    public class Timeout
    {
        [JsonProperty("implicit")]
        public int Implicit { get; set; }
        [JsonProperty("pageload")]
        public int PageLoad { get; set; }
        [JsonProperty("script")]
        public int Script { get; set; }
        [JsonProperty("commandtimeout")]
        public int CommandTimeout { get; set; }
        [JsonProperty("idletimeout")]
        public int IdleTimeout { get; set; }
        [JsonProperty("maxduration")]
        public int MaxDuration { get; set; }

        public Dictionary<string, int> ToDictionary()
        {
            var json = JsonConvert.SerializeObject(this);
            var dictionary = JsonConvert.DeserializeObject<Dictionary<string, int>>(json);
            return dictionary;
        }
    }
}