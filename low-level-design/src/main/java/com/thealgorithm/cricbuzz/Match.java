package com.thealgorithm.cricbuzz;

import com.thealgorithm.cricbuzz.event.MatchEvent;
import com.thealgorithm.cricbuzz.pubsub.MatchEventObserver;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Match extends MatchEventObserver {
  private Team teamA;
  private Team teamB;
  private Team tossWinningTeam;
  private Team firstBattingTeam;
  private Inning firstInning;
  private Inning secondInning;

  public Match(Team teamA, Team teamB, Team tossWinningTeam, Team firstBattingTeam) {
    this.teamA = teamA;
    this.teamB = teamB;
    this.tossWinningTeam = tossWinningTeam;
    this.firstBattingTeam = firstBattingTeam;

    this.firstInning = new Inning(other(firstBattingTeam), firstBattingTeam);
    this.secondInning = new Inning(firstBattingTeam, other(firstBattingTeam));
  }

  private Team other(Team team) {
    return teamA.equals(team) ? teamB : teamA;
  }

  @Override
  public void observe(MatchEvent matchEvent) {
    // score board updaters
  }
}
