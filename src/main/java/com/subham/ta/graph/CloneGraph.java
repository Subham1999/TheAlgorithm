package com.subham.ta.graph;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class CloneGraph {
  static class Node {
    public int val;
    public List<Node> neighbors;

    public Node() {
      val = 0;
      neighbors = new ArrayList<Node>();
    }

    public Node(int _val) {
      val = _val;
      neighbors = new ArrayList<Node>();
    }

    public Node(int _val, ArrayList<Node> _neighbors) {
      val = _val;
      neighbors = _neighbors;
    }
  }

  static class Solution {
    private void dfs2(Node node, Map<Integer, Node> clonedNodes, Set<Integer> visited) {
      if (node == null) {
        return;
      }

      if (visited.contains(node.val)) {
        return;
      }

      visited.add(node.val);

      for (Node nei : node.neighbors) {
        Node newCurrentNode = clonedNodes.get(node.val);
        Node newNeighborNode = clonedNodes.get(nei.val);
        newCurrentNode.neighbors.add(newNeighborNode);
      }

      for (Node nei : node.neighbors) {
        dfs2(nei, clonedNodes, visited);
      }
    }

    private void dfs(Node node, Map<Integer, Node> clonedNodes, Set<Integer> visited) {
      if (node == null || visited.contains(node.val)) {
        return;
      }
      visited.add(node.val);
      clonedNodes.putIfAbsent(node.val, new Node(node.val, new ArrayList<>()));
      for (Node neighbour : node.neighbors) {
        dfs(neighbour, clonedNodes, visited);
      }
    }

    public Node cloneGraph(Node node) {
      Map<Integer, Node> clonedNodes = new HashMap<>();
      Set<Integer> visited = new HashSet<>();
      dfs(node, clonedNodes, visited);
      visited.clear();
      dfs2(node, clonedNodes, visited);
      return clonedNodes.get(node.val);
    }
  }

  public static void main(String[] args) {
    Node node1 = new Node(1, new ArrayList<>());
    Node node2 = new Node(2, new ArrayList<>());
    Node node3 = new Node(3, new ArrayList<>());
    Node node4 = new Node(4, new ArrayList<>());

    node1.neighbors.add(node2);
    node1.neighbors.add(node4);

    node2.neighbors.add(node1);
    node2.neighbors.add(node3);

    node3.neighbors.add(node2);
    node3.neighbors.add(node4);

    node4.neighbors.add(node1);
    node4.neighbors.add(node3);

    Node cloned = new Solution().cloneGraph(node1);
    printDfs(node1, new HashSet<>());
    System.out.println("--- new --- ");
    printDfs(cloned, new HashSet<>());
  }

  static void printDfs(Node node, Set<Integer> visited) {
    if (visited.contains(node.val)) {
      return;
    }
    System.out.println(node.val + " neighbours = [" + node.neighbors.size() + "] ");
    visited.add(node.val);
    node.neighbors.forEach(n -> printDfs(n, visited));
  }
}
