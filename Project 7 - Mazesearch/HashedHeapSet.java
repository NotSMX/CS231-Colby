import java.util.Comparator;
import java.util.HashMap;

public class HashedHeapSet<T> implements PriorityQueue<T> {

    // by default, we'll build a min-heap: that means the smallest item
    // according to the Comparator will be stored in heap[1] (the root)
    private T[] heap;
    private int size;
    private Comparator<T> comparator;
    /**
     * This HashMap should map each item of type T to the index it's sitting in.
     */
    private HashMap<T, Integer> indexMapping;

    public HashedHeapSet(Comparator<T> comparator, boolean maxHeap) {
        if (comparator != null) {
            this.comparator = comparator;
        } else {
            this.comparator = new Comparator<T>() {

                @Override
                @SuppressWarnings("unchecked")
                public int compare(T o1, T o2) {
                    return ((Comparable<T>) o1).compareTo(o2);
                }

            };
        }

        if (maxHeap) {
            this.comparator = new Comparator<T>() {

                @Override
                public int compare(T o1, T o2) {
                    return HashedHeapSet.this.comparator.compare(o2, o1);
                }

            };
        }
        size = 0;
        heap = (T[]) new Object[16];
        indexMapping = new HashMap<>();
    }

    public HashedHeapSet(Comparator<T> comparator) {
        this(comparator, false);
    }

    public HashedHeapSet(boolean maxHeap) {
        this(null, maxHeap);
    }

    public HashedHeapSet() {
        this(null, false);
    }

    private int left(int index) {
        return index * 2;
    }

    private int right(int index) {
        return index * 2 + 1;
    }

    private int parent(int index) {
        return index / 2;
    }

    private boolean exists(int index) {
        if (index < heap.length && heap[index] != null) {
            return true;
        }
        return false;
    }

    private boolean hasLeft(int index) {
        return exists(left(index));
    }

    private boolean hasRight(int index) {
        return exists(right(index));
    }

    private void swap(int index1, int index2) {
        T temp = heap[index1];
        heap[index1] = heap[index2];
        heap[index2] = temp;
    }

    private void resize(int newCapacity) {
        T[] newHeap = (T[]) new Object[newCapacity];
        for (int i = 1; i <= size; i++) {
            newHeap[i] = heap[i];
        }
        heap = newHeap;
    }

    @Override
    public void offer(T item) {
        if (item == null) {
            throw new IllegalArgumentException("Item cannot be null");
        }
        // Ensure capacity
        if (size >= heap.length - 1) {
            resize(heap.length * 2);
        }
        size++;
        heap[size] = item;
        indexMapping.put(item, size);
        bubbleUp(size);
    }

    private void bubbleUp(int index) {
        while (index > 1 && comparator.compare(heap[index], heap[parent(index)]) < 0) {
            swap(index, parent(index));
            index = parent(index);
        }
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public T peek() {
        return heap[1];
    }

    @Override
    public T poll() {
        if (size == 0) {
            return null;
        }
        T min = heap[1];
        swap(1, size);
        heap[size] = null;
        indexMapping.remove(min);
        size--;
        bubbleDown(1);
        return min;
    }

    private void bubbleDown(int index) {
        while (hasLeft(index)) {
            int smallerChild = left(index);
            if (hasRight(index) && comparator.compare(heap[right(index)], heap[left(index)]) < 0) {
                smallerChild = right(index);
            }

            if (comparator.compare(heap[index], heap[smallerChild]) < 0) {
                break;
            } else {
                swap(index, smallerChild);
            }
            index = smallerChild;
        }
    }

    public boolean contains(T item) {
        return indexMapping.containsKey(item);
    }

    public boolean remove(T item) {
        Integer index = indexMapping.get(item);
        if (index != null) {
            swap(index, size);
            heap[size] = null;
            indexMapping.remove(item);
            size--;
            bubbleDown(index);
            return true;
        }
        return false;
    }

    @Override
    public void updatePriority(T item) {
        if (!indexMapping.containsKey(item)) {
            throw new IllegalArgumentException("Item not found in the heap");
        }
        int index = indexMapping.get(item);
        bubbleUp(index);
        bubbleDown(index);
    }

     public static void main(String[] args) {
        // Set up HashedHeapSet with a custom comparator
        HashedHeapSet<Integer> hashedHeapSet = new HashedHeapSet<>((a, b) -> Integer.compare(a, b));

        int dataSize = 100000; // Adjust the size as needed
        for (int i = 0; i < dataSize; i++) {
            hashedHeapSet.offer(i);
        }

        // Test contains method
        long startTime = System.nanoTime();
        boolean containsResult = hashedHeapSet.contains(1); 
        long endTime = System.nanoTime();
        System.out.println("Contains operation took: " + (endTime - startTime) + " nanoseconds");
        System.out.println("Contains result: " + containsResult);

        // Test remove method
        startTime = System.nanoTime();
        boolean removeResult = hashedHeapSet.remove(42); 
        endTime = System.nanoTime();
        System.out.println("Remove operation took: " + (endTime - startTime) + " nanoseconds");
        System.out.println("Remove result: " + removeResult);

        // Test updatePriority method
        startTime = System.nanoTime();
        hashedHeapSet.updatePriority(3);
        endTime = System.nanoTime();
        System.out.println("UpdatePriority operation took: " + (endTime - startTime) + " nanoseconds");
    }
}