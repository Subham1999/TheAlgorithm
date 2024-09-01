package com.thealgorithm.graph;

public class FloydWarshallAlgorithm {
  static final int INF = Integer.MAX_VALUE;

  static int[][] generateCostMatrix(int[][] graph) {
    runFloydWarshall(graph);
    return graph;
  }

  private static void runFloydWarshall(int[][] graph) {
    for (int k = 0; k < graph.length; k++) { // intermediate
      for (int i = 0; i < graph.length; i++) { // source
        for (int j = 0; j < graph.length; j++) { // destination
          if ((graph[i][j] > graph[i][k] + graph[k][j])
              && graph[i][k] != INF
              && graph[k][j] != INF) {
            graph[i][j] = graph[i][k] + graph[k][j];
          }
        }
      }
    }
  }

  public static void main(String[] args) {
    int[][] g = {
      {0, 2, INF, INF, INF},
      {INF, 0, 5, INF, INF},
      {INF, 5, 0, INF, 1},
      {INF, INF, INF, 0, 20},
      {INF, 2, INF, INF, 0}
    };

    int[][] costMatrix = generateCostMatrix(g);
    for (int i = 0; i < costMatrix.length; i++) {
      for (int j = 0; j < costMatrix[i].length; j++) {
        System.out.printf("%s\t", costMatrix[i][j] == INF ? "inf" : costMatrix[i][j]);
      }
      System.out.println();
    }
  }
}
