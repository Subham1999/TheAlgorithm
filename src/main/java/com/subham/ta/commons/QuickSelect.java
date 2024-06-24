package com.subham.ta.commons;

import java.util.Random;

public class QuickSelect {
  private static final Random RANDOM = new Random();

  public static int quickSelect(final int[] arr, final int lo, final int hi, final int k) {
    if (lo == hi) {
      return arr[hi];
    }

    partition(arr, lo, hi);
    return 0;
  }

  private static int partition(final int[] arr, final int lo, final int hi) {
    final int pivot = arr[RANDOM.nextInt(lo, (hi - lo + 1))];
    int j = hi;
    int i = lo;
    for (; i <= j; ++i) {
      if (arr[i] > pivot) {
        swap(arr, i, j);
        --j;
        --i;
      }
    }
    return i;
  }

  private static void swap(final int[] arr, final int from, final int to) {
    int t = arr[from];
    arr[from] = arr[to];
    arr[to] = t;
  }

  public static void main(String[] args) {
    System.out.println(partition(new int[] {4, 5, 3, 2, 1, 0, 2, 11, 12, -2, -2, 2, 0}, 0, 12));
  }
}
