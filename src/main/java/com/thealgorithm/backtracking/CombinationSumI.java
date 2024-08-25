package com.thealgorithm.backtracking;

import java.util.ArrayList;
import java.util.List;

public class CombinationSumI {
  public List<List<Integer>> combinationSum(int[] candidates, int target) {
    List<List<Integer>> result = new ArrayList<>();
    List<Integer> runningSet = new ArrayList<>();
    combinationSum(candidates, candidates.length - 1, target, result, runningSet);
    return result;
  }

  void combinationSum(
      int[] arr, int index, int target, List<List<Integer>> result, List<Integer> currentSet) {
    if (index < 0 || target < 0) {
      return;
    }

    if (target == 0) {
      result.add(new ArrayList<>(currentSet));
      return;
    }

    // include
    currentSet.addLast(arr[index]);
    combinationSum(arr, index, target - arr[index], result, currentSet);
    currentSet.removeLast();

    // exclude
    combinationSum(arr, index - 1, target, result, currentSet);
  }

  public static void main(String[] args) {
    System.out.println(new CombinationSumI().combinationSum(new int[] {2, 3, 6, 7}, 7));
  }
}
