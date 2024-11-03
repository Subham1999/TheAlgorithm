package com.thealgorithm.cricbuzz;

import lombok.Data;

@Data
public class Player {
  private String name;
  private PlayerType playerType;
  private BattingScoreCard battingScoreCard;
  private BowlingScoreCard bowlingScoreCard;
}
