namespace Simple.Sauce
{
    public class Timeout
    {
        public int Implicit { get; set; }
        public int PageLoad { get; set; }
        public int Script { get; set; }
        public int CommandTimeout { get; set; }
        public int IdleTimeout { get; set; }
        public int MaxDuration { get; set; }
    }
}