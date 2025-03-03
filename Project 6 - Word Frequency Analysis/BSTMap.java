package Lab06;
import java.util.ArrayList;
import java.util.Comparator;

/**
 * Author: Daniel Yu
 * 
 * To create a Binary Search Tree that maps a set of keys to specific values.
 */

public class BSTMap<K, V> implements MapSet<K, V> {
    Node<K, V> root; // from which we can reach every other Node
    int size; // the number of entries in the mapping.
    Comparator<K> comparator; // will help us organize the structure.

    private static class Node<K, V> extends KeyValuePair<K, V> {
        // declaration of variables
        private Node<K, V> left;
        private Node<K, V> right;

        /*
         * a constructor that initializes next to null and the container field to item.
         */
        public Node(K key, V value) {
            super(key, value);
            left = null;
            right = null;
        }

    }

    // : if comparator isn't null, saves it to the matching field. Otherwise (if it
    // is null), creates a new Comparator<K> assuming that K is Comparable.
    public BSTMap(Comparator<K> comparator) {
        if (comparator != null) {
            this.comparator = comparator;
        } else {
            this.comparator = new Comparator<K>() {
                @Override
                @SuppressWarnings("unchecked")
                public int compare(K o1, K o2) {
                    if (o1 instanceof Comparable) {
                        return ((Comparable<K>) o1).compareTo(o2);
                    } else {
                        throw new UnsupportedOperationException("No comparator specified for non-comparable key type.");
                    }
                }
            };
        }
        root = null;
        size = 0;
    }

    // : calls the first constructor with a null Comparator.
    public BSTMap() {
        this(null);
    }

    // returns the size.
    @Override
    public int size() {
        return size;
    }

    // resets fields to default values.
    @Override
    public void clear() {
        root = null;
        size = 0;
        this.comparator = new Comparator<K>() {
            @Override
            @SuppressWarnings("unchecked")
            public int compare(K o1, K o2) {
                if (o1 instanceof Comparable) {
                    return ((Comparable<K>) o1).compareTo(o2);
                } else {
                    throw new UnsupportedOperationException("No comparator specified for non-comparable key type.");
                }
            }
        };
    }

    // Associates the specified value with the specified key in this map. If the map
    // previously contained a mapping for the key, the old value is replaced. Does
    // nothing if value is null. Returns the previous value associated with key, or
    // null if there was no mapping for key. For this method, you should implement a
    // recursive helper function:
    public V put(K key, V value) {
        if (value == null) {
            return null;
        }

        if (this.root == null) {
            this.root = new Node<K, V>(key, value);
            this.size++;
            return null;
        }
        size++;
        return put(key, value, root);
    }

    // helper method
    private V put(K key, V value, Node<K, V> cur) {
        if (comparator.compare(key, cur.getKey()) < 0) {
            if (cur.left != null) {
                return put(key, value, cur.left);
            } else {
                cur.left = new Node<K, V>(key, value);
                return null;
            }
        } else if (comparator.compare(key, cur.getKey()) > 0) {
            if (cur.right != null) {
                return put(key, value, cur.right);
            } else {
                cur.right = new Node<K, V>(key, value);
                return null;
            }
        } else { // in this case, cur.getKey() == key
            V result = cur.getValue();
            cur.setValue(value);
            return result;
        }
    }

    // Returns the value to which the specified key is mapped, or null if this map
    // contains no mapping for the key.
    @Override
    public V get(K key) {
        return get(key, root);
    }

    /*
     * For the get(K key) method, this is
     * a private recursive helper function
     */
    private V get(K key, Node<K, V> cur) {
        // if no mapping for the key
        if (cur == null) {
            return null;
        }

        /*
         * call on the root, to see if if the key is
         * bigger or less than the root; depending on how it compares,
         * it will move to the left or right
         */
        if (comparator.compare(key, cur.getKey()) < 0) {
            return get(key, cur.left);
        } else if (comparator.compare(key, cur.getKey()) > 0) {
            return get(key, cur.right);
        } else {
            return cur.getValue();
        }
    }

