package tc.trie;

import java.util.List;

public class SuffixTree {
    private final static char SENTINEL = '\0';
    private Trie<Boolean> root = new Trie<>();
    public SuffixTree(String text) {
        String path = text + SENTINEL;
        int L = path.length();
        for (int i = 0; i < L; ++i) {
            String subPath = path.substring(i);
            Trie<Boolean> sentinelNode = root.makeTarget(subPath);
            sentinelNode.setValue(true);
        }
    }
    public List<String> suffixes() {
        List<String> pathList = root.listTrails("");
        pathList.replaceAll(
            (s) -> { return s.substring(0, s.length() - 1); }
        );
        return pathList;
    }
    public boolean isSubstring(String query) {
        Trie<Boolean> target = root.findTarget(query);
        return target != null;
    }
    public boolean endsWith(String query) {
        String trail = query + SENTINEL;
        Trie<Boolean> target = root.findTarget(trail);
        return target != null;
    }
    public int occurrenceCount(String query) {
        int count = 0;
        Trie<Boolean> target = root.findTarget(query);
        if (target != null) {
            count = target.countTrails();
        }
        return count;
    }
}
