//package com.subham.ta.graph;
//
//import static java.util.Collections.swap;
//
//import java.util.ArrayList;
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//import java.util.Set;
//
//public class PrimsMST {
//  private static final int INF = Integer.MAX_VALUE;
//
//  static class Graph {
//    Map<Integer, Map<Integer, Integer>> edges;
//    Set<Integer> nodes;
//
//    public Graph() {
//      this.edges = new HashMap<>();
//    }
//
//    void addEdge(int u, int v, int w) {
//      edges.putIfAbsent(u, new HashMap<>());
//      edges.get(u).putIfAbsent(v, w);
//      nodes.add(u);
//      nodes.add(v);
//    }
//
//    int noOfNodes() {
//      return nodes.size();
//    }
//
//    Set<Integer> getAllNodes() {
//      return nodes;
//    }
//  }
//
//  static class BinaryMinHeap {
//    int size;
//    List<Integer> tree;
//    Map<Integer, Integer> valToIndex;
//    Map<Integer, Integer> valToWeight;
//
//    BinaryMinHeap(int size) {
//      this.size = size;
//      this.tree = new ArrayList<>();
//      this.valToIndex = new HashMap<>();
//      this.valToWeight = new HashMap<>();
//    }
//
//    public BinaryMinHeap(Graph graph) {
//      this(graph.noOfNodes());
//      for (int node : graph.getAllNodes()) {
//        this.addEntry(node, INF);
//        heapify(valToIndex.get(node));
//      }
//    }
//
//    private void heapify(int index) {
//      if (index <= 0 || index >= this.tree.size()) {
//        return;
//      }
//      int parent = (index - 1) / 2;
//      if (valToWeight.get(tree.get(parent)) > valToWeight.get(tree.get(index))) {
//        swap(tree, index, parent);
//      }
//      heapify(parent);
//    }
//
//    private void addEntry(int node, int val) {
//      this.tree.add(node);
//      this.valToWeight.put(node, val);
//      this.valToIndex.put(node, this.tree.size() - 1);
//    }
//
//    public void decrease(int node, int newVal) {
//      int index = valToIndex.get(node);
//      valToWeight.put(node, newVal);
//      heapify(index);
//    }
//
//    public int[] getNode(int index) {
//      return new int[] {tree.get(index), valToWeight.get(tree.get(index))};
//    }
//
//    public int[] extractMin() {
//      int[] topNode = getNode(0);
//      int[] lastNode = getNode(tree.size() - 1);
//
//      // re-balance the heap
//
//      int currentIndex = 0;
//
//      swap(tree, tree.size() - 1, 0);
//      valToIndex.put(lastNode[0], 0);
//
//      tree.removeLast();
//      valToIndex.remove(topNode[0]);
//      valToWeight.remove(topNode[0]);
//
//      while (true) {
//        int leftChildIndex = (currentIndex * 2) + 1;
//        int rightChildIndex = (currentIndex * 2) + 2;
//
//        int[] leftNode = getNode(leftChildIndex);
//        int[] rightNode = getNode(rightChildIndex);
//
//        if (leftNode[1] < rightNode[1]) {
//          valToIndex.put(leftNode[0], leftChildIndex);
//          valToIndex.put()
//          swap(tree, currentIndex, leftChildIndex);
//        }
//      }
//
//      return topNode;
//    }
//  }
//
//  public static void main(String[] args) {
//    Graph graph = new Graph();
//
//    graph.addEdge(1, 2, 1);
//    graph.addEdge(1, 3, 3);
//    graph.addEdge(1, 4, 1);
//    graph.addEdge(3, 4, 2);
//    graph.addEdge(4, 5, 10);
//    graph.addEdge(2, 5, 2);
//
//    System.out.println(findMST(graph));
//  }
//
//  private static List<int[]> findMST(Graph graph) {
//    // build binary-min-heap from graph
//
//    return null;
//  }
//}
