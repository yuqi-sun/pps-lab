import lab01.tdd.CircularList;
import lab01.tdd.CircularListImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.stream.IntStream;

import static org.junit.jupiter.api.Assertions.*;

/**
 * The test suite for testing the CircularList implementation
 */
public class CircularListTest {

    //TODO: test implementation

    /*@Disabled
    @Test public void testTodo(){
        Assertions.fail();
    }*/

    private CircularList list;

    @BeforeEach
    void beforeEach() {
        this.list = new CircularListImpl();
    }

    @Test
    void testAdd(){
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
    void testIsNotEmpty(){
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
}
