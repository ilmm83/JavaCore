package edu.datastructures.common;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

import static java.lang.Integer.max;

public class MinIndexedDPriorityQueue<T extends Comparable<T>> {

    // Number of elements in the heap
    private final int capacity;

    // Current number of elements the heap
    private int size;

    // The degree of every node in the heap
    private final int degree;

    // Lookup arrays to track the child/parent indexes of each node
    private int[] child, parent;

    // The Position Map maps Key indexes (ki) to where the position of that
    // key is represented in the priority queue in the domain [0, size).
    private final int[] pm;

    // The Inverse Map stores the indexes of the keys in the range
    // [0, size) which make up the priority queue. It should be noted that
    // 'im' and 'pm' are inverses of each other, so: pm[im[i]] = im[pm[i]] = i
    private final int[] im;

    // Initializes an associated with the keys. It is very important to note
    // that this array is indexed by the key indexes (aka 'ki').
    private final T[] values;

    // Initializes a D-ary heap with a maximum capacity of maxSize
    public MinIndexedDPriorityQueue(int maxSize, int degree) {
        if (maxSize <= 0) throw new IllegalArgumentException("MaxSize is less then null");
        this.capacity = max(degree + 1, maxSize);
        this.degree = max(2, degree);

        pm = new int[capacity];
        im = new int[capacity];
        child = new int[capacity];
        parent = new int[capacity];
        values = (T[]) new Object[capacity];

        // Construct a heap with 'default' values.
        for (int i = 0; i < capacity; i++) {
            child[i] = 2 * i + 1;
            parent[i] = (i - 1) / degree;
            pm[i] = im[i] = -1;
        }
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public boolean contains(int ki) {
        keyInBoundsOrThrow(ki);
        return pm[ki] != -1;
    }



    public int peekMinKeyIndex() {
        isNotEmptyOrThrow();
        return im[0];
    }

    public int pollMinKeyIndex() {
        int minKi = peekMinKeyIndex();
        delete(minKi);
        return minKi;
    }

    public T peekMinValue() {
        isNotEmptyOrThrow();
        return values[im[0]];
    }

    public T pollMinValue() {
        isNotEmptyOrThrow();
        T minValue = values[im[0]];
        delete(im[0]);
        return minValue;
    }


    // Inserts a value into the min indexed binary
    // heap. The key index must not already be in
    // the heap and the value must not be null.
    public T insert(int ki, T value) {
        keyInBoundsOrThrow(ki);
        isNotEmptyOrThrow();
        values[ki] = value;
        pm[ki] = size;
        im[size] = ki;
        swim(size);
        size += 1;
        return value;
    }

    private void delete(int ki) {
        keyInBoundsOrThrow(ki);
        int i = pm[ki];
        swap(i, --size);
        sink(i);
        swim(i);
        values[ki] = null;
        pm[ki] = -1;
        im[size] = -1;
    }

    // Swims up node i with its position in heap 'pm',
    // (zero based) until heap invariant is satisfied
    private void swim(int i) {
        while (less(i, parent[i])) {
            swap(i, parent[i]);
            i = parent[i];
        }
    }

    private boolean less(int i, int j) {
        return values[im[i]].compareTo(values[im[j]]) < 0;
    }

    private boolean less(T i, T j) {
        return i.compareTo(j) < 0;
    }

    // Sink down node i with its position in heap 'pm'
    private void sink(int i) {
        while (true) {
            int left = 2 * i + 1, right = 2 * i + 2, smallest = left;
            if (right < size && less(right, left)) smallest = right;
            if (left >= size && less(i, smallest)) break;
            swap(smallest, i);
            i = smallest;
        }
    }

    private void swap(int i, int j) {
        pm[im[i]] = j;
        pm[im[j]] = i;
        int tmp = im[i];
        im[i] = im[j];
        im[j] = tmp;
    }

    // Updates the value of a key in the binary
    // heap. The key index must exist and the
    // value must not be null
    public void update(int ki, T value) {
        keyExistAndValueNotNullOrTrow(ki, value);
        final int i = pm[ki];
        values[ki] = value;
        sink(i);
        swim(i);
    }

    // Strictly increases the value associated with 'ki' to 'value'
    public void increase(int ki, T value) {
        keyExistAndValueNotNullOrTrow(ki, value);
        if (less(values[ki], value)) {
            values[ki] = value;
            sink(pm[ki]);
        }
    }

    // Strictly decreases the value associated with 'ki' to 'value'
    public void decrease(int ki, T value) {
        keyExistAndValueNotNullOrTrow(ki, value);
        if (less(value, values[ki])) {
            values[ki] = value;
            swim(pm[ki]);
        }
    }


    // Some Checks
    private void keyExistAndValueNotNullOrTrow(int ki, T value) {
        keyInBoundsOrThrow(ki);
        valueNotNullOrTrow(value);
    }

    private void valueNotNullOrTrow(T value) {
        if (value == null) throw new IllegalArgumentException("Value is null");
    }

    private void keyInBoundsOrThrow(int ki) {
        if (ki < 0 || ki >= capacity)
            throw new IllegalArgumentException("Key index out of bounds; received: " + ki);
    }

    private void isNotEmptyOrThrow() {
        if (isEmpty()) throw new NoSuchElementException("Priority queue is underflow");
    }

    @Override
    public String toString() {
        List<Integer> lst = new ArrayList<>(size);
        for (int i = 0; i < size; i++) lst.add(im[i]);
        return lst.toString();
    }

}
