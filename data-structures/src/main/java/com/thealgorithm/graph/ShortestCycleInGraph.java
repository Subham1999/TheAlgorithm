package com.thealgorithm.graph;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Queue;

/**
 * @author: Subham Santra
 */
public class ShortestCycleInGraph {
  public int findShortestCycle(int n, int[][] edges) {

    // Build the Graph DS
    Map<Integer, List<Integer>> graph = new HashMap<>();
    for (int[] edge : edges) {
      graph.putIfAbsent(edge[0], new ArrayList<>());
      graph.putIfAbsent(edge[1], new ArrayList<>());

      graph.get(edge[0]).add(edge[1]);
      graph.get(edge[1]).add(edge[0]);
    }

    // System.out.println(graph);

    // Will do BFS
    // But we need to know for each node - the shortest cycle that node is part of.
    return BFS(graph, n);
  }

  int BFS(Map<Integer, List<Integer>> graph, int n) {
    int[] shortestCycleCount = new int[n];
    Arrays.fill(shortestCycleCount, Integer.MAX_VALUE);

    // Will perform breadth first search on all nodes
    for (int node = 0; node < n; ++node) {
      int[] time = new int[n];
      Queue<int[]> queue = new ArrayDeque<>();
      queue.add(new int[] {node, 0, -1});

      while (!queue.isEmpty()) {
        int[] _node = queue.poll();
        int currentNode = _node[0];
        int visitingTime = _node[1];
        int parentNode = _node[2];

        // the current node is already visited
        // that means this is a cycle with 'node'
        if (time[currentNode] > 0) {
          shortestCycleCount[node] =
              Math.min(shortestCycleCount[node], time[currentNode] + visitingTime);
          continue;
        }

        // calculate current node's visiting time.
        // if not first time, then always keep the lowest visiting time from 'node' to 'currentNode'
        time[currentNode] =
            (time[currentNode] == 0) ? visitingTime : Math.min(time[currentNode], visitingTime);

        for (int child : graph.getOrDefault(currentNode, Collections.emptyList())) {
          if (child == parentNode) {
            continue;
          }

          queue.add(new int[] {child, visitingTime + 1, currentNode});
        }
      }
    }
    // System.out.println(Arrays.toString(shortestCycleCount));
    return Arrays.stream(shortestCycleCount).filter(i -> i != Integer.MAX_VALUE).min().orElse(-1);
  }

  public static void main(String[] args) {
    System.out.println(
        new ShortestCycleInGraph()
            .findShortestCycle(
                7, new int[][] {{0, 1}, {1, 2}, {2, 0}, {3, 4}, {4, 5}, {5, 6}, {6, 3}}));

    System.out.println(
        new ShortestCycleInGraph()
            .findShortestCycle(
                9,
                new int[][] {
                  {0, 1}, {1, 2}, {2, 3}, {0, 3}, {3, 4}, {4, 5}, {5, 6}, {7, 8}, {8, 0}
                }));

    System.out.println(
        new ShortestCycleInGraph().findShortestCycle(3, new int[][] {{0, 1}, {1, 2}}));

    System.out.println(
        new ShortestCycleInGraph()
            .findShortestCycle(
                6, new int[][] {{4, 1}, {5, 1}, {3, 2}, {5, 0}, {4, 0}, {3, 0}, {2, 1}}));

    // Exception test case;
    // ans: 4
    System.out.println(
        new ShortestCycleInGraph()
            .findShortestCycle(
                6, new int[][] {{4, 2}, {5, 1}, {5, 0}, {0, 3}, {5, 2}, {1, 4}, {1, 3}, {3, 4}}));
  }
}
