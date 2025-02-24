package com.thealgorithm.tree.basics;

import lombok.Data;

@Data
public class BinarySearchTreeNode {
  private int data;
  private BinarySearchTreeNode left;
  private BinarySearchTreeNode right;

  /** For ROOT the depth is 1 */
  private int depth;

  /** This includes the current node itself */
  private int subtreeSize;

  public BinarySearchTreeNode(int data) {
    this.data = data;
    this.subtreeSize = 1;
  }
}
