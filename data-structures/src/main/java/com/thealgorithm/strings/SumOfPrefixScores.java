//package com.thealgorithm.strings;
//
//import java.util.Arrays;
//
///**
// * @author: Subham Santra
// */
//public class SumOfPrefixScores {
//  static class TrieNode {
//    char c;
//    TrieNode[] nxt;
//    boolean isEnd;
//    int score;
//
//    public TrieNode(char c) {
//      this.c = c;
//      this.nxt = new TrieNode[26];
//    }
//
//    @Override
//    public String toString() {
//      return "<" + c + ", " + Arrays.toString(nxt) + '>';
//    }
//  }
//
//  static class Trie {
//    TrieNode root = new TrieNode('a');
//
//    void add(String a) {
//      add(a, 0, root);
//    }
//
//    void add(String a, int i, TrieNode node) {
//      if (i == a.length()) return;
//      node.score++;
//      if (node.nxt[a.charAt(i) - 'a'] != null) {
//        if (i == a.length() - 1) {
//          node.nxt[a.charAt(i) - 'a'].isEnd = true;
//        }
//        add(a, i + 1, node.nxt[a.charAt(i) - 'a']);
//        return;
//      }
//      node.nxt[a.charAt(i) - 'a'] = new TrieNode(a.charAt(i));
//      node.nxt[a.charAt(i) - 'a'].score = 1;
//      if (i == a.length() - 1) {
//        node.nxt[a.charAt(i) - 'a'].isEnd = true;
//      }
//      add(a, i + 1, node.nxt[a.charAt(i) - 'a']);
//    }
//
//    //    void DFS() {
//    //      DFS(root);
//    //    }
//
//    //    int DFS(TrieNode node) {
//    //      if (node == null) {
//    //        return 0;
//    //      }
//    //
//    //      boolean isNull = true;
//    //      for (int i = 0; i < node.nxt.length; ++i) {
//    //        if (node.nxt[i] != null) {
//    //          isNull = false;
//    //          break;
//    //        }
//    //      }
//    //
//    //      if (isNull) {
//    //        return node.score = 1;
//    //      }
//    //
//    //      for (int i = 0; i < node.nxt.length; ++i) {
//    //        node.score += DFS(node.nxt[i]);
//    //      }
//    //
//    //      node.score += (node.isEnd ? 1 : 0);
//    //      return node.score;
//    //    }
//
//    int scoreOf(String a) {
//      TrieNode node = root.nxt[a.charAt(0) - 'a'];
//      int i = 0;
//      int score = 0;
//
//      while (node != null) {
//        score += node.score;
//        if (i + 1 < a.length()) {
//          node = node.nxt[a.charAt(i + 1) - 'a'];
//          i++;
//        } else {
//          break;
//        }
//      }
//
//      return score;
//    }
//  }
//
//  static class Solution {
//    public int[] sumPrefixScores(String[] words) {
//      int[] score = new int[words.length];
//      Trie trie = new Trie();
//
//      for (String word : words) trie.add(word);
//
//      //      trie.DFS();
//
//      int idx = 0;
//      for (String word : words) {
//        score[idx] = trie.scoreOf(word);
//        idx++;
//      }
//
//      return score;
//    }
//  }
//
//  public static void main(String[] args) {
//    System.out.println(
//        Arrays.toString(new Solution().sumPrefixScores(new String[] {"abc", "ab", "bc", "b"})));
//
//    System.out.println(Arrays.toString(new Solution().sumPrefixScores(new String[] {"abcd"})));
//
//    Arrays.stream(new int[] {}).max().or
//  }
//}
