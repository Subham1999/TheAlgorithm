package com.thealgorithm.linkedlist;

/**
 * @author: Subham Santra
 */
public class DoublyLinkedList {
  DoublyLinkedListNode head;
  DoublyLinkedListNode tail;

  public void addLast(int data) {
    if (isEmpty()) {
      head = tail = new DoublyLinkedListNode(data, null, null);
    } else {
      tail.next = new DoublyLinkedListNode(data, null, null);
    }
  }

  private boolean isEmpty() {
    return head == null && tail == null;
  }
}
