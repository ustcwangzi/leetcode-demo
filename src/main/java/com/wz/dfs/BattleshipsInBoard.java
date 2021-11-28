package com.wz.dfs;

/**
 * Given an m x n matrix board where each cell is a battleship 'X' or empty '.', return the number of the battleships on board.
 * Battleships can only be placed horizontally or vertically on board.
 * In other words, they can only be made of the shape 1 x k (1 row, k columns) or k x 1 (k rows, 1 column), where k can be of any size.
 * At least one horizontal or vertical cell separates between two battleships (i.e., there are no adjacent battleships).
 *
 * Example 1:
 * @link ../../../../resource/BattleshipsInBoard.jpg
 * Input: board = [["X",".",".","X"],[".",".",".","X"],[".",".",".","X"]]
 * Output: 2
 *
 * Constraints:
 * 1. m == board.length
 * 2. n == board[i].length
 * 3. 1 <= m, n <= 200
 * 4. board[i][j] is either '.' or 'X'.
 */
public class BattleshipsInBoard {
    public static void main(String[] args) {
        char[][] board = new char[][]{
                {'X', '.', '.', 'X'},
                {'.', '.', '.', 'X'},
                {'.', '.', '.', 'X'},
        };
        System.out.println(countBattleships(board));
    }

    /**
     * DFS，与 {@link NumberOfIslands} 类似
     */
    public static int countBattleships(char[][] board) {
        int count = 0;
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                if (board[i][j] == 'X') {
                    count++;
                    dfs(board, i, j);
                }
            }
        }
        return count;
    }

    private static void dfs(char[][] board, int i, int j) {
        if (i < 0 || i >= board.length || j < 0 || j >= board[0].length || board[i][j] == '.') {
            return;
        }

        board[i][j] = '.';
        dfs(board, i - 1, j);
        dfs(board, i + 1, j);
        dfs(board, i, j - 1);
        dfs(board, i, j + 1);
    }
}
