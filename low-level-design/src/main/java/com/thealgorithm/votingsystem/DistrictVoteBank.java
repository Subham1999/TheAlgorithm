package com.thealgorithm.votingsystem;

import java.util.Map;

public class DistrictVoteBank extends VoteBank {
  public DistrictVoteBank(
      VoteBank parent, Map<Party, Integer> snapShot) {
    super(parent, snapShot);
  }
}
