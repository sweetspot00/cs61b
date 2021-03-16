package synthesizer;
import org.junit.Test;
import static org.junit.Assert.*;

/** Tests the ArrayRingBuffer class.
 *  @author Josh Hug
 */

public class TestArrayRingBuffer {
    @Test
    public void someTest() {
        ArrayRingBuffer<Integer> arb = new ArrayRingBuffer<Integer>(5);
        arb.enqueue(15);
        arb.enqueue(23);
        arb.enqueue(2);
        arb.enqueue(0);
        int actual = arb.dequeue();
        int expected = 15;
        assertEquals(expected,actual);
        actual = arb.peek();
        assertEquals(23,actual);
        arb.dequeue();
        arb.dequeue();
        arb.dequeue();
        assertTrue(arb.isEmpty());
        arb.enqueue(1);
        arb.enqueue(4);

//        actual = arb.peek();
//        assertEquals(0, actual);
        arb.enqueue(6);
        arb.enqueue(8);
        arb.enqueue(0);
        actual = arb.fillCount();
        assertEquals(5,actual);
    }
}
