package com.wz.math;

import java.util.Arrays;

/**
 * Given a positive integer n, find the least number of perfect square numbers (for example, 1, 4, 9, 16, ...) which sum to n.
 *
 * Example 1:
 * Input: n = 12
 * Output: 3
 * Explanation: 12 = 4 + 4 + 4.
 *
 * Example 2:
 * Input: n = 13
 * Output: 2
 * Explanation: 13 = 4 + 9.
 */
public class PerfectSquares {
    public static void main(String[] args) {
        System.out.println(numSquares(12));
        System.out.println(numSquares(13));
    }

    /**
     * 动态规则
     * dp[i] 表示第 i 个数的完美平方数
     * num  拆解  完美平方数
     * 1    1*1
     * 2    1+1     1
     * 3    2+1     1
     * 4    2*2
     * 5    1+4     4
     * 6    2+4     4
     * 7    3+4     4
     * 8    4+4     4
     * 9    3*3
     * 10   1+9     9
     * 11   2+9     9
     * 12   3+9     9
     * 13   4+9     9
     * 14   5+9     9
     * 15   6+9     9
     * 16   4*4
     * 所有的完美平方数都可以看做一个普通数加上一个完美平方数，递推式就是：
     * dp[i + j * j] = Math.min(dp[i] + 1, dp[i + j * j])
     */
    public static int numSquares(int n) {
        int[] dp = new int[n + 1];
        Arrays.fill(dp, Integer.MAX_VALUE);

        dp[0] = 0;
        for (int i = 0; i <= n; ++i) {
            for (int j = 1; i + j * j <= n; ++j) {
                dp[i + j * j] = Math.min(dp[i + j * j], dp[i] + 1);
            }
        }

        return dp[n];
    }
}
