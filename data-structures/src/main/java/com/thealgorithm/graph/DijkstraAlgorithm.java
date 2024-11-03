package com.thealgorithm.graph;

import static java.util.AbstractMap.*;

import java.util.*;

public class DijkstraAlgorithm {
  public static class InvalidWeightException extends Exception {
    private String msg;

    public InvalidWeightException(String msg) {
      super(msg);
      this.msg = msg;
    }
  }

  void dijkstra(int[][] graph, int n, int source) throws InvalidWeightException {
    int[] dist = new int[n];
    Arrays.fill(dist, Integer.MAX_VALUE);
    dist[source] = 0;

    Set<Integer> shortestPathFound = new HashSet<>();
    PriorityQueue<Entry<Integer, Integer>> priorityQueue =
        new PriorityQueue<>(Comparator.comparingInt(Entry::getValue));

    priorityQueue.offer(new SimpleEntry<>(source, 0));
    shortestPathFound.add(source);

    while (!priorityQueue.isEmpty()) {
      Entry<Integer, Integer> shortedstEntry = priorityQueue.poll();
      int node = shortedstEntry.getKey();
      int cost = shortedstEntry.getValue();

      for (int i = 0; i < n; ++i) {
        if (graph[node][i] < 0) {
          throw new InvalidWeightException("Invalid data");
        }
        if (node != i && graph[node][i] != 0) {
          if (dist[i] > cost + graph[node][i]) {
            dist[i] = cost + graph[node][i];
            int finalI = i;
            priorityQueue.removeIf(e -> e.getKey() == finalI);
            priorityQueue.offer(new SimpleEntry<>(i, dist[i]));
          }
        }
      }
      shortestPathFound.add(node);
      if (shortestPathFound.size() == n) break;
    }

    System.out.println(Arrays.toString(dist));
  }

  public static void main(String[] args) throws InvalidWeightException {

    GraphCreator graphCreator =
        GraphCreator.getUndirected(9)
            .addEdge(0, 1, 4)
            .addEdge(0, 7, 8)
            .addEdge(1, 7, 11)
            .addEdge(1, 2, 8)
            .addEdge(2, 8, 2)
            .addEdge(8, 6, 6)
            .addEdge(8, 6, 6)
            .addEdge(7, 6, 1)
            .addEdge(7, 8, 7)
            .addEdge(6, 5, 2)
            .addEdge(2, 5, 4)
            .addEdge(2, 3, 7)
            .addEdge(5, 3, 14)
            .addEdge(5, 4, 10)
            .addEdge(3, 4, 9);

    new DijkstraAlgorithm().dijkstra(graphCreator.getAdjacencyMatrix(), graphCreator.getSize(), 0);
    new DijkstraAlgorithm().dijkstra(graphCreator.getAdjacencyMatrix(), graphCreator.getSize(), 8);
    new DijkstraAlgorithm().dijkstra(graphCreator.getAdjacencyMatrix(), graphCreator.getSize(), 6);
  }
}
