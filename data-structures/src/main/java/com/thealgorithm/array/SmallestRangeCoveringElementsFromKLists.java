package com.thealgorithm.array;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;

/**
 * @author: Subham Santra
 */
public class SmallestRangeCoveringElementsFromKLists {
  static class Value {
    int listIdx;
    int val;

    public Value(int li, int v) {
      listIdx = li;
      val = v;
    }

    @Override
    public String toString() {
      return "[" + val + ']';
    }
  }

  public int[] smallestRange(List<List<Integer>> nums) {
    final int k = nums.size();
    List<List<Value>> listOfList = new ArrayList<>();

    for (int i = 0; i < nums.size(); ++i) {
      List<Value> l = new ArrayList<>();
      for (int j = 0; j < nums.get(i).size(); ++j) {
        l.add(new Value(i, nums.get(i).get(j)));
      }
      listOfList.add(l);
    }

    List<Value> sortedList = merge(listOfList);
    // System.out.println(sortedList);
    Map<Integer, Integer> taken = new HashMap<>();
    List<Value> window = new ArrayList<>();

    Comparator<int[]> ranegComparator =
        (a, b) -> {
          int d1 = a[1] - a[0];
          int d2 = b[1] - b[0];
          return d1 == d2 ? (b[0] - a[0]) : d2 - d1;
        };

    PriorityQueue<int[]> minRange = new PriorityQueue<>(1, ranegComparator);

    for (int r = 0, l = 0; r < sortedList.size(); ++r) {
      Value currentEntry = sortedList.get(r);
      window.add(currentEntry);
      taken.put(currentEntry.listIdx, 1 + taken.getOrDefault(currentEntry.listIdx, 0));

      while (taken.size() == k) {
        minRange.add(new int[] {window.getFirst().val, window.getLast().val});

        // POLL first from WINDOW
        Value firstEntry = window.removeFirst();

        // update MAP
        taken.put(firstEntry.listIdx, taken.get(firstEntry.listIdx) - 1);
        if (taken.get(firstEntry.listIdx) == 0) {
          taken.remove(firstEntry.listIdx);
        }
      }

      while (minRange.size() > 1) minRange.poll();
    }

    return minRange.poll();
  }

  List<Value> merge(List<List<Value>> listOfList) {
    // System.out.println("merging " + listOfList);
    if (listOfList.isEmpty()) {
      return null;
    }
    if (listOfList.size() == 1) {
      return listOfList.get(0);
    }

    int m = listOfList.size() / 2;
    return mergeTwoLists(
        merge(listOfList.subList(0, m)), merge(listOfList.subList(m, listOfList.size())));
  }

  List<Value> mergeTwoLists(List<Value> l1, List<Value> l2) {
    List<Value> result = new ArrayList<>();
    int i = 0, j = 0;

    while (i < l1.size() && j < l2.size()) {
      if (l1.get(i).val < l2.get(j).val) {
        result.add(l1.get(i));
        ++i;
      } else {
        result.add(l2.get(j));
        ++j;
      }
    }

    if (i < l1.size()) {
      result.addAll(l1.subList(i, l1.size()));
    }

    if (j < l2.size()) {
      result.addAll(l2.subList(j, l2.size()));
    }

    // System.out.println("merged " + result);
    return result;
  }

  public static void main(String[] args) {
    System.out.println(
        Arrays.toString(
            new SmallestRangeCoveringElementsFromKLists()
                .smallestRange(
                    List.of(
                        List.of(4, 10, 15, 24, 26),
                        List.of(0, 9, 12, 20),
                        List.of(5, 18, 22, 30)))));

    System.out.println(
        Arrays.toString(
            new SmallestRangeCoveringElementsFromKLists()
                .smallestRange(
                    List.of(
                        List.of(4, 10, 15, 24, 26, 29),
                        List.of(0, 9, 12, 30),
                        List.of(5, 18, 16, 31)))));

    System.out.println(
        Arrays.toString(
            new SmallestRangeCoveringElementsFromKLists()
                .smallestRange(List.of(List.of(1, 2, 3), List.of(1, 2, 3), List.of(1, 2, 3)))));
  }
}
