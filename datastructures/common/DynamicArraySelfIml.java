package FirstStap.datastructures.common;

import java.util.Iterator;
import java.util.Spliterator;
import java.util.function.Consumer;

@SuppressWarnings("unchecked")
public class DynamicArraySelfIml<T> implements Iterable<T> {

    private T[] arr;    // Array with type objects
    private int len = 0; // Length user thinks array is
    private int capacity = 0; // Actual length of array

    public DynamicArraySelfIml() {
        this(16);
    }

    // Create an array with specified capacity
    public DynamicArraySelfIml(int capacity) {
        if (capacity <= 0) throw new IllegalArgumentException("Illegal an array capacity: " + capacity);
        this.capacity = capacity;
        arr = (T[]) new Object[capacity];
    }


    public int size() {
        return len;
    }

    public boolean isEmpty() {
        return size() == 0;
    }

    public T get(int index) {
        return arr[index];
    }

    public void set(int index, T element) {
        arr[index] = element;
    }

    public void clear() {
        for (int i = 0; i < arr.length; i++)
            arr[i] = null;
        len = 0;
    }

    public void add(T elem) {
        // Time to resize!
        if (len + 1 >= capacity) {
            if (capacity == 0) capacity = 1;
            else capacity *= 2; // double the size
            T[] new_arr = (T[]) new Object[capacity];
            for (int i = 0; i < len; i++) new_arr[i] = arr[i];
            arr = new_arr; // arr has extra nulls padded
        }
        arr[len++] = elem;
    }

    public T removeAt(int index) {
        if (index >= len && index < 0)
            throw new IndexOutOfBoundsException("Index of an element is out of bounds: " + index);
        T elem = arr[index];                            // Removable  element
        T[] new_arr = (T[]) new Object[len - 1];    // Temp array
        for (int i = 0, j = 0; i < len; i++, j++)  // WTF
            if (i == index) j--;                      // For skip removable element
            else new_arr[j] = arr[i];                  // Fill the temp array
        arr = new_arr;                            // Fill the actual array
        capacity = --len;
        return elem;                                  // return deleted one
    }

    public boolean remove(Object o) {
        for (int i = 0; i < len; i++) {
            if (arr[i].equals(o)) {
                removeAt(i);
                return true;
            }
        }
        return false;
    }

    public int indexOf(Object o) {
        for (int i = 0; i < len; i++) {
            if (arr[i].equals(o)) return i;
        }
        return -1;
    }

    public boolean contains(Object o) {
        return indexOf(o) != -1;
    }


    @Override
    public Iterator<T> iterator() {
        return new Iterator<T>() {
            int index = 0;

            @Override
            public boolean hasNext() {
                return index < len;
            }

            @Override
            public T next() {
                return arr[index++];
            }
        };
    }

    @Override
    public String toString() {
        if (len == 0) return "[]";
        else {
            StringBuilder sb = new StringBuilder(len).append("[");
            for (int i = 0; i < len - 1; i++) sb.append(arr[i] + ", ");
            return sb.append(arr[len - 1] + "]").toString();
        }
    }
}
