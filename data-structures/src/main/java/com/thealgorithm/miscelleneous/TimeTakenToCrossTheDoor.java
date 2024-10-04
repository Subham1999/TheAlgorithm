package com.thealgorithm.miscelleneous;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author: Subham Santra
 */
public class TimeTakenToCrossTheDoor {
  static class Node implements Comparable<Node> {
    int index;
    int state;
    int time;

    public Node(int index, int state, int time) {
      this.index = index;
      this.state = state;
      this.time = time;
    }

    @Override
    public int compareTo(Node o) {
      if (time == o.time) {
        return Integer.compare(index, o.index);
      }
      return Integer.compare(time, o.time);
    }
  }

  List<Node> sameArrivals(PriorityQueue<Node> priorityQueue) {
    List<Node> nodes = new ArrayList<>();
    int nextTime = priorityQueue.isEmpty() ? 0 : priorityQueue.peek().time;
    while (!priorityQueue.isEmpty() && priorityQueue.peek().time == nextTime) {
      nodes.add(priorityQueue.poll());
    }
    return nodes;
  }

  public int[] solve(int[] arrival, int[] state) {
    final PriorityQueue<Node> entryQueue = new PriorityQueue<>();
    final int n = arrival.length;
    final int[] timeToCross = new int[n];

    for (int i = 0; i < n; ++i) {
      entryQueue.add(new Node(i, state[i], arrival[i]));
    }

    AtomicInteger lastSecond = new AtomicInteger(-2);
    AtomicInteger lastState = new AtomicInteger(-2);
    while (!entryQueue.isEmpty()) {
      List<Node> sameArrivals = sameArrivals(entryQueue);
      Node possibleNext;
      if (lastSecond.get() == sameArrivals.get(0).time - 1) {
        // Last second is used by someone else.
        possibleNext =
            sameArrivals.stream()
                .filter(node -> node.state == lastState.get())
                .findFirst()
                .orElse(sameArrivals.get(0));
      } else {
        possibleNext = sameArrivals.get(0);
      }

      // update result array for possibleNext
      timeToCross[possibleNext.index] = possibleNext.time;
      lastState.set(possibleNext.state);
      lastSecond.set(possibleNext.time);

      // add again to entry queue
      sameArrivals.stream()
          .filter(node -> node.index != possibleNext.index)
          .peek(node -> node.time++)
          .forEach(entryQueue::add);
    }

    return timeToCross;
  }

  public static void main(String[] args) {
    System.out.println(
        Arrays.toString(
            new TimeTakenToCrossTheDoor()
                .solve(new int[] {0, 1, 1, 2, 4}, new int[] {0, 1, 0, 0, 1})));

    System.out.println(
        Arrays.toString(
            new TimeTakenToCrossTheDoor()
                .solve(new int[] {0, 0, 1, 1, 1, 2, 3}, new int[] {0, 1, 0})));

    System.out.println(
        Arrays.toString(
            new TimeTakenToCrossTheDoor().solve(new int[] {0, 0, 0}, new int[] {1, 0, 1})));
  }
}
