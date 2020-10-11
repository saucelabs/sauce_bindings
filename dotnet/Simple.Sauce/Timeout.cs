using System.Collections.Generic;
using Newtonsoft.Json;

namespace Sauce.Bindings
{
    public class Timeout
    {
        [JsonProperty("implicit")]
        public int Implicit { get; set; }

        [JsonProperty("pageLoad")]
        public int PageLoad { get; set; }

        [JsonProperty("script")]
        public int Script { get; set; }

        [JsonProperty("commandTimeout")]
        public int CommandTimeout { get; set; } = 90;

        [JsonProperty("idleTimeout")]
        public int IdleTimeout { get; set; } = 300;

        [JsonProperty("maxDuration")]
        public int MaxDuration { get; set; } = 1800;

        public Dictionary<string, int> ToDictionary()
        {
            var json = JsonConvert.SerializeObject(this, JsonUtils.SerializerSettings());
            var dictionary = JsonConvert.DeserializeObject<Dictionary<string, int>>(json, JsonUtils.SerializerSettings());
            return dictionary;
        }
    }
}