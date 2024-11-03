package com.thealgorithm.cricbuzz.event;

import com.thealgorithm.cricbuzz.Player;
import lombok.Data;

@Data
public class SixerEvent extends NormalRunMatchEvent {

  public SixerEvent(Player bowledBy, Player batmanOne, Player batmanTwo, boolean sideChange) {
    super(bowledBy, batmanOne, batmanTwo, 6, sideChange);
  }
}
