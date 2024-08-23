package com.subham.ta.stackqueue;

import java.util.Stack;

public class EvaluateReversePolish {
  public static void main(String[] args) {
    System.out.println(new Solution().evalRPN(new String[] {"1", "2", "+", "3", "*", "4", "-"}));
  }

  static class Solution {
    public int evalRPN(String[] tokens) {
      Stack<Integer> stack = new Stack<>();

      for (String token : tokens) {
        switch (token) {
          case "+" -> {
            if (stack.size() < 2) {
              return 0;
            }
            Integer second = stack.pop();
            Integer first = stack.pop();
            stack.push(first + second);
          }
          case "-" -> {
            if (stack.size() < 2) {
              return 0;
            }
            Integer second = stack.pop();
            Integer first = stack.pop();
            stack.push(first - second);
          }
          case "*" -> {
            if (stack.size() < 2) {
              return 0;
            }
            Integer second = stack.pop();
            Integer first = stack.pop();
            stack.push(first * second);
          }
          case "/" -> {
            if (stack.size() < 2) {
              return 0;
            }
            Integer second = stack.pop();
            Integer first = stack.pop();
            stack.push(first / second);
          }
          default -> {
            int val = Integer.parseInt(token.trim());
            stack.push(val);
          }
        }
      }

      return stack.peek();
    }
  }
}
