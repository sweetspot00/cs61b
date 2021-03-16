import java.util.Arrays;

public class UnionFind {

    // TODO - Add instance variables?
    public Integer[] father;
    public Integer[] size;

    /* Creates a UnionFind data structure holding n vertices. Initially, all
       vertices are in disjoint sets. */
    public UnionFind(int n) {
        // TODO
        father = new Integer[n];
        size = new Integer[n];
        Arrays.fill(father, -1);
        Arrays.fill(size, 1);
    }

    /* Throws an exception if v1 is not a valid index. */
    private void validate(int vertex) {
        // TODO
        if (vertex < 0 || vertex >= father.length) {
            throw new IllegalArgumentException();
        }
    }

    /* Returns the size of the set v1 belongs to. */
    public int sizeOf(int v1) {
        // TODO
        return size[v1];
    }

    /* Returns the parent of v1. If v1 is the root of a tree, returns the
       negative size of the tree for which v1 is the root. */
    public int parent(int v1) {
        // TODO
        return (father[v1] == -1) ? -size[v1] : father[v1];
    }

    /* Returns true if nodes v1 and v2 are connected. */
    public boolean connected(int v1, int v2) {
        // TODO

        return find(v1) == find(v2);
    }

    /* Connects two elements v1 and v2 together. v1 and v2 can be any valid 
       elements, and a union-by-size heuristic is used. If the sizes of the sets
       are equal, tie break by connecting v1's root to v2's root. Unioning a 
       vertex with itself or vertices that are already connected should not 
       change the sets but may alter the internal structure of the data. */
    public void union(int v1, int v2) {
        // TODO
        int father1 = find(v1);
        int father2 = find(v2);
        if (father1 != father2) {
            if (size[father1] > size[father2]) {
                father[father2] = father1;
                size[father1] += 1;
            } else {
                father[father1] = father2;
                size[father2] += 1;
            }
        }
    }

    /* Returns the root of the set V belongs to. Path-compression is employed
       allowing for fast search-time. */
    public int find(int vertex) {
        // TODO
        int root = vertex;
        while (father[root] >= 0) {
            root = father[root];
        }
        int originalFather;
        while (vertex != root) {
            originalFather = father[vertex];
            father[vertex] = root;
            vertex = originalFather;
        }
        return root;
    }

}

