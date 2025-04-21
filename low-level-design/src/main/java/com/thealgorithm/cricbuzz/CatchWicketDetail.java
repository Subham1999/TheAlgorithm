package com.thealgorithm.cricbuzz;

import lombok.Data;

@Data
public class CatchWicketDetail extends WicketDetail {
  private Player caughtBy;

  public CatchWicketDetail(Player batsman, Player bowler, Player caughtBy) {
    super(batsman, bowler);
    this.caughtBy = caughtBy;
  }
}
