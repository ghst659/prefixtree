package tc.trie;

import org.junit.Assert;
import org.junit.Test;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

public class TrieMapTest {
    @Test
    public void testGetEmpty() {
        TrieMap<Integer> tm = new TrieMap<>();
        Assert.assertNull(tm.get("foo"));
    }
    @Test
    public void testPutGet() {
        TrieMap<Integer> tm = new TrieMap<>();
        Map<String, Integer> data = new TreeMap<>();
        data.put("foo", 42);
        data.put("bar", 91);
        data.put("fog", 88);
        data.put("folly", 243);

        for (Map.Entry<String, Integer> kv: data.entrySet()) {
            tm.put(kv.getKey(), kv.getValue());
        }

        Assert.assertEquals(data.size(), tm.size());
        for (Map.Entry<String, Integer> kv: data.entrySet()) {
            Assert.assertEquals(kv.getValue(), tm.get(kv.getKey()));
        }
        Assert.assertNull(tm.get("fool"));
    }
    @Test
    public void testGetElements() {
        TrieSet s = new TrieSet();
        String[] data = {
            "foo", "bar", "foot", "ball", "fool", "folly", "barfly", "bola"
        };
        for (String word : data) {
            s.add(word);
        }

        Set<String> actuals = new HashSet<>(s.allElements());

        Assert.assertEquals(data.length, actuals.size());
        for (String word : data) {
            Assert.assertTrue(actuals.contains(word));
        }
    }

    @Test
    public void testGetMatchingElements() {
        TrieSet s = new TrieSet();
        String[] data = {
            "foo", "bar", "foot", "ball", "fool", "folly", "barfly", "bola", "bold", "bolder"
        };
        String[] prefixes = {
            "any", "fo", "bo", "b", "foot"
        };
        for (String testPrefix: prefixes) {
            Set<String> fset = new HashSet<>();
            for (String word : data) {
                if (word.startsWith(testPrefix)) {
                    fset.add(word);
                }
            }
            for (String word : data) {
                s.add(word);
            }
            Set<String> actuals = new HashSet<>(s.matchingElements(testPrefix));
            Assert.assertTrue(fset.equals(actuals));
        }
    }
}
