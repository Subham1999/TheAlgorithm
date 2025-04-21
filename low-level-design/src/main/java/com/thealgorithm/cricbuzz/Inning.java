package com.thealgorithm.cricbuzz;

import java.util.ArrayList;
import java.util.List;
import lombok.Data;

@Data
public class Inning {
  private Team bowlingTeam;
  private Team battingTeam;
  private List<WicketDetail> wicketDetails = new ArrayList<>();

  public Inning(Team bowlingTeam, Team battingTeam) {
    this.bowlingTeam = bowlingTeam;
    this.battingTeam = battingTeam;
  }
}
