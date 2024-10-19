package com.thealgorithm.observer.example;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @author: Subham Santra
 */
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class WeatherUpdate {
  String scale;
  double temp;

  @Override
  public String toString() {
    return "WeatherUpdate{" + "scale='" + scale + '\'' + ", temp=" + temp + '}';
  }
}
