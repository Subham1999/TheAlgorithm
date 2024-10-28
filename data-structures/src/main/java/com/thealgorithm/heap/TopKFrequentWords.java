package com.thealgorithm.heap;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * @author: Subham Santra
 */
public class TopKFrequentWords {
  public List<String> topKFrequent(String[] words, int k) {
    return Arrays.stream(words)
        .collect(Collectors.toMap(Function.identity(), s -> 1, Integer::sum))
        .entrySet()
        .stream()
        .sorted(
            (kv1, kv2) -> {
              int diff = kv1.getValue() - kv2.getValue();
              if (diff == 0) {
                return kv1.getKey().compareTo(kv2.getKey());
              }
              return -diff;
            })
        .limit(k)
        .map(Map.Entry::getKey)
        .collect(Collectors.toList());
  }

  public static void main(String[] args) throws IOException {
    //    System.out.println(
    //        new TopKFrequentWords()
    //            .topKFrequent(new String[] {"i", "love", "leetcode", "i", "love", "coding"}, 2));
  }
}
