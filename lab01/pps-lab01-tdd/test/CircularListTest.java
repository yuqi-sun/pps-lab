import lab01.tdd.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Optional;
import java.util.stream.IntStream;

import static org.junit.jupiter.api.Assertions.*;

/**
 * The test suite for testing the CircularList implementation
 */
public class CircularListTest {

    final static private int NUM_ELEMENT = 3;
    private CircularList list;
    private final SelectStrategyFactory selectStrategyFactory = new SelectStrategyFactoryImpl();

    @BeforeEach
    void beforeEach() {
        this.list = new CircularListImpl();
    }

    @Test
    void testAdd() {
        this.list.add(1);
        assertFalse(this.list.isEmpty());
        assertEquals(1, this.list.size());
        assertEquals(Optional.of(1), this.list.next());
    }

    @Test
    void testSize() {
        this.fillList();
        assertEquals(NUM_ELEMENT, this.list.size());
    }

    @Test
    void testIsEmpty() {
        assertEquals(0, this.list.size());
        assertTrue(this.list.isEmpty());
        assertEquals(Optional.empty(), this.list.next());
        assertEquals(Optional.empty(), this.list.previous());
    }

    @Test
    void testNext() {
        this.fillList();
        IntStream.concat(IntStream.range(0, NUM_ELEMENT), IntStream.range(0,1))
                .forEach(i -> assertEquals(Optional.of(i), this.list.next()));
    }

    @Test
    void testPrevious() {
        this.fillList();
        IntStream.concat(IntStream.range(-NUM_ELEMENT+1, 1), IntStream.range(-NUM_ELEMENT+1, 0))
                 .map(i -> -i)
                 .forEach(i -> assertEquals(Optional.of(i), this.list.previous()));
    }

    @Test
    void testReset() {
        this.fillList();
        this.testNext();
        this.list.reset();
        assertEquals(Optional.of(0), this.list.next());
    }

    /* test with strategy */
    @Test
    void testNextEven() {
        this.fillList();
        final SelectStrategy evenStrategy = this.selectStrategyFactory.createEvenStrategy();
        IntStream.concat(IntStream.range(0, NUM_ELEMENT), IntStream.range(0, NUM_ELEMENT))
                .filter(i -> i%2 == 0)
                .forEach(i -> assertEquals(Optional.of(i),
                        this.list.next(evenStrategy)));
    }

    @Test
    void testEmptyEven() {
        this.list.add(1);
        this.list.add(3);

        assertEquals(Optional.empty(), this.list.next(this.selectStrategyFactory.createEvenStrategy()));
    }

    @Test
    void testMultipleOf() {
        this.fillList();
        final int divisor = 2;
        final SelectStrategy multipleOfStrategy = this.selectStrategyFactory.createMultipleOfStrategy(divisor);
        IntStream.concat(IntStream.range(0, NUM_ELEMENT), IntStream.range(0, NUM_ELEMENT))
                .filter(i -> i%divisor == 0)
                .forEach(i -> assertEquals(Optional.of(i),
                        this.list.next(multipleOfStrategy)));
    }

    @Test
    void testEmptyMultipleOf() {
        this.list.add(1);
        this.list.add(3);

        assertEquals(Optional.empty(), this.list.next(this.selectStrategyFactory.createMultipleOfStrategy(7)));
    }

    @Test
    void testEquals() {
        final int equalValue = 0;
        this.fillList();
        this.list.add(equalValue);

        final SelectStrategy equalsStrategy = this.selectStrategyFactory.createEqualsStrategy(equalValue);
        IntStream.concat(IntStream.range(0, NUM_ELEMENT), IntStream.range(0, NUM_ELEMENT))
                .filter(i -> i == equalValue)
                .forEach(i -> assertEquals(Optional.of(i),
                        this.list.next(equalsStrategy)));
    }

    @Test
    void testEmptyEquals() {
        this.fillList();
        assertEquals(Optional.empty(), this.list.next(this.selectStrategyFactory.createEqualsStrategy(5)));
    }

    private void fillList() {
        IntStream.range(0, NUM_ELEMENT)
                 .forEach(i -> this.list.add(i));
    }
}
