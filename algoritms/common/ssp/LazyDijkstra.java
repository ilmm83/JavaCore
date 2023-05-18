package FirstStap.algoritms.common.ssp;

import java.util.*;

public class LazyDijkstra {

    // Small epsilon value to comparing double values.
    private static final double EPS = 1e-6;

    // An Edge class to represent a directed edge
    // between two nodes with a certain cost.
    static class Edge {
        int from, to;
        double cost;

        public Edge(int from, int to, double cost) {
            this.from = from;
            this.to = to;
            this.cost = cost;
        }
    }

    // Node class to track the nodes to visit while running Dijkstra's
    static class Node {
        int id;
        double value;

        public Node(int id, double value) {
            this.id = id;
            this.value = value;
        }
    }

    private int n;
    private double[] dist;
    private Integer[] prev;
    private List<List<Edge>> graph;

    private Comparator<Node> comp = (o1, o2) -> {
        if (Math.abs(o1.value - o2.value) < EPS) return 0;
        return (o1.value - o2.value) > 0 ? +1 : -1;
    };


    /**
     * Initialize the solver by providing the graph size and a starting node. Use the {@link #addEdge}
     * method to actually add edges to the graph.
     *
     * @param n - The number of nodes in the graph.
     */

    public LazyDijkstra(int n, Comparator<Node> comp) {
        this.n = n;
        if (comp == null) throw new IllegalArgumentException();
        this.comp = comp;
    }

    public LazyDijkstra(int n) {
        this.n = n;
        createEmptyGraph();
    }

    /**
     * Reconstructs the shortest path (of nodes) from 'start' to 'end' inclusive.
     *
     * @return An array of nodes indexes of the shortest path from 'start' to 'end'. If 'start' and
     * 'end' are not connected then an empty array is returned.
     */
    public List<Integer> reconstructPath(int start, int end) {
        if (end < 0 || end >= n) throw new IllegalArgumentException();
        if (start < 0 || start >= n) throw new IllegalArgumentException();
        double dist = dijkstra(start, end);
        List<Integer> path = new ArrayList<>();
        if (dist == Double.POSITIVE_INFINITY) return path;
        for (Integer at = end; at != null; at = prev[at]) path.add(at);
        Collections.reverse(path);
        return path;
    }

    public double dijkstra(int start, int end) {
        // Maintain an array of the minimum distance to each node
        dist = new double[n];
        Arrays.fill(dist, Double.POSITIVE_INFINITY);
        dist[start] = 0;

        // Keep a priority queue of the next most promising node to visit.
        PriorityQueue<Node> pq = new PriorityQueue<>(2 * n, comp);
        pq.offer(new Node(start, 0));

        // Array used to track which nodes have already been visited.
        boolean[] visited = new boolean[n];
        prev = new Integer[n];

        while (!pq.isEmpty()) {
            Node node = pq.poll();
            visited[node.id] = true;

            // We already found a better path before we got to
            // processing this node, so we can ignore it
            if (dist[node.id] < node.value) continue;

            List<Edge> edges = graph.get(node.id);
            for (Edge edge : edges) {
                // You cannot get a shorter path by revisiting
                // a node you have already visited before.
                if (visited[edge.to]) continue;

                // Relax edge by updating minimum cost if applicable
                double newDist = dist[edge.from] + edge.cost;
                if (newDist < dist[edge.to]) {
                    prev[edge.to] = edge.from;
                    dist[edge.to] = newDist;
                    pq.offer(new Node(edge.to, dist[edge.to]));
                }
            }
            // Once we've visited all the nodes spanning from the end
            // node we know we can return the minimum distance value to
            // the end node because it cannot get any better after this pont.
            if (node.id == end) return dist[end];
        }
        return Double.POSITIVE_INFINITY;
    }

    public void addEdge(int from, int to, int cost) {
        graph.get(from).add(new Edge(from, to, cost));
    }

    private void createEmptyGraph() {
        graph = new ArrayList<>(n);
        for (int i = 0; i < n; i++) graph.add(new ArrayList<>());
    }
}
