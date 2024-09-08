package com.thealgorithm.binsearch;

/**
 * @author: Subham Santra
 */
public class KokoEatingBananas {
  public int minEatingSpeed(int[] piles, int h) {
    long hi = 0L;
    long lo = 1;
    for (int pile : piles) {
      hi = Math.max(pile, hi);
    }
    // System.out.printf("Range speed %d to %d\n", lo, hi);
    // start binary search
    long minimumSpeed = 1L;
    while (lo <= hi) {
      long mid = ((hi - lo) >> 1) + lo;
      long kokoFinishTime = kokoFinishTime(piles, mid);
      // System.out.printf("finish time %d for speed %d\n", kokoFinishTime, mid);
      if (kokoFinishTime <= h) {
        // Taking exact same time or even less, we shall reduce speed and check
        minimumSpeed = mid;
        hi = mid - 1;
      } else {
        // Taking more time to finish, to reduce time, we need to increase speed
        lo = mid + 1;
      }
    }
    return (int) minimumSpeed;
  }

  long kokoFinishTime(int[] piles, long speed) {
    long carry = 0L;
    for (double pile : piles) {
      carry += (long) Math.ceil(pile / speed);
    }
    return carry;
  }

  public static void main(String[] args) {
    System.out.println(new KokoEatingBananas().minEatingSpeed(new int[] {3, 6, 7, 11}, 8));
    System.out.println(new KokoEatingBananas().minEatingSpeed(new int[] {100}, 99));
    System.out.println(
        new KokoEatingBananas()
            .minEatingSpeed(new int[] {805306368, 805306368, 805306368}, 1000000000));
  }
}
