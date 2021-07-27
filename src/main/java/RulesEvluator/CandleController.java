package RulesEvluator;

import Model.Candle;

import java.util.PriorityQueue;

public class CandleController {

    private PriorityQueue<Candle> candlesQueue = new PriorityQueue<>();
    //TODO: think about different coins...

    public CandleController() {
        candlesQueue.add(new Candle());
    }

    public void calculateCloseSMA(double newClose) {

    }

    public void calculateOpenSMA(double newOpen) {

    }

    public void calculateHighSMA(double newHigh) {

    }

    public void calculateLowSMA(double newLow) {

    }

    public void addNewCandle(Candle newCandle) {

    }
}
