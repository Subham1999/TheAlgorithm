package com.thealgorithm.votingsystem;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class Party {
  private String name;
  private List<Candidate> candidates;
}
