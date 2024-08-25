package com.thealgorithm.array;

public class AGraphIsAValidTree {
    private boolean cycleExist = false;

    // invoker
    public boolean validTree(int n, int[][] edges) {
        int[][] graph = new int[n][n];

        for (int i = 0; i < edges.length; ++i) {
            graph[edges[i][0]][edges[i][1]] = 1;
            graph[edges[i][1]][edges[i][0]] = 1;
        }

        // Start DFS
        boolean[] visited = new boolean[n];
        dfs(0, -1, visited, graph);

        if (cycleExist) {
            return false;
        }

        for (int i = 0; i < n; ++i) {
            if (!visited[i]) {
                // That means after a single pass DFS
                // i-th node was not visited
                // which proves that graph is not connected
                return false;
            }
        }

        return true;
    }

    void dfs(int node, int parent, boolean[] visited, int[][] graph) {
        // System.out.println("- cc : " + node + " - " + parent);
        if (!valid(graph, node)) {
            return;
        }

        if (cycleExist) {
            return;
        }

        if (visited[node]) {
            //System.out.println("cc : " + node + " - " + parent);
            cycleExist = true;
            return;
        }

        visited[node] = true;

        for (int nextNode = 0; nextNode < graph.length; ++nextNode) {
            if (graph[node][nextNode] == 1 && nextNode != parent) {
                dfs(nextNode, node, visited, graph);
            }
        }
    }

    boolean valid(int[][] graph, int node) {
        return 0 <= node && node < graph.length;
    }
}
