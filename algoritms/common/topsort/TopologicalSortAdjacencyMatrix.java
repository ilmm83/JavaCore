package FirstStap.algoritms.common.topsort;

public class TopologicalSortAdjacencyMatrix {


    public int[] topsort(int[][] matrix) {
        int n = matrix.length;
        int[] ordering = new int[n];
        boolean[] visited = new boolean[n];
        int index = n - 1;
        for (int u = 0; u < n; u++)
            if (!visited[u]) index = visit(index, matrix, ordering, visited, u);
        return ordering;
    }

    private int visit(int index, int[][] matrix, int[] ordering, boolean[] visited, int u) {
        if (visited[u]) return index;
        visited[u] = true;

        for (int v = 0; v < matrix.length; v++)
            if (!visited[v] && matrix[u][v] != 0) index = visit(index, matrix, ordering, visited, v);
        ordering[index--] = u;
        return index;
    }

}
