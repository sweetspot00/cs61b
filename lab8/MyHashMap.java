import java.util.ArrayList;
import java.util.Iterator;
import java.util.Set;
import java.util.HashSet;

public class MyHashMap<K, V> implements Map61B<K, V>{
    private class Node {
        private K key;
        private V value;

        private Node(K k, V v) {
            this.key = k;
            this.value = v;
        }
    }
    private static final int DEFAULT_SIZE = 16;
    private double MAX_LF = 0.75;

    private HashSet<Node>[] buckets;//用linkedList实现可能会更快，hashset实现超时
    private int size;

    private double loadFactor() {
        return size * 0.0 / buckets.length;
    }
    private void resize() {
        HashSet<Node>[] tmp = new HashSet[buckets.length * 2];
        int newLength = buckets.length * 2;
        for (int i = 0; i < buckets.length; i += 1) {
            for (Node node : buckets[i]) {
                tmp[hash(node.key, newLength)].add(node);
            }
        }
        buckets = tmp;
    }
    /** Computes the hash function of the given key. Consists of
     *  computing the hashcode, followed by modding by the number of buckets.
     *  To handle negative numbers properly, uses floorMod instead of %.
     */
    private int hash(K key) {
        if (key == null) {
            return 0;
        }

        int numBuckets = buckets.length;
        return Math.floorMod(key.hashCode(), numBuckets);
    }
    private int hash(K key, int length) {
        if (key == null) {
            return 0;
        }
        return Math.floorMod(key.hashCode(), length);
    }

    public MyHashMap() {
        buckets = new HashSet[DEFAULT_SIZE];
        this.clear();

    }
    public MyHashMap(int initialSize) {
        buckets = new HashSet[initialSize];
        this.clear();
    }
    public MyHashMap(int initialSize, double loadFactor) {
        buckets = new HashSet[initialSize];
        this.MAX_LF = loadFactor;
        this.clear();

    }
    /** Removes all of the mappings from this map. */
    @Override
    public void clear() {
        this.size = 0;
        for (int i = 0; i < this.buckets.length; i += 1) {
            buckets[i] = new HashSet<Node>();
        }

    }

    /** Returns true if this map contains a mapping for the specified key. */
    @Override
    public boolean containsKey(K key) {
        V res = get(key);
        if (res == null) {
            return false;
        }
        return true;
    }

    /**
     * Returns the value to which the specified key is mapped, or null if this
     * map contains no mapping for the key.
     */
    @Override
    public V get(K key) {
        int hashcode = hash(key);

        for (Node node: buckets[hashcode]) {
            if (node.key.equals(key)) {
                return node.value;
            }
        }

        return null;
    }

    /** Returns the number of key-value mappings in this map. */
    @Override
    public int size() {
        return size;
    }

    /**
     * Associates the specified value with the specified key in this map.
     * If the map previously contained a mapping for the key,
     * the old value is replaced.
     */
    @Override
    public void put(K key, V value) {
        int idx = hash(key);
        boolean flag = false;
        for (Node node : buckets[idx]) {
            if (node.key.equals(key)) {
                node.value = value;
                flag = true;
                break;
            }
        }
        double lf = loadFactor();
        if (lf > MAX_LF) {
            resize();
            idx = hash(key);
        }
        if (!flag) {
            buckets[idx].add(new Node(key, value));
            size += 1;
        }

    }

    /** Returns a Set view of the keys contained in this map. */
    @Override
    public Set<K> keySet() {
        Set<K> res = new HashSet<>();
        for (HashSet<Node> bucket : buckets) {
            for (Node node : bucket) {
                res.add(node.key);
            }
        }
        return res;
    }
    private class MyHashMapIterator implements Iterator<K> {
        private int cnt;
        private ArrayList<Node> nodes;
        private ArrayList<Node> NodeSet() {
            ArrayList<Node> res = new ArrayList<>();
            for (HashSet<Node> bucket : buckets) {
                res.addAll(bucket);
            }
            return res;
        }
        public MyHashMapIterator() {
            cnt = 0;
            nodes = NodeSet();
        }
        public boolean hasNext() {
            return cnt < size;
        }

        public K next() {
            Node res = nodes.remove(cnt);
            cnt += 1;
            return res.key;
        }
    }

    @Override
    public Iterator<K> iterator() {
        return new MyHashMapIterator();
    }

    /**
     * Removes the mapping for the specified key from this map if present.
     * Not required for Lab 8. If you don't implement this, throw an
     * UnsupportedOperationException.
     */
    @Override
    public V remove(K key) {
        if (containsKey(key)) {
            int idx = hash(key);
            for (Node node : buckets[idx]) {
                if (node.key.equals(key)) {
                    V res = node.value;
                    buckets[idx].remove(node);
                    return res;
                }
            }
        }
        return null;

    }

    /**
     * Removes the entry for the specified key only if it is currently mapped to
     * the specified value. Not required for Lab 8. If you don't implement this,
     * throw an UnsupportedOperationException.
     */
    @Override
    public V remove(K key, V value) {
        if (containsKey(key)) {
            int idx = hash(key);
            for (Node node : buckets[idx]) {
                if (node.key.equals(key) && node.value.equals(value)) {
                    return node.value;
                }
            }
        }
        return null;
    }
}
