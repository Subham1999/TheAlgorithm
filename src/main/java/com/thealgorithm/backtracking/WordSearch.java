package com.thealgorithm.backtracking;

/**
 * @author: Subham Santra
 */
public class WordSearch {
  public boolean exist(char[][] board, String word) {
    final int m = board.length;
    final int n = board[0].length;
    final boolean[][] taken = new boolean[m][n];

    for (int i = 0; i < m; ++i) {
      for (int j = 0; j < n; ++j) {
        if (board[i][j] == word.charAt(0)) {
          if (EXPLORE(board, i, j, m, n, 0, word, taken)) {
            return true;
          }
        }
      }
    }

    return false;
  }

  boolean EXPLORE(
      char[][] board,
      int i,
      int j,
      int m,
      int n,
      int wordCharIndex,
      String word,
      boolean[][] taken) {
    if (wordCharIndex == word.length()) {
      return true;
    }

    if (!((0 <= i && i < m) && (0 <= j && j < n))) {
      return false;
    }

    if (taken[i][j]) {
      return false;
    }

    boolean ans = false;

    taken[i][j] = true;
    if (board[i][j] == word.charAt(wordCharIndex)) {
      // We will explore further
      ans |= EXPLORE(board, i, j + 1, m, n, wordCharIndex + 1, word, taken);
      ans |= EXPLORE(board, i, j - 1, m, n, wordCharIndex + 1, word, taken);
      ans |= EXPLORE(board, i + 1, j, m, n, wordCharIndex + 1, word, taken);
      ans |= EXPLORE(board, i - 1, j, m, n, wordCharIndex + 1, word, taken);
    }
    taken[i][j] = false;
    return ans;
  }

  public static void main(String[] args) {
    char[][] board = {{'A', 'B', 'C', 'E'}, {'S', 'F', 'C', 'S'}, {'A', 'D', 'E', 'E'}};
    System.out.println(new WordSearch().exist(board, "ABCCED"));
    System.out.println(new WordSearch().exist(board, "ABCF"));
    System.out.println(new WordSearch().exist(board, "SEE"));
  }
}
