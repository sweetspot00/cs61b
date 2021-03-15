import org.junit.Test;
import static org.junit.Assert.*;
public class ADTest {
    @Test
    public void testGet(){
        ArrayDeque<Integer> a = new ArrayDeque<Integer>();
//        a.addLast(12);
        a.addFirst(13);
        Integer b = 13;
        assertEquals(b,a.get(0));

    }
}
