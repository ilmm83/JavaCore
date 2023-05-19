package edu.java_core.algorithms.common.dfs;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class DepthFirstSearchAdjacencyListIterative<T> {

    static class Vertex<T> {
        List<Vertex<T>> neighbors = new ArrayList<>();
        T data;
        boolean visited;

        public Vertex(T data) {
            this.data = data;
        }
    }

    public void dfs(Vertex<T> startVector) {
        Stack<Vertex<T>> stack = new Stack<>();
        stack.push(startVector);
        while (!stack.isEmpty()) {
            Vertex<T> node = stack.pop();
            if (!node.visited) {
                node.visited = true;
                node.neighbors.forEach(stack::push);
            }
        }
    }
}

