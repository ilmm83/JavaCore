package edu.java_core.algorithms.common.dfs;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class DepthFirstSearchAdjacencyListRecursive {

    static class Edge {
        int from, to, cost;
        public Edge(int from, int to, int cost) {
            this.from = from;
            this.to = to;
            this.cost = cost;
        }
    }

    public long dfs(int at, boolean[] visited, Map<Integer, List<Edge>> graph) {
        // Base case: Node already visited
        if (visited[at]) return 0L;

        // Visit this node
        visited[at] = true;
        long count = 1;

        // Visit all edges adjacent to where we're at
        List<Edge> edges = graph.get(at);
        if (edges != null)
            for (Edge edge : edges)
                count += dfs(edge.to, visited, graph);
        return count;
    }

    // Helper method to set up graph
    public static void addDirectedEdge(Map<Integer, List<Edge>> graph, int from, int to, int cost) {
        List<Edge> list = graph.computeIfAbsent(from, k -> new ArrayList<Edge>());
        list.add(new Edge(from, to, cost));
    }
}
