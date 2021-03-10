import org.junit.Test;

import static org.junit.Assert.*;


public class testFilk {
    @Test
    public void testIsSameNumber(){
        Integer A = 128;
        Integer B = 128;
        assertTrue(Flik.isSameNumber(A,B));
    }
}
