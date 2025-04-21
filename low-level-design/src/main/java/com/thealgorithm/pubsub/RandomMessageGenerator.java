package com.thealgorithm.pubsub;

import java.util.Random;

public class RandomMessageGenerator {
  private static final Random RANDOM = new Random();
  private static final String SET = "abcd efghi jklmn   op q r stuv qxyz  124 56 789 0";

  public static String getMessage() {
    int len = RANDOM.nextInt(10);
    StringBuilder stringBuilder = new StringBuilder();
    for (int i = 0; i < len; ++i) {
      stringBuilder.append(SET.charAt(RANDOM.nextInt(SET.length())));
    }
    return stringBuilder.toString();
  }
}
