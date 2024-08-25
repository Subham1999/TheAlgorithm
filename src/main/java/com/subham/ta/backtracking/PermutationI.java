package com.subham.ta.backtracking;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author: Subham Santra
 */
public class PermutationI {

  public List<List<Integer>> permute(int[] nums) {
    List<List<Integer>> result = new ArrayList<>();
    permute(nums, 0, result);
    return result;
  }

  void permute(int[] arr, int i, List<List<Integer>> result) {
    if (i == arr.length) {
      result.add(Arrays.stream(arr).boxed().collect(Collectors.toList()));
      return;
    }

    for (int j = i; j < arr.length; ++j) {
      swap(arr, i, j);
      permute(arr, i + 1, result);
      swap(arr, i, j);
    }
  }

  void swap(int[] arr, int f, int t) {
    int x = arr[f];
    arr[f] = arr[t];
    arr[t] = x;
  }

  public static void main(String[] args) {
    System.out.println(new PermutationI().permute(new int[] {0}));
    System.out.println(new PermutationI().permute(new int[] {1}));
    System.out.println(new PermutationI().permute(new int[] {1, 2}));
    System.out.println(new PermutationI().permute(new int[] {1, 2, 3}));
  }
}
