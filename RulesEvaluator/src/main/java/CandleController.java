import DataStructures.FixedSizeQueue;

public class CandleController {

    private static final CandleController instance = new CandleController();
    private int size = 5;
    private FixedSizeQueue<Candle> candlesQueue = new FixedSizeQueue<>(size);
    //TODO: think about different coins...

    public static CandleController getInstance() {
        return instance;
    }

    private CandleController() {
        for (int i = 0; i < size; i++)
            candlesQueue.enqueue(new Candle());
    }

    public void addNewCandle(Candle newCandle) {
        candlesQueue.enqueue(newCandle);
    }

    public double calculateSMA(int interval, Candle.Item item) {
        double result = 0;
        for (int i = 0; i < interval; i++) {
            result += candlesQueue.get(size - 1 - i).getItem(item);
        }
        return result / interval;
    }

    public Candle getLastCandle() {
        return candlesQueue.get(size - 1);
    }
}
