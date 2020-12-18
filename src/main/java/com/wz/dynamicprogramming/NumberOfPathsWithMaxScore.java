package com.wz.dynamicprogramming;

import java.util.Arrays;
import java.util.List;

/**
 * You are given a square board of characters. You can move on the board starting at the bottom right square marked with the character 'S'.
 * You need to reach the top left square marked with the character 'E'. The rest of the squares are labeled either with
 * a numeric character 1, 2, ..., 9 or with an obstacle 'X'. In one move you can go up, left or up-left (diagonally)
 * only if there is no obstacle there.
 * Return a list of two integers: the first integer is the maximum sum of numeric characters you can collect,
 * and the second is the number of such paths that you can take to get that maximum sum, taken modulo 10^9 + 7.
 * In case there is no path, return [0, 0].
 *
 * Example 1:
 * Input: board = ["E23","2X2","12S"]
 * Output: [7,1]
 *
 * Example 2:
 * Input: board = ["E12","1X1","21S"]
 * Output: [4,2]
 *
 * Constraints:
 * 2 <= board.length == board[i].length <= 100
 */
public class NumberOfPathsWithMaxScore {
    public static void main(String[] args) {
        System.out.println(Arrays.toString(pathsWithMaxScore(Arrays.asList("E23", "2X2", "12S"))));
    }

    public static int[] pathsWithMaxScore(List<String> board) {
        int m = board.size(), n = board.get(0).length(), mod = 1000000007;
        int[][][] dp = new int[m][n][2];
        int[][] dir = new int[][]{{0, 1}, {1, 0}, {1, 1}};
        char[][] arr = new char[m][n];
        for (int i = 0; i < m; i++) {
            arr[i] = board.get(i).toCharArray();
        }
        arr[0][0] = '0';
        dp[m - 1][n - 1] = new int[]{1, 1};

        for (int i = m - 1; i >= 0; i--) {
            for (int j = n - 1; j >= 0; j--) {
                if ((i == m - 1 && j == n - 1) || arr[i][j] == 'X') continue;
                int add = arr[i][j] - '0';
                for (int[] d : dir) {
                    int r = i + d[0], c = j + d[1];
                    if (r < m && c < n && dp[r][c][0] > 0) dp[i][j][0] = Math.max(dp[i][j][0], dp[r][c][0] + add);
                }
                for (int[] d : dir) {
                    int r = i + d[0], c = j + d[1];
                    if (r < m && c < n && dp[r][c][0] > 0 && dp[i][j][0] == dp[r][c][0] + add)
                        dp[i][j][1] = (dp[i][j][1] + dp[r][c][1]) % mod;
                }
            }
        }
        return new int[]{dp[0][0][0] > 0 ? dp[0][0][0] - 1 : 0, dp[0][0][1]};
    }
}
