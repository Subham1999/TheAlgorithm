package com.subham.ta.graph;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class TopologicalSort {

  public static void main(String[] args) {
    test0();
    test1();
  }

  private static void test1() {
    int N = 6;
    int[][] graph = {
      {0, 0, 0, 0, 0, 0, 0},
      {0, 0, 1, 0, 0, 0, 0},
      {0, 0, 0, 0, 1, 0, 0},
      {0, 0, 1, 0, 0, 0, 0},
      {0, 0, 0, 0, 0, 1, 0},
      {0, 0, 0, 0, 0, 0, 0},
      {0, 1, 0, 1, 0, 0, 0}
    };
    System.out.println(topologicalSort(graph, N));
  }

  private static void test0() {
    int N = 5;
    int[][] graph = {
      {0, 0, 0, 0, 0, 0}, // not a node
      {0, 0, 1, 0, 0, 0}, // 1
      {0, 0, 0, 1, 1, 0}, // 2
      {0, 0, 0, 0, 0, 1}, // 3
      {0, 0, 0, 0, 0, 1}, // 4
      {0, 0, 0, 0, 0, 0} // 5
    };
    System.out.println(topologicalSort(graph, N));
  }

  private static List<Integer> topologicalSort(int[][] graph, int noOfNodes) {
    final List<Integer> orderedTraversal = new ArrayList<>();
    final Set<Integer> visitedNodes = new HashSet<>();

    for (int node = 1; node <= noOfNodes; ++node) {
      traverse(node, graph, noOfNodes, orderedTraversal, visitedNodes);
    }

    Collections.reverse(orderedTraversal);
    return orderedTraversal;
  }

  private static void traverse(
      int node,
      int[][] graph,
      int noOfNodes,
      List<Integer> orderedTraversal,
      Set<Integer> visitedNodes) {

    if (node <= 0 || node > noOfNodes) {
      return;
    }
    if (visitedNodes.contains(node)) {
      return;
    }

    visitedNodes.add(node);
    for (int nextNode = 1; nextNode <= noOfNodes; ++nextNode) {
      if (graph[node][nextNode] != 0) { // Edge exists
        traverse(nextNode, graph, noOfNodes, orderedTraversal, visitedNodes);
      }
    }
    orderedTraversal.add(node);
  }
}
