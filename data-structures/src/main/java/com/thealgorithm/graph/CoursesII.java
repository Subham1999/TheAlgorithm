package com.thealgorithm.graph;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CoursesII {
  public static void main(String[] args) {
    System.out.println(
        Arrays.toString(new Solution().findOrder(4, new int[][] {{1, 0}, {2, 0}, {3, 1}, {3, 2}})));
  }

  static class Solution {
    private boolean cycleExists = false;
    private List<Integer> orderedList;

    public int[] findOrder(int numCourses, int[][] prerequisites) {
      orderedList = new ArrayList<>();
      Map<Integer, List<Integer>> graph = new HashMap<>();
      for (int i = 0; i < numCourses; ++i) {
        graph.putIfAbsent(i, new ArrayList<>());
      }

      for (int[] edge : prerequisites) {
        graph.get(edge[1]).add(edge[0]);
      }

      // System.out.println(graph);
      int[] visited = new int[numCourses];

      for (Map.Entry<Integer, List<Integer>> e : graph.entrySet()) {
        if (visited[e.getKey()] == 0) {
          dfs(graph, numCourses, e.getKey(), visited);
        }
      }

      int[] res = new int[orderedList.size()];

      System.out.println(orderedList);

      for (int i = orderedList.size() - 1, j = 0; (i >= 0); --i, ++j) {
        res[j] = orderedList.get(i);
      }

      return cycleExists ? new int[] {} : res;
    }

    void dfs(Map<Integer, List<Integer>> graph, int courseLen, int node, int[] visited) {
      if (cycleExists) {
        return;
      }

      if (!(0 <= node && node < courseLen) || visited[node] == 2) {
        return;
      }

      if (visited[node] == 1) {
        cycleExists = true;
        return;
      }

      visited[node] = 1;

      for (int next : graph.get(node)) {
        dfs(graph, courseLen, next, visited);
      }

      visited[node] = 2;
      orderedList.add(node);
    }
  }
}
