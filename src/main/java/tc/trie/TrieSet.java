package tc.trie;

import java.util.List;

public class TrieSet {
    private TrieMap<Boolean> table = new TrieMap<>();
    public void add(CharSequence key) {
        this.table.put(key, true);
    }
    public boolean contains(CharSequence key) {
        return this.table.containsKey(key);
    }
    public List<String> allElements() {
        return this.table.allKeys();
    }
    public List<String> matchingElements(String prefix) {
        return this.table.matchingKeys(prefix);
    }
    public int size() {
        return this.table.size();
    }
}
