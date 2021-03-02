package lab01.tdd;

public class EqualsStrategy implements SelectStrategy {

    final private int value;

    EqualsStrategy(final int value) {
        this.value = value;
    }
    @Override
    public boolean apply(int element) {
        return element == value;
    }
}
