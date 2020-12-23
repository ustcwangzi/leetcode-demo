package com.wz.dynamicprogramming;

import java.util.Arrays;

/**
 * Given three integers n, m and k. Consider the following algorithm to find the maximum element of an array of positive integers:
 * @see ../../../../resource/BuildArrayWhereYouCanFindMaximumExactlyKComparisons.jpg
 * You should build the array arr which has the following properties:
 * arr has exactly n integers.
 * 1 <= arr[i] <= m where (0 <= i < n).
 * After applying the mentioned algorithm to arr, the value search_cost is equal to k.
 * Return the number of ways to build the array arr under the mentioned conditions.
 * As the answer may grow large, the answer must be computed modulo 10^9 + 7.
 *
 * Example 1:
 * Input: n = 2, m = 3, k = 1
 * Output: 6
 * Explanation: The possible arrays are [1, 1], [2, 1], [2, 2], [3, 1], [3, 2] [3, 3]
 *
 * Example 2:
 * Input: n = 5, m = 2, k = 3
 * Output: 0
 * Explanation: There are no possible arrays that satisify the mentioned conditions.
 *
 * Example 3:
 * Input: n = 9, m = 1, k = 1
 * Output: 1
 * Explanation: The only possible array is [1, 1, 1, 1, 1, 1, 1, 1, 1]
 *
 * Constraints:
 * 1. 1 <= n <= 50
 * 2. 1 <= m <= 100
 * 3. 0 <= k <= n
 */
public class BuildArrayWhereYouCanFindMaximumExactlyKComparisons {
    public static void main(String[] args) {
        System.out.println(numOfArrays(2, 3, 1));
    }

    private static final int MOD = 1000000007;

    public static int numOfArrays(int n, int m, int k) {
        int[][][] dp = new int[2][k + 1][m + 1];
        dp[0][0][0] = 1;

        for (int i = 1; i <= n; i++) {
            int cur = i & 1, prev = cur ^ 1;
            for (int j = 1; j <= k; j++) {
                int preSum = dp[prev][j - 1][0];
                for (int g = 1; g <= m; g++) {
                    dp[cur][j][g] = (int) ((long) g * dp[prev][j][g] % MOD);
                    dp[cur][j][g] += preSum;
                    dp[cur][j][g] %= MOD;

                    preSum += dp[prev][j - 1][g];
                    preSum %= MOD;
                }
            }
            Arrays.fill(dp[cur][0], 0);
        }
        return sumArr(dp[n & 1][k]);
    }

    private static int sumArr(int[] arr) {
        int sum = 0;
        for (int num : arr) {
            sum += num;
            sum %= MOD;
        }
        return sum;
    }
}
