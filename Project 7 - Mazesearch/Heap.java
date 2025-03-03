
/**
 * Author: Daniel Yu
 * 
 * Heap Structure that acts as Priority Queue.
 */

import java.util.Comparator;

public class Heap<T> implements PriorityQueue<T> {
    Comparator<T> comparator;
    int size;
    Node<T> rootNode;
    Node<T> lastNode;

    private class Node<T> {
        // declaration of variables
        private T data;
        private Node<T> left;
        private Node<T> right;
        private Node<T> parent;

        /*
         * a constructor that initializes values to null and the container field to
         * item.
         */
        public Node(T item) {
            this.data = item;
            this.left = null;
            this.right = null;
            this.parent = null;
        }

        /*
         * returns the value of the container field
         */
        public T getData() {
            return this.data;
        }

        /*
         * returns the value of the container field
         */
        public void setData(T item) {
            data = item;
        }

        /*
         * sets left to the given node
         */
        public void setLeft(Node<T> n) {
            this.left = n;
        }

        /*
         * sets right to the given node
         */
        public void setRight(Node<T> n) {
            this.right = n;
        }

        /*
         * sets parent to the given node
         */
        public void setParent(Node<T> n) {
            this.parent = n;
        }

    }

    // : this Heap should use the given comparator to determine the order of this
    // structure. If the comparator is null, the type T will be assumed to be
    // Comparable (and a new Comparator should be created accordingly). If maxHeap
    // is false, then a max-heap will be constructed instead (and a new Comparator
    // should be created accordingly).
    public Heap(Comparator<T> comparator, boolean maxHeap) {
        if (comparator != null) {
            this.comparator = comparator;
        } else {
            if (maxHeap == true) {
                this.comparator = new Comparator<T>() {
                    @Override
                    @SuppressWarnings("unchecked")
                    public int compare(T o1, T o2) {
                        if (o1 instanceof Comparable) {
                            return ((Comparable<T>) o1).compareTo(o2);
                        } else {
                            throw new UnsupportedOperationException(
                                    "No comparator specified for non-comparable key type.");
                        }
                    }

                };
            } else {
                this.comparator = new Comparator<T>() {

                    @Override
                    @SuppressWarnings("unchecked")
                    public int compare(T o1, T o2) {
                        if (o2 instanceof Comparable) {
                            return ((Comparable<T>) o2).compareTo(o1);
                        } else {
                            throw new UnsupportedOperationException(
                                    "No comparator specified for non-comparable key type.");
                        }
                    }

                };
            }
        }

    }

    // : equivalent to Heap(comparator, false).
    public Heap(Comparator<T> comparator) {
        this(comparator, false);
    }

    // : equivalent to Heap(null, maxHeap).
    public Heap(boolean maxHeap) {
        this(null, maxHeap);
    }

    // : equivalent to Heap(null, false).
    public Heap() {
        this(null, false);
    }

    // : swaps the data between these two nodes.
    private void swap(Node<T> node1, Node<T> node2) {
        T temp = node1.data;
        node1.setData(node2.data);
        node2.setData(temp);
    }

    // : when we add a new Node in, we'll add it at the leaves of the heap. As such,
    // we'll need to 'bubble' its way up the heap. This (presumably recursive)
    // method checks if curNode has a parent and if that parent's data is of lesser
    // priority than curNode's. If so, we swap the data of curNode with its parent
    // and recurse on curNode's parent.
    private void bubbleUp(Node<T> curNode) {
        if (curNode.parent != null && comparator.compare(curNode.data, curNode.parent.data) > 0) {
            swap(curNode, curNode.parent);
            bubbleUp(curNode.parent);
        }
    }

    // : when we call poll, we'll need to put a new Node in the root. To do so,
    // we'll take whatever Node is currently the furthest-right leaf and replace the
    // data of the root with that node's data. Afterwards, we'll need to take that
    // entry and sift it down the heap as long as either of its children has greater
    // priority than it. Consider the following pseudo-code:
    private void bubbleDown(Node<T> curNode) {
        if (curNode.left == null) {
            // then we know curNode has no children, so we can just end
            return;
        } else if (curNode.right == null) {
            // then we know curNode has exactly one child, just its left
            // so we just need to determine if we need to swap to the left
            if (comparator.compare(curNode.left.data, curNode.data) > 0) {
                swap(curNode, curNode.left);
                bubbleDown(curNode.left);
            }
        } else {
            // then we know that curNode has both a left and right child
            // so we first have to determine which child is of greater priority
            Node<T> preferredChild = (comparator.compare(curNode.left.data, curNode.right.data) > 0) ? curNode.left
                    : curNode.right;
            if (comparator.compare(preferredChild.data, curNode.data) > 0) {
                swap(curNode, preferredChild);
                bubbleDown(preferredChild);
            }
        }
    }

    // returns the number of items in the Heap.
    public int size() {
        return size;
    }

    // returns the item of highest priority in the Heap. This should be the item
    // stored in the root.
    public T peek() {
        if (rootNode != null) {
            return rootNode.getData();
        }
        return null;
    }

