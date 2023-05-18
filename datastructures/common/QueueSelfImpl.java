package FirstStap.datastructures.common;

import java.util.Iterator;
import java.util.LinkedList;

public class QueueSelfImpl<T> implements Iterable<T> {

    private LinkedList<T> list = new LinkedList<>();

    public QueueSelfImpl() {}

    public QueueSelfImpl(T elem) {
        offer(elem);
    }

    // Add an element to the back of the Queue
    public void offer(T elem) {
        list.addLast(elem);
    }

    public int size() {
        return list.size();
    }

    public boolean isEmpty() {
        return size() == 0;
    }

    // Take a first element and not remove
    public T peek() {
        if (isEmpty()) throw new RuntimeException("Queue is empty");
        return list.peekFirst();
    }

    // Take a first element and remove
    public T poll() {
        if (isEmpty()) throw new RuntimeException("Queue is empty");
        return list.removeFirst();
    }

    @Override
    public Iterator<T> iterator() {
        return list.iterator();
    }
}
