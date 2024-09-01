package com.thealgorithm.competitive;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class CpTemplate {
  public static void main(String[] args) {
    CpUtils cpUtils = CpUtils.getInstance();
    int n = cpUtils.intVal();
  }

  static final class CpUtils {
    private final BufferedReader br;
    private StringTokenizer st;

    private CpUtils() {
      br = new BufferedReader(new InputStreamReader(System.in));
    }

    public static CpUtils getInstance() {
      return new CpUtils();
    }

    public String next() {
      while (st == null || !st.hasMoreTokens()) {
        try {
          st = new StringTokenizer(br.readLine());
        } catch (IOException e) {
          System.err.println(e);
        }
      }
      return st.nextToken();
    }

    public int intVal() {
      return Integer.parseInt(next());
    }

    public long longVal() {
      return Long.parseLong(next());
    }

    public double doubleVal() {
      return Double.parseDouble(next());
    }

    public String nextLine() {
      try {
        return br.readLine();
      } catch (IOException e) {
        System.err.println(e);
        return null;
      }
    }
  }

  private static final class Solution {
    void solve() {}
  }
}
