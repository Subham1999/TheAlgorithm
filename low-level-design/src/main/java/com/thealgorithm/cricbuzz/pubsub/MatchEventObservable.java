package com.thealgorithm.cricbuzz.pubsub;

import com.thealgorithm.cricbuzz.event.MatchEvent;
import java.util.ArrayList;
import java.util.List;

public class MatchEventObservable {
  private MatchEvent matchEvent;
  private List<MatchEventObserver> matchEventObservers;

  public MatchEventObservable() {
    this.matchEventObservers = new ArrayList<>();
  }

  public void onEvent(MatchEvent matchEvent) {
    synchronized (this) {
      for (MatchEventObserver matchEventObserver : matchEventObservers) {
        matchEventObserver.observe(matchEvent);
      }
    }
  }
}
