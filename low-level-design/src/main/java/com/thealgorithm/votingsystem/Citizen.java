package com.thealgorithm.votingsystem;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class Citizen {
  private String name;
  private String voterId;
  private Candidate choosenCandidate;

  public Vote castVote() {
    return new Vote(choosenCandidate);
  }
}
