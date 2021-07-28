package RulesEvluator;

import Model.Candle;
import Model.FixedSizeQueue;

public class CandleController {

    private static final CandleController instance = new CandleController();
    private int size = 120;
    private FixedSizeQueue<Candle> candlesQueue = new FixedSizeQueue<>(size);
    //TODO: think about different coins...


    public static CandleController getInstance() {
        return instance;
    }

    public CandleController() {
        for (int i = 0; i < size; i++)
            candlesQueue.enqueue(new Candle());
    }

    public double calculateCloseSMA(double newClose) {
        double prevClose = candlesQueue.get(size - 1).getCloseSMA();
        return (prevClose * (size - 1) + newClose) / size;
    }

    public double calculateOpenSMA(double newOpen) {
        double prevOpen = candlesQueue.get(size - 1).getOpenSMA();
        return (prevOpen * (size - 1) + newOpen) / size;
    }

    public double calculateHighSMA(double newHigh) {
        double prevHigh = candlesQueue.get(size - 1).getHighSMA();
        return (prevHigh * (size - 1) + newHigh) / size;
    }

    public double calculateLowSMA(double newLow) {
        double prevLow = candlesQueue.get(size - 1).getLowSMA();
        return (prevLow * (size - 1) + newLow) / size;
    }

    public void addNewCandle(Candle newCandle) {
        updateSMAs();
        double closeSMA = calculateCloseSMA(newCandle.getClose());
        double openSMA = calculateOpenSMA(newCandle.getOpen());
        double highSMA = calculateHighSMA(newCandle.getHigh());
        double lowSMA = calculateLowSMA(newCandle.getLow());
        newCandle.setCloseSMA(closeSMA);
        newCandle.setHighSMA(highSMA);
        newCandle.setOpenSMA(openSMA);
        newCandle.setLowSMA(lowSMA);
        candlesQueue.enqueue(newCandle);
        //TODO: evaluate rules
    }

    public void updateSMAs() {
        Candle dropped = candlesQueue.get(0);
        for (int i = 1; i < size; i++) {
            Candle currentCandle = candlesQueue.get(i);
            int oldSize = i + 1;
            double newCloseSMA = ((currentCandle.getCloseSMA() * i) - dropped.getCloseSMA()) / i;
            double newOpenSMA = ((currentCandle.getOpenSMA() * i) - dropped.getOpenSMA()) / i;
            double newHighSMA = ((currentCandle.getHighSMA() * i) - dropped.getHighSMA()) / i;
            double newLowSMA = ((currentCandle.getLowSMA() * i) - dropped.getLowSMA()) / i;
            currentCandle.setCloseSMA(newCloseSMA);
            currentCandle.setOpenSMA(newOpenSMA);
            currentCandle.setHighSMA(newHighSMA);
            currentCandle.setLowSMA(newLowSMA);
        }
    }

    public Candle getCandleBefore(int i) {
        return candlesQueue.get((size - 1) - i);
    }

    public double calculateSMA(int interval, Candle.Item item) {
        double result = 0;
        for (int i = 0; i < interval; i++)
            result += candlesQueue.get(size - 1 - i).getItem(item);
        return result / interval;
    }
}
