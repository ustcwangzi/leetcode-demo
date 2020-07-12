package com.wz.lists;

/**
 * On an 8 x 8 chessboard, there is one white rook.  There also may be empty squares, white bishops, and black pawns.
 * These are given as characters 'R', '.', 'B', and 'p' respectively. Uppercase characters represent white pieces,
 * and lowercase characters represent black pieces.
 * The rook moves as in the rules of Chess: it chooses one of four cardinal directions (north, east, west, and south),
 * then moves in that direction until it chooses to stop, reaches the edge of the board, or captures an opposite colored
 * pawn by moving to the same square it occupies.  Also, rooks cannot move into the same square as other friendly bishops.
 * Return the number of pawns the rook can capture in one move.
 *
 * Example 1:
 * Input: [[".",".",".",".",".",".",".","."],[".",".",".","p",".",".",".","."],[".",".",".","R",".",".",".","p"],
 * [".",".",".",".",".",".",".","."],[".",".",".",".",".",".",".","."],[".",".",".","p",".",".",".","."],
 * [".",".",".",".",".",".",".","."],[".",".",".",".",".",".",".","."]]
 * Output: 3
 * Explanation:
 * In this example the rook is able to capture all the pawns.
 *
 * Example 2:
 * Input: [[".",".",".",".",".",".",".","."],[".","p","p","p","p","p",".","."],[".","p","p","B","p","p",".","."],
 * [".","p","B","R","B","p",".","."],[".","p","p","B","p","p",".","."],[".","p","p","p","p","p",".","."],
 * [".",".",".",".",".",".",".","."],[".",".",".",".",".",".",".","."]]
 * Output: 0
 * Explanation:
 * Bishops are blocking the rook to capture any pawn.
 */
public class AvailableCapturesForRook {
    public static void main(String[] args) {
        char[][] board = new char[][]{
                {'.', '.', '.', '.', '.', '.', '.', '.'},
                {'.', '.', '.', 'p', '.', '.', '.', '.'},
                {'.', '.', '.', 'R', '.', '.', '.', 'p'},
                {'.', '.', '.', '.', '.', '.', '.', '.'},
                {'.', '.', '.', '.', '.', '.', '.', '.'},
                {'.', '.', '.', '.', '.', '.', '.', '.'},
                {'.', '.', '.', 'p', '.', '.', '.', '.'},
                {'.', '.', '.', '.', '.', '.', '.', '.'},
                {'.', '.', '.', '.', '.', '.', '.', '.'}
        };
        System.out.println(numRookCaptures(board));
    }

    /**
     * 在一个国际象棋的棋盘上，有一个白车(R)，有若干白象（B）、黑卒（p），其余是空白（.），问这个白车在只移动一次的情况下，能吃掉哪几个黑卒
     * 找到车所在的位置，从这个位置往上下左右四个方向逐一搜索，搜索到‘卒’的话就记录加1
     */
    public static int numRookCaptures(char[][] board) {
        int x = -1, y = -1;
        int m = board.length, n = board[0].length;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (board[i][j] == 'R') {
                    x = i;
                    y = j;
                }
            }
        }

        int result = 0;
        for (int j = y + 1; j < n && board[x][j] != 'B'; j++) {
            if (board[x][j] == 'p') {
                result++;
                break;
            }

        }
        for (int j = y - 1; j > -1 && board[x][j] != 'B'; j--) {
            if (board[x][j] == 'p') {
                result++;
                break;
            }
        }
        for (int i = x + 1; i < m && board[i][y] != 'B'; i++) {
            if (board[i][y] == 'p') {
                result++;
                break;
            }
        }
        for (int i = x - 1; i > -1 && board[i][y] != 'B'; i--) {
            if (board[i][y] == 'p') {
                result++;
                break;
            }
        }
        return result;
    }
}
