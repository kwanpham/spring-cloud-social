package quan.dev.stockservice.model;

import lombok.Data;

import java.util.List;

@Data
public class Root {

    public List<Datum> data;
    public String type;

    @Data
    public static class Datum{
        public double p;
        public String s;
        public long t;
        public double v;
    }

}
