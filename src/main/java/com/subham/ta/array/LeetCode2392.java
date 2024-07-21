package com.subham.ta.array;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

public class LeetCode2392 {
  static class Solution {
    private static final int VISITED = 3;
    private static final int IN_PROGRESS = 2;
    private static AtomicBoolean IS_CYCLIC = new AtomicBoolean(false);

    public int[][] buildMatrix(int k, int[][] rowConditions, int[][] colConditions) {
      int[][] graph1 = new int[1 + k][1 + k];
      for (int i = 0; i < rowConditions.length; ++i) {
        graph1[rowConditions[i][0]][rowConditions[i][1]] = 1;
      }
      List<Integer> rowOrder = topologicalSort(graph1);
      if (IS_CYCLIC.get()) {
        return new int[][] {};
      }
      int[][] graph2 = new int[1 + k][1 + k];
      for (int i = 0; i < colConditions.length; ++i) {
        graph2[colConditions[i][0]][colConditions[i][1]] = 1;
      }
      List<Integer> colOrder = topologicalSort(graph2);
      if (IS_CYCLIC.get()) {
        return new int[][] {};
      }
      int[][] mat = new int[k][k];
      for (int i = 0; i < k; ++i) {
        for (int j = 0; j < k; ++j) {
          if (rowOrder.get(i) == colOrder.get(j)) {
            mat[i][j] = rowOrder.get(i);
          }
        }
      }
      return mat;
    }

    private List<Integer> topologicalSort(int[][] graph) {
      List<Integer> completed = new ArrayList<>();
      int[] visited = new int[graph.length];
      for (int i = 1; i < graph.length; i++) {
        if (visited[i] == 0) {
          dfs(graph, i, completed, visited);
        }
      }
      Collections.reverse(completed);
      return completed;
    }

    private void dfs(int[][] graph, int node, List<Integer> completed, int[] visited) {
      if (IS_CYCLIC.get() || visited[node] == VISITED) return;
      if (visited[node] == IN_PROGRESS) {
        IS_CYCLIC.set(true);
        return;
      }
      visited[node] = IN_PROGRESS;
      for (int next = 0; next < graph.length; next++) {
        if (graph[node][next] == 1) {
          dfs(graph, next, completed, visited);
        }
      }
      visited[node] = VISITED;
      completed.add(node);
    }
  }

  public static void main(String[] args) {
    int k = 8;
    int[][] rowConditions = {
      {1, 2}, {7, 3}, {4, 3}, {5, 8}, {7, 8}, {8, 2}, {5, 8}, {3, 2}, {1, 3}, {7, 6}, {4, 3},
      {7, 4}, {4, 8}, {7, 3}, {7, 5}
    };
    int[][] colConditions = {{5, 7}, {2, 7}, {4, 3}, {6, 7}, {4, 3}, {2, 3}, {6, 2}};
    int[][] ints = new Solution().buildMatrix(k, rowConditions, colConditions);

    for (int i = 0; i < ints.length; ++i) {
      for (int j = 0; j < ints[i].length; ++j) {
        System.out.printf("%d\t", ints[i][j]);
      }
      System.out.println();
    }
  }
}
