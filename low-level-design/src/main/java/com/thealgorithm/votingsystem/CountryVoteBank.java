package com.thealgorithm.votingsystem;

import java.util.Map;

public class CountryVoteBank extends VoteBank {
  public CountryVoteBank(
      VoteBank parent, Map<Party, Integer> snapShot) {
    super(parent, snapShot);
  }
}
