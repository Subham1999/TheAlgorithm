package com.thealgorithm.linkedlist;

import java.util.ArrayList;
import java.util.List;

public class ReverseSentence {

  public static class Node {
    char c;
    Node next;

    public Node(char c, Node next) {
      this.c = c;
      this.next = next;
    }

    public Node() {}
  }

  public static class Creator {
    public static Node prepare(String sentence) {
      if (sentence.isBlank()) {
        return null;
      }

      Node head = new Node();
      Node node = head;

      for (char c : sentence.toCharArray()) {
        node.c = c;
        node.next = new Node();
      }

      return head;
    }
  }

  public static class Printer {
    static void print(Node node) {
      for (; node != null; node = node.next) {
        System.out.print(node.c);
        System.out.print(' ');
      }
      System.out.println();
    }
  }

  public static class Solution {
    public static Node reverseIt(Node head) {
      Node node = head;
      Node left, right;
      left = right = node;

      while (node != null) {
        if (node.c == ' ') {
          partialReverse(left, right);
          right = left = node.next;
        } else {
          right = node;
        }
      }
      return head;
    }

    private static void partialReverse(Node left, Node right) {
      Node head = left;

    }
  }

  public static void main(String[] args) {
    Node sentence = Creator.prepare("Hi this is Subham, are you ok?");
    Printer.print(sentence);
    Printer.print(Solution.reverseIt(sentence));
  }
}
