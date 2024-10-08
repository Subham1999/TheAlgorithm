package com.thealgorithm.strings;

import java.util.Comparator;
import java.util.LinkedList;
import java.util.ListIterator;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.stream.Collectors;

/**
 * @author: Subham Santra
 */
public class ReorganizeStrings {
  static class Solution {
    static class Entry {
      char c;
      int count;

      public Entry(char c, int count) {
        this.c = c;
        this.count = count;
      }
    }

    public String reorganizeString(String s) {
      Comparator<Entry> comp = (e1, e2) -> Integer.compare(e2.count, e1.count);
      PriorityQueue<Entry> priorityQueue = new PriorityQueue<>(comp);
      Map<Integer, Integer> frequency =
          s.chars().boxed().collect(Collectors.toMap(c -> c, c -> 1, Integer::sum));
      System.out.println(frequency);
      frequency.forEach((key, value) -> priorityQueue.add(new Entry((char) key.intValue(), value)));

      LinkedList<Integer>   list = new LinkedList<>();

      ListIterator<Integer> integerListIterator = list.listIterator();


      StringBuilder b = new StringBuilder();

      while (!priorityQueue.isEmpty()) {
        Entry top = priorityQueue.poll();
        if (b.isEmpty()) {
          b.append(top.c);
          top.count--;
          if (top.count > 0) priorityQueue.add(top);
        } else {
          if (b.charAt(b.length() - 1) == top.c) {
            if (priorityQueue.isEmpty()) {
              return "";
            }
            Entry second = priorityQueue.poll();
            b.append(second.c);
            second.count--;
            priorityQueue.add(top);
            if (second.count > 0) priorityQueue.add(second);
          } else {
            b.append(top.c);
            top.count--;
            if (top.count > 0) priorityQueue.add(top);
          }
        }
      }

      return b.toString();
    }
  }

  public static void main(String[] args) {
    System.out.println(new Solution().reorganizeString("aabbab"));
    System.out.println(new Solution().reorganizeString("aaaabccccd"));
    System.out.println(new Solution().reorganizeString("aaabbbccc"));
  }
}
