package edu.java_core.algorithms.common.bfs;

import lombok.Data;
import lombok.ToString;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class BreadthFirstSearch<T> {

    private final Vertex<T> startVertex;

    public BreadthFirstSearch(Vertex<T> startVertex) {
        this.startVertex = startVertex;
    }

    @Data
    static class Vertex<T> {
        @ToString.Exclude
        List<Vertex<T>> neighbors = new ArrayList<>();
        T data;
        boolean visited;

        public Vertex(T data) {
            this.data = data;
        }

    }

    // Not work on cyclic graphs
    public void bfsNoCyclicGraphs() {
        Queue<Vertex<T>> queue = new LinkedList<>();
        queue.add(startVertex);
        while (!queue.isEmpty()) {
            Vertex<T> v = queue.poll();
            System.out.println(v);
            v.setVisited(true);
            queue.addAll(v.getNeighbors());
        }
    }

    public void bfsCyclicGraphs() {
        Queue<Vertex<T>> queue = new LinkedList<>();
        queue.add(startVertex);
        while (!queue.isEmpty()) {
            Vertex<T> v = queue.poll();
            if (!v.isVisited()) {
                v.setVisited(true);
                System.out.println(v);
                queue.addAll(v.getNeighbors());
            }
        }
    }

}
