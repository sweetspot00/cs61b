import static org.junit.Assert.*;
import org.junit.Test;

public class TestArrayDequeGold {

    @Test
    public void testDeque() {
        StudentArrayDeque<Integer> my = new StudentArrayDeque<Integer>();
        ArrayDequeSolution<Integer> corr = new ArrayDequeSolution<Integer>();

//        StringBuilder message = new StringBuilder();
        while (true) {
            double numberBetweenZeroAndOne = StdRandom.uniform();
            int number = StdRandom.uniform(10);
            if (numberBetweenZeroAndOne < 0.25) {
                my.addFirst(number);
                corr.addFirst(number);
//                message.append(String.format("addFirst(%d)\n", i));
                System.out.printf("addFirst(%d)\n",number);
            } else if (numberBetweenZeroAndOne < 0.5 && numberBetweenZeroAndOne >= 0.25) {
                my.addLast(number);
                corr.addLast(number);
//                message.append(String.format("addLast(%d)\n", i));
                System.out.printf("addFirst(%d)\n", number);
            } else if (numberBetweenZeroAndOne >= 0.5 && numberBetweenZeroAndOne < 0.75
                        && !my.isEmpty() && !corr.isEmpty()) {
                Integer myx = my.removeLast();
                Integer cx = corr.removeLast();
//                message.append("removeLast()\n");
                System.out.print("removeLast()\n");
                assertEquals(cx, myx);

            } else if (numberBetweenZeroAndOne >= 0.75 && !my.isEmpty() && !corr.isEmpty()) {
                if (my.size() == 0) {
                    continue;
                }
                Integer myx = my.removeFirst();
                Integer cx = corr.removeFirst();
                System.out.print("removeFirst()\n");
                assertEquals(cx, myx);

            }else {
                assertEquals(my.isEmpty(),corr.isEmpty());
            }
//            System.out.print("my:");
//            my.printDeque();
//            System.out.print("correct:");
//            corr.printDeque();

        }
    }

}
