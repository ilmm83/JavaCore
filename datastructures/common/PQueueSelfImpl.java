package FirstStap.datastructures.common;

import java.util.*;

public class PQueueSelfImpl<T extends Comparable<T>> {
    // The number of elements currently inside the heap
    private int heapSize = 0;

    // The internal capacity of the heap
    private int heapCapacity = 0;

    // A dynamic list to track the elements inside the heap
    private List<T> heap = null;

    // This map keeps track of the possible indicates a particular
    // node value is found in the heap. Having this mapping lets
    // us have O(log(n)) removals and O(1) element containment check
    // at the const of some additional space and minor overhead
    private Map<T, TreeSet<Integer>> map = new HashMap<>();

    // Construct and initially empty priority queue
    public PQueueSelfImpl() {
        this(1);
    }

    // Construct a priority queue with an initial capacity
    public PQueueSelfImpl(int size) {
        heap = new ArrayList<>(size);
    }

    // Construct a priority queue using heapify in O(n) time
    public PQueueSelfImpl(T[] elem) {
        heapSize = heapCapacity = elem.length;
        heap = new ArrayList<T>(heapCapacity);

        // Place all element in heap
        for (int i = 0; i < heapSize; i++) {
            mapAdd(elem[i], i);
            heap.add(elem[i]);
        }

        // Heapify process, O(n)
        for (int i = Math.max(0, (heapSize / 2) - 1); i >= 0; i--) {
            sink(i);
        }
    }

    // Priority queue construction, O(nlog(n))
    public PQueueSelfImpl(Collection<T> elems) {
        this(elems.size());
        for (T elem : elems) add(elem);
    }

    public boolean isEmpty() {
        return heapSize == 0;
    }

    public void clear() {
        for (int i = 0; i < heapCapacity; i++)
            heap.set(i, null);
        heapSize = 0;
        map.clear();
    }

    public int size() {
        return heapSize;
    }

    // Returns the value of the element with the lowest
    // priority in this priority queue. If the priority
    // queue is empty null is returned.
    public T peek() {
        if (isEmpty()) return null;
        return heap.get(0);
    }

    // Removes the root of the heap, O(log(n))
    public T poll() {
        if (isEmpty()) return null;
        return removeAt(0);
    }

    // Test if element is in heap, O(1)
    public boolean contains(T elem) {
        if (elem == null) return false;
        // Map lookup to check containment, O(1)
        return map.containsKey(elem);
    }

    // Adds an element to the priority queue, the
    // element must not be null, O(log(n))
    public void add(T elem) {
        if (elem == null) throw new IllegalArgumentException("Element is null");
        if (heapSize < heapCapacity) {
            heap.set(heapSize, elem);
        } else {
            heap.add(elem);
            heapCapacity++;
        }
        mapAdd(elem, heapSize);
        swim(heapSize);
        heapSize++;
    }

    // Test if the value of node i <= node j
    // This method assumes i & j are valid indicates, O(1)
    private boolean less(int i, int j) {
        T node1 = heap.get(i);
        T node2 = heap.get(j);
        return node1.compareTo(node2) <= 0;
    }

    // Bottom up node swim, O(log(n))
    private void swim(int index) {

        // Grab the index of the next parent node WRT to k
        int parent = (index - 1) / 2;

        // Keep swimming while we have not reached the
        // root and while we're less than our parent.
        while (index > 0 && less(index, parent)) {
            // Exchange parent with an index
            swap(parent, index);
            index = parent;

            // Grab the index of the next parent node WRT to index
            parent = (index - 1) / 2;
        }
    }

    private void swap(int i, int j) {
        T i_node = heap.get(i), j_node = heap.get(j);
        heap.set(i, j_node);
        heap.set(j, i_node);
        mapSwap(i_node, j_node, i, j);
    }

    // Exchange the index of two nodes internally within the map
    private void mapSwap(T i_node, T j_node, int i, int j) {
        Set<Integer> s1 = map.get(i_node), s2 = map.get(j_node);
        s1.remove(i); s2.remove(j);
        s1.add(j); s2.add(i);
    }

    public boolean remove(T element) {
        if (element == null) return false;
        Integer index = mapGet(element);
        if (index != null) removeAt(index);
        return index != null;
    }

    private T removeAt(Integer index) {
        if (isEmpty()) return null;

        heapSize--;
        T removed = heap.get(index);

        heap.set(heapSize, null);
        mapRemove(removed, heapSize);

        // Removed last element
        if (index == heapSize) return removed;
        T elem = heap.get(index);
        // Try sinking
        sink(index);
        // or else swimming
        if (heap.get(index).equals(elem)) swim(index);
        return removed;
    }

    // Recursively checks if this heap is a min heap
    // This method is just for testing purposes to make
    // sure the heap invariant is still being maintained
    // Called this method with index=0 to start at the root
    public boolean isMinHeap(int index) {

        //if we are outside the bound of the heap return true
        if (index >= heapSize) return true;

        int left = (index * 2) + 1, right = (index * 2) + 2;
        // Make sure that the current node index is less than
        // both of its children left, and right if they exist
        // return false otherwise to indicate an invalid heap
        if (left < heapSize && !less(index, right)) return false;
        if (right < heapSize && !less(index, right)) return false;

        // Recurse on both children to make sure they're also valid heaps
        return isMinHeap(left) && isMinHeap(right);
    }

    // Removes the index at a given value, O(log(n))
    private void mapRemove(T value, int index) {
        TreeSet<Integer> i = map.get(value); // Take a value
        i.remove(index);                    // remove an index
        if (i.size() == 0) map.remove(value);   // remove a value
    }

    // Extract an index position for the given value
    // NOTE: if a value exists multiple times in the heap the highest
    // index is returned (this has arbitrarily been chosen)
    private Integer mapGet(T element) {
        TreeSet<Integer> set = map.get(element);
        if (set == null) return null;
        return set.last(); // Return the highest elem in the set
    }

    // Top down node sink, O(log(n))
    private void sink(int index) {

        while (true) {
            // Left and right nodes
            int left = (2 * index) + 1, right = (2 * index) + 2;
            int smallest = left; // Assume left node is the smallest node of the two children

            // Find witch is smaller of the two nodes and set it
            if (right < heapSize && less(right, left)) smallest = right;

            // Stop if we're outside the bounds of the tree
            // or stop early if we cannot sink index anymore
            if (left >= heapSize || less(index, smallest)) break;

            // Move down the tree following the smallest node
            swap(smallest, index);
            index = smallest;
        }
    }

    // Add a node value and its index to the map
    public void mapAdd(T t, int i) {
        TreeSet<Integer> indexes = map.get(t);
        // New value being inserted in map
        if (indexes == null) {
            indexes = new TreeSet<>();
            indexes.add(i);
            map.put(t, indexes);
        } else indexes.add(i);
    }

}
