package com.wz.dynamicprogramming;

import java.util.Arrays;

/**
 * The chess knight has a unique movement, it may move two squares vertically and one square horizontally,
 * or two squares horizontally and one square vertically (with both forming the shape of an L).
 * The possible movements of chess knight are shown in this diagaram:
 * A chess knight can move as indicated in the chess diagram below:
 * @see ../../../../resource/KnightDialer1.jpg
 * We have a chess knight and a phone pad as shown below, the knight can only stand on a numeric cell (i.e. blue cell).
 * @see ../../../../resource/KnightDialer2.jpg
 * Given an integer n, return how many distinct phone numbers of length n we can dial.
 * You are allowed to place the knight on any numeric cell initially and then you should perform n - 1 jumps to
 * dial a number of length n. All jumps should be valid knight jumps.
 * As the answer may be very large, return the answer modulo 109 + 7.
 *
 * Example 1:
 * Input: n = 1
 * Output: 10
 * Explanation: We need to dial a number of length 1, so placing the knight over any numeric cell of the 10 cells is sufficient.
 *
 * Example 2:
 * Input: n = 2
 * Output: 20
 * Explanation: All the valid number we can dial are [04, 06, 16, 18, 27, 29, 34, 38, 40, 43, 49, 60, 61, 67, 72, 76, 81, 83, 92, 94]
 *
 * Constraints:
 * 1 <= n <= 5000
 */
public class KnightDialer {
    public static void main(String[] args) {
        System.out.println(knightDialer(2));
    }

    public static int knightDialer(int n) {
        int[][] possible = new int[][]{{4, 6}, {6, 8}, {7, 9}, {4, 8}, {3, 9, 0},
                {}, {1, 7, 0}, {2, 6}, {1, 3}, {2, 4}};
        long[][] dp = new long[n + 1][10];
        int mod = 1000000007;
        Arrays.fill(dp[1], 1);

        for (int i = 2; i <= n; i++) {
            for (int j = 0; j < 10; j++) {
                for (int p : possible[j]) {
                    dp[i][j] = dp[i][j] + dp[i - 1][p];
                }
                dp[i][j] = dp[i][j] % mod;
            }
        }

        long result = 0;
        for (int i = 0; i < 10; i++) {
            result = result + dp[n][i];
        }

        return (int) (result % mod);
    }
}
