package com.thealgorithm.dp;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author: Subham Santra
 */
public class DifferentWaysToAddParenthesis {
  public List<Integer> diffWaysToCompute(String expression) {
    List<Integer> result = new ArrayList<>();

    List<Integer> numbers =
        Arrays.stream(expression.split("[/+-/*]"))
            .map(Integer::parseInt)
            .collect(Collectors.toList());

    List<Character> operators =
        Arrays.stream(expression.split("[0-9]+"))
            .filter(s -> !s.isBlank())
            .map(c -> Character.valueOf(c.charAt(0)))
            .collect(Collectors.toList());

    // System.out.println(numbers);
    // System.out.println(operators);

    return result;
  }

  void generate(List<Integer> numbers, List<Character> operators, int start, int end) {

    int len = end - start + 1;

    for (int l = 2; l < len; ++l) {

    }
  }

  public static void main(String[] args) {
    System.out.println(new DifferentWaysToAddParenthesis().diffWaysToCompute("2*3-4*5/7/12"));
  }
}
