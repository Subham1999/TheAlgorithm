package com.thealgorithm.graph;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Queue;

public class LeetCode2045 {
  public static void main(String[] args) {
    test1();
    test2();
  }

  private static void test1() {
    int n = 6;
    int[][] edges = {{1, 2}, {1, 3}, {3, 5}, {2, 4}, {5, 4}, {4, 6}};
    int time = 3;
    int change = 100;
    System.out.println(new Solution().secondMinimum(n, edges, time, change));
  }

  private static void test2() {
    int n = 8;
    int[][] edges = {
      {1, 2}, {1, 3}, {2, 5}, {2, 4}, {3, 4}, {3, 5}, {3, 6}, {5, 7}, {6, 7}, {7, 8}
    };
    int time = 1;
    int change = 100;
    System.out.println(new Solution().secondMinimum(n, edges, time, change));
  }

  static class Solution {

    public int secondMinimum(int n, int[][] edges, int time, int change) {
      Map<Integer, List<Integer>> adj = new HashMap<>();

      for (int[] edge : edges) {
        adj.putIfAbsent(edge[0], new ArrayList<>());
        adj.putIfAbsent(edge[1], new ArrayList<>());

        adj.get(edge[0]).add(edge[1]);
        adj.get(edge[1]).add(edge[0]);
      }

      Queue<Integer> queue = new ArrayDeque<>();
      int[][] dist = new int[n + 1][2];

      for (int i = 0; i <= n; i++) {
        Arrays.fill(dist[i], -1);
      }

      queue.add(1);
      dist[1][0] = 0;

      int iteration = 1;

      while (!queue.isEmpty()) {
        Queue<Integer> nextQueue = new ArrayDeque<>();

        while (!queue.isEmpty()) {
          Integer node = queue.poll();

          for (int nextNode : adj.get(node)) {
            if (dist[nextNode][0] == -1) {
              dist[nextNode][0] = iteration;
              nextQueue.add(nextNode);
            } else if (dist[nextNode][1] == -1 && iteration != dist[nextNode][0]) {
              dist[nextNode][1] = iteration;
              nextQueue.add(nextNode);
            }
          }
        }

        ++iteration;
        queue = nextQueue;
      }

      int nodeCount = dist[n][1] == -1 ? dist[n][0] + 2 : dist[n][1];

      //      System.out.println(Arrays.toString(dist[n]));
      return calculateTime(nodeCount + 1, time, change);
    }

    private int calculateTime(int nodeCount, int time, int change) {
      int totalTime = 0;
      for (int i = 1; i < nodeCount; ++i) {
        totalTime += time;
        if (i < (nodeCount - 1)) {
          if ((totalTime / change) % 2 == 1) {
            totalTime = (totalTime / change + 1) * change;
          }
        }
      }
      return totalTime;
    }
  }
}
