 package edu.datastructures.common;

import java.util.Iterator;

public class DoublyLinkedListSelfIml<T> implements Iterable {

    private int size = 0;
    private Node<T> head = null;
    private Node<T> tail = null;


    // Internal node class to represent data
    private class Node<T> {
        T data;
        Node<T> next, prev;

        public Node(T data, Node<T> next, Node<T> prev) {
            this.data = data;
            this.next = next;
            this.prev = prev;
        }

        @Override
        public String toString() {
            return data.toString();
        }
    }

    // Empty this Linked List, O(n)
    public void clear() {
        Node<T> t = head;
        while (t != null) {
            Node<T> next = head.next;
            t.prev = t.next = null;         // 0_-_-_+
            t.data = null;
            t = next;
        }
        head = tail = t = null;
        size = 0;
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size() == 0;
    }

    public void add(T elem) {
        addLast(elem);
    }

    public void addFirst(T elem) {
        if (isEmpty()) head = tail = new Node<>(elem, null, null);
        else {
            head.prev = new Node<>(elem, head, null);
            head = head.prev;
        }
        size++;
    }

    public void addLast(T elem) {
        if (isEmpty()) head = tail = new Node<>(elem, null, null);
        else {
            tail.next = new Node<>(elem, null, tail);
            tail = tail.next;
        }
        size++;
    }

    public T peekFirst() {
        if (isEmpty()) throw new RuntimeException("List is Empty");
        return head.data;
    }

    public T peekLast() {
        if (isEmpty()) throw new RuntimeException("List is Empty");
        return tail.data;
    }

    public T removeFirst() {
        if (isEmpty()) throw new RuntimeException("List is Empty");
        T data = head.data;
        head = head.next;
        --size;
        if (isEmpty()) tail = null;         // if the list is empty set the tail to null as wall
        else head.prev = null;              // Do a memory clean of the previous node
        return data;                        // return the element witch we just remove
    }

    public T removeLast() {
        if (isEmpty()) throw new RuntimeException("List is empty");
        T data = tail.data;
        tail = tail.prev;
        --size;
        if (isEmpty()) head = null;
        else tail.next = null;
        return data;
    }

    public T remove(Node<T> node) {
        if (node.prev == null) return removeFirst();
        if (node.next == null) return removeLast();

        node.next.prev = node.prev; // 1<---2---3
        node.prev.next = node.next; // 1---2-->3
        // result: null <-- 1 <---> 3--> null

        T data = node.data;

        // memory clean up
        node.data = null;
        node = node.prev = node.next = null;

        --size;
        return data;
    }

    public T removeAt(int index) {

        // Make sure the index provided is valid
        if (index < 0 || index >= size) throw new IndexOutOfBoundsException();
        int i;
        Node<T> t;
        // Search from the front of list
        if (index < size / 2) {
            for (i = 0, t = head; i != index; i++) {
                t = t.next;
            }
            // Search from the back of list
        } else {
            for (i = size - 1, t = tail; i != index; i--) {
                t = t.prev;
            }
        }
        return remove(t);
    }

    public boolean remove(Object o) {
        Node<T> t = head;

        if (o == null) {
            for (t = head; t != null; t = t.next) {
                if (t.data == null) {
                    remove(t);
                    return true;
                }
            }
        } else {
            for (t = head; t != null; t = t.next) {
                if (t.data.equals(o)) {
                    remove(o);
                    return true;
                }
            }
        }
        return false;
    }

    public int indexOf(Object o) {
        int index = 0;
        Node<T> t = head;

        if (o == null) {
            for (t = head; t != null; t = t.next, index++) {
                if (t.data == null) {
                    return index;
                }
            }
        } else {
            for (t = head; t != null; t = t.next, index++) {
                if (t.data.equals(o)) {
                    return index;
                }
            }
        } return -1;
    }

    public boolean contains(Object o) {
        return indexOf(o) != -1;
    }

    @Override
    public Iterator<T> iterator() {
        return new Iterator<T>() {
            private Node<T> t = head;
            @Override
            public boolean hasNext() {
                return t != null;
            }
            @Override
            public T next() {
                T data = t.data;
                t = t.next;
                return data;
            }
        };
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder(size);
        sb.append("[");
        for (Node<T> t = head; t != null; t = t.next){
            sb.append(t.data + ", ");
        } return sb.append("]").toString();
    }
}
