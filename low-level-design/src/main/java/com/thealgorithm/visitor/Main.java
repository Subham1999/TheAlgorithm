package com.thealgorithm.visitor;

public class Main {
  public static void main(String[] args) {
    DeluxRoom deluxRoom = new DeluxRoom();
    RoomCleaner roomCleaner = new RoomCleaner();
    deluxRoom.accept(roomCleaner);
  }
}
