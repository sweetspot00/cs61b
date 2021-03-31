import edu.princeton.cs.algs4.Queue;

import org.junit.Test;
import static org.junit.Assert.*;

public class TestSortAlgs {

    @Test
    public void testQuickSort() {
        Queue<String> tas = new Queue<String>();
        tas.enqueue("Joe");
        tas.enqueue("Omar");
        tas.enqueue("Itai");
        tas.enqueue("Asf");
        tas.enqueue("Bafae");
        Queue<String> act = QuickSort.quickSort(tas);
        for (int i = 1; i < tas.size(); i += 1) {
            assertTrue(act.dequeue().compareTo(act.peek()) <= 0);
        }

    }

    @Test
    public void testMergeSort() {
        Queue<String> tas = new Queue<String>();
        tas.enqueue("Joe");
        tas.enqueue("Omar");
        tas.enqueue("Itai");
        tas.enqueue("Asf");
        tas.enqueue("Bafae");
        Queue<String> act = MergeSort.mergeSort(tas);
        for (int i = 1; i < tas.size(); i += 1) {
            assertTrue(act.dequeue().compareTo(act.peek()) <= 0);
        }


    }

    /**
     * Returns whether a Queue is sorted or not.
     *
     * @param items  A Queue of items
     * @return       true/false - whether "items" is sorted
     */
    private <Item extends Comparable> boolean isSorted(Queue<Item> items) {
        if (items.size() <= 1) {
            return true;
        }
        Item curr = items.dequeue();
        Item prev = curr;
        while (!items.isEmpty()) {
            prev = curr;
            curr = items.dequeue();
            if (curr.compareTo(prev) < 0) {
                return false;
            }
        }
        return true;
    }
}
