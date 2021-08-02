public class Rule {

    public static enum Operator {
        EQUAL,
        LESS,
        MORE,
    }

    private String name;
    private Candle.Symbol symbol;
    private Candle.Item item;
    private int interval;
    private Operator operator;
    private double value;

    public boolean evaluate(double SMA) {
        return switch (operator) {
            case LESS -> SMA < value;
            case MORE -> SMA > value;
            case EQUAL -> Math.abs(SMA - value) < 0.00001;
        };
    }

    public String getName() {
        return name;
    }

    public Candle.Symbol getSymbol() {
        return symbol;
    }

    public Candle.Item getItem() {
        return item;
    }

    public int getInterval() {
        return interval;
    }

    public Operator getOperator() {
        return operator;
    }

    public double getValue() {
        return value;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSymbol(Candle.Symbol symbol) {
        this.symbol = symbol;
    }

    public void setItem(Candle.Item item) {
        this.item = item;
    }

    public void setInterval(int interval) {
        this.interval = interval;
    }

    public void setOperator(Operator operator) {
        this.operator = operator;
    }

    public void setValue(double value) {
        this.value = value;
    }
}
