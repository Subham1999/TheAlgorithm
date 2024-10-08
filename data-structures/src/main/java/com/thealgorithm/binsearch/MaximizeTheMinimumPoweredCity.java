//package com.thealgorithm.binsearch;
//
//import java.util.Arrays;
//import java.util.PriorityQueue;
//
///**
// * @author: Subham Santra
// */
//public class MaximizeTheMinimumPoweredCity {
//  public long maxPower(int[] stations, int r, int k) {
//    int n = stations.length;
//    int[] ps = new int[n];
//    int w = 0;
//
//    for (int i = 0; i < n; ++i) {
//      for (int j = Math.max(i - r, 0); j <= Math.min(i + r, n - 1); ++j) {
//        ps[i] += stations[j];
//      }
//    }
//
//    int lo = Arrays.stream(ps).min().getAsInt();
//    int hi = Arrays.stream(ps).max().getAsInt() + k;
//
//    int mxMin = 0;
//
//    while (lo <= hi) {
//      int m = lo + ((hi - lo) >> 1);
//      long l = mIsMinimumHere(ps, r, k, m);
//
//      System.out.printf("%d %d\n", l, m);
//      if (l > k) {
//        hi = m - 1;
//      } else {
//        mxMin = m;
//        lo = m + 1;
//      }
//    }
//
//    return mxMin;
//  }
//
//  private long mIsMinimumHere(int[] ps, int r, int k, int m) {
//    //PriorityQueue<Integer> window = new PriorityQueue<>();
//    int usedPlants = 0;
//
//    for (int i = 0; i < ps.length; ++i) {
//      int x = 0;
//      for (int j = Math.max(i - r, 0); j <= Math.min(i + r, n - 1); ++j) {
//        x  += (ps[j] < m) ? m - ps[j] : 0;
//      }
//      usedPlants
//    }
//    return usedPlants;
//  }
//
//  public static void main(String[] args) {
//    //    System.out.println(
//    //        new MaximizeTheMinimumPoweredCity().maxPower(new int[] {2, 7, 11, 9, 5}, 1, 2));
//
//    System.out.println(
//        new MaximizeTheMinimumPoweredCity().maxPower(new int[] {1, 2, 4, 5, 0}, 1, 2));
//
//    //    System.out.println(
//    //      new MaximizeTheMinimumPoweredCity().mIsMinimumHere(new int[] {2, 7, 11, 9, 5}, 1, 2,
//    // 5));
//  }
//}
