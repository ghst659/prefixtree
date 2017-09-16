package tc.trie;

import java.util.LinkedList;
import java.util.List;

public class TrieMap<V> {
    private TrieNode<V> root = new TrieNode<>();
    public int size() {
        return root.countPaths();
    }
    public void put(CharSequence key, V val) {
        root.put(key, val);
    }
    public V get(CharSequence key) {
        V result = root.get(key);
        return result;
    }
    public boolean containsKey(CharSequence key) {
        return this.get(key) != null;
    }
    public List<String> allKeys() {
        return root.listPaths("");
    }
    public List<String> matchingKeys(CharSequence prefix) {
        List<String> result = new LinkedList<>();
        TrieNode<V> target = root.findTarget(prefix);
        if (target != null) {
            result.addAll(target.listPaths(prefix.toString()));
        }
        return result;
    }
}
