package tc.trie;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class DictTest {
    private static final String DICTFILE = "/usr/share/dict/american-english";
    private static List<String> ALLWORDS = new ArrayList<>();
    static {
        ALLWORDS.clear();
        long dt = latency(
            ()-> {
                try (BufferedReader br = new BufferedReader(new FileReader(DICTFILE))) {
                    for (String line = br.readLine(); line != null; line = br.readLine()) {
                        ALLWORDS.add(line);
                    }
                } catch (IOException e) {
                    System.err.format("IOException: %s\n", e.getMessage());
                }
            }
        );
        System.err.format("%s: %d words in %d\n", DICTFILE, ALLWORDS.size(), dt);
    }
    private static long latency(Runnable operation) {
        long t0 = System.currentTimeMillis();
        operation.run();
        long t1 = System.currentTimeMillis();
        return t1 - t0;
    }

    private TrieSet sut = null;
    @Before
    public void setUp() {
        sut = new TrieSet();
        TrieNode.instanceCount = 0L;
        long dt = latency(
            () -> {
                for (String text : ALLWORDS) {
                    sut.add(text);
                }
            }
        );
        System.err.format("Trie build: %d, nodeCount %d\n", dt, TrieNode.instanceCount);
    }
    @After
    public void tearDown() {
        sut = null;
    }
    @Test
    public void testSize() {
        Assert.assertEquals(ALLWORDS.size(), sut.size());
    }
    @Test
    public void testSubset() {
        String[] SUBSETS = {
            "a", "qual", "e", "f", "actual", "game", "in", "out", "zz"
        };
        for (String prefix: SUBSETS) {
            System.err.println(prefix);
            Set<String> expectedSubset = compileSubset(prefix);
            Set<String> foundSubset = findSubset(prefix);
            Assert.assertEquals(expectedSubset, foundSubset);
        }
    }
    private Set<String> findSubset(String prefix) {
        Set<String> foundSubset = new HashSet<>();
        long dt = latency(
            () -> {
                foundSubset.addAll(sut.matchingElements(prefix));
            }
        );
        System.err.format("%s: trie   latency: %d\n", prefix, dt);
        return foundSubset;
    }
    private Set<String> compileSubset(String prefix) {
        Set<String> result = new HashSet<>();
        long dt = latency(
            () -> {
                for (String word: ALLWORDS) {
                    if (word.startsWith(prefix)) {
                        result.add(word);
                    }
                }
            }
        );
        System.err.format("%s: linear latency: %d\n", prefix, dt);
        return result;
    }
}
