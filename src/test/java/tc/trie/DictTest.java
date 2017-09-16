package tc.trie;

import org.junit.Assert;
import org.junit.Test;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

public class DictTest {
    final String DICTFILE = "/usr/share/dict/british-english";
    @Test
    public void testIngest() {
        int dataCount = 0;
        TrieSet sut = new TrieSet();
        String SUBSET_PREFIX = "game";
        Set<String> expectedSubset = new HashSet<>();
        try (BufferedReader br = new BufferedReader(new FileReader(DICTFILE))) {
            for (String line = br.readLine(); line != null; line = br.readLine()) {
                ++dataCount;
                if (line.startsWith(SUBSET_PREFIX)) {
                    expectedSubset.add(line);
                }
                sut.add(line);
            }
        } catch (IOException e) {
            System.err.format("IOException: %s\n", e.getMessage());
        } finally {
            System.err.format("data count = %d\n", dataCount);
        }
        Assert.assertEquals(dataCount, sut.size());
        Set<String> select = new HashSet(sut.matchingElements(SUBSET_PREFIX));
        Assert.assertEquals(expectedSubset, select);
        for (String word: select) {
            System.err.format("select-word: %s\n", word);
        }
    }
}
