package com.thealgorithm.tree.basics;

public class BinarySearchTree {
  private BinarySearchTreeNode root;

  public BinarySearchTree() {}

  public void add(int data) {
    if (root == null) {
      root = new BinarySearchTreeNode(data);
    } else {
      add(root, data);
    }
  }

  private void add(BinarySearchTreeNode node, int data) {
    if (node.getData() < data) {
      if (node.getRight() == null) {
        node.setRight(new BinarySearchTreeNode(data));
      } else {
        add(node.getRight(), data);
      }
    } else {
      if (node.getLeft() == null) {
        node.setLeft(new BinarySearchTreeNode(data));
      } else {
        add(node.getLeft(), data);
      }
    }
  }

  public boolean search(int data) {
    return search(root, data);
  }

  private boolean search(BinarySearchTreeNode node, int data) {
    if (node == null) {
      return false;
    }
    if (node.getData() == data) {
      return true;
    }

    if (node.getData() < data) {
      return search(node.getRight(), data);
    }
    return search(node.getLeft(), data);
  }

  public void remove(int data) {}
}
