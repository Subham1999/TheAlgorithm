package com.thealgorithm.array;

public class SortZeroOneTwo {
  public static void main(String[] args) {}

  static void sort(int[] arr) {
    int left = 0;
    int right = arr.length - 1;

    while (left <= right) {
      if (arr[left] == 2 && arr[right] == 0) {}
    }
  }

  static void swap(int[] a, int i, int j) {
    int x = a[i];
    a[i] = a[j];
    a[j] = x;
  }
}
