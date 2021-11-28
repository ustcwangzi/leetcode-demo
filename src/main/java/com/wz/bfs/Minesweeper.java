package com.wz.bfs;

import java.util.Arrays;

/**
 * Let's play the minesweeper game (Wikipedia, online game)!
 * You are given a 2D char matrix representing the game board. 'M' represents an unrevealed mine,
 * 'E' represents an unrevealed empty square, 'B' represents a revealed blank square that has no adjacent
 * (above, below, left, right, and all 4 diagonals) mines, digit ('1' to '8') represents how many mines are
 * adjacent to this revealed square, and finally 'X' represents a revealed mine.
 * Now given the next click position (row and column indices) among all the unrevealed squares ('M' or 'E'),
 * return the board after revealing this position according to the following rules:
 * 1. If a mine ('M') is revealed, then the game is over - change it to 'X'.
 * 2. If an empty square ('E') with no adjacent mines is revealed, then change it to revealed blank ('B') and
 *    all of its adjacent unrevealed squares should be revealed recursively.
 * 3. If an empty square ('E') with at least one adjacent mine is revealed, then change it to a digit ('1' to '8')
 *    representing the number of adjacent mines.
 * 4. Return the board when no more squares will be revealed.
 *
 * Example 1:
 * Input:
 * [['E', 'E', 'E', 'E', 'E'],
 *  ['E', 'E', 'M', 'E', 'E'],
 *  ['E', 'E', 'E', 'E', 'E'],
 *  ['E', 'E', 'E', 'E', 'E']]
 * Click : [3,0]
 * Output:
 * [['B', '1', 'E', '1', 'B'],
 *  ['B', '1', 'M', '1', 'B'],
 *  ['B', '1', '1', '1', 'B'],
 *  ['B', 'B', 'B', 'B', 'B']]
 * Explanation:
 * @link ../../../../resource/Minesweeper1.jpg
 *
 * Example 2:
 * Input:
 * [['B', '1', 'E', '1', 'B'],
 *  ['B', '1', 'M', '1', 'B'],
 *  ['B', '1', '1', '1', 'B'],
 *  ['B', 'B', 'B', 'B', 'B']]
 * Click : [1,2]
 * Output:
 * [['B', '1', 'E', '1', 'B'],
 *  ['B', '1', 'X', '1', 'B'],
 *  ['B', '1', '1', '1', 'B'],
 *  ['B', 'B', 'B', 'B', 'B']]
 * Explanation:
 * @link ../../../../resource/Minesweeper2.jpg
 *
 * Note:
 * 1. The range of the input matrix's height and width is [1,50].
 * 2. The click position will only be an unrevealed square ('M' or 'E'), which also means the input board contains at least one clickable square.
 * 3. The input board won't be a stage when game is over (some mines have been revealed).
 * 4. For simplicity, not mentioned rules should be ignored in this problem. For example, you don't need to reveal all the unrevealed mines when the game is over, consider any cases that you will win the game or flag any squares.
 */
public class Minesweeper {
    public static void main(String[] args) {
        char[][] board = new char[][]{
                {'E', 'E', 'E', 'E', 'E'},
                {'E', 'E', 'M', 'E', 'E'},
                {'E', 'E', 'E', 'E', 'E'},
                {'E', 'E', 'E', 'E', 'E'}
        };
        char[][] result = updateBoard(board, new int[]{3, 0});
        for (char[] array : result) {
            System.out.println(Arrays.toString(array));
        }
    }

    private static final int[][] DIRS = new int[][]{{1, 0}, {0, 1}, {1, 1}, {1, -1}, {-1, 1}, {-1, 0}, {0, -1}, {-1, -1}};

    /**
     * DFS
     * 1. 如果点到了雷 'M'，将当前位置改为 'X' 并结束函数
     * 2. 点到了 'E'，先搜集一下它的四周（3*3个格子）有几个雷——如果有雷，将当前格子改为数字；如果周围无雷，那么将当前格子改成 'B'；
     *    如果在深度优先遍历中发现自身是雷或者‘B’，则返回，什么都不修改
     * 3. 往当前格子的（3*3-1）个方向即左上、上、右上、左、右、左下、下、右下按顺序进行 DFS，具体操作同2
     */
    public static char[][] updateBoard(char[][] board, int[] click) {
        if (board[click[0]][click[1]] == 'M') {
            board[click[0]][click[1]] = 'X';
            return board;
        }

        dfs(board, new boolean[board.length][board[0].length], click[0], click[1]);
        return board;
    }

    private static void dfs(char[][] board, boolean[][] visited, int i, int j) {
        if (i < 0 || i == board.length || j < 0 || j == board[i].length || visited[i][j] || board[i][j] == 'M') {
            return;
        }

        visited[i][j] = true;
        int mines = 0;

        for (int[] dir : DIRS) {
            int x = i + dir[0], y = j + dir[1];
            if (x < 0 || x >= board.length || y < 0 || y >= board[0].length) {
                continue;
            }
            mines += board[x][y] == 'M' ? 1 : 0;
        }

        if (mines > 0) {
            board[i][j] = Character.forDigit(mines, 10);
        } else {
            board[i][j] = 'B';
            for (int[] dir : DIRS) {
                int x = i + dir[0], y = j + dir[1];
                if (x < 0 || x >= board.length || y < 0 || y >= board[0].length) {
                    continue;
                }
                dfs(board, visited, x, y);
            }
        }
    }

}
