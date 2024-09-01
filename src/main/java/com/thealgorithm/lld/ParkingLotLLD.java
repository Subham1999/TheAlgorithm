package com.thealgorithm.lld;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.List;
import lombok.Data;

/**
 * @author: Subham Santra
 */
@Data
abstract class Vehicle {
  String vehicleNumber;
  String registration;
  String owner;
}

class Bike extends Vehicle {}

class Car extends Vehicle {}

class Truck extends Vehicle {}

@Data
abstract class ParkingSpot {
  int level;
  int id;
  boolean isOccupied;
  Vehicle occupiedVehicle;
  Timestamp occupiedAt;

  abstract boolean canOccupy(Vehicle vehicle);

  public boolean occupy(Vehicle vehicle) {
    if (canOccupy(vehicle)) {
      isOccupied = true;
      occupiedVehicle = vehicle;
      occupiedAt = Timestamp.from(Instant.now());
      return true;
    }
    return false;
  }
}

class BikeParkingSpot extends ParkingSpot {
  @Override
  boolean canOccupy(Vehicle vehicle) {
    return (vehicle instanceof Bike) && !isOccupied();
  }
}

class CarParkingSpot extends ParkingSpot {
  @Override
  boolean canOccupy(Vehicle vehicle) {
    return (vehicle instanceof Car) && !isOccupied();
  }
}

class TruckParkingSpot extends ParkingSpot {
  @Override
  boolean canOccupy(Vehicle vehicle) {
    return (vehicle instanceof Car) && !isOccupied();
  }
}

class ParkingSpotNotEmptyException extends Exception {
  public ParkingSpotNotEmptyException(int parkingSpotId) {
    super(Integer.toString(parkingSpotId));
  }
}

@Data
class Level {
  int level;
  List<ParkingSpot> parkingSpots;

  void addMore(ParkingSpot parkingSpot) {
    parkingSpot.setLevel(level);
    this.parkingSpots.add(parkingSpot);
  }

  void remove(int parkingSpotId) throws ParkingSpotNotEmptyException {
    for (ParkingSpot parkingSpot : parkingSpots) {
      if (parkingSpot.getId() == parkingSpotId) {
        if (parkingSpot.isOccupied()) {
          throw new ParkingSpotNotEmptyException(parkingSpotId);
        } else {
          parkingSpots.remove(parkingSpot);
        }
      }
    }
  }

  ParkingSpot parkThis(Vehicle vehicle) {
    for (ParkingSpot parkingSpot : parkingSpots) {
      if (parkingSpot.occupy(vehicle)) {
        return parkingSpot;
      }
    }
    return null;
  }
}

@Data
class ParkingLot {
  List<Level> parkingLevels;

  void park(final Vehicle vehicle) {

  }
}

public class ParkingLotLLD {}
