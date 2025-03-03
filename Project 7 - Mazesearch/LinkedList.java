
/**
 * Author: Daniel Yu
 * 
 * Ported from previous projects, a base line for methods
 */

import java.util.Iterator; // defines the Iterator interface

import java.util.ArrayList;

public class LinkedList<T> implements Stack<T>, Queue<T>, Iterable<T>// T just stores a data type that the user wants to add
{
    private class Node {
        // declaration of variables
        private T data;
        private Node next;
        private Node prev;

        /*
         * a constructor that initializes next to null and the container field to item.
         */
        public Node(T item) {
            this.data = item;
            this.next = null;
            this.prev = null;
        }

        /*
         * returns the value of the container field
         */
        public T getData() {
            return this.data;
        }

        /*
         * sets next to the given node
         */
        public void setNext(Node n) {
            this.next = n;
        }

        /*
         * returns the next field
         */
        public Node getNext() {
            return this.next;
        }

        /*
         * sets prev to the given node
         */
        public void setPrev(Node prev) {
            this.prev = prev;
        }

        public Node getPrev() {
            return this.prev;
        }

    }

    private int size; // how many items have been inserted
    private Node head; // the first Node in my list
    private Node tail; // the last Node in my list

    /*
     * constructor that initializes the fields so it is an empty lsit
     */
    public LinkedList() {
        size = 0;
        head = null;
        tail = null;
    }

    /*
     * inserts the item at the beginning of the list
     */
    public void addFirst(T data) {
        if (size == 0) {
            head = new Node(data);
            tail = head;
            size++;
        } else {
            Node temp = new Node(data);
            temp.setNext(head);
            head.setPrev(temp);
            head = temp;
            size++;
        }
    }

    /*
     * inserts the item at the specified position in the list
     */
    public void addLast(T data) {
        if (size == 0) {
            addFirst(data);
        } else {
            Node temp = new Node(data);
            temp.setPrev(tail);
            tail.setNext(temp);
            tail = temp;
            size++;
        }
    }

    public void add(T data) {
        if (size == 0) {
            head = new Node(data);
            tail = head;
            size++;
        } else {
            Node temp = new Node(data);
            temp.setNext(head);
            head.setPrev(temp);
            head = temp;
            size++;
        }
    }

    /*
     * inserts the item at the specified position in the list
     */
    public void add(int index, T item) {
        // out of bounds
        if (index < 0 || index > size) {
            System.out.println("Invalid index for add");
            return;
        } else if (index == 0) // if the index is at the beginning
        {
            add(item);
        } else {
            Node walker = this.head;
            for (int i = 0; i < index - 1; i++) {
                walker = walker.getNext();
            }

            Node newNode = new Node(item);
            newNode.setNext(walker.getNext());
            walker.setNext(newNode);
            this.size++;
        }

    }

    /*
     * empties the list (resets the fields so it is an empty list)
     */
    public void clear() {
        this.head = null;
        size = 0;
    }

    /*
     * returns true if o is present in this list,
     * otherwise this method returns false
     */
    public boolean contains(Object o) {
        Node walker = this.head;
        @SuppressWarnings("unchecked")
        T data = (T) o;
        for (int i = 0; i < size; i++) {
            if (walker.getData().equals(data)) {
                return true;
            }
            walker = walker.getNext();
        }

        return false;
    }

    /*
     * Returns true if o is a LinkedList that also contains the same items in the
     * same order
     */
    public boolean equals(Object o) {
        if (!(o instanceof LinkedList)) {
            return false;
        }
        // If I have reached this line, o must be a LinkedList
        @SuppressWarnings("unchecked")
        LinkedList<T> oAsALinkedList = (LinkedList<T>) o;

        // Now I have a reference to something Java knows is a LinkedList!
        Node walker = this.head;

        for (int x = 0; x < size - 1; x++) {
            if (walker.getData() == oAsALinkedList.get(x)) {
                walker = walker.getNext();
                continue;
            } else {
                return false;
            }
        }
        return true;

    }

