using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Text;

namespace SimpleSauce
{
    public class EdgeVersion
    {
        private EdgeVersion(string value) { Value = value; }

        public string Value { get; set; }

        public static EdgeVersion _18 { get { return new EdgeVersion("18.17763"); } }
        public static EdgeVersion _17 { get { return new EdgeVersion("17.17134"); } }

        public static EdgeVersion _16 { get { return new EdgeVersion("16.16299"); } }

        public static EdgeVersion _15 { get { return new EdgeVersion("15.15063"); } }

        public static EdgeVersion _14 { get { return new EdgeVersion("14.14393"); } }
        public static EdgeVersion _13 { get { return new EdgeVersion("13.10586"); } }


    }
}
