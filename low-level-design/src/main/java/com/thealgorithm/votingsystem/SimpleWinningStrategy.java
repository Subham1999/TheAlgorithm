package com.thealgorithm.votingsystem;

public class SimpleWinningStrategy extends WinningStrategy {

  public SimpleWinningStrategy(VoteBank voteBank) {
    super(voteBank);
  }

  @Override
  public Party whoWin() {
    VoteBank voteBank = super.getVoteBank();
    return voteBank.getSnapShot().entrySet().stream()
        .reduce(
            (pe1, pe2) -> {
              if (pe1.getValue() > pe2.getValue()) {
                return pe1;
              }
              return pe2;
            })
        .orElseThrow()
        .getKey();
  }
}
