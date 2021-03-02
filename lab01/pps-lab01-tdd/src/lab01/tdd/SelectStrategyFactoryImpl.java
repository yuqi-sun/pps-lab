package lab01.tdd;

public class SelectStrategyFactoryImpl implements SelectStrategyFactory {
    @Override
    public SelectStrategy createEvenStrategy() {
        return createMultipleOfStrategy(2);
    }

    @Override
    public SelectStrategy createMultipleOfStrategy(int divisor) {
        return element -> element % divisor == 0;
    }

    @Override
    public SelectStrategy createEqualsStrategy(int value) {
        return element -> element == value;
    }
}
