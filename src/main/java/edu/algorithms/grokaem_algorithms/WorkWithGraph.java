package edu.algorithms.grokaem_algorithms;

import java.util.*;

public class WorkWithGraph {
    public static void main(String[] args) {
        // define edges of the graph
        List<Edge> edges = Arrays.asList(
                new Edge(0, 1, 2), new Edge(0, 2, 4),
                new Edge(1, 2, 4), new Edge(2, 0, 5),
                new Edge(4, 5, 1), new Edge(5, 4, 3));

        // call graph class Constructor to construct a graph
        Graph graph = new Graph(edges);

        // print the graph as an adjacency list
        Graph.printGraph(graph);
    }
}

//class to store edges of the weighted graph
class Edge {
    int src, dest, weight;

    public Edge(int src, int dest, int weight) {
        this.src = src;
        this.dest = dest;
        this.weight = weight;
    }

}

class Graph {
    // node of adjacency list
    static class Node {
        int value, weight;

        public Node(int value, int weight) {
            this.value = value;
            this.weight = weight;
        }
    }

    // define adjacency list
    List<List<Node>> adjList = new ArrayList<>();

    public Graph(List<Edge> edges) {
        // adjacency list memory allocation
        for (int i = 0; i < edges.size(); i++) {
            adjList.add(i, new ArrayList<>());
        }

        // add edges to the graph
        for (Edge e : edges) {
            // allocate new node in adjacency List from src to dest
            adjList.get(e.src).add(new Node(e.dest, e.weight));
        }
    }

    // print adjacency list for the graph
    public static void printGraph(Graph graph) {
        int srcVertex = 0;
        int listSize = graph.adjList.size();

        System.out.println("The concept of the graph");
        while (srcVertex < listSize) {
            // traverse through the adjacency list and print the edges
            for (Node edge : graph.adjList.get(srcVertex)) {
                System.out.print(("Vertex: " + srcVertex + " ==> " + edge.value
                        + " ( " + edge.weight + " )\t"));
            }
            System.out.println();
            srcVertex++;
        }
    }
}

















