package tc.trie;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class TrieNode<V> {
    public static long instanceCount = 0L;
    private Map<Character, TrieNode<V>> tbl = null;
    private V value = null;
    public TrieNode() {
        instanceCount++;
    }
    // ---------------
    public TrieNode<V> findTarget(CharSequence key) {
        TrieNode<V> target = null;
        int L = key.length();
        if (L == 0) {
            target = this;
        } else if (L > 0 && this.tbl != null) {
            Character c = key.charAt(0);
            if (this.tbl.containsKey(c)) {
                target = this.tbl.get(c).findTarget(key.subSequence(1, L));
            }
        }
        return target;
    }
    public TrieNode<V> makeTarget(CharSequence key) {
        TrieNode<V> target = null;
        int L = key.length();
        if (L == 0) {
            target = this;
        } else if (L > 0) {
            if (this.tbl == null) {
                this.tbl = new TreeMap<>();
            }
            Character c = key.charAt(0);
            if (!this.tbl.containsKey(c)) {
                this.tbl.put(c, new TrieNode<V>());
            }
            target = this.tbl.get(c).makeTarget(key.subSequence(1, L));
        }
        return target;
    }

    public void put(CharSequence key, V val) {
        TrieNode<V> target = this.makeTarget(key);
        target.value = val;
    }

    public V get(CharSequence key) {
        TrieNode<V> target = this.findTarget(key);
        return (target != null) ? target.value : null;
    }

    public List<String> listPaths(String prefix) {
        List<String> result = new LinkedList<>();
        if (value != null) {
            result.add(prefix);
        }
        if (this.tbl != null) {
            for (Map.Entry<Character, TrieNode<V>> kv: this.tbl.entrySet()) {
                String newPrefix = prefix + kv.getKey();
                result.addAll(kv.getValue().listPaths(newPrefix));
            }
        }
        return result;
    }
    public int countPaths() {
        int count = 0;
        if (value != null) {
            count++;
        }
        if (this.tbl != null) {
            for (Map.Entry<Character, TrieNode<V>> kv: this.tbl.entrySet()) {
                count += kv.getValue().countPaths();
            }
        }
        return count;
    }
}
