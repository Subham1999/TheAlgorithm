package com.thealgorithm.observer.example;

import java.util.ArrayList;

/**
 * @author: Subham Santra
 */
public class Test {
  public static void main(String[] args) {
    WeatherUpdateChannel weatherUpdateChannel = new WeatherUpdateChannel(new ArrayList<>(), null);

    TelevisionWatcher televisionWatcher = new TelevisionWatcher(weatherUpdateChannel);
    FacebookWatcher facebookWatcher = new FacebookWatcher(weatherUpdateChannel);

    weatherUpdateChannel.addObserver(televisionWatcher);
    weatherUpdateChannel.addObserver(facebookWatcher);

    weatherUpdateChannel.change(new WeatherUpdate("cel", 10));
    weatherUpdateChannel.change(new WeatherUpdate("cel", 11));
    weatherUpdateChannel.change(new WeatherUpdate("cel", 12.5));

    weatherUpdateChannel.removeObserver(televisionWatcher);
    weatherUpdateChannel.change(new WeatherUpdate("cel", 13.5));
    weatherUpdateChannel.change(new WeatherUpdate("cel", 11.5));
    weatherUpdateChannel.change(new WeatherUpdate("cel", 9.5));
  }
}
