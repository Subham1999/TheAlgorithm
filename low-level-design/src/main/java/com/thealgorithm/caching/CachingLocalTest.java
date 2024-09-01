package com.thealgorithm.caching;

import java.nio.charset.StandardCharsets;
import java.util.Scanner;
import java.util.StringTokenizer;

/**
 * @author: Subham Santra
 */
public class CachingLocalTest {
  public static void main(String[] args) {
    InMemoryKeyValStore keyValStore = InMemoryKeyValStore.create();
    try (Scanner scanner = new Scanner(System.in)) {

      do {
        String line = scanner.nextLine();
        StringTokenizer tokenizer = new StringTokenizer(line);

        String var1 = tokenizer.nextToken();
        String var2 = tokenizer.nextToken();
        String var3 = tokenizer.hasMoreTokens() ? tokenizer.nextToken() : "";
        String var4 = tokenizer.hasMoreTokens() ? tokenizer.nextToken() : "";

        try {
          if (var1.equalsIgnoreCase("put")) {
            if (var4.isBlank()) {
              keyValStore.put(var2, var3.getBytes(StandardCharsets.UTF_8), 60_000L);
            } else {
              keyValStore.put(var2, var3.getBytes(StandardCharsets.UTF_8), Long.parseLong(var4));
            }
          } else if (var1.equalsIgnoreCase("get")) {
            System.out.println(new String(keyValStore.get(var2)));
          } else if (var1.equalsIgnoreCase("rem")) {
            keyValStore.rem(var2);
          }
        } catch (DataNotPresentException | DataExpiredException e) {
          System.err.println(e.getMessage());
        }
      } while (scanner.hasNextLine());
    }
  }
}
