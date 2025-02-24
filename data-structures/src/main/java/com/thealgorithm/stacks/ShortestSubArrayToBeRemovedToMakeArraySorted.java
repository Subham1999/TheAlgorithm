package com.thealgorithm.stacks;

import java.util.Arrays;

public class ShortestSubArrayToBeRemovedToMakeArraySorted {

  public int findLengthOfShortestSubarray(int[] arr) {
    int leftSequenceStop = 0, rightSequenceStop = arr.length - 1;

    for (int i = 1; i < arr.length; ++i) {
      if (arr[i - 1] > arr[i]) {
        leftSequenceStop = i - 1;
        break;
      }
    }

    for (int i = arr.length - 2; i >= leftSequenceStop; --i) {
      if (arr[i] > arr[i + 1]) {
        rightSequenceStop = i + 1;
        break;
      }
    }

    int answer = 0;

    System.out.println(Arrays.toString(Arrays.copyOfRange(arr, 0, leftSequenceStop)));
    System.out.println(Arrays.toString(Arrays.copyOfRange(arr, rightSequenceStop, arr.length)));

    for (int i = rightSequenceStop; i < arr.length; ++i) {
      answer = Math.max(answer, upperBoundIndex(arr, 0, leftSequenceStop, arr[i]) + arr.length - i);
    }

    for (int i = leftSequenceStop; i >= 0; --i) {
      answer =
          Math.max(
              answer,
              lowerBoundIndex(arr, rightSequenceStop, arr.length - 1, arr[leftSequenceStop]));
    }

    return answer == 0 ? 0 : arr.length - answer;
  }

  int upperBoundIndex(int[] arr, int lo, int hi, int t) {
    int m;
    int ans = -1;

    while (lo <= hi) {
      m = (lo + hi) >> 1;
      if (arr[m] <= t) {
        lo = m + 1;
      } else {
        ans = m;
        hi = m - 1;
      }
    }

    return ans;
  }

  int lowerBoundIndex(int[] arr, int lo, int hi, int t) {
    int m;
    int ans = arr.length;

    while (lo <= hi) {
      m = (lo + hi) >> 1;
      if (arr[m] >= t) {
        hi = m - 1;
      } else {
        ans = m;
        lo = m + 1;
      }
    }

    return ans;
  }

  public static void main(String[] args) {
        System.out.println(
            new ShortestSubArrayToBeRemovedToMakeArraySorted()
                .findLengthOfShortestSubarray(new int[] {1, 2, 11, 10, 4, 11, 12, 15}));
    //
    //    System.out.println(
    //        new ShortestSubArrayToBeRemovedToMakeArraySorted()
    //            .findLengthOfShortestSubarray(new int[] {1, 2, 2, 3, 3, 3, 4, 4, 4, 5, 6}));
    //
    //    System.out.println(
    //        new ShortestSubArrayToBeRemovedToMakeArraySorted()
    //            .findLengthOfShortestSubarray(new int[] {5, 4, 3, 2, 1}));

    System.out.println(
        new ShortestSubArrayToBeRemovedToMakeArraySorted()
            .findLengthOfShortestSubarray(new int[] {1, 2, 3, 10, 0, 7, 8, 9}));
  }
}
