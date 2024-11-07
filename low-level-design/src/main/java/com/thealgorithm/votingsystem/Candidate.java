package com.thealgorithm.votingsystem;

import lombok.Data;
import lombok.Getter;

@Getter
public class Candidate extends Citizen {
  private Party party;

  public Candidate(String name, String voterId, Party party) {
    super(name, voterId, null);
    this.party = party;
  }
}