    // Returns true if this map contains a mapping for the specified key to a value.
    @Override
    public boolean containsKey(K key) {
        V val = get(key);

        if (val == null) {
            return false;
        }
        return true;

    }

    // Returns an ArrayList of all the keys in the map in sorted order from least to
    // greatest. While not required, I would probably use a helper function such as
    @Override
    public ArrayList<K> keySet() {
        ArrayList<K> keys = new ArrayList<K>();
        keySet(root, keys);
        return keys;
    }

    /*
     * By doing so, the method for keySet() is as simple as
     * calling our recursive helper function with a new ArrayList.
     */
    private void keySet(Node<K, V> cur, ArrayList<K> output) {
        // if cur is null
        // return;
        if (cur == null) {
            return;
        }
        /*
         * recurse to the left
         * add my own key to the output
         * recurse to the right
         */
        keySet(cur.left, output);
        output.add(cur.getKey());
        keySet(cur.right, output);
    }

    // Returns an ArrayList of all the values in the map in the same order returned
    // by keySet()
    @Override
    public ArrayList<V> values() {
        ArrayList<V> valueSet = new ArrayList<V>();
        values(root, valueSet);
        return valueSet;
    }

    private void values(Node<K, V> cur, ArrayList<V> output) {
        // if cur is null
        // return;
        if (cur == null) {
            return;
        }
        /*
         * recurse to the left
         * add my own value to the output
         * recurse to the right
         */
        values(cur.left, output);
        output.add(cur.getValue());
        values(cur.right, output);

    }

    // Returns an ArrayList of each KeyValuePair in the map in the same order as the
    // keys as returned by keySet()
    @Override
    public ArrayList<KeyValuePair<K, V>> entrySet() {
        ArrayList<KeyValuePair<K, V>> bothSet = new ArrayList<KeyValuePair<K, V>>();
        entrySet(root, bothSet);
        return bothSet;
    }

    // helper method (recursion)
    private void entrySet(Node<K, V> cur, ArrayList<KeyValuePair<K, V>> output) {
        // if cur is null
        // return;
        if (cur == null) {
            return;
        }
        /*
         * add my own value to the output
         * recurse to the left
         * recurse to the right
         */
        output.add(cur);
        entrySet(cur.left, output);
        entrySet(cur.right, output);
    }

    // Returns a String that represents the BSTMap accurately depicting the levels.
    // For example, if I input the KVPs (10, "ten"), (1, "one"), (20, "twenty"),
    // (15, "fifteen") in that order, I might use the following String
    // representation:
    // right: <20 -> twenty>
    // left: <15 -> fifteen>
    // root: <10 -> ten>
    // left: <1 -> one>
    public String toString() {
        return toString(root, 0, "root").strip();
    }

    // helper method (recursion)
    private String toString(Node<K, V> cur, int depth, String direction) {
        if (cur == null) {
            return "";
        }

        String left = toString(cur.left, depth + 1, "left");
        String myself = direction + '\t' + "   ".repeat(depth) + "<" + cur.getKey() + ", " + cur.getValue() + ">"
                + '\n';
        String right = toString(cur.right, depth + 1, "right");

        return myself + left + right;
    }

    // returns the length of a maximal root to leaf (a leaf is a Node which has no
    // children) path.
    @Override
    public int maxDepth() {
        // If root is null, there is no binary tree, so return 0
        if (root == null) {
            return 0;
        }
        // Recursive call on root, starting with depth 1
        return maxDepth(root, 1);
    }

