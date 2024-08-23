package com.subham.ta.array;

import java.util.Arrays;

public class IntQuickSort {
  public static void main(String[] args) {
    int[] nums = {5, 4, 3, 3, 3, 3, 2, 2, 2, 2, 1, 1, 1, 1, 5, 4, 4};
    new Solution().sortArray(nums);
    System.out.println(Arrays.toString(nums));
  }

  static class Solution {
    void swap(int[] a, int f, int t) {
      int x = a[f];
      a[f] = a[t];
      a[t] = x;
    }

    int partition(int[] nums, int lo, int hi) {
      int currentPivot = nums[lo];
      int left = lo - 1;
      int right = hi + 1;
      while (true) {
        do {
          left++;
        } while (nums[left] < currentPivot);
        do {
          right--;
        } while (nums[right] > currentPivot);
        if (left >= right) {
          return right;
        }
        swap(nums, left, right);
      }
    }

    void quickSort(int[] nums, int from, int to) {
      if (from >= to || (from < 0 || from >= nums.length) || (to < 0 || to >= nums.length)) {
        return;
      }
      int part = partition(nums, from, to);
      System.out.println("pivot=" + part + ", array=" + Arrays.toString(nums));
      quickSort(nums, from, part);
      quickSort(nums, part + 1, to);
    }

    public int[] sortArray(int[] nums) {
      quickSort(nums, 0, nums.length - 1);
      return nums;
    }
  }
}
