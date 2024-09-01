package com.thealgorithm.miscelleneous;

import java.util.Arrays;

public class LeetCode1235 {
  public static void main(String[] args) {
    Solution solution = new Solution();

    // test case: 1
    System.out.println(
        solution.jobScheduling(
            new int[] {1, 2, 3, 3}, new int[] {3, 4, 5, 6}, new int[] {50, 10, 40, 70}));

    // test case: 2
    System.out.println(
        solution.jobScheduling(
            new int[] {1, 2, 3, 4, 6},
            new int[] {3, 5, 10, 6, 9},
            new int[] {20, 20, 100, 70, 60}));

    // test case: 3
    System.out.println(
        solution.jobScheduling(
            new int[] {1, 2, 3, 3}, new int[] {4, 3, 4, 5}, new int[] {2, 3, 10, 11}));

    // test case: 4
    System.out.println(
        solution.jobScheduling(
            new int[] {6, 15, 7, 11, 1, 3, 16, 2},
            new int[] {19, 18, 19, 16, 10, 8, 19, 8},
            new int[] {2, 9, 1, 19, 5, 7, 3, 19}));
  }

  private static class Job {
    int startTime;
    int endTime;
    int profit;

    public Job(int startTime, int endTime, int profit) {
      this.startTime = startTime;
      this.endTime = endTime;
      this.profit = profit;
    }
  }

  private static class Solution {
    public int jobScheduling(int[] startTime, int[] endTime, int[] profit) {
      int[] memo = new int[profit.length];
      Arrays.fill(memo, -1);

      Job[] jobs = new Job[profit.length];

      for (int i = 0; i < profit.length; ++i) {
        jobs[i] = new Job(startTime[i], endTime[i], profit[i]);
      }

      Arrays.sort(
          jobs,
          (job1, job2) -> {
            if (job1.startTime != job2.startTime) {
              return job1.startTime - job2.startTime;
            }
            return job2.profit - job1.profit;
          });

      return jobScheduling(jobs, 0, memo);
    }

    private int jobScheduling(final Job[] jobs, final int currentJob, final int[] memo) {
      // base case
      if (currentJob >= jobs.length) {
        return 0;
      }

      // If already calculated then return from memo
      if (memo[currentJob] != -1) {
        return memo[currentJob];
      }

      // return the maximum profit from here
      // maximum profit will be max of the below options
      // 1. All possible combinations of job including the current job;
      // 2. Or excluding the current job;
      int maxProfitWithCurrentJob = jobs[currentJob].profit;
      int maxProfitWithoutCurrentJob;

      // case 1: calculate the maximum profit including the current job in schedule
      int nextJob = nextNonConflictJob(jobs, currentJob);
      if (nextJob != -1) {
        maxProfitWithCurrentJob += jobScheduling(jobs, nextJob, memo);
      }
      // case 2: calculate nextJob if current job was not considered.
      maxProfitWithoutCurrentJob = jobScheduling(jobs, currentJob + 1, memo);
      return memo[currentJob] = Math.max(maxProfitWithCurrentJob, maxProfitWithoutCurrentJob);
    }

    private int nextNonConflictJob(Job[] jobs, int currentJob) {
      int lo = currentJob + 1;
      int hi = jobs.length - 1;
      int optimalAnswer = -1;

      while (lo <= hi) {
        int mid = (hi - lo) / 2 + lo;

        if (jobs[mid].startTime >= jobs[currentJob].endTime) {
          optimalAnswer = mid;
          hi = mid - 1;
        } else {
          lo = mid + 1;
        }
      }
      return optimalAnswer;
    }
  }
}
