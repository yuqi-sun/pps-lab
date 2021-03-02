package lab01.tdd;

public interface SelectStrategyFactory {

    SelectStrategy createEvenStrategy();

    SelectStrategy createMultipleOfStrategy(int divisor);

    SelectStrategy createEqualsStrategy(int value);
}
