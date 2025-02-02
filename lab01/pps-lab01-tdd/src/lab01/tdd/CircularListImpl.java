package lab01.tdd;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.IntStream;

public class CircularListImpl implements CircularList {

    private final int RESET_POSITION = -1;
    private final List<Integer> list;
    private int currentPosition;

    public CircularListImpl() {
        this.list = new ArrayList<>();
        this.reset();
    }

    @Override
    public void add(int element) {
        this.list.add(element);
    }

    @Override
    public int size() {
        return this.list.size();
    }

    @Override
    public boolean isEmpty() {
        return this.list.isEmpty();
    }

    @Override
    public Optional<Integer> next() {
        return isEmpty() ? Optional.empty()
                : Optional.of(this.list.get(this.getNextPosition(this.currentPosition+1, 0)));
    }

    @Override
    public Optional<Integer> previous() {
        return isEmpty() ? Optional.empty()
                : Optional.of(this.list.get(this.getNextPosition(this.currentPosition-1, this.size()-1)));
    }

    @Override
    public void reset() {
        this.currentPosition = RESET_POSITION;
    }

    @Override
    public Optional<Integer> next(SelectStrategy strategy) {

        return IntStream.concat(IntStream.range(this.currentPosition+1, this.size()), IntStream.range(0, this.currentPosition))
                        .filter(i -> strategy.apply(this.list.get(i)))
                        .boxed()
                        .findFirst()
                        .map(i -> this.list.get(this.getNextPosition(i, 0)));
    }

    private int getNextPosition(final int newPosition, final int resetPosition) {
        this.currentPosition = newPosition >= 0 && newPosition <= this.size()-1 ? newPosition : resetPosition;
        return this.currentPosition;
    }
}
