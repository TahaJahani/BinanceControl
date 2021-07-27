package Model;

import java.io.Serializable;
import java.util.HashMap;

public class Candle implements Serializable {

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
    private double openSMA;
    private double closeSMA;
    private double highSMA;
    private double lowSMA;

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

    public double getOpenSMA() {
        return openSMA;
    }

    public double getCloseSMA() {
        return closeSMA;
    }

    public double getHighSMA() {
        return highSMA;
    }

    public double getLowSMA() {
        return lowSMA;
    }

    public void setOpenSMA(double openSMA) {
        this.openSMA = openSMA;
    }

    public void setCloseSMA(double closeSMA) {
        this.closeSMA = closeSMA;
    }

    public void setHighSMA(double highSMA) {
        this.highSMA = highSMA;
    }

    public void setLowSMA(double lowSMA) {
        this.lowSMA = lowSMA;
    }
}
