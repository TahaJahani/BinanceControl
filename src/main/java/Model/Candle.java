package Model;

public class Candle {

    public static enum Symbol {
        BTCUSD,
        ETHUSD,
        EOSUSD,
        XRPUSD,
    }

    private Symbol symbol;
    private String interval;
    private long open_time;
    private double open;
    private double high;
    private double low;
    private double close;
    private double volume;
    private double turnover;
    private double SMA;

    public Symbol getSymbol() {
        return symbol;
    }

    public String getInterval() {
        return interval;
    }

    public long getOpen_time() {
        return open_time;
    }

    public double getOpen() {
        return open;
    }

    public double getHigh() {
        return high;
    }

    public double getLow() {
        return low;
    }

    public double getClose() {
        return close;
    }

    public double getVolume() {
        return volume;
    }

    public double getTurnover() {
        return turnover;
    }

    public double getSMA() {
        return SMA;
    }
}
