package com.thealgorithm.visitor;

public class DeluxRoom implements Room{
  @Override
  public void accept(RoomVisitor roomVisitor) {
    roomVisitor.visit(this);
  }
}
