package com.thealgorithm.tree.basics;

public class TreeTest {
  public static void main(String[] args){

    BinarySearchTree binarySearchTree = new BinarySearchTree();

    System.out.println(binarySearchTree.search(10));

    binarySearchTree.add(10);

    System.out.println(binarySearchTree.search(10));

    binarySearchTree.add(10);
    binarySearchTree.add(5);
    binarySearchTree.add(55);

    System.out.println(binarySearchTree.search(70));
    System.out.println(binarySearchTree.search(55));
  }
}