    // helper method (recursion)
    private int maxDepth(Node<K, V> cur, int depth) {
        // If the current node is a leaf node (has no children), return the current
        // depth
        if (cur == null) {
            return depth - 1;
        }

        // Recursive calls to find the maximum depth in the left and right subtrees
        int leftDepth = maxDepth(cur.left, depth + 1);
        int rightDepth = maxDepth(cur.right, depth + 1);

        // Return the maximum depth from left and right subtrees, plus 1 for the current
        // node
        return Math.max(leftDepth, rightDepth);
    }

    // removes the given key from the structure and returns whatever value is
    // currently associated with it. Be careful to not delete all of that Node's
    // children though!
    @Override
    public V remove(K key) {
        Node<K, V> target = find(root, key);
        Node<K, V> targetParent = findParent(root, key);
        handleReplacement(target, targetParent);
        size--;
        return target.getValue();
    }

    // find the node that has the key
    public Node<K, V> find(Node<K, V> cur, K key) {
        if (cur.getKey() == key) {
            return cur;
        } else {
            if (cur.left != null) {
                if (cur.left.getKey() == key) {
                    return cur.left;
                }
                Node<K, V> left = find(cur.left, key);
                if (left != null) {
                    return left;
                }
            }
            if (cur.right != null) {
                if (cur.right.getKey() == key) {
                    return cur.right;
                }
                Node<K, V> right = find(cur.right, key);
                if (right != null) {
                    return right;
                }
            }
        }
        return null;
    }

    // find the parent of the node that has the key
    public Node<K, V> findParent(Node<K, V> cur, K key) {
        if (cur.getKey() == key) {
            return null;
        } else {
            if (cur.left != null) {
                if (cur.left.getKey() == key) {
                    return cur;
                }
                Node<K, V> left = find(cur.left, key);
                if (left != null) {
                    return left;
                }
            }
            if (cur.right != null) {
                if (cur.right.getKey() == key) {
                    return cur;
                }
                Node<K, V> right = find(cur.right, key);
                if (right != null) {
                    return right;
                }
            }
        }
        return null;
    }

    // removing the key while preserving its children
    private void handleReplacement(Node<K, V> toDelete, Node<K, V> toDeleteParent) {
        Node<K, V> replacement = null; // to be found in the following conditionals
        if (toDelete.left == null) {
            replacement = toDelete.right;
        } /// replacement is just the right
        else if (toDelete.right == null) {
            replacement = toDelete.left;
        } // replacement is just the left
        else { // we'll have to find it.
               // set replacement to be the next largest (or smallest) Node
            replacement = findClosestSmallest(toDelete.left);
            // recursively call handleReplacement on this Node (or just do it yourself, I
            // guarantee you this node has just one child)
            handleReplacement(replacement, toDelete);
            replacement.left = toDelete.left;
            replacement.right = toDelete.right;
        }

        // update toDeleteParent's child (which ever one is currently toDelete) to be
        // replacement
        if (toDeleteParent == null) {
            // If toDelete is the root
            root = replacement;
        } else if (toDeleteParent.left == toDelete) {
            toDeleteParent.left = replacement;
        } else {
            toDeleteParent.right = replacement;
        }

    }

    // find the best node to replace the target
    private Node<K, V> findClosestSmallest(Node<K, V> target) {
        Node<K, V> result = target;
        while (target.right != null) {
            result = target.right;
        }
        return result;

    }

    public static void main(String[] args) {
        // this will sort the strings lexicographically (dictionary-order)
        BSTMap<String, Integer> words = new BSTMap<>();
        words.put("ten", 10);
        words.put("five", 5);
        words.put("three", 3);
        System.out.println(words);

        // this will sort the strings in reverse lexicographic order
        BSTMap<String, Integer> wordsReverse = new BSTMap<>(new Comparator<String>() {

            @Override
            public int compare(String o1, String o2) {
                return o2.compareTo(o1);
            }

        });

        wordsReverse.put("ten", 10);
        wordsReverse.put("five", 5);
        wordsReverse.put("three", 3);
        System.out.println(wordsReverse);
    }
}