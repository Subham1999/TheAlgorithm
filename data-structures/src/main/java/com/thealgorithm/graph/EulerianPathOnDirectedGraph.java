//package com.thealgorithm.graph;
//
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//import java.util.Set;
//
///**
// * @author: Subham Santra
// */
//public class EulerianPathOnDirectedGraph {
//
//  public static <K, V> List<Edge<K, V>> findEulerianPath(Graph<K, V> graph) {
//    Set<Edge<K, V>> edgeSet = graph.getEdgeSet();
//    Set<Vertex<K, V>> vertexSet = graph.getVertexSet();
//    Map<Vertex<K, V>, int[]> degree = new HashMap<>(); // {int, out}
//
//    for (var vrtx : vertexSet) {
//      degree.put(vrtx, new int[] {0, 0});
//    }
//
//    for (var edge : edgeSet) {
//      degree.get(edge.getVertex2())[0]++;
//      degree.get(edge.getVertex1())[1]++;
//    }
//
//    // Find a vertex where, out > in
//    Vertex<K, V> v = null;
//    for (var vrtx: vertexSet) {
//      if (degree.get(vrtx)[1] > degree.get(vrtx)[0]) {
//        v = vrtx;
//        break;
//      }
//    }
//
//    while (true) {
//      Set<Vertex<K, V>> neighbors = v.getNeighbors();
//
//      Math.g
//    }
//  }
//
//  public static void main(String[] args) {}
//}
