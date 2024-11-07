package com.thealgorithm.votingsystem;

import lombok.Getter;
import lombok.Setter;

import java.util.HashMap;
import java.util.Map;

@Setter
@Getter
public abstract class VoteBank {
  private VoteBank parent;
  private Map<Party, Integer> snapShot;
  private WinningStrategy winningStrategy;
//  private Map<Party, Integer> winCount;

  public VoteBank(VoteBank parent, Map<Party, Integer> snapShot) {
    this.parent = parent;
    this.snapShot = snapShot;
    //this.winningStrategy = winningStrategy;
    //this.winCount = new HashMap<>();
  }

  public void updateToParent() {
    parent.getFromChildren(this.snapShot);
  }

  public synchronized void getFromChildren(Map<Party, Integer> snapShot) {
    snapShot.forEach(
        (party, count) -> {
          this.snapShot.put(party, this.snapShot.getOrDefault(party, 0) + count);
        });
    updateToParent();
    declareWinner();
  }

  public void declareWinner() {
    Party party = winningStrategy.whoWin();
    System.out.println("Won by " + party.getName());
  }
}
