//package com.thealgorithm.array;
//
//import java.util.Arrays;
//
///**
// * @author: Subham Santra
// */
//public class KthSmallestInLexicographicalOrder {
//  int[] off = {1, 11, 111, 111_1, 111_11, 111_111, 111_111_1, 111_111_11, 111_111_111};
//  int[] off_d = {-1, 0, 1, 2, 3, 4, 5, 6, 7, 8, 9};
//  int[] off_z = {1, 9, 99, 999, 999_9, 999_99, 999_999, 999_999_9, 999_999_99, 999_999_999};
//
//  public int findKthNumber(int n, int k) {
//    if (n <= 9) {
//      return k;
//    }
//    int nd = (int) Math.ceil(Math.log10(n));
//    int[] an = new int[nd];
//    Arrays.fill(an, -1);
//    func(an, nd, 0, k);
//    // System.out.println(Arrays.toString(an));
//
//    int num = 0;
//    for (int ix : an) {
//      if (ix == -1) {
//        break;
//      }
//      num = (num * 10 + ix);
//    }
//    return num;
//  }
//
//  void func(int[] a, int n, int i, int k) {
//    // System.out.println(Arrays.toString(a) + " " + n + " " + i + " " + k);
//    if (i >= n) {
//      return;
//    }
//    if (i == n - 1) {
//      a[i] = a.length == 0 ? off_d[k] : off_d[k - 1];
//      return;
//    }
//    int rem = a.length - i - 1;
//
//    for (int d = (i == 0 ? 1 : 0); d <= 9; ++d) {
//      if (d == 0) {
//        int range = 0;
//        for (int r = 0; r < rem; ++r) {
//          range += (r == 0) ? 1 : (off[r] * 9);
//        }
//        if (k <= range) {
//          a[i] = d;
//          func(a, n, i + 1, k);
//          return;
//        }
//        k -= range;
//      } else {
//        int range = off[rem];
//        if (k <= range) {
//          a[i] = d;
//          func(a, n, i + 1, k);
//          return;
//        }
//        k -= range;
//      }
//    }
//  }
//
//  public static void main(String[] args) {
//    System.out.println(new KthSmallestInLexicographicalOrder().findKthNumber(13, 5));
//    System.out.println(new KthSmallestInLexicographicalOrder().findKthNumber(1, 1));
//    System.out.println(new KthSmallestInLexicographicalOrder().findKthNumber(37, 25));
//    Arrays.bin
//  }
//}
