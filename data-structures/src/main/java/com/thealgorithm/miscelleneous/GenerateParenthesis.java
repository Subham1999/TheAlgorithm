package com.thealgorithm.miscelleneous;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author: Subham Santra
 */
public class GenerateParenthesis {

  public List<String> generate(int c1, int c2, int c3) {
    Set<String> result = new HashSet<>();
    generate(c1, 0, 0, c2, 0, 0, c3, 0, 0, result, new StringBuilder());
    return new ArrayList<>(result);
  }

  private void generate(
      int c1,
      int o1,
      int e1,
      int c2,
      int o2,
      int e2,
      int c3,
      int o3,
      int e3,
      Set<String> result,
      StringBuilder comb) {

    if (e1 > o1 || e2 > o2 || e3 > o3) {
      return;
    }

    //        if (o1 == c1 && o1 == e1 && o2 == c2 && o2 == e2 && o3 == c3 && o3 == e3) {
    if (o1 == c1 && o2 == c2 && o3 == c3) {
      //    if (o1 == e1 && o2 == e2 && o3 == e3) {
      result.add(comb.toString());
      return;
    }

    if (o1 < c1) {
      comb.append('(');
      generate(c1, o1 + 1, e1, c2, o2, e2, c3, o3, e3, result, comb);
      comb.deleteCharAt(comb.length() - 1);

      comb.append(')');
      generate(c1, o1, e1 + 1, c2, o2, e2, c3, o3, e3, result, comb);
      comb.deleteCharAt(comb.length() - 1);
    }

    if (o2 < c2) {
      comb.append('{');
      generate(c1, o1, e1, c2, o2 + 1, e2, c3, o3, e3, result, comb);
      comb.deleteCharAt(comb.length() - 1);

      comb.append('}');
      generate(c1, o1, e1, c2, o2, e2 + 1, c3, o3, e3, result, comb);
      comb.deleteCharAt(comb.length() - 1);
    }

    if (o3 < c3) {
      comb.append('[');
      generate(c1, o1, e1, c2, o2, e2, c3, o3 + 1, e3, result, comb);
      comb.deleteCharAt(comb.length() - 1);

      comb.append(']');
      generate(c1, o1 + 1, e1, c2, o2, e2, c3, o3, e3 + 1, result, comb);
      comb.deleteCharAt(comb.length() - 1);
    }
  }

  public static void main(String[] args) {
    System.out.println(new GenerateParenthesis().generate(2, 2, 2));
  }
}
