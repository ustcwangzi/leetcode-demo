package com.wz.dynamicprogramming;

import java.util.Arrays;

/**
 * On an NxN chessboard, a knight starts at the r-th row and c-th column and attempts to make exactly K moves.
 * The rows and columns are 0 indexed, so the top-left square is (0, 0), and the bottom-right square is (N-1, N-1).
 * A chess knight has 8 possible moves it can make, as illustrated below. Each move is two squares in a cardinal direction,
 * then one square in an orthogonal direction.
 * @link ../../../../resource/KnightProbabilityInChessboard.jpg
 * Each time the knight is to move, it chooses one of eight possible moves uniformly at random
 * (even if the piece would go off the chessboard) and moves there.
 * The knight continues moving until it has made exactly K moves or has moved off the chessboard.
 * Return the probability that the knight remains on the board after it has stopped moving.
 *
 * Example:
 * Input: 3, 2, 0, 0
 * Output: 0.0625
 * Explanation: There are two moves (to (1,2), (2,1)) that will keep the knight on the board.
 * From each of those positions, there are also two moves that will keep the knight on the board.
 * The total probability the knight stays on the board is 0.0625.
 *
 * Note:
 * 1. N will be between 1 and 25.
 * 2. K will be between 0 and 100.
 * 3. The knight always initially starts on the board.
 */
public class KnightProbabilityInChessboard {
    public static void main(String[] args) {
        System.out.println(knightProbability(3, 2, 0, 0));
    }

    /**
     * 动态规划，思路以{@link OutOfBoundaryPaths}类似
     * 允许走 K 步，走完 K 步之后还能留在棋盘上的概率是多少，要求概率，必须要先分别求出分子和分母，其中分子是走完K步还在棋盘上的走法，
     * 分母是没有限制条件的总共的走法。那么分母最好算，每步走有 8 种跳法，那么 K 步就是 8 的 K 次方种了，关键是要求出分子。
     * dp[i][j] 表示在棋盘 (i,j) 位置上走完当前步骤还留在棋盘上的走法总和，初始化为1，将步骤这个维度当成了时间维度在不停更新。
     * 下面先写出8种走法的位置的坐标，就像之前遍历迷宫上下左右四个方向坐标一样，不过这次位置变了而已。
     * 然后一步一步来遍历，每一步都需要完整遍历一遍棋盘的每个位置，新建一个临时数组 tmp，大小和 dp 数组相同，但是初始化为0，
     * 然后对于遍历到的棋盘上的每一个格子，都遍历 8 中解法，如果新的位置不在棋盘上了，直接跳过，否则就加上上一步中的 dp 数组中对应的值，
     * 遍历完棋盘后，将 dp 数组更新为这个临时数组 tmp
     */
    public static double knightProbability(int N, int K, int r, int c) {
        if (K == 0) {
            return 1;
        }
        double[][] dp = new double[N][N];
        for (double[] array : dp) {
            Arrays.fill(array, 1);
        }

        int[][] dirs = {{-1, -2}, {-2, -1}, {-2, 1}, {-1, 2}, {1, 2}, {2, 1}, {2, -1}, {1, -2}};
        for (int m = 0; m < K; ++m) {
            double[][] tmp = new double[N][N];
            for (int i = 0; i < N; ++i) {
                for (int j = 0; j < N; ++j) {
                    for (int[] dir : dirs) {
                        int x = i + dir[0], y = j + dir[1];
                        if (x < 0 || x >= N || y < 0 || y >= N) {
                            continue;
                        }
                        tmp[i][j] += dp[x][y];
                    }
                }
            }
            dp = tmp;
        }
        return dp[r][c] / Math.pow(8, K);
    }
}
