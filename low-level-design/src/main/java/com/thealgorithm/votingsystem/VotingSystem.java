package com.thealgorithm.votingsystem;

import java.util.Map;

public interface VotingSystem {
  Map<Party, Integer> collectVote();
}
