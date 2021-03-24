import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public class BSTMap<K extends Comparable<K>, V> implements Map61B<K, V>{
    private class Node {
        /* (K, V) pair stored in this Node. */
        private K key;
        private V value;

        /* Children of this Node. */
        private Node left;
        private Node right;

        private Node(K k, V v) {
            key = k;
            value = v;
        }
    }

    private Node root;  /* Root node of the tree. */
    private int size; /* The number of key-value pairs in the tree */

    /* Creates an empty BSTMap. */
    public BSTMap() {
        clear();
    }

    @Override
    public void clear() {
        root = null;
        size = 0;
    }
    @Override
    public boolean containsKey(K key) {
        V res = get(key);
        if (res == null) {
            return false;
        }
        return true;
    }

    private Node getHelper(Node node, K key) {
        if (node == null) {
            return null;
        }
        if (key.equals(node.key)) {
            return node;
        } else if (key.compareTo(node.key) < 0) { //key小于node key
            return getHelper(node.left, key);
        } else {
            return getHelper(node.right, key);
        }

    }
    @Override
    public V get(K key) {
        Node res = getHelper(root, key);
        if (res == null) {
            return null;
        } else {
            return res.value;
        }
    }
    @Override
    public int size() {
        return size;
    }

    @Override
    public V remove(K key, V value) {
        throw new UnsupportedOperationException();
    }
    private Node putHelper(Node node, K key, V value) {
        if (node == null) {
            return new Node(key, value);
        }
        if (key.compareTo(node.key) < 0) {
            node.left = putHelper(node.left, key, value);
        } else if (key.compareTo(node.key) > 0) {
            node.right = putHelper(node.right, key, value);
        }
        return node;
    }
    @Override
    public void put(K key, V value) {
        if (!containsKey(key)) {
            size += 1;
        }
        root = putHelper(root, key, value);
    }
    private Set<K> keySetHelper(Node node, Set<K> set) {
        if (node != null) {
            keySetHelper(node.left, set);
            set.add(node.key);
            keySetHelper(node.right, set);
        }
        return set;
    }
    @Override
    public Set<K> keySet() {
        Set<K> set = new HashSet<K>();
        return keySetHelper(root, set);
    }
    private Node findMaxMin(Node node) {
        if (node.left == null) {
            return node;
        }
        return findMaxMin(node.left);
    }
    private Node deleteMinHelper(Node node) {
        if (node.left == null) {
            return node.right;
        }
        node.left = deleteMinHelper(node.left);
        return node;
    }
    private Node removeHelper(Node node, K key) {
        if (node == null) {
            return null;
        }
        if (key.compareTo(node.key) < 0) {
            node.left = removeHelper(node.left, key);
        } else if (key.compareTo(node.key) > 0) {
            node.right = removeHelper(node.right, key);
        } else {
            if (node.left == null) {
                return node.right;
            }
            if (node.right == null) {
                return node.left;
            }
            Node MaxMin = findMaxMin(node.right);
            MaxMin.right = deleteMinHelper(node.right);
            MaxMin.left = node.left;
            node = MaxMin;
        }
        return node;
    }

    @Override
    public V remove(K key) {
        if (!containsKey(key)) {
            return null;
        }
        size -= 1;
        V res = get(key);
        root = removeHelper(root, key);
        return res;
    }

    @Override
    public Iterator<K> iterator() {
        throw new UnsupportedOperationException();
    }
    private void inOderHelper(Node node) {
        if (node != null) {
            inOderHelper(node.left);
            System.out.println(node.value);
            inOderHelper(node.right);
        }
    }
    public void printInOrder() {
        inOderHelper(root);
    }
}
