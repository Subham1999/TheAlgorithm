package com.thealgorithm.graph;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class MakeLexicographicallySmallestArrayBySwappingElements {

  private static class Solution {
    private static class Point {
      final int val;
      final int index;

      public Point(int val, int index) {
        this.val = val;
        this.index = index;
      }
    }

    public int[] lexicographicallySmallestArray(int[] nums, int limit) {
      final Point[] points = new Point[nums.length];
      for (int i = 0; i < nums.length; ++i) {
        points[i] = new Point(nums[i], i);
      }

      Arrays.sort(points, Comparator.comparingInt(p -> p.val));

      // Connected components
      List<List<Point>> list = new ArrayList<>();
      List<Point> tempList = new ArrayList<>();

      for (int i = 0; i < points.length; ++i) {
        if (i == 0) {
          tempList.add(points[i]);
        } else {
          if (points[i].val - tempList.getLast().val > limit) {
            list.add(tempList);
            tempList = new ArrayList<>();
          }
          tempList.add(points[i]);
        }
      }
      list.add(tempList);

      Map<Integer, Iterator<Point>> map = new HashMap<>();
      for (var l : list) {
        for (var ll : l) {
          map.put(ll.index, l.iterator());
        }
      }

      int[] answer = new int[nums.length];
      for (int i = 0; i < nums.length; ++i) {
        answer[i] = map.get(i).next().val;
      }

      return answer;
    }
  }

  public static void main(String[] args){
    System.out.println(new Solution().lexicographicallySmallestArray(new int[] {1, 4, 2, 1, 4, 2, 1}, 3));
  }
}
