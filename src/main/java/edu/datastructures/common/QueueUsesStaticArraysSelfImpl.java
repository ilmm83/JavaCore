package edu.datastructures.common;

import java.util.Arrays;
import java.util.Iterator;

@SuppressWarnings("unchecked")
public class QueueUsesStaticArraysSelfImpl<T> implements Iterable<T> {

    private T[] queue;
    private int len = 0;
    private int capacity = 0;

    public QueueUsesStaticArraysSelfImpl(T element) {
        queue = (T[]) new Object[capacity];
        offer(element);
    }

    public int size() {
        return len;
    }

    public boolean isEmpty() {
        return size() == 0;
    }

    // Add element to the back of the Queue
    public void offer(T data) {
        if (len + 1 >= capacity) {
            if (capacity == 0) capacity = 1;
            else capacity *= 2;
            T[] temp = (T[]) new Object[capacity];
            if (len >= 0) System.arraycopy(queue, 0, temp, 0, len);
            queue = temp;
        }
        queue[len++] = data;
    }

    // Removing first element from the queue
    public void poll() {
        if (len <= 0) throw new RuntimeException("Queue is empty");
        T[] new_array = (T[]) new Object[len - 1];
        System.arraycopy(queue, 1, new_array, 0, len - 1);
        queue = new_array;
    }

    @Override
    public Iterator<T> iterator() {
        return new Iterator<T>() {
            @Override
            public boolean hasNext() {
                return false;
            }

            @Override
            public T next() {
                return null;
            }
        };
    }

    @Override
    public String toString() {
        return Arrays.toString(queue);
    }
}
