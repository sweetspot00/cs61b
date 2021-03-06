package hw3.hash;

import org.junit.Test;
import static org.junit.Assert.*;
import java.util.Random;
import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.HashSet;
import java.util.List;

public class TestSimpleOomage {

    @Test
    public void testHashCodeDeterministic() {
        SimpleOomage so = SimpleOomage.randomSimpleOomage();
        int hashCode = so.hashCode();
        for (int i = 0; i < 100; i += 1) {
            assertEquals(hashCode, so.hashCode());
        }
    }

    @Test
    public void testHashCodePerfect() {

        Random R = new Random();
        ArrayList<Integer> red = new ArrayList<>();
        Deque<Integer> blue = new LinkedList<>();
        for (int i = 0; i < 10; i += 1) {
            int r = R.nextInt(51);
            red.add(r * 5);
            blue.addFirst(r * 5);
        }
        int green = 15;
        SimpleOomage a = new SimpleOomage(red.get(0), green, blue.getFirst());
        SimpleOomage b = new SimpleOomage(red.get(0), green, blue.getFirst());
        SimpleOomage c = new SimpleOomage(red.get(2), green, blue.getLast());
        SimpleOomage d = new SimpleOomage(0, 0, 60);
        SimpleOomage e = new SimpleOomage(0, 5, 10);
        assertEquals(a.hashCode(), b.hashCode());
        assertNotEquals(a.hashCode(), c.hashCode());
        assertNotEquals(b.hashCode(), c.hashCode());
        assertNotEquals(d.hashCode(), e.hashCode());

    }

    @Test
    public void testEquals() {
        SimpleOomage ooA = new SimpleOomage(5, 10, 20);
        SimpleOomage ooA2 = new SimpleOomage(5, 10, 20);
        SimpleOomage ooB = new SimpleOomage(50, 50, 50);
        assertEquals(ooA, ooA2);
        assertNotEquals(ooA, ooB);
        assertNotEquals(ooA2, ooB);
        assertNotEquals(ooA, "ketchup");
    }


    @Test
    public void testHashCodeAndEqualsConsistency() {
        SimpleOomage ooA = new SimpleOomage(5, 10, 20);
        SimpleOomage ooA2 = new SimpleOomage(5, 10, 20);
        HashSet<SimpleOomage> hashSet = new HashSet<>();
        hashSet.add(ooA);
        assertTrue(hashSet.contains(ooA2));
    }

    @Test
    public void testRandomOomagesHashCodeSpread() {
        List<Oomage> oomages = new ArrayList<>();
        int N = 10000;

        for (int i = 0; i < N; i += 1) {
            oomages.add(SimpleOomage.randomSimpleOomage());
        }

        assertTrue(OomageTestUtility.haveNiceHashCodeSpread(oomages, 10));
    }

    /** Calls tests for SimpleOomage. */
    public static void main(String[] args) {
        jh61b.junit.textui.runClasses(TestSimpleOomage.class);
    }
}
