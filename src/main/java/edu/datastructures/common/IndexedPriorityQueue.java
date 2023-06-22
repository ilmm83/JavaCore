package edu.datastructures.common;

public class IndexedPriorityQueue<T extends Comparable<T>> extends MinIndexedDPriorityQueue<T> {


    public IndexedPriorityQueue(int maxSize, int degree) {
        super(maxSize, degree);
    }
}
