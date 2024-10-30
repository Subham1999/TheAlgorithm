package com.thealgorithm.linkedlist;

/**
 * @author: Subham Santra
 */
public class DoublyLinkedListNode extends LinkedListNode {
  DoublyLinkedListNode previous;

  public DoublyLinkedListNode(int data, LinkedListNode next, DoublyLinkedListNode previous) {
    super(data, next);
    this.previous = previous;
  }
}
