package com.wz.hash;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Determine if a 9 x 9 Sudoku board is valid. Only the filled cells need to be validated according to the following rules:
 * 1. Each row must contain the digits 1-9 without repetition.
 * 2. Each column must contain the digits 1-9 without repetition.
 * 3. Each of the nine 3 x 3 sub-boxes of the grid must contain the digits 1-9 without repetition.
 * Note:
 * 1. A Sudoku board (partially filled) could be valid but is not necessarily solvable.
 * 2. Only the filled cells need to be validated according to the mentioned rules.
 *
 * Example 1:
 * @see ../../../../resource/ValidSudoku.jpg
 * Input: board =
 * [["5","3",".",".","7",".",".",".","."]
 * ,["6",".",".","1","9","5",".",".","."]
 * ,[".","9","8",".",".",".",".","6","."]
 * ,["8",".",".",".","6",".",".",".","3"]
 * ,["4",".",".","8",".","3",".",".","1"]
 * ,["7",".",".",".","2",".",".",".","6"]
 * ,[".","6",".",".",".",".","2","8","."]
 * ,[".",".",".","4","1","9",".",".","5"]
 * ,[".",".",".",".","8",".",".","7","9"]]
 * Output: true
 *
 * Example 2:
 * Input: board =
 * [["8","3",".",".","7",".",".",".","."]
 * ,["6",".",".","1","9","5",".",".","."]
 * ,[".","9","8",".",".",".",".","6","."]
 * ,["8",".",".",".","6",".",".",".","3"]
 * ,["4",".",".","8",".","3",".",".","1"]
 * ,["7",".",".",".","2",".",".",".","6"]
 * ,[".","6",".",".",".",".","2","8","."]
 * ,[".",".",".","4","1","9",".",".","5"]
 * ,[".",".",".",".","8",".",".","7","9"]]
 * Output: false
 * Explanation: Same as Example 1, except with the 5 in the top left corner being modified to 8. Since there are two 8's in the top left 3x3 sub-box, it is invalid.
 *
 * Constraints:
 * 1. board.length == 9
 * 2. board[i].length == 9
 * 3. board[i][j] is a digit or '.'.
 */
public class ValidSudoku {
    public static void main(String[] args) {
        char[][] board = new char[][]{
                {'5', '3', '.', '.', '7', '.', '.', '.', '.'},
                {'6', '.', '.', '1', '9', '5', '.', '.', '.'},
                {'.', '9', '8', '.', '.', '.', '.', '6', '.'},
                {'8', '.', '.', '.', '6', '.', '.', '.', '3'},
                {'4', '.', '.', '8', '.', '3', '.', '.', '1'},
                {'7', '.', '.', '.', '2', '.', '.', '.', '6'},
                {'.', '6', '.', '.', '.', '.', '2', '8', '.'},
                {'.', '.', '.', '4', '1', '9', '.', '.', '5'},
                {'.', '.', '.', '.', '8', '.', '.', '7', '9'}};
        System.out.println(isValidSudoku(board));

        board = new char[][]{
                {'.', '.', '4', '.', '.', '.', '6', '3', '.'},
                {'.', '.', '.', '.', '.', '.', '.', '.', '.'},
                {'5', '.', '.', '.', '.', '.', '.', '9', '.'},
                {'.', '.', '.', '5', '6', '.', '.', '.', '.'},
                {'4', '.', '3', '.', '.', '.', '.', '.', '1'},
                {'.', '.', '.', '7', '.', '.', '.', '.', '.'},
                {'.', '.', '.', '5', '.', '.', '.', '.', '.'},
                {'.', '.', '.', '.', '.', '.', '.', '.', '.'},
                {'.', '.', '.', '.', '.', '.', '.', '.', '.'}};
        System.out.println(isValidSudoku(board));

        board = new char[][]{
                {'8', '3', '.', '.', '7', '.', '.', '.', '.'},
                {'6', '.', '.', '1', '9', '5', '.', '.', '.'},
                {'.', '9', '8', '.', '.', '.', '.', '6', '.'},
                {'8', '.', '.', '.', '6', '.', '.', '.', '3'},
                {'4', '.', '.', '8', '.', '3', '.', '.', '1'},
                {'7', '.', '.', '.', '2', '.', '.', '.', '6'},
                {'.', '6', '.', '.', '.', '.', '2', '8', '.'},
                {'.', '.', '.', '4', '1', '9', '.', '.', '5'},
                {'.', '.', '.', '.', '8', '.', '.', '7', '9'}};
        System.out.println(isValidSudoku(board));
    }

    /**
     * 每一行、每一列的 1～9 不能重复，每九个元素不能重复，依次进行判断即可
     */
    public static boolean isValidSudoku(char[][] board) {
        int n = board.length;
        List<Set<Character>> colList = new ArrayList<>(9);
        for (int i = 0; i < n; i++) {
            colList.add(new HashSet<>(9));
        }

        for (char[] array : board) {
            // 判断每一行
            Set<Character> rowSet = new HashSet<>(9);
            for (int j = 0; j < n; j++) {
                // 判断每一列
                Set<Character> colSet = colList.get(j);
                char cur = array[j];
                if (inValid(cur) || rowSet.contains(cur) || colSet.contains(cur)) {
                    return false;
                }
                if (cur != '.') {
                    rowSet.add(cur);
                    colSet.add(cur);
                }
            }
        }

        for (int i = 0; i < board.length; i += 3) {
            for (int j = 0; j < board[0].length; j += 3) {
                if (!valid(board, i, j)) {
                    return false;
                }
            }
        }
        return true;
    }

    private static boolean valid(char[][] board, int startI, int startJ) {
        // 判断当前九个元素是否重复
        Set<Character> set = new HashSet<>(9);
        for (int i = startI; i < startI + 3; i++) {
            for (int j = startJ; j < startJ + 3; j++) {
                char cur = board[i][j];
                if (inValid(cur) || set.contains(cur)) {
                    return false;
                }
                if (cur != '.') {
                    set.add(cur);
                }
            }
        }

        return true;
    }

    private static boolean inValid(char cur) {
        return cur != '.' && (cur < '1' || cur > '9');
    }
}
