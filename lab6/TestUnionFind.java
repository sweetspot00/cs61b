import org.junit.Test;

import java.util.Arrays;

import static org.junit.Assert.*;
public class TestUnionFind {
    @Test
    public void testinit() {
        UnionFind actual = new UnionFind(10);
        Integer[] expectedFather = new Integer[10];
        Integer[] expectedSize = new Integer[10];
        Arrays.fill(expectedFather,-1);
        Arrays.fill(expectedSize,1);
        assertArrayEquals(expectedFather,actual.father);
        assertArrayEquals(expectedSize,actual.size);
    }
    @Test
    public void testUnion() {
        UnionFind actual = new UnionFind(10);
        actual.union(2,3);
        actual.union(3,4);
        actual.union(8,9);
        actual.union(7,2);
        Integer[] expectedFather = new Integer[]{-1,-1,3,-1,3,-1,-1,3,9,-1};
        Integer[] expectedSize = new Integer[]{1,1,1,4,1,1,1,1,1,2};
        assertArrayEquals(expectedFather, actual.father);
        assertArrayEquals(expectedSize, actual.size);
    }
    @Test
    public void testFind() {
        UnionFind actual = new UnionFind(10);
        actual.union(2,3);
        actual.union(3,4);
        actual.union(8,9);
        actual.union(7,2);
        int actualFather  = actual.find(7);
        int expectedFather = 3;
        int expectedParent = 3;
        int actualParent = actual.parent(7);
        assertEquals(expectedFather,actualFather);
        assertEquals(expectedParent,actualParent);
    }

}