    // returns the item specificed by the given index
    public T get(int index) {
        Node walker = this.head;
        for (int i = 0; i < index; i++) {
            walker = walker.getNext();
        }
        return walker.getData();
    }

    // returns true if list is empty, otherwise the met
    public boolean isEmpty() {
        if (this.head == null) {
            return true;
        }
        return false;
    }

    /*
     * removes the first item of the list and returns it
     */
    public T remove() {
        T result = this.head.getData();
        this.head = this.head.getNext();
        if (head != null) {
            this.head.setPrev(null);
        }
        size--;

        return result;
    }

    /*
     * removes the item at the specificed position in the list and returns
     * it
     */
    public T remove(int index) {
        if (index < 0 || index >= size()) {
            System.out.println("Out of Bounds!");
        } else if (index == 0) {
            // remove belongs to the LinkedList
            return this.remove();
        }
        Node walker = this.head;
        Node previous = walker;
        for (int x = 0; x < index; x++) {
            previous = walker;
            walker = walker.getNext();
        }
        previous.setNext(walker.getNext());
        T result = walker.getData();
        size--;

        return result;
    }

    /**
     * This method returns and removes the last entry of the list.
     * 
     * @return the last entry of the list.
     */
    public T removeLast() {
        T temp;
        if (size == 1) {
            temp = tail.getData();
            head = null;
            tail = null;
            size--;
        } else {
            temp = tail.getData();
            tail = tail.getPrev();
            tail.setNext(null);
            size--;
        }

        return temp;
    }

    /*
     * returns the size of the list
     */
    public int size() {
        return size;
    }

    /*
     * returns a String representation of the list
     */
    public String toString() {
        String result = "";
        Node walker = this.head;
        while (walker != null) {
            result += walker.getData();
            if (walker.getNext() != null) {
                result += ", ";
            }
            walker = walker.getNext();
        }
        return result;
    }

    /*
     * Converts the LinkedList to an ArrayList with the items
     * in the same order
     */
    public ArrayList<T> toArrayList() {
        ArrayList<T> temp = new ArrayList<T>();
        Node walker = this.head;
        for (int x = 0; x < size - 1; x++) {
            temp.add(walker.getData());
            walker = walker.getNext();
        }

        return temp;
    }

    /*
     * Return a new LLIterator pointing to the head of the list
     */
    public Iterator<T> iterator() {
        return new LLIterator(this.head);
    }

    /*
     * An Iterator subclass that handles traversing teh list
     */
    private class LLIterator implements Iterator<T> {
        private Node current;

        /*
         * the constructor for the LLIterator given the head of a list
         */
        public LLIterator(Node head) {
            this.current = head;
        }

        /*
         * return true if there are still values to traverse if the current
         * node reference is not null)
         */
        public boolean hasNext() {
            return (this.current != null);
        }

        /*
         * returns the next item in the list, which is the item contained
         * in the current node. The method also needs to move the traversal
         * along to the next node in the list
         */
        public T next() {
            T result = this.current.getData();
            this.current = this.current.getNext();

            return result;
        }

        /*
         * does nothing. Implementing this function is optional for an Iterator
         */
        public void remove() {

        }
    }

    @Override
    public T peek() {
        return head.getData();
    }

    public T pop() {
        return remove();
    }

    public void push(T item) {
        add(item);
    }

    @Override
    public void offer(T item) {
        if (size == 0) {
            addFirst(item);
        } else {
            Node temp = new Node(item);
            temp.setPrev(tail);
            tail.setNext(temp);
            tail = temp;
            size++;
        }
    }

    @Override
    public T poll() {
        return remove();
    }

    public static void main(String[] args) {
        LinkedList<Integer> a = new LinkedList<Integer>();
        // testing add methods
        a.push(10);
        a.push(12);
        a.push(15);
        System.out.println(a);

        // testing size method
        System.out.println(a.size());

        // testing peek()
        System.out.println(a.peek());
        System.out.println(a.size());

        // testing push()
        System.out.println(a.pop());
        System.out.println(a.size());
    }
}