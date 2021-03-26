import java.util.*;

public class MyTrieSet implements TrieSet61B {

    private class Node {
        private Hashtable<Character, Node> map;
        private boolean isKey;
        private Character nodeKey;
        public Node() {
            map = new Hashtable<>();
            isKey = false;
        }
        public Node(Character c, boolean b) {
            map = new Hashtable<>();
            nodeKey = c;
            isKey = b;
        }

    }
    private Node root;

    public MyTrieSet() {
        root = new Node();
    }

    /** Clears all items out of Trie */
    @Override
    public void clear() {
        root = new Node();
    }

    /** Returns true if the Trie contains KEY, false otherwise */
    @Override
    public boolean contains(String key) {
        Node curr = root;
        for (int i = 0; i < key.length(); i += 1) {
            char c = key.charAt(i);
            if (!curr.map.containsKey(c)) {
                return false;
            }
            curr = curr.map.get(c);
        }
        return true;
    }

    /** Inserts string KEY into Trie */
    @Override
    public void add(String key) {
        if (key == null || key.length() < 1) {
            return;
        }
        Node curr = root;
        for (int i = 0; i < key.length(); i += 1) {
            char c = key.charAt(i);
            if (!curr.map.containsKey(c)) {
                curr.map.put(c, new Node(c, false));
            }
            curr = curr.map.get(c);
        }
        curr.isKey = true;

    }

    private void keysWithPrefixHelper(Node node, String tmp, List<String> res, String prefix) {
        if (node.isKey && !node.map.isEmpty()) {
            res.add(prefix + tmp);
        }
        if (node.isKey && node.map.isEmpty()) {
            res.add(prefix + tmp);
            return;
        }
        for (Node curr : node.map.values()) {
            tmp += curr.nodeKey;
            keysWithPrefixHelper(curr, tmp, res, prefix);
            tmp = tmp.substring(0, tmp.length() - 1);
        }

    }

    /** Returns a list of all words that start with PREFIX */
    @Override
    public List<String> keysWithPrefix(String prefix) {

        List<String> res = new ArrayList<String>();

        Node curr = root;
        for (int i = 0; i < prefix.length(); i += 1) {
            char c = prefix.charAt(i);
            if (curr.map.containsKey(c)) {
                curr = curr.map.get(c);
            }
        }
        keysWithPrefixHelper(curr, "", res, prefix);
        return res;
    }

    /** Returns the longest prefix of KEY that exists in the Trie
     * Not required for Lab 9. If you don't implement this, throw an
     * UnsupportedOperationException.
     */

    @Override
    public String longestPrefixOf(String key) {
        Node curr = root;
        int i = 0;
        String res = "";
        while (!curr.map.isEmpty() && i < key.length()) {
            char c = key.charAt(i);
            if (curr.map.containsKey(c)) {
                i += 1;
                res += c;
                curr = curr.map.get(c);
            } else {
                break;
            }
        }
        return res;

    }
}
