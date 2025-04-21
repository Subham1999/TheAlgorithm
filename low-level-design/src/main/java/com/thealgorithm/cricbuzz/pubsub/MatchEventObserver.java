package com.thealgorithm.cricbuzz.pubsub;

import com.thealgorithm.cricbuzz.event.MatchEvent;

public abstract class MatchEventObserver {

  public abstract void observe(MatchEvent matchEvent);
}
