package com.thealgorithm.binsearch;

/**
 * @author: Subham Santra
 */
public class CapacityToShipPackagesWithInDDays {

  public int shipWithinDays(int[] weights, int days) {
    int n = weights.length;
    int[] prefixSum = new int[n + 1];
    int weightMin = Integer.MIN_VALUE;

    for (int i = 0; i < n; ++i) {
      prefixSum[i + 1] = prefixSum[i] + weights[i];
      weightMin = Math.max(weightMin, weights[i]);
    }

    int weightMax = prefixSum[prefixSum.length - 1];

    int ans = Integer.MAX_VALUE;

    while (weightMin <= weightMax) {
      int mid = (weightMax + weightMin) / 2;
      int checkDays = checkDays(prefixSum, mid, 1, n);
      System.out.printf("midWeight = %d days = %d\n", mid, checkDays);
      if (checkDays <= days) {
        ans = mid;
        weightMax = mid - 1;
      } else {
        weightMin = mid + 1;
      }
    }

    return ans;
  }

  private int checkDays(int[] prefixSum, int weight, int lo, int hi) {
    if (lo > hi) {
      return 0;
    }
    if (lo == hi) {
      return (prefixSum[lo] - prefixSum[lo - 1]) <= weight ? 1 : 0;
    }
    int pos = lowerBound(prefixSum, lo, weight);
    if (pos == -1) {
      return Integer.MAX_VALUE;
    }
    return 1 + checkDays(prefixSum, weight, pos + 1, hi);
  }

  private int lowerBound(int[] prefixSum, int lo, int target) {
    int right = prefixSum.length - 1;
    int left = lo;
    int pos = -1;
    while (left <= right) {
      int mid = (left + right) / 2;
      int midWeight = querySum(prefixSum, lo, mid);
      if (midWeight <= target) {
        pos = mid;
        left = mid + 1;
      } else {
        right = mid - 1;
      }
    }
    return pos;
  }

  private int querySum(int[] prefixSum, int lo, int hi) {
    return prefixSum[hi] - prefixSum[lo - 1];
  }

  public static void main(String[] args) {
    System.out.println(
        new CapacityToShipPackagesWithInDDays().shipWithinDays(new int[] {3, 2, 2, 4, 1, 4}, 3));

    System.out.println(
        new CapacityToShipPackagesWithInDDays().shipWithinDays(new int[] {1, 2, 3, 1, 1}, 4));

    System.out.println(
        new CapacityToShipPackagesWithInDDays()
            .shipWithinDays(new int[] {1, 2, 3, 4, 5, 6, 7, 8, 9, 10}, 5));
  }
}
