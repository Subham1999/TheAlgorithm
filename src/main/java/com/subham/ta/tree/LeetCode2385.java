package com.subham.ta.tree;

import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

public class LeetCode2385 {
  private static final class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;

    TreeNode() {}

    TreeNode(int val) {
      this.val = val;
    }

    TreeNode(int val, TreeNode left, TreeNode right) {
      this.val = val;
      this.left = left;
      this.right = right;
    }
  }

  private static class Solution {
    private Map<Integer, TreeNode> nodeMap = new HashMap<>();
    private Map<Integer, Set<Integer>> neighbourMap = new HashMap<>();

    public int amountOfTime(TreeNode root, int start) {
      AtomicInteger maxTime = new AtomicInteger(0);
      buildMap(root, null);
      infectTheTree(nodeMap.get(start), 0, new HashSet<>(), maxTime);
      return maxTime.get();
    }

    private void infectTheTree(
        TreeNode node, int currentTime, Set<Integer> infected, AtomicInteger maxTime) {

      if (node == null) {
        return;
      }

      // mark current node as infected
      infected.add(node.val);
      maxTime.set(Math.max(maxTime.get(), currentTime));

      // start infecting the neighbours
      Set<Integer> neighbours = neighbourMap.get(node.val);
      for (int neighbour : neighbours) {
        if (neighbour == -1) {
          continue;
        }
        if (!infected.contains(neighbour)) {
          infectTheTree(nodeMap.get(neighbour), currentTime + 1, infected, maxTime);
        }
      }
    }

    private void buildMap(TreeNode node, TreeNode parent) {
      if (node == null) {
        return;
      }

      nodeMap.put(node.val, node);
      neighbourMap.put(
          node.val,
          new HashSet<>(
              Arrays.asList(
                  parent == null ? -1 : parent.val,
                  node.left == null ? -1 : node.left.val,
                  node.right == null ? -1 : node.right.val)));

      buildMap(node.left, node);
      buildMap(node.right, node);
    }
  }
}
