package com.thealgorithm.cricbuzz.event;

import com.thealgorithm.cricbuzz.Player;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class NormalRunMatchEvent extends MatchEvent {
  private int totalScore;
  private boolean sideChange;

  public NormalRunMatchEvent(
      Player bowledBy, Player batmanOne, Player batmanTwo, int totalScore, boolean sideChange) {
    super(bowledBy, batmanOne, batmanTwo);
    this.totalScore = totalScore;
    this.sideChange = sideChange;
  }
}
