import static org.junit.Assert.*;
import org.junit.Test;

public class TestArrayDequeGold {

    @Test
    public void testDeque() {
        StudentArrayDeque<Integer> my = new StudentArrayDeque<Integer>();
        ArrayDequeSolution<Integer> corr = new ArrayDequeSolution<Integer>();
        int i = 0;
        StringBuilder message = new StringBuilder();
        while (i < 100) {
            double numberBetweenZeroAndOne = StdRandom.uniform();
            if (numberBetweenZeroAndOne < 0.25) {
                my.addFirst(i);
                corr.addFirst(i);
                message.append(String.format("addFirst(%d)\n", i));
            } else if (numberBetweenZeroAndOne < 0.5 && numberBetweenZeroAndOne >= 0.25) {
                my.addLast(i);
                corr.addLast(i);
                message.append(String.format("addLast(%d)\n", i));
            } else if (numberBetweenZeroAndOne >= 0.5 && numberBetweenZeroAndOne < 0.75) {
                if (my.size() == 0) {
                    continue;
                }
                Integer myx = my.removeLast();
                Integer cx = corr.removeLast();
                message.append("removeLast()\n");
                assertEquals(cx, myx);
                fail(message.toString());

            } else {
                if (my.size() == 0) {
                    continue;
                }
                Integer myx = my.removeFirst();
                Integer cx = corr.removeFirst();
                assertEquals(cx, myx);
                fail(message.toString());
            }
            i += 1;
//            System.out.print("my:");
//            my.printDeque();
//            System.out.print("correct:");
//            corr.printDeque();

        }


    }
}