    // adds the specified item into the Heap. The main difficulty here is trying to
    // find the right place to put the new item in the Heap before calling bubbleUp.
    // The trick is to start from last, then traverse your way to the next location.
    // If the size before the offer call is odd, this is easy (take one step up,
    // make a new Node to the right). Otherwise, it'll be a bit more complicated. I
    // recommend drawing it out and seeing what the pattern is.
    public void offer(T item) {
        Node<T> newNode = new Node<>(item);
        if (rootNode == null) {
            rootNode = newNode;
            lastNode = newNode;
        } else {
            // System.out.println("adding " + newNode.data);
            if (size % 2 == 0) {
                Node<T> parent = lastNode.parent;
                if (parent.right == null) {
                    parent.setRight(newNode);
                }
                newNode.setParent(parent);
            } else {
                if (lastNode == rootNode) {
                    rootNode.setLeft(newNode);
                    newNode.setParent(rootNode);
                } else {
                    Node<T> temp = lastNode;
                    temp = temp.parent;
                    if (temp.parent != null) {
                        boolean endBranch = false;
                        if (temp.parent.left == temp) {
                            endBranch = true;
                        }
                        temp = temp.parent;
                        if (endBranch) {
                            temp = temp.right;
                            while (temp.left != null) {
                                temp = temp.right;
                            }
                        } else {
                            while (temp != rootNode && temp.parent.right == temp) {
                                temp = temp.parent;
                            }
                            if (temp != rootNode) {
                                temp = temp.parent;
                                temp = temp.right;

                                while (temp.left != null) {
                                    temp = temp.left;
                                }
                            } else {
                                while (temp.left != null) {
                                    temp = temp.left;
                                }
                            }
                        }

                    } else {
                        while (temp.left != null) {
                            temp = temp.left;
                        }
                    }

                    temp.setLeft(newNode);
                    newNode.setParent(temp);
                }
            }
        }
        lastNode = newNode;
        // System.out.println(lastNode.data);
        size++;
        bubbleUp(lastNode);
    }

    // returns and removes the item of highest priority. In order to do this, you
    // should take whatever item is in the last Node and replace the root's data
    // with it. Like the offer method, the main difficulty here is updating the last
    // Node.
    public T poll() {
        if (rootNode != null) {
            // System.out.println("polling " + rootNode.data);
            T data = rootNode.getData();
            if (size != 1) {
                // Replace the root with the last node and remove the last node
                // System.out.println("replaced " + rootNode.data + " with " + lastNode.data);
                rootNode.data = lastNode.data;
                // 2 things happen when removing: Either you end a branch, or you removed half
                // of it, this is for determining the new lastNode
                boolean endBranch;
                if (lastNode.parent.left == lastNode) {
                    endBranch = true;
                } else {
                    endBranch = false;
                }
                // remove the last node
                if (lastNode.parent != null) {
                    if (lastNode.parent.left == lastNode) {
                        lastNode.parent.left = null;
                    } else {
                        lastNode.parent.right = null;
                    }
                }
                size--;
                // setting the new lastNode
                // check if there's only the root node now
                if (size != 1) {
                    // iterator
                    Node<T> temp = lastNode;
                    temp = temp.parent;
                    // if you ended the branch of 2 where the lastNode was, you need to traverse
                    // either to the closest branch on within the same depth, or go to the far right
                    // to start subtracting the previous branch
                    if (endBranch) {
                        // check if its part of a bigger branch
                        while (temp != rootNode && temp.parent.left == temp) {
                            temp = temp.parent;
                        }
                        // checking if you removed the last Node of a level, if so you go all the way
                        // right, but if not you just go around the branch to the otherside
                        if (temp != rootNode) {
                            temp = temp.parent;
                            temp = temp.left;

                            while (temp.right != null) {
                                temp = temp.right;
                            }
                        } else {
                            while (temp.right != null) {
                                temp = temp.right;
                            }
                        }
                        lastNode = temp;
                    } else {
                        // go to the otherside of the branch
                        temp = temp.left;
                        lastNode = temp;
                    }
                    // Bubble down the new root to maintain the heap property
                    bubbleDown(rootNode);
                } else {
                    // one Node left
                    lastNode = rootNode;
                }
            } else {
                // empty Heap
                rootNode = null;
                lastNode = null;
                size--;
            }

            return data;
        }
        return null;

    }

    // Updates the priority of the given item - that is, ensures that it is 'behind'
    // items with higher priority and 'ahead' of items with lower priority. Assumes
    // all other items' priorities in this Priority Queue have not changed. The main
    // difficulty here is finding the item whose priority you are updating - in this
    // structure as we are currently maintaining it, there can be no expectation of
    // finding the item quickly, so any linear traversal to find the given item is
    // fine. My solution does the standard binary-tree recursive technique.
    @Override
    public void updatePriority(T item) {
        // Find the node containing the item using linear traversal
        Node<T> targetNode = findNode(rootNode, item);
        if (targetNode != null) {
            // Update priority by performing bubble-up and bubble-down
            bubbleUp(targetNode);
            bubbleDown(targetNode);
        }
    }

    // Helper method to find a specific node containing the item
    private Node<T> findNode(Node<T> currentNode, T item) {
        if (currentNode == null) {
            return null;
        }
        if (currentNode.data.equals(item)) {
            return currentNode;
        }
        Node<T> leftResult = findNode(currentNode.left, item);
        Node<T> rightResult = findNode(currentNode.right, item);
        return leftResult != null ? leftResult : rightResult;
    }

    public static void main(String[] args) {
        Heap<Double> test = new Heap<>();
        for (double x = 50; x >= 0; x -= 2) {
            test.offer(x);
            if (test.lastNode.parent != null) {
                System.out.println(test.lastNode.parent.getData() + " " + test.lastNode.getData());
            }
            test.offer(x - 1);
            if (test.lastNode.parent != null) {
                System.out.println(test.lastNode.parent.getData() + " " + test.lastNode.getData());
            }
        }
        System.out.println(test.poll());
        System.out.println(test.poll());
        System.out.println(test.poll());
    }
}