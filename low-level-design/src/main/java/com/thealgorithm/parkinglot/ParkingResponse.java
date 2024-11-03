package com.thealgorithm.parkinglot;

import java.sql.Timestamp;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

/**
 * @author: Subham Santra
 */
@AllArgsConstructor
@Getter
@Setter
public class ParkingResponse {
  private Vehicle vehicle;
  private int floorNumber;
  private Timestamp entryTs;
}
