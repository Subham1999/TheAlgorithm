package com.thealgorithm.array;

import java.util.HashMap;
import java.util.Map;

/**
 * @author: Subham Santra
 */
public class FindTwonNonOverlappingSubArrays {
  public int minSumOfLengths(int[] arr, int target) {
    int n = arr.length;
    int INF = 1000_000;
    int[] prefix = new int[n];
    int[] suffix = new int[n];

    Map<Integer, Integer> map = new HashMap<>();

    int sum = 0;
    map.put(0, -1);
    for (int i = 0; i < n; ++i) {
      int boundary = map.getOrDefault(sum - target, -2);
      if (boundary != -2) {
        prefix[i] = i - boundary - 1;
      } else {
        prefix[i] = (i == 0 ? INF : prefix[i - 1]);
      }
      sum += arr[i];
      map.put(sum, i);
    }

    map.clear();
    map.put(0, n);
    sum = 0;

    for (int i = n - 1; i >= 0; --i) {
      sum += arr[i];
      int boundary = map.getOrDefault(sum - target, -2);
      if (boundary != -2) {
        suffix[i] = boundary - i;
      } else {
        suffix[i] = (i == (n - 1) ? INF : suffix[i + 1]);
      }
      map.put(sum, i);
    }

//    System.out.println(Arrays.toString(prefix));
//    System.out.println(Arrays.toString(suffix));

    for (int i = 1; i < n; ++i) prefix[i] = Math.min(prefix[i - 1], prefix[i]);
    for (int i = n - 2; i >= 0; --i) suffix[i] = Math.min(suffix[i + 1], suffix[i]);

    int ans = INF;
    for (int i = 0; i < n; ++i) {
      ans = Math.min(ans, prefix[i] + suffix[i]);
    }
    return ans == INF ? -1 : ans;
  }

  public static void main(String[] args) {

    System.out.println(
        new FindTwonNonOverlappingSubArrays().minSumOfLengths(new int[] {1, 1, 2, 2, 2, 4, 3}, 3));

    System.out.println(
        new FindTwonNonOverlappingSubArrays().minSumOfLengths(new int[] {4, 3, 2, 6, 2, 3, 4}, 6));
  }
}
