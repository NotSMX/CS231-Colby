package Lab06;
import java.util.ArrayList;

/**
 * Author: Daniel Yu
 * 
 * Testng BSTMap methods
 */

public class BSTMapTests {
    public static void main(String[] args) {
        // setup
        BSTMap<String, Integer> bstMap = new BSTMap<>();

        // verify
        System.out.println(bstMap + " != null");

        // test
        assert bstMap != null : "Error in BSTMap::BSTMap()";

        // Test put method
        bstMap.put("apple", 5);
        bstMap.put("banana", 3);
        bstMap.put("cherry", 8);
        bstMap.put("potato", 2);
        System.out.println(bstMap);

        // verify
        System.out.println("Size of BSTMap: " + bstMap.size());
        assert bstMap.size() == 4 : "Error in BSTMap::put()";

        // test
        assert bstMap.containsKey("banana") : "Error in BSTMap::containsKey()";
        assert !bstMap.containsKey("grape") : "Error in BSTMap::containsKey()";

        // Test get method
        int value = bstMap.get("apple");

        // verify
        System.out.println("Value for key 'apple': " + value);
        assert value == 5 : "Error in BSTMap::get()";

        // Test remove method
        int removedValue = bstMap.remove("banana");
        System.out.println(bstMap);

        // verify
        System.out.println("Removed value for key 'banana': " + removedValue);
        System.out.println("Size of BSTMap after removal: " + bstMap.size());
        assert bstMap.size() == 2 : "Error in BSTMap::remove()";

        // test
        assert !bstMap.containsKey("banana") : "Error in BSTMap::remove()";

        // test
        ArrayList<String> keys = bstMap.keySet();

        // verify
        System.out.println("Keys in BSTMap: " + keys);
        assert keys.size() == 2 : "Error in BSTMap::keySet()";

        // Test method: values()
        // verify
        System.out.println("Values in BSTMap: " + bstMap.values());

        // test
        ArrayList<Integer> values = bstMap.values();

        // verify
        System.out.println("Values in BSTMap: " + values);
        assert values.size() == 2 : "Error in BSTMap::values()";

        // Test method: entrySet()
        // verify
        System.out.println("EntrySet in BSTMap: " + bstMap.entrySet());

        // verify
        System.out.println("EntrySet in BSTMap: " + bstMap.entrySet());
        assert bstMap.entrySet().size() == 2 : "Error in BSTMap::entrySet()";

        // Test method: maxDepth()
        // verify
        System.out.println("Max Depth of BSTMap: " + bstMap.maxDepth());

        // setup
        bstMap.put("fig", 15);
        bstMap.put("grape", 2);
        bstMap.put("kiwi", 7);

        // verify
        System.out.println("BSTMap after adding more elements: " + bstMap);

        // test
        int maxDepth = bstMap.maxDepth();

        // verify
        System.out.println("Max Depth of BSTMap after adding more elements: " + maxDepth);
        assert maxDepth == 5 : "Error in BSTMap::maxDepth()";

        // Test clear method
        bstMap.clear();

        // verify
        System.out.println("Size of BSTMap after clearing: " + bstMap.size());
        assert bstMap.size() == 0 : "Error in BSTMap::clear()";

        System.out.println("All tests passed successfully.");
    }
}
