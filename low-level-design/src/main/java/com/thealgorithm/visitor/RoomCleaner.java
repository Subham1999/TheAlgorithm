package com.thealgorithm.visitor;

public class RoomCleaner implements RoomVisitor {
  @Override
  public void visit(Room room) {
    if (room instanceof DeluxRoom) {
      System.out.println("Charge 400");
    } else {
      System.out.println("Charge 200");
    }
  }
}
