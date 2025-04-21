package com.thealgorithm.cricbuzz.event;

import com.thealgorithm.cricbuzz.Player;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class MatchEvent {
  private Player bowledBy;
  private Player batmanOne;
  private Player batmanTwo;

  public MatchEvent(Player bowledBy, Player batmanOne, Player batmanTwo) {
    this.bowledBy = bowledBy;
    this.batmanOne = batmanOne;
    this.batmanTwo = batmanTwo;
  }
}
