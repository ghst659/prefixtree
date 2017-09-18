package tc.trie;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class SuffixTreeTest {
    private SuffixTree sut = null;
    @Before
    public void setUp() {
        // pass
    }
    @Test
    public void testStructure() {
        Trie.instanceCount = 0;
        sut = new SuffixTree("abaaba");
        Assert.assertEquals(22, Trie.instanceCount);
    }
    @Test
    public void testAllSuffixes() {
        sut = new SuffixTree("abaaba");
        String[] expected = {
            "", "a", "ba", "aba", "aaba", "baaba", "abaaba"
        };
        Set<String> answer = new HashSet<>(Arrays.asList(expected));
        Set<String> allsuf = new HashSet<>(sut.suffixes());
        Assert.assertEquals(answer, allsuf);
    }
    @Test
    public void testEndsWith() {
        sut = new SuffixTree("abaaba");
        String[] expected = {
            "", "a", "ba", "aba", "aaba", "baaba", "abaaba"
        };
        for (String text: expected) {
            Assert.assertTrue(sut.endsWith(text));
        }
    }
    @Test
    public void testIsSubstring() {
        sut = new SuffixTree("abaaba");
        Assert.assertTrue(sut.isSubstring(""));
        Assert.assertTrue(sut.isSubstring("a"));
        Assert.assertFalse(sut.isSubstring("q"));
        Assert.assertTrue(sut.isSubstring("baaba"));
        Assert.assertTrue(sut.isSubstring("baab"));
        Assert.assertFalse(sut.isSubstring("bb"));
    }
    @Test
    public void testOccurrenceCount() {
        sut = new SuffixTree("box formation");
        Assert.assertEquals(3, sut.occurrenceCount("o"));
        Assert.assertEquals(1, sut.occurrenceCount("or"));
        Assert.assertEquals(0, sut.occurrenceCount("v"));
        Assert.assertEquals(1, sut.occurrenceCount("format"));
    }
}
