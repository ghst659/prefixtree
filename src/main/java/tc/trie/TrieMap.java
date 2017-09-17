package tc.trie;

import java.util.LinkedList;
import java.util.List;

public class TrieMap<V> {
    private Trie<V> root = new Trie<>();
    public int size() {
        return root.countTrails();
    }
    public void put(CharSequence key, V val) {
        Trie<V> target = root.makeTarget(key);
        target.setValue(val);
    }
    public V get(CharSequence key) {
        Trie<V> target = root.findTarget(key);
        return (target != null) ? target.getValue() : null;
    }
    public boolean containsKey(CharSequence key) {
        return this.get(key) != null;
    }
    public List<String> allKeys() {
        return root.listTrails("");
    }
    public List<String> matchingKeys(CharSequence prefix) {
        List<String> result = new LinkedList<>();
        Trie<V> target = root.findTarget(prefix);
        if (target != null) {
            result.addAll(target.listTrails(prefix.toString()));
        }
        return result;
    }
}
