package com.thealgorithm.backtracking;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author: Subham Santra
 */
public class CombinationSumII {
  static class Solution {
    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
      List<List<Integer>> result = new ArrayList<>();

      Arrays.sort(candidates);
      helper(candidates, 0, 0, target, result, new ArrayList<>());

      return result;
    }

    void helper(
        final int[] arr,
        final int i,
        final int sum,
        final int target,
        final List<List<Integer>> result,
        final List<Integer> currentSet) {

      if (sum > target) {
        return;
      }

      if (sum == target) {
        result.add(new ArrayList<>(currentSet));
        return;
      }

      if (i == arr.length) {
        return;
      }

      // include
      currentSet.addLast(arr[i]);
      helper(arr, i + 1, sum + arr[i], target, result, currentSet);
      currentSet.removeLast();

      // exclude : not only the current index also all the indexes having same value
      // This is required because we want to remove the duplicate set
      // 1 2 2 2 2 2 5
      // When we have found {1, 2[1], 2[2]} we shall not add {1, 2[1], 2[3]} ... etc.
      int j = i;
      while (j < arr.length && arr[j] == arr[i]) ++j;
      helper(arr, j, sum, target, result, currentSet);
    }
  }

  public static void main(String[] args) {
    System.out.println(new Solution().combinationSum2(new int[] {2, 5, 2, 1, 2}, 5));
    System.out.println(new Solution().combinationSum2(new int[] {2, 2, 2, 2, 2, 2, 4, 4, 4, 4, 8, 7, 1, 1}, 8));
  }
}
