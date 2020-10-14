using System.Collections.Generic;
using Newtonsoft.Json;

namespace Sauce.Bindings
{
    public class Timeout
    {
        public int Implicit { get; set; }

        public int PageLoad { get; set; }

        public int Script { get; set; }

        public int CommandTimeout { get; set; } = 90;

        public int IdleTimeout { get; set; } = 300;

        public int MaxDuration { get; set; } = 1800;

        public Dictionary<string, int> ToDictionary()
        {
            var json = JsonConvert.SerializeObject(this, JsonUtils.SerializerSettings());
            var dictionary = JsonConvert.DeserializeObject<Dictionary<string, int>>(json, JsonUtils.SerializerSettings());
            return dictionary;
        }
    }
}