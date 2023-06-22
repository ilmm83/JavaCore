package edu.algorithms.common.topsort;

import java.util.List;
import java.util.Map;

public class TopologicalSort {

    static class Edge {
        int from, to, weight;

        public Edge(int from, int to, int weight) {
            this.from = from;
            this.to = to;
            this.weight = weight;
        }
    }

    // Helper method that performs a depth first search on the graph to give
    // us the topological ordering we want. Instead of maintaining a stack
    // of the nodes we see we simply place them inside the ordering array
    // in reverse order for simplicity.
    private static int dfs(int i, int at, boolean[] visited, int[] ordering, Map<Integer, List<Edge>> graph) {
        visited[at] = true;
        List<Edge> edges = graph.get(at);
        if (edges != null)
            for (Edge edge : edges) if (!visited[edge.to]) i = dfs(i, edge.to, visited, ordering, graph);
        ordering[i] = at;
        return i - 1;
    }

    // Finds a topological ordering of the nodes in a Directed Acyclic Graph (DAG)
    // The input to this function is an adjacency list for a graph and the number
    // of nodes in the graph.
    //
    // NOTE: 'numNodes' is not necessarily the number of nodes currently present
    // in the adjacency list since you can have singleton nodes with no edges which
    // wouldn't be present in the adjacency list but are still part of the graph!
    //
    public static int[] topologicalSort(Map<Integer, List<Edge>> graph, int numberOfNodes) {
        int i = numberOfNodes - 1;
        boolean[] visited = new boolean[numberOfNodes];
        int[] ordering = new int[numberOfNodes];
        for (int at = 0; at < i; at++) if (!visited[at]) at = dfs(i, at, visited, ordering, graph);
        return ordering;
    }

    // A useful application of the topological sort is to find the shortest path
    // between two nodes in a Directed Acyclic Graph (DAG). Given an adjacency list
    // this method finds the shortest path to all nodes starting at 'start'
    //
    // NOTE: 'numNodes' is not necessarily the number of nodes currently present
    // in the adjacency list since you can have singleton nodes with no edges which
    // wouldn't be present in the adjacency list but are still part of the graph!
    public static Integer[] dagShortestPath(Map<Integer, List<Edge>> graph, int start, int numNodes) {
        int[] topsort = topologicalSort(graph, numNodes);
        Integer[] dist = new Integer[numNodes];
        dist[start] = 0;
        for (int i = 0; i < numNodes; i++) {
            int nodeIndex = topsort[i];
            if (dist[nodeIndex] != null) {
                List<Edge> adjacentEdges = graph.get(nodeIndex);
                if (adjacentEdges != null) {
                    for (Edge edge : adjacentEdges) {
                        int newDist = dist[nodeIndex] + edge.weight;
                        if (dist[edge.to] == null) dist[edge.to] = newDist;
                        else dist[edge.to] = Math.min(dist[edge.to], newDist);
                    }
                }
            }
        }
        return dist;
    }
}