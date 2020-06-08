package com.wz.lists;

import java.util.Arrays;

/**
 * According to the Wikipedia's article: "The Game of Life, also known simply as Life,
 * is a cellular automaton devised by the British mathematician John Horton Conway in 1970."
 *
 * Given a board with m by n cells, each cell has an initial state live (1) or dead (0).
 * Each cell interacts with its eight neighbors (horizontal, vertical, diagonal) using the following four rules
 * (taken from the above Wikipedia article):
 *
 * Any live cell with fewer than two live neighbors dies, as if caused by under-population.
 * Any live cell with two or three live neighbors lives on to the next generation.
 * Any live cell with more than three live neighbors dies, as if by over-population..
 * Any dead cell with exactly three live neighbors becomes a live cell, as if by reproduction.
 * Write a function to compute the next state (after one update) of the board given its current state.
 * The next state is created by applying the above rules simultaneously to every cell in the current state,
 * where births and deaths occur simultaneously.
 *
 * Example:
 * Input:
 * [
 *   [0,1,0],
 *   [0,0,1],
 *   [1,1,1],
 *   [0,0,0]
 * ]
 * Output:
 * [
 *   [0,0,0],
 *   [1,0,1],
 *   [0,1,1],
 *   [0,1,0]
 * ]
 */
public class GameOfLife {
    public static void main(String[] args) {
        int[][] board = new int[][]{
                {0, 1, 0},
                {0, 0, 1},
                {1, 1, 1},
                {0, 0, 0}
        };
        gameOfLife(board);
        for (int[] array : board) {
            System.out.println(Arrays.toString(array));
        }
    }

    /**
     * 所有的位置必须被同时更新，但在循环程序中还是一个位置一个位置更新的，当一个位置更新了，这个位置成为其他位置的 neighbor 时，
     * 怎么知道其未更新的状态呢？可以使用状态机转换：
     * 状态0：死细胞转为死细胞
     * 状态1：活细胞转为活细胞
     * 状态2：活细胞转为死细胞
     * 状态3：死细胞转为活细胞
     * 最后对所有状态对2取余，则状态0和2就变成死细胞，状态1和3就是活细胞，达成目的。
     * 先对原数组进行逐个扫描，对于每一个位置，扫描其周围八个位置，如果遇到状态1或2，就计数器累加1，
     * 扫完8个邻居，如果少于两个活细胞或者大于三个活细胞，而且当前位置是活细胞的话，标记状态2，
     * 如果正好有三个活细胞且当前是死细胞的话，标记状态3
     * 完成一遍扫描后再对数据扫描一遍，对2取余变成最终结果
     */
    public static void gameOfLife(int[][] board) {
        int m = board.length, n = board[0].length;
        int[] dx = {-1, -1, -1, 0, 0, 1, 1, 1};
        int[] dy = {-1, 0, 1, -1, 1, -1, 0, 1};
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                int count = 0;
                for (int k = 0; k < 8; k++) {
                    int x = i + dx[k], y = j + dy[k];
                    if (x >= 0 && x < m && y >= 0 && y < n && (board[x][y] == 1 || board[x][y] == 2)) {
                        count++;
                    }
                }
                if (board[i][j] == 1 && (count < 2 || count > 3)) {
                    board[i][j] = 2;
                } else if (board[i][j] == 0 && count == 3) {
                    board[i][j] = 3;
                }
            }
        }
        for (int i = 0; i < m; ++i) {
            for (int j = 0; j < n; ++j) {
                board[i][j] %= 2;
            }
        }
    }
}
