package com.thealgorithm.visitor;

public interface Room {
  void accept(RoomVisitor roomVisitor);
}
