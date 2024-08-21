//package com.subham.ta.strings;
//
//import java.util.ArrayDeque;
//import java.util.ArrayList;
//import java.util.Arrays;
//import java.util.Collections;
//import java.util.List;
//import java.util.Queue;
//
//public class LongestRepeatingCharacterReplacement {
//  static class Solution {
//    static class Sequence {
//      int start;
//      int end;
//
//      public Sequence(int s, int e) {
//        start = s;
//        end = e;
//      }
//
//      @Override
//      public String toString() {
//        return "{" + start + ", " + end + '}';
//      }
//    }
//
//    public int characterReplacement(String s, int k) {
//      int[] lastIndex = new int[26];
//      Arrays.fill(lastIndex, -1);
//
//      List<ArrayDeque<Sequence>> sequences = new ArrayList<>();
//
//      for (int i = 0; i < 26; i++) {
//        sequences.add(new ArrayDeque<>());
//      }
//
//      char lastChar = '#';
//
//      for (int i = 0; i < s.length(); ++i) {
//        char c = s.charAt(i);
//        if (lastChar == c) {
//          ArrayDeque<Sequence> charSeq = sequences.get(c - 'A');
//          if (charSeq.isEmpty()) {
//            charSeq.addLast(new Sequence(i, i));
//          } else {
//            charSeq.getLast().end = i;
//          }
//        } else {
//          ArrayDeque<Sequence> charSeq = sequences.get(c - 'A');
//          charSeq.addLast(new Sequence(i, i));
//        }
//        lastIndex[c - 'A'] = i;
//        lastChar = c;
//      }
//
//      int longestRepeatingLetter = 0;
//
//      for (final ArrayDeque<Sequence> charSequences : sequences) {
//
//        int rem = k;
//
//        List<Sequence> sequences1 = new ArrayList<>(charSequences);
//        Queue<Sequence> se = new ArrayDeque<>();
//
//        int lo = 0;
//
//        for (int i = 0; i < sequences1.size(); ++i) {
//
//          while (!se.isEmpty() && sequences1.get(i).start - se.)
//        }
//
//      }
//      return 0;
//    }
//  }
//
//  public static void main(String[] args) {
//    System.out.println(new Solution().characterReplacement("AABAABBAA", 2));
//  }
//}
