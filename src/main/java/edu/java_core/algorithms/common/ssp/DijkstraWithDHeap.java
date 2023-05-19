package edu.java_core.algorithms.common.ssp;

import edu.java_core.datastructures.common.MinIndexedDPriorityQueue;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

// Eager version
public class DijkstraWithDHeap {
    // An edge class to represent a directed edge
    // between two nodes with a certain cost.
    public static class Edge {

        int to;
        double cost;

        public Edge(int to, double cost) {
            this.to = to;
            this.cost = cost;
        }

    }

    private final int n;
    private int edgeCount;
    private double[] dist;
    private Integer[] prev;
    private List<List<Edge>> graph;


    public DijkstraWithDHeap(int n) {
        this.n = n;
        createEmptyGraph();
    }

    private void createEmptyGraph() {
        graph = new ArrayList<>(n);
        for (int i = 0; i < n; i++) graph.add(new ArrayList<>());
    }

    public void addEdge(int from, int to, int cost) {
        edgeCount++;
        graph.get(from).add(new Edge(to, cost));
    }

    // Run Dijkstra's algorithm on a directed graph to find the shortest path
    // from a starting node to an ending node. If there is no path between the
    // starting node and the destination node the returned value is set to be
    // Double.POSITIVE_INFINITY.
    public double dijkstra(int start, int end) {
        int degree = edgeCount / 2;
        MinIndexedDPriorityQueue<Double> ipq = new MinIndexedDPriorityQueue<>(n, degree);
        ipq.insert(start, 0.0);

        dist = new double[n];
        Arrays.fill(dist, Double.POSITIVE_INFINITY);
        dist[start] = 0;

        prev = new Integer[n];
        boolean[] visited = new boolean[n];

        while (!ipq.isEmpty()) {
            int nodeId = ipq.peekMinKeyIndex();

            visited[start] = true;
            double minValue = ipq.pollMinValue();

            if (minValue > dist[nodeId]) continue;

            for (Edge edge : graph.get(nodeId)) {
                if (visited[edge.to]) continue;

                // Relax edge by updating minimum cost if applicable
                double newDist = dist[nodeId] + edge.cost;
                if (newDist < dist[edge.to]) {
                    dist[edge.to] = newDist;
                    prev[edge.to] = nodeId;
                    // Insert the cost of going to a node for the first time in the PQ,
                    // or try and update it to a better value by calling decrease.
                    if (!ipq.contains(edge.to)) ipq.insert(edge.to, newDist);
                    else ipq.decrease(edge.to, newDist);
                }
            }
            // Once we've processed the end node we can return early (without
            // necessarily visiting the whole graph) because we know we cannot get a
            // shorter path by routing through any other nodes since Dijkstra's is
            // greedy and there are no negative edge weights.
            if (nodeId == end) return dist[end];
        }
        return Double.POSITIVE_INFINITY;
    }

    public List<Integer> reconstructPath(int start, int end) {
        if (end < 0 || end >= n ) throw new IllegalArgumentException();
        if (start < 0 || start >= n ) throw new IllegalArgumentException();
        List<Integer> path = new ArrayList<>();
        double dist = dijkstra(start, end);
        if (dist == Double.POSITIVE_INFINITY) return path;
        for (Integer at = end; at != null; at = prev[at]) path.add(at);
        Collections.reverse(path);
        return path;
    }


}