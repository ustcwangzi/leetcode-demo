package com.wz.dynamicprogramming;

/**
 * A die simulator generates a random number from 1 to 6 for each roll. You introduced a constraint to the generator
 * such that it cannot roll the number i more than rollMax[i] (1-indexed) consecutive times.
 * Given an array of integers rollMax and an integer n, return the number of distinct sequences that can be obtained with exact n rolls.
 * Two sequences are considered different if at least one element differs from each other. Since the answer may be too large, return it modulo 10^9 + 7.
 *
 * Example 1:
 * Input: n = 2, rollMax = [1,1,2,2,2,3]
 * Output: 34
 * Explanation: There will be 2 rolls of die, if there are no constraints on the die, there are 6 * 6 = 36 possible combinations.
 * In this case, looking at rollMax array, the numbers 1 and 2 appear at most once consecutively,
 * therefore sequences (1,1) and (2,2) cannot occur, so the final answer is 36-2 = 34.
 *
 * Example 2:
 * Input: n = 2, rollMax = [1,1,1,1,1,1]
 * Output: 30
 *
 * Example 3:
 * Input: n = 3, rollMax = [1,1,1,2,2,3]
 * Output: 181
 *
 * Constraints:
 * 1. 1 <= n <= 5000
 * 2. rollMax.length == 6
 * 3. 1 <= rollMax[i] <= 15
 */
public class DiceRollSimulation {
    public static void main(String[] args) {
        System.out.println(dieSimulator(2, new int[]{1, 1, 2, 2, 2, 3}));
    }

    /**
     * dp[i][j][k] 表示前 i 次投，最后一次结果为 j 且最后的数字连续了 k 次的方案数
     * 初始时，dp[0][j][1] = 1，其余均为 0。
     * 转移时，对于每一个 i 和 j，枚举上一次最后的结果 t，如果 j==t，则 dp[i][j][k] = dp[i][j][k] + dp[i−1][j][k−1]
     * 否则 dp[i][j][1] = dp[i][j][1] + dp[i−1][t][k]
     * 最终答案为 dp[n−1][j][k] 的总和
     */
    public static int dieSimulator(int n, int[] rollMax) {
        int mod = 1000000007;
        int[][][] dp = new int[n][6][16];
        for (int j = 0; j < 6; j++) {
            dp[0][j][1] = 1;
        }

        for (int i = 1; i < n; i++) {
            for (int j = 0; j < 6; j++) {
                for (int t = 0; t < 6; t++) {
                    if (j == t) {
                        for (int k = 2; k <= rollMax[j]; k++) {
                            dp[i][j][k] = (dp[i][j][k] + dp[i - 1][j][k - 1]) % mod;
                        }
                    } else {
                        for (int k = 1; k <= rollMax[t]; k++) {
                            dp[i][j][1] = (dp[i][j][1] + dp[i - 1][t][k]) % mod;
                        }
                    }
                }
            }
        }

        int result = 0;
        for (int j = 0; j < 6; j++) {
            for (int k = 1; k <= rollMax[j]; k++) {
                result = (result + dp[n - 1][j][k]) % mod;
            }
        }
        return result;
    }
}
