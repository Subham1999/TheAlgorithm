package com.thealgorithm.binsearch;

import java.util.stream.Collectors;

/**
 * @author: Subham Santra
 */
public class FindMinimumInRotatedSortedArray {
  static class Solution {

    int pivot(int[] nums) {
      int lo = 0;
      int hi = nums.length - 1;
      int mid;

      while (lo <= hi) {
        mid = (lo + hi) >> 1;
        if (mid + 1 > hi) {
          return hi;
        }
        if (nums[mid] > nums[mid + 1]) {
          return mid;
        }
        if (nums[mid] < nums[0]) {
          hi = mid - 1;
        } else {
          lo = mid + 1;
        }
      }
      return 0;
    }

    public int findMin(int[] nums) {
      int pivot = pivot(nums);
      if (pivot == nums.length - 1) return nums[0];
      return nums[pivot + 1];
    }
  }

  public static void main(String[] args) {
    //    System.out.println(new Solution().findMin(new int[] {1}));
    //    System.out.println(new Solution().findMin(new int[] {6, 7, 1, 2, 3, 4, 5}));
    //    System.out.println(new Solution().findMin(new int[] {6, 7, 8, 9, 10, 11, 12, 13, 5}));
    //    System.out.println(new Solution().findMin(new int[] {6, 7, 8, 9, 10, 11, 12, 13}));

    String s = "abcdabaaaa";

    System.out.println(s.chars().boxed().collect(Collectors.toMap(k -> k, k -> 1, Integer::sum)));
  }
}
