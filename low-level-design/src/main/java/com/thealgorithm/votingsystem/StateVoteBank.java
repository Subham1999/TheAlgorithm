package com.thealgorithm.votingsystem;

import java.util.Map;

public class StateVoteBank extends VoteBank {
  public StateVoteBank(
      VoteBank parent, Map<Party, Integer> snapShot) {
    super(parent, snapShot);
  }
}
