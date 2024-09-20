package com.thealgorithm.interval;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

/**
 * @author: Subham Santra
 */
public class MergeInterval {
  private List<int[]> sortedInterval;

  public MergeInterval() {
    this.sortedInterval = new LinkedList<>();
  }

  List<int[]> getIntervals() {
    return sortedInterval;
  }

  void merge(int[] interval) {
    if (sortedInterval.isEmpty()) {
      sortedInterval.add(interval);
    } else {
      int position =
          Collections.binarySearch(
              sortedInterval,
              interval,
              (i1, i2) -> {
                if (i1[0] == i2[0]) {
                  return i2[1] - i1[1];
                }
                return i1[0] - i2[0];
              });

      if (position < 0) {
        position = -(position + 1);

        if (sortedInterval.isEmpty()) {
          sortedInterval.add(interval);
        } else {
          sortedInterval.add(position, interval);
          int left = interval[0];
          int right = interval[1];
          int li = position;
          int ri = position;
          for (int i = position - 1; i >= 0; --i) {
            if (inSameRange(sortedInterval.get(i), interval)) {
              li = i;
              left = Math.min(left, sortedInterval.get(i)[0]);
              right = Math.max(right, sortedInterval.get(i)[1]);
            }
          }
          for (int i = position; i < sortedInterval.size(); ++i) {
            if (inSameRange(sortedInterval.get(i), interval)) {
              ri = i;
              right = Math.max(right, sortedInterval.get(i)[1]);
              left = Math.min(left, sortedInterval.get(i)[0]);
            }
          }
          sortedInterval.subList(li, ri + 1).clear();
          sortedInterval.add(li, new int[] {left, right});
        }
      }
    }
  }

  private boolean inSameRange(int[] interval, int[] target) {
    return (interval[0] <= target[0] && target[1] <= interval[1])
        || (interval[0] >= target[0] && target[1] >= interval[1]);
  }

  void print() {
    sortedInterval.stream().forEach(a -> System.out.printf("(%d, %d)\n", a[0], a[1]));
    System.out.println("----");
  }

  public static void main(String[] args) {
    MergeInterval mergeInterval = new MergeInterval();
    mergeInterval.print();

    mergeInterval.merge(new int[] {1, 10});
    mergeInterval.print();

    mergeInterval.merge(new int[] {4, 8});
    mergeInterval.print();
  }
}
