package lab01.tdd;

public class MultipleOfStrategy implements SelectStrategy{

    final private int divisor;

    MultipleOfStrategy(final int divisor) {
        this.divisor = divisor;
    }

    @Override
    public boolean apply(int element) {
        return element % divisor == 0;
    }
}
