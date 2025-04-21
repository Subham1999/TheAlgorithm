package com.thealgorithm.cricbuzz;

import lombok.Data;

import java.util.List;

@Data
public class Team {
  private List<Player> playing11;
  private List<Player> extraPlayers;
}
