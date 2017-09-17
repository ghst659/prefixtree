package tc.trie;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class Trie<V> {
    public static long instanceCount = 0L;
    private Map<Character, Trie<V>> tbl = null;
    private V value = null;
    public Trie() {
        instanceCount++;
    }
    // ---------------
    public V getValue() {
        return value;
    }
    public void setValue(V newValue) {
        value = newValue;
    }
    public Trie<V> findTarget(CharSequence path) {
        Trie<V> target = null;
        int L = path.length();
        if (L == 0) {
            target = this;
        } else if (L > 0 && this.tbl != null) {
            Character c = path.charAt(0);
            if (this.tbl.containsKey(c)) {
                target = this.tbl.get(c).findTarget(path.subSequence(1, L));
            }
        }
        return target;
    }
    public Trie<V> makeTarget(CharSequence path) {
        Trie<V> target = null;
        int L = path.length();
        if (L == 0) {
            target = this;
        } else if (L > 0) {
            if (this.tbl == null) {
                this.tbl = new TreeMap<>();
            }
            Character c = path.charAt(0);
            if (!this.tbl.containsKey(c)) {
                this.tbl.put(c, new Trie<V>());
            }
            target = this.tbl.get(c).makeTarget(path.subSequence(1, L));
        }
        return target;
    }
    public List<String> listTrails(String accumulatedTrail) {
        List<String> result = new LinkedList<>();
        if (value != null) {
            result.add(accumulatedTrail);
        }
        if (this.tbl != null) {
            for (Map.Entry<Character, Trie<V>> kv: this.tbl.entrySet()) {
                String nextAccumulatedTrail = accumulatedTrail + kv.getKey();
                result.addAll(kv.getValue().listTrails(nextAccumulatedTrail));
            }
        }
        return result;
    }
    public int countTrails() {
        int count = 0;
        if (value != null) {
            count++;
        }
        if (this.tbl != null) {
            for (Map.Entry<Character, Trie<V>> kv: this.tbl.entrySet()) {
                count += kv.getValue().countTrails();
            }
        }
        return count;
    }
    public int childCount() {
        int count = 0;
        if (this.tbl != null) {
            count = this.tbl.size();
        }
        return count;
    }
}
