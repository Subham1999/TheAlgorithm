package com.thealgorithm.miscelleneous;

import com.thealgorithm.commons.Pair;
import java.util.HashSet;
import java.util.Set;

/**
 * @author: Subham Santra
 */
public class WalkingRobotSimulation {

  static class Solution {
    public int robotSim(int[] commands, int[][] obstacles) {

      Set<Pair<Integer, Integer>> obstacleSet = new HashSet<>();
      for (int[] obs : obstacles) {
        obstacleSet.add(Pair.of(obs[0], obs[1]));
      }

      int[] current = new int[2];

      char direction = 'e';

      int max = 0;

      for (int command : commands) {
        if (command < 0) {
          direction = changeDirection(direction, command == -1);
        } else {
          // move(dir, current, command, obstacleSet);
          int x = current[0];
          int y = current[1];

          for (int c = 1; c <= command; ++c) {
            int xx = x + (direction == 'e' ? 1 : (direction == 'w' ? -1 : 0));
            int yy = y + (direction == 'n' ? 1 : (direction == 's' ? -1 : 0));
            if (obstacleSet.contains(Pair.of(xx, yy))) {
              break;
            }
            current[0] = x = xx;
            current[1] = y = yy;
            max = Math.max(max, (int) Math.sqrt(x * x + y * y));
          }
        }
      }
      return max;
    }

    char changeDirection(char dir, boolean left) {
      return switch (dir) {
        case 'e' -> left ? 'n' : 's';
        case 'n' -> left ? 'w' : 'e';
        case 'w' -> left ? 's' : 'n';
        case 's' -> left ? 'e' : 'w';
        default -> '0';
      };
    }
  }

  public static void main(String[] args) {}
}
