package com.wz.array;

/**
 * Tic-tac-toe is played by two players A and B on a 3 x 3 grid.
 * Here are the rules of Tic-Tac-Toe:
 * Players take turns placing characters into empty squares (" ").
 * The first player A always places "X" characters, while the second player B always places "O" characters.
 * "X" and "O" characters are always placed into empty squares, never on filled ones.
 * The game ends when there are 3 of the same (non-empty) character filling any row, column, or diagonal.
 * The game also ends if all squares are non-empty.
 * No more moves can be played if the game is over.
 * Given an array moves where each element is another array of size 2 corresponding to the row and column
 * of the grid where they mark their respective character in the order in which A and B play.
 * Return the winner of the game if it exists (A or B), in case the game ends in a draw return "Draw",
 * if there are still movements to play return "Pending".
 * You can assume that moves is valid (It follows the rules of Tic-Tac-Toe), the grid is initially empty and A will play first.
 *
 * Example 1:
 * Input: moves = [[0,0],[2,0],[1,1],[2,1],[2,2]]
 * Output: "A"
 * Explanation: "A" wins, he always plays first.
 * "X  "    "X  "    "X  "    "X  "    "X  "
 * "   " -> "   " -> " X " -> " X " -> " X "
 * "   "    "O  "    "O  "    "OO "    "OOX"
 *
 * Example 2:
 * Input: moves = [[0,0],[1,1],[0,1],[0,2],[1,0],[2,0]]
 * Output: "B"
 * Explanation: "B" wins.
 * "X  "    "X  "    "XX "    "XXO"    "XXO"    "XXO"
 * "   " -> " O " -> " O " -> " O " -> "XO " -> "XO "
 * "   "    "   "    "   "    "   "    "   "    "O  "
 *
 * Example 3:
 * Input: moves = [[0,0],[1,1],[2,0],[1,0],[1,2],[2,1],[0,1],[0,2],[2,2]]
 * Output: "Draw"
 * Explanation: The game ends in a draw since there are no moves to make.
 * "XXO"
 * "OOX"
 * "XOX"
 *
 * Example 4:
 * Input: moves = [[0,0],[1,1]]
 * Output: "Pending"
 * Explanation: The game has not finished yet.
 * "X  "
 * " O "
 * "   "
 */
public class FindWinnerOnTicTacToeGame {
    public static void main(String[] args) {
        int[][] moves = new int[][]{
                {0, 0}, {2, 0}, {1, 1}, {2, 1}, {2, 2}
        };
        System.out.println(tictactoe(moves));

        moves = new int[][]{
                {0, 0}, {1, 1}, {0, 1}, {0, 2}, {1, 0}, {2, 0}
        };
        System.out.println(tictactoe(moves));

        moves = new int[][]{
                {0, 0}, {1, 1}, {2, 0}, {1, 0}, {1, 2}, {2, 1}, {0, 1}, {0, 2}, {2, 2}
        };
        System.out.println(tictactoe(moves));

        moves = new int[][]{
                {0, 0}, {1, 1}
        };
        System.out.println(tictactoe(moves));
    }

    /**
     * 井字棋，A 用 "X" 作为棋子， B 用 "O" 作为棋子，只要有 3 个相同的（非空）棋子排成一条直线（行、列、对角线）时，游戏结束
     * 如果游戏存在获胜者（A 或 B），就返回该游戏的获胜者；如果游戏以平局结束，则返回 "Draw"；如果游戏未结束，则返回 "Pending"
     * 按照井字棋的规则填充 1 代表 A、2 代表 B，然后根据填充的数组 grid 判断结果即可
     */
    public static String tictactoe(int[][] moves) {
        int[][] grid = new int[3][3];
        for (int i = 0; i < moves.length; i++) {
            grid[moves[i][0]][moves[i][1]] = i % 2 == 0 ? 1 : 2;
        }
        if (isVictory(grid, 1)) {
            return "A";
        }
        if (isVictory(grid, 2)) {
            return "B";
        }
        return moves.length == 9 ? "Draw" : "Pending";
    }

    private static boolean isVictory(int[][] grid, int player) {
        return (grid[0][0] == grid[0][1] && grid[0][1] == grid[0][2] && grid[0][2] == player) ||
                (grid[1][0] == grid[1][1] && grid[1][1] == grid[1][2] && grid[1][2] == player) ||
                (grid[2][0] == grid[2][1] && grid[2][1] == grid[2][2] && grid[2][2] == player) ||
                (grid[0][0] == grid[1][0] && grid[1][0] == grid[2][0] && grid[2][0] == player) ||
                (grid[0][1] == grid[1][1] && grid[1][1] == grid[2][1] && grid[2][1] == player) ||
                (grid[0][2] == grid[1][2] && grid[1][2] == grid[2][2] && grid[2][2] == player) ||
                (grid[0][0] == grid[1][1] && grid[1][1] == grid[2][2] && grid[2][2] == player) ||
                (grid[0][2] == grid[1][1] && grid[1][1] == grid[2][0] && grid[2][0] == player);
    }
}
