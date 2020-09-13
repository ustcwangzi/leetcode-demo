package com.wz.math;

/**
 * A Tic-Tac-Toe board is given as a string array board. Return True if and only if it is possible
 * to reach this board position during the course of a valid tic-tac-toe game.
 * The board is a 3 x 3 array, and consists of characters " ", "X", and "O".  The " " character represents an empty square.
 * Here are the rules of Tic-Tac-Toe:
 * Players take turns placing characters into empty squares (" ").
 * The first player always places "X" characters, while the second player always places "O" characters.
 * "X" and "O" characters are always placed into empty squares, never filled ones.
 * The game ends when there are 3 of the same (non-empty) character filling any row, column, or diagonal.
 * The game also ends if all squares are non-empty.
 * No more moves can be played if the game is over.
 *
 * Example 1:
 * Input: board = ["O  ", "   ", "   "]
 * Output: false
 * Explanation: The first player always plays "X".
 *
 * Example 2:
 * Input: board = ["XOX", " X ", "   "]
 * Output: false
 * Explanation: Players take turns making moves.
 *
 * Example 3:
 * Input: board = ["XXX", "   ", "OOO"]
 * Output: false
 *
 * Example 4:
 * Input: board = ["XOX", "O O", "XOX"]
 * Output: true
 *
 * Note:
 * board is a length-3 array of strings, where each string board[i] has length 3.
 * Each board[i][j] is a character in the set {" ", "X", "O"}.
 */
public class ValidTicTacToeState {
    public static void main(String[] args) {
        String[] board = new String[]{"O  ", "   ", "   "};
        System.out.println(validTicTacToe(board));

        board = new String[]{"XXX", "   ", "OOO"};
        System.out.println(validTicTacToe(board));

        board = new String[]{"XOX", "O O", "XOX"};
        System.out.println(validTicTacToe(board));
    }

    /**
     * 根据例子1和例子2得出下棋顺序是有规律的，必须是先X后O，不能破坏这个顺序，那么可以使用一个 turns 变量，当是X时，turns 自增1，
     * 反之若是O，则 turns 自减1，那么最终 turns 一定是0或者1，其他任何值都是错误的，比如例子1中，turns 就是 -1，
     * 例子2中，turns 是2，都是不对的。根据例子3，可以得出结论，只能有一个玩家获胜，可以用两个变量 xwin 和 owin，来记录两个玩家的获胜状态，
     * 由于井字棋的制胜规则是横竖斜任意一个方向有三个连续的就算赢，那么分别在各个方向查找3个连续的X，有的话 xwin 赋值为 true，
     * 还要查找3个连续的O，有的话 owin 赋值为 true，例子3中 xwin 和 owin 同时为 true 了，是错误的。
     * 还有一种情况，例子中没有 cover 到的是：
     * X X X
     * O O _
     * O _ _
     * 这里虽然只有 xwin 为 true，但是这种状态还是错误的，因为一旦第三个X放下后，游戏立即结束，不会有第三个O放下，这么检验这种情况呢？
     * 这时 turns 变量就非常的重要了，当第三个O放下后，turns 自减1，此时 turns 为0了，而正确的应该是当 xwin 为 true 的时候，
     * 第三个O不能放下，那么 turns 不减1，则还是1，这样就可以区分情况五了。当然，可以交换X和O的位置，即当 owin 为 true 时，turns 一定要为0
     */
    public static boolean validTicTacToe(String[] board) {
        int n = board.length, turn = 0;
        boolean xWin = false, oWin = false;
        int[] row = new int[n], col = new int[n];
        int d = 0, antiD = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (board[i].charAt(j) == 'X') {
                    turn++;
                    row[i]++;
                    col[j]++;
                    if (i == j) {
                        d++;
                    }
                    if (i + j == 2) {
                        antiD++;
                    }
                } else if (board[i].charAt(j) == 'O') {
                    turn--;
                    row[i]--;
                    col[j]--;
                    if (i == j) {
                        d--;
                    }
                    if (i + j == 2) {
                        antiD--;
                    }
                }
            }
        }

        for (int i = 0; i < n; i++) {
            xWin |= row[i] == n;
            xWin |= col[i] == n;

            oWin |= row[i] == -n;
            oWin |= col[i] == -n;
        }

        xWin = xWin || d == n || antiD == n;
        oWin = oWin || d == -n || antiD == -n;

        if ((xWin && turn != 1) || (oWin && turn != 0)) {
            return false;
        }

        return turn == 0 || turn == 1;
    }
}
