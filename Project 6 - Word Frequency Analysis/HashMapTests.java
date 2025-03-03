package Lab06;
import java.util.ArrayList;

/**
 * Author: Daniel Yu
 * 
 * Testng HashMap methods
 */

public class HashMapTests {
    public static void main(String[] args) {
        // setup
        HashMap<String, Integer> hashMap = new HashMap<>();

        // verify
        System.out.println(hashMap + " != null");

        // test
        assert hashMap != null : "Error in HashMap::HashMap()";

        // Test put method
        hashMap.put("apple", 5);
        hashMap.put("banana", 3);
        hashMap.put("cherry", 8);

        // verify
        System.out.println("Size of HashMap: " + hashMap.size());
        assert hashMap.size() == 3 : "Error in HashMap::put()";

        // test
        assert hashMap.containsKey("banana") : "Error in HashMap::containsKey()";
        assert !hashMap.containsKey("grape") : "Error in HashMap::containsKey()";

        // Test get method
        int value = hashMap.get("apple");

        // verify
        System.out.println("Value for key 'apple': " + value);
        assert value == 5 : "Error in HashMap::get()";

        // Test put method with the same key (should update the value)
        hashMap.put("apple", 10);

        // verify
        System.out.println("Updated value for key 'apple': " + hashMap.get("apple"));
        assert hashMap.get("apple") == 10 : "Error in HashMap::put()";

        // Test remove method
        int removedValue = hashMap.remove("banana");

        // verify
        System.out.println("Removed value for key 'banana': " + removedValue);
        System.out.println("Size of HashMap after removal: " + hashMap.size());
        assert hashMap.size() == 2 : "Error in HashMap::remove()";

        // test
        assert !hashMap.containsKey("banana") : "Error in HashMap::remove()";

        // test
        ArrayList<String> keys = hashMap.keySet();

        // verify
        System.out.println("Keys in HashMap: " + keys);
        assert keys.size() == 2 : "Error in HashMap::keySet()";

        // Test method: values()
        // verify
        System.out.println("Values in HashMap: " + hashMap.values());

        // test
        ArrayList<Integer> values = hashMap.values();

        // verify
        assert values.size() == 2 : "Error in HashMap::values()";

        // Test method: entrySet()
        // verify
        System.out.println("EntrySet in HashMap: " + hashMap.entrySet());

        // verify
        assert hashMap.entrySet().size() == 2 : "Error in HashMap::entrySet()";

        // Test method: maxDepth() with multiple elements
        hashMap.put("fig", 15);
        hashMap.put("grape", 2);
        hashMap.put("kiwi", 7);

        // verify
        System.out.println("HashMap after adding more elements: \n" + hashMap);

        // test
        int maxDepth = hashMap.maxDepth();

        // verify
        System.out.println("Max Depth of HashMap after adding more elements: " + maxDepth);
        assert maxDepth == 1 : "Error in HashMap::maxDepth()";

        // Test method: maxDepth() with collision (same hash code)
        hashMap.put("pplea", 100);
        hashMap.put("peal", 200);

        // verify
        System.out.println("HashMap after adding collision elements: \n" + hashMap);

        // test
        maxDepth = hashMap.maxDepth();

        // verify
        System.out.println("Max Depth of HashMap after adding collision elements: " + maxDepth);
        assert maxDepth > 1 : "Error in HashMap::maxDepth()";

        // Test clear method
        hashMap.clear();

        // verify
        System.out.println("Size of HashMap after clearing: " + hashMap.size());
        assert hashMap.size() == 0 : "Error in HashMap::clear()";

        System.out.println("All tests passed successfully.");
    }
}
