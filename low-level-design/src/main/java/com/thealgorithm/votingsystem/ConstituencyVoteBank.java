package com.thealgorithm.votingsystem;

import java.util.Map;

public class ConstituencyVoteBank extends VoteBank {
  private ConstituencyVotingSystem constituencyVotingSystem;

  public ConstituencyVoteBank(
      VoteBank parent,
      Map<Party, Integer> snapShot,
      ConstituencyVotingSystem constituencyVotingSystem) {

    super(parent, snapShot);
    this.constituencyVotingSystem = constituencyVotingSystem;
  }

  @Override
  public void getFromChildren(Map<Party, Integer> snapShot) {
    super.setSnapShot(constituencyVotingSystem.collectVote());
    updateToParent();
    declareWinner();
  }
}
