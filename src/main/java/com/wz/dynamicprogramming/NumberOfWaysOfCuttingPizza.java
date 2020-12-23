package com.wz.dynamicprogramming;

/**
 * Given a rectangular pizza represented as a rows x cols matrix containing the following characters:
 * 'A' (an apple) and '.' (empty cell) and given the integer k. You have to cut the pizza into k pieces using k-1 cuts.
 * For each cut you choose the direction: vertical or horizontal, then you choose a cut position at the cell boundary
 * and cut the pizza into two pieces. If you cut the pizza vertically, give the left part of the pizza to a person.
 * If you cut the pizza horizontally, give the upper part of the pizza to a person. Give the last piece of pizza to the last person.
 * Return the number of ways of cutting the pizza such that each piece contains at least one apple.
 * Since the answer can be a huge number, return this modulo 10^9 + 7.
 *
 * Example 1:
 * @see ../../../../resource/NumberOfWaysOfCuttingPizza.jpg
 * Input: pizza = ["A..","AAA","..."], k = 3
 * Output: 3
 * Explanation: The figure above shows the three ways to cut the pizza. Note that pieces must contain at least one apple.
 *
 * Example 2:
 * Input: pizza = ["A..","AA.","..."], k = 3
 * Output: 1
 *
 * Constraints:
 * 1. 1 <= rows, cols <= 50
 * 2. rows == pizza.length
 * 3. cols == pizza[i].length
 * 4. 1 <= k <= 10
 * 5. pizza consists of characters 'A' and '.' only.
 */
public class NumberOfWaysOfCuttingPizza {
    public static void main(String[] args) {
        System.out.println(ways(new String[]{"A..", "AAA", "..."}, 3));
    }

    public static int ways(String[] pizza, int k) {
        int m = pizza.length, n = pizza[0].length(), mod = 1000000007;
        int[][] a = new int[m + 1][n + 1];
        for (int i = m - 1; i >= 0; i--) {
            for (int j = n - 1; j >= 0; j--) {
                a[i][j] = a[i + 1][j] + a[i][j + 1] - a[i + 1][j + 1] + (pizza[i].charAt(j) == 'A' ? 1 : 0);
            }
        }

        int[][][] dp = new int[m + 1][n + 1][k];
        for (int i = m - 1; i >= 0; i--) {
            for (int j = n - 1; j >= 0; j--) {
                for (int x = 0; x < k; x++) {
                    if (x == 0) {
                        dp[i][j][0] = (a[i][j] > 0 ? 1 : 0);
                    } else {
                        //horizontal
                        for (int i1 = i + 1; i1 < m; i1++) {
                            if (a[i][j] - a[i1][j] > 0) {
                                dp[i][j][x] = (dp[i][j][x] + dp[i1][j][x - 1]) % mod;
                            }
                        }
                        //vertical
                        for (int j1 = j + 1; j1 < n; j1++) {
                            if (a[i][j] - a[i][j1] > 0) {
                                dp[i][j][x] = (dp[i][j][x] + dp[i][j1][x - 1]) % mod;
                            }
                        }
                    }
                }
            }
        }
        return dp[0][0][k - 1];
    }
}
