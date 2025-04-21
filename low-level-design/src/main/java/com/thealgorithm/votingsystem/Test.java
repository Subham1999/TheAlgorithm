package com.thealgorithm.votingsystem;

import java.util.HashMap;

public class Test {
  public static void main(String[] args) {
    VoteBank countryVoteBank = new CountryVoteBank(null, new HashMap<>());
    VoteBank westBengal = new StateVoteBank(countryVoteBank, new HashMap<>());
    VoteBank howrahVoteBank = new DistrictVoteBank(westBengal, new HashMap<>());
    ConstituencyVoteBank amtaVoteBank =
        new ConstituencyVoteBank(howrahVoteBank, new HashMap<>(), new ConstituencyVotingSystem());

    
  }
}
