package com.thealgorithm.graph;

import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Set;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;

@Getter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@AllArgsConstructor
class HeapEntry {
  @EqualsAndHashCode.Include int node;
  int distanceFromSource;
}

class MaxHeap {

  public HeapEntry extractMin() {
    return null;
  }

  public void update(int node, int newVal) {}
}

/**
 * @author: Subham Santra
 */
public class SingleSourceShortestPathUsingDijkstra {

  private List<Integer> findShortestPath(int[][] graph, int source) {
    PriorityQueue<HeapEntry> priorityQueue =
        new PriorityQueue<>(Comparator.comparingInt(HeapEntry::getDistanceFromSource));

    // Set<>

    for (int i = 0; i < graph.length; ++i) {
      if (i == source) {
        priorityQueue.offer(new HeapEntry(i, 0));
      } else {
        priorityQueue.offer(new HeapEntry(i, Integer.MAX_VALUE));
      }
    }
  }

  public static void main(String[] args) {
    int[][] graph = new int[9][9];
  }
}
