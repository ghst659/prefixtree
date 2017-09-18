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
        SuffixTree sut = new SuffixTree("abaaba");
        Trie<Boolean> root = sut.getRoot();
        Trie<Boolean> target = root.findTarget("ab");
        target.setValue(false);
        Trie.Item<Boolean> n1 = root.deepestNode(
            0,
            (Trie<Boolean> n) -> {return n.getValue() != null && ! n.getValue();}
        );
        Assert.assertEquals(3, n1.depth);
        Trie.Item<Boolean> n2 = root.deepestNode(
            0,
            (Trie<Boolean> n) -> {return n.childCount() > 1;}
        );
        Assert.assertEquals(4, n2.depth);
    }
}
