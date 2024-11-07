package com.thealgorithm.votingsystem;

import java.util.List;
import java.util.Map;

public class ConstituencyVotingSystem implements VotingSystem {
  private List<Candidate> candidates;
  private List<Citizen> citizens;
  private Map<Party, Integer> voteCount;

  @Override
  public Map<Party, Integer> collectVote() {
    for (Citizen citizen : citizens) {
      Vote vote = citizen.castVote();
      Candidate candidate = vote.getCandidate();
      if (candidate != null) {
        voteCount.put(candidate.getParty(), 1 + voteCount.getOrDefault(candidate.getParty(), 0));
      }
    }
    return voteCount;
  }
}
