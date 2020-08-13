package com.wz.array;

/**
 * Given a 2D board and a word, find if the word exists in the grid.
 * The word can be constructed from letters of sequentially adjacent cell,
 * where "adjacent" cells are those horizontally or vertically neighboring.
 * The same letter cell may not be used more than once.
 * <p>
 * Example:
 * board =
 * [
 * ['A','B','C','E'],
 * ['S','F','C','S'],
 * ['A','D','E','E']
 * ]
 * Given word = "ABCCED", return true.
 * Given word = "SEE", return true.
 * Given word = "ABCB", return false.
 */
public class WordSearch {
    public static void main(String[] args) {
        char[][] board = new char[][]{
                {'A', 'B', 'C', 'E'},
                {'S', 'F', 'C', 'S'},
                {'A', 'D', 'E', 'E'}
        };
        System.out.println(exist(board, "ABCCED"));
        System.out.println(exist(board, "SEE"));
        System.out.println(exist(board, "ABCB"));
    }

    /**
     * 用 DFS 算法的思路来做，不同点在于要对每一个元素进行 DFS
     * 并且在一次DFS中，要用visit数组记录位置是否被访问过，一次DFS完成后，清空所有访问记录
     */
    public static boolean exist(char[][] board, String word) {
        boolean[][] visited = new boolean[board.length][board[0].length];
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                if (search(board, word, 0, i, j, visited)) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * 从字母表中的一个元素开始，上下左右递归，如果递归结果是找到了，就返回true
     *
     * @param index   当前寻找的是第几个字母
     * @param row     字母表的行
     * @param col     字母表的列
     * @param visited 访问记录
     */
    private static boolean search(char[][] board, String word, int index, int row, int col, boolean[][] visited) {
        // 单词已找完
        if (index == word.length()) {
            return true;
        }
        // 超过边界或已访问过
        if (row < 0 || row >= board.length || col < 0 || col >= board[0].length || visited[row][col]) {
            return false;
        }
        if (board[row][col] != word.charAt(index)) {
            return false;
        }

        // 对于访问过的位置标为 true
        visited[row][col] = true;
        // 上下左右递归查找
        boolean result = search(board, word, index + 1, row - 1, col, visited)
                || search(board, word, index + 1, row + 1, col, visited)
                || search(board, word, index + 1, row, col - 1, visited)
                || search(board, word, index + 1, row, col + 1, visited);

        // 一轮结束后，要重新标为 false,因为下一轮又是可以访问的
        visited[row][col] = false;
        return result;
    }
}
