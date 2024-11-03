package com.thealgorithm.cricbuzz.event;

import com.thealgorithm.cricbuzz.Player;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class FourEvent extends NormalRunMatchEvent {
  public FourEvent(Player bowledBy, Player batmanOne, Player batmanTwo, boolean sideChange) {
    super(bowledBy, batmanOne, batmanTwo, 4, sideChange);
  }
}
