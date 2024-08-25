package com.thealgorithm.backtracking;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author: Subham Santra
 */
public class SubsetsII {

  public static void main(String[] args) {
    System.out.println(new SubsetsII().subsetsWithDup(new int[] {1, 2, 2, 1, 1, 1}));
  }

  public List<List<Integer>> subsetsWithDup(int[] nums) {
    int len = nums.length;
    int powerLen = 1 << len;

    Set<List<Integer>> result = new HashSet<>();
    for (int i = 0; i < powerLen; ++i) {
      int j = i;
      int k = 0;
      List<Integer> set = new ArrayList<>();
      while (j > 0) {
        if ((j & 1) == 1) {
          set.add(nums[k]);
        }
        ++k;
        j >>= 1;
      }
      result.add(set);
    }
    return new ArrayList<>(result);
  }
}
