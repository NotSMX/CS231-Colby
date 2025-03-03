package Lab06;
import java.util.ArrayList;

/**
 * Author: Daniel Yu
 * 
 * To create a Hash Map (or Table) that maps a set of keys to specific values.
 */

public class HashMap<K, V> implements MapSet<K, V> {
    private int size;
    private Node<K, V>[] a;
    private double maxLoadFactor;

    private static class Node<K, V> extends KeyValuePair<K, V> {
        // declaration of variables
        private Node<K, V> next;

        /*
         * a constructor that initializes next to null and the container field to item.
         */
        public Node(K key, V value) {
            super(key, value);
            next = null;
        }

    }

    // : calls the following constructor with a reasonably chosen starting capacity
    // (Java uses 16).
    public HashMap() {
        size = 0;
        a = new Node[16];
        maxLoadFactor = .75;
    }

    // : calls the following constructor with a reasonable load factor (Java uses
    // .75).
    public HashMap(int capacity) {
        size = 0;
        a = new Node[capacity];
        maxLoadFactor = .75;
    }

    // : initializes the HashMap with the given capacity and stores the given
    // loadFactor.
    public HashMap(int capacity, double loadFactor) {
        size = 0;
        a = new Node[capacity];
        maxLoadFactor = loadFactor;
    }

    // : this method returns the length of the array handling this map. Note that
    // this is not necessarily the number of items actually stored in that array;
    // there can (and should) be many empty slots.
    public int capacity() {
        return a.length;
    }

    // : this method should return the index of the underlying array in which the
    // given key should be stored. A reasonable definition for this method could be
    private int hash(K key) {
        return Math.abs(key.hashCode() % capacity());
    }

    // : returns the size.
    public int size() {
        return size;
    }

    // : resets fields to default values
    public void clear() {
        size = 0;
        a = new Node[16];
    }

    // : Associates the specified value with the specified key in this map. If the
    // map previously contained a mapping for the key, the old value is replaced.
    // Does nothing if value is null. Returns the previous value associated with
    // key, or null if there was no mapping for key.
    public V put(K key, V value) {
        int index = hash(key);
        // System.out.println(index);
        Node<K, V> newNode = new Node<>(key, value);
        // System.out.println(a[index]);
        if (a[index] == null) {
            a[index] = newNode;
        } else {
            Node<K, V> current = a[index];
            if (current.getKey().equals(key)) {
                V oldValue = current.getValue();
                current.setValue(value);
                return oldValue;
            }
            while (current.next != null) {
                // System.out.println(current.getKey().equals(key));
                if (current.getKey().equals(key)) {
                    V oldValue = current.getValue();
                    current.setValue(value);
                    return oldValue;
                }
                current = current.next;
            }
            current.next = newNode;
        }
        size++;

        // Check load factor and resize if necessary
        // System.out.println(capacity() + " " + maxLoadFactor);
        // System.out.println(capacity() * maxLoadFactor / 4.0 + " " + size + " " +
        // capacity() * maxLoadFactor);
        if (capacity() * maxLoadFactor / 4.0 >= size || size >= capacity() * maxLoadFactor) {
            resize();
        }

        return null;
    }

    private void resize() {
        Node<K, V>[] oldArray = a;
        if (capacity() * maxLoadFactor / 4.0 >= size) {
            a = new Node[capacity() / 2];
        } else {
            a = new Node[capacity() * 2];
        }
        size = 0;

        for (Node<K, V> node : oldArray) {
            Node<K, V> current = node;
            while (current != null) {
                int index = hash(current.getKey()); // Calculate new index for the current key
                Node<K, V> newNode = new Node<>(current.getKey(), current.getValue());

                if (a[index] == null) {
                    a[index] = newNode;
                } else {
                    Node<K, V> temp = a[index];
                    while (temp.next != null) {
                        temp = temp.next;
                    }
                    temp.next = newNode;
                }
                size++;
                current = current.next;
            }
        }
    }

    // : Returns the value to which the specified key is mapped, or null if this map
    // contains no mapping for the key.
    public V get(K key) {
        int index = hash(key);
        Node<K, V> current = a[index];
        while (current != null) {
            if (current.getKey().equals(key)) {
                return current.getValue();
            }
            current = current.next;
        }
        return null;
    }

    // : Returns true if this map contains a mapping for the specified key to a
    // value.
    public boolean containsKey(K key) {
        int index = hash(key);
        Node<K, V> current = a[index];
        while (current != null) {
            if (current.getKey().equals(key)) {
                return true;
            }
            current = current.next;
        }
        return false;
    }

    // : Removes the specified key (and its associated value) from this mapping and
    // returns the value it was mapped to.
    public V remove(K key) {
        int index = hash(key);
        Node<K, V> current = a[index];
        Node<K, V> prev = null;
        while (current != null) {
            if (current.getKey().equals(key)) {
                if (prev == null) {
                    a[index] = current.next;
                } else {
                    prev.next = current.next;
                }
                size--;
                return current.getValue();
            }
            prev = current;
            current = current.next;
        }
        return null;
    }

    // : Returns an ArrayList of all the keys in the map in no particular order.
    public ArrayList<K> keySet() {
        ArrayList<K> keys = new ArrayList<>();
        for (Node<K, V> node : a) {
            Node<K, V> current = node;
            while (current != null) {
                keys.add(current.getKey());
                current = current.next;
            }
        }
        return keys;
    }

    // : Returns an ArrayList of all the values in the map in the same order
    // returned by keySet()
    public ArrayList<V> values() {
        ArrayList<V> values = new ArrayList<>();
        for (Node<K, V> node : a) {
            Node<K, V> current = node;
            while (current != null) {
                values.add(current.getValue());
                current = current.next;
            }
        }
        return values;
    }

    // : Returns an ArrayList of each KeyValuePair in the map in the same order as
    // the keys as returned by keySet().
    public ArrayList<KeyValuePair<K, V>> entrySet() {
        ArrayList<KeyValuePair<K, V>> entries = new ArrayList<>();
        for (Node<K, V> node : a) {
            Node<K, V> current = node;
            while (current != null) {
                entries.add(new KeyValuePair<>(current.getKey(), current.getValue()));
                current = current.next;
            }
        }
        return entries;
    }

    // : Returns a String that represents the HashMap.
    public String toString() {
        String o = "";
        for (Node<K, V> node : a) {
            Node<K, V> current = node;
            while (current != null) {
                o += "<" + current.getKey() + ", " + current.getValue() + ">";
                if (current.next != null) {
                    o += " --> ";
                } else {
                    o += "\n";
                }
                current = current.next;
            }
        }
        return o;
    }

    // : returns the size of the biggest bucket (the number of items in the bucket
    // with the most items)
    public int maxDepth() {
        int maxDepth = 0;
        for (Node<K, V> node : a) {
            Node<K, V> current = node;
            int depth = 0;
            while (current != null) {
                depth++;
                current = current.next;
            }
            maxDepth = Math.max(maxDepth, depth);
        }
        return maxDepth;
    }

}