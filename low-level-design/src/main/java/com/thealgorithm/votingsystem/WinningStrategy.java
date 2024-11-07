package com.thealgorithm.votingsystem;


import lombok.Getter;

@Getter
public abstract class WinningStrategy {
  private VoteBank voteBank;

  public WinningStrategy(VoteBank voteBank) {
    this.voteBank = voteBank;
  }

  public abstract Party whoWin();
}
