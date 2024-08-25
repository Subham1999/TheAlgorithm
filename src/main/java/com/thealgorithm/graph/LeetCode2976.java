package com.thealgorithm.graph;

import java.math.BigInteger;

public class LeetCode2976 {
  static class Solution {
    private static final long INF = Long.MAX_VALUE;

    void runFloydWarshall(long[][] graph) {
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

    public long minimumCost(
        String source, String target, char[] original, char[] changed, int[] cost) {
      // prepare the graph
      final long[][] graph = new long[26][26];

      for (int i = 0; i < 26; ++i) {
        for (int j = 0; j < 26; ++j) {
          graph[i][j] = INF;
        }
      }

      for (int i = 0; i < original.length; ++i) {
        graph[original[i] - 'a'][changed[i] - 'a'] =
            Math.min(graph[original[i] - 'a'][changed[i] - 'a'], cost[i]);
      }

      for (int i = 0; i < 26; ++i) {
        graph[i][i] = 0L;
      }

      runFloydWarshall(graph);

      BigInteger totalCost = BigInteger.valueOf(0);

      for (int i = 0; i < source.length(); ++i) {
        int x = source.charAt(i) - 'a';
        int y = target.charAt(i) - 'a';
        if (graph[x][y] == INF) {
          return -1;
        }
        totalCost.add(BigInteger.valueOf(graph[x][y]));
      }

      return totalCost.intValue();
    }
  }
}
