import lab01.tdd.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.stream.IntStream;

import static org.junit.jupiter.api.Assertions.*;

/**
 * The test suite for testing the CircularList implementation
 */
public class CircularListTest {

    private CircularList list;
    private final SelectStrategyFactory selectStrategyFactory = new SelectStrategyFactoryImpl();

    @BeforeEach
    void beforeEach() {
        this.list = new CircularListImpl();
    }

    @Test
    void testAdd() {
        this.list.add(1);
        assertEquals(1, this.list.next().get());
    }

    @Test
    void testSize() {
        IntStream.range(0, 10)
                .forEach(i -> this.list.add(i));
        assertEquals(10, this.list.size());
    }

    @Test
    void testIsEmpty() {
        assertTrue(this.list.isEmpty());
    }

    @Test
    void testIsNotEmpty() {
        this.list.add(1);
        assertFalse(this.list.isEmpty());
    }

    @Test
    void testNext() {
        IntStream.range(0, 5)
                .peek(i -> this.list.add(i))
                .forEach(i -> assertEquals(i, this.list.next().get()));

        assertEquals(0, this.list.next().get());
    }

    @Test
    void testPrevious() {
        IntStream.range(0, 5)
                .forEach(i -> this.list.add(i));

        this.list.next();
        this.list.next();
        assertEquals(0, this.list.previous().get());
        assertEquals(4, this.list.previous().get());
    }

    @Test
    void testReset() {
        IntStream.range(0, 5)
                .forEach(i -> this.list.add(i));

        this.list.next();
        this.list.next();
        this.list.reset();

        assertEquals(0, this.list.next().get());
    }

    /* test with strategy */
    @Test
    void testNextEven() {
        IntStream.range(0, 3)
                .forEach(i -> this.list.add(i));

        this.list.next();
        assertEquals(2, this.list.next(this.selectStrategyFactory.createEvenStrategy()).get());
        assertEquals(0, this.list.next(this.selectStrategyFactory.createEvenStrategy()).get());
    }

    @Test
    void testMultipleOf() {
        IntStream.range(0, 3)
                .forEach(i -> this.list.add(i));

        this.list.next();

        assertEquals(2, this.list.next(this.selectStrategyFactory.createMultipleOfStrategy(2)).get());
        assertEquals(0, this.list.next(this.selectStrategyFactory.createMultipleOfStrategy(2)).get());
    }

    @Test
    void testEquals() {
        IntStream.range(0, 3)
                .forEach(i -> this.list.add(i));
        this.list.add(0);

        this.list.next();

        assertEquals(0, this.list.next(this.selectStrategyFactory.createEqualsStrategy(0)).get());
        assertEquals(0, this.list.next(this.selectStrategyFactory.createEqualsStrategy(0)).get());
    }
}
