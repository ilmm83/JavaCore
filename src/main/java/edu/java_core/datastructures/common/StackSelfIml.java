package edu.java_core.datastructures.common;

import java.util.Iterator;
import java.util.LinkedList;

public class StackSelfIml<T> implements Iterable<T> {

    private LinkedList<T> list = new LinkedList<>();

    public StackSelfIml() {
    }

    public StackSelfIml(T element) {
        push(element);
    }

    public T pop() {
        if (isEmpty()) throw new RuntimeException("Stack is Empty");
        return list.removeLast();
    }

    public T peek() {
        if (isEmpty()) throw new RuntimeException("Stack is Empty");
        return list.peekLast();
    }

    public void push(T o) {
        list.addLast(o);
    }

    public int size() {
        return list.size();
    }

    public boolean isEmpty() {
        return size() == 0;
    }

    @Override
    public Iterator<T> iterator() {
        return list.iterator();
    }
}
