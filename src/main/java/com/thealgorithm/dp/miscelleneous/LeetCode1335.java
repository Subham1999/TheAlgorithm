package com.thealgorithm.dp.miscelleneous;

import java.util.Arrays;

/**
 * @see <a href="https://leetcode.com/problems/minimum-difficulty-of-a-job-schedule/">1335. Minimum
 *     Difficulty of a Job Schedule</a>
 */
public class LeetCode1335 {
  public static void main(String[] args) {
    Solution solution = new Solution();
    System.out.println(solution.minDifficulty(new int[] {6, 5, 4, 3, 2, 1}, 2));
    System.out.println(solution.minDifficulty(new int[] {9, 9, 9}, 3));
    System.out.println(solution.minDifficulty(new int[] {9, 9, 9}, 4));
    System.out.println(solution.minDifficulty(new int[] {11, 111, 22, 222, 33, 333, 44, 444}, 6));
  }

  static class Solution {
    private int[][] memo;

    public int minDifficulty(int[] jobDifficulty, int d) {
      memo = new int[jobDifficulty.length][d + 1];
      for (int[] ints : memo) {
        Arrays.fill(ints, -1);
      }
      int minDifficulty = minDifficulty(jobDifficulty, 0, d - 1);
      return minDifficulty == Integer.MAX_VALUE ? -1 : minDifficulty;
    }

    private int minDifficulty(
        final int[] jobDifficulties, final int currPartitionStart, final int leftDays) {

      int effectiveLength = jobDifficulties.length - currPartitionStart;
      if (leftDays < 0) {
        return -1;
      }

      // Always, no. of jobs left should be at least equal to leftDays.
      // Otherwise, we can not allocate all days. (i.e. effectiveLength >= leftDays)
      if (leftDays > effectiveLength) {
        return -1;
      }

      if (memo[currPartitionStart][leftDays] != -1) {
        return memo[currPartitionStart][leftDays];
      }

      int currentStateResult = Integer.MAX_VALUE;
      if (leftDays == 0) {
        int maxDiff = Integer.MIN_VALUE;
        for (int i = currPartitionStart; i < jobDifficulties.length; ++i) {
          maxDiff = Math.max(maxDiff, jobDifficulties[i]);
        }
        currentStateResult = maxDiff;
      } else {

        int currentPartitionMaxJobDiff = jobDifficulties[currPartitionStart];

        for (int nextPartition = currPartitionStart + 1;
            nextPartition < jobDifficulties.length;
            nextPartition++) {

          // calculate the next difficulties if partitioned again at nextPartition index
          int calculatedDiff = minDifficulty(jobDifficulties, nextPartition, leftDays - 1);

          currentPartitionMaxJobDiff =
              Math.max(currentPartitionMaxJobDiff, jobDifficulties[nextPartition - 1]);

          if (calculatedDiff != -1) {
            currentStateResult =
                Math.min(currentStateResult, calculatedDiff + currentPartitionMaxJobDiff);
          }
        }
      }

      return currentStateResult == Integer.MAX_VALUE
          ? -1
          : (memo[currPartitionStart][leftDays] = currentStateResult);
    }
  }
}
