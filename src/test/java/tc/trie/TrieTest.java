package tc.trie;

import org.junit.Assert;
import org.junit.Test;

public class TrieTest {
    @Test
    public void testDepth() {
        Trie<Long> root = new Trie<>();
        Trie<Long> target = root.makeTarget("abaaba");
        target.setValue(1L);
        int depth = root.maxDepth(0);
        Assert.assertEquals(7, depth);
    }
    @Test
    public void testDeepestNode() {
        Trie<Long> root = new Trie<>();
        Trie<Long> target = root.makeTarget("abaaba");
        target.setValue(1L);
        target = root.findTarget("ab");
        target.setValue(2L);
        Trie.Item<Long> n1 = root.deepestNode(
            0,
            (Trie<Long> n) -> {return n.getValue() != null && n.getValue() == 1L;}
        );
        Assert.assertEquals(7, n1.depth);
        Trie.Item<Long> n2 = root.deepestNode(
            0,
            (Trie<Long> n) -> {return n.getValue() != null && n.getValue() == 2L;}
        );
        Assert.assertEquals(3, n2.depth);
//        Trie.Item<Long> n3 = root.deepestNode(
//            0,
//            (Trie<Long> n) -> {return n.childCount() > 1;}
//        );
//        Assert.assertEquals(2, n3.depth);
    }
}
