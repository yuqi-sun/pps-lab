package lab01.tdd;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class CircularListImpl implements CircularList {

    private final List<Integer> list;
    private int currentPosition;

    public CircularListImpl() {
        this.list = new ArrayList<>();
        this.currentPosition = -1;
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
        this.updatePosition(currentPosition + 1, 0);
        return Optional.of(this.list.get(currentPosition));
    }

    @Override
    public Optional<Integer> previous() {
        this.updatePosition(currentPosition - 1, this.size() - 1);
        return Optional.of(this.list.get(currentPosition));
    }

    @Override
    public void reset() {
        this.currentPosition = -1;
    }

    @Override
    public Optional<Integer> next(SelectStrategy strategy) {
        return Optional.empty();
    }

    private void updatePosition(final int newPosition, final int resetPosition) {
        this.currentPosition = newPosition >= 0 && newPosition <= this.size() - 1 ? newPosition : resetPosition;
    }
}
