package com.wz.dynamicprogramming;

/**
 * There are n uniquely-sized sticks whose lengths are integers from 1 to n. You want to arrange the sticks such that
 * exactly k sticks are visible from the left. A stick is visible from the left if there are no longer sticks to the left of it.
 * For example, if the sticks are arranged [1,3,2,5,4], then the sticks with lengths 1, 3, and 5 are visible from the left.
 * Given n and k, return the number of such arrangements. Since the answer may be large, return it modulo 10^9 + 7.
 *
 * Example 1:
 * Input: n = 3, k = 2
 * Output: 3
 * Explanation: [1,3,2], [2,3,1], and [2,1,3] are the only arrangements such that exactly 2 sticks are visible.
 * The visible sticks are underlined.
 *
 * Example 2:
 * Input: n = 5, k = 5
 * Output: 1
 * Explanation: [1,2,3,4,5] is the only arrangement such that all 5 sticks are visible.
 * The visible sticks are underlined.
 *
 * Example 3:
 * Input: n = 20, k = 11
 * Output: 647427950
 * Explanation: There are 647427950 (mod 10^9 + 7) ways to rearrange the sticks such that exactly 11 sticks are visible.
 *
 * Constraints:
 * 1. 1 <= n <= 1000
 * 2. 1 <= k <= n
 */
public class NumberOfWaysToRearrangeSticksWithKSticksVisible {
    public static void main(String[] args) {
        System.out.println(rearrangeSticks(3, 2));
        System.out.println(rearrangeSticks(5, 5));
        System.out.println(rearrangeSticks(20, 11));
    }

    /**
     * dp[i][j] 表示从后i个元素 n-i+1,n-i+2,n-i+3,...,n 中选出组成能够看到 j 个棍子的组合数
     * 此时假如一个更小的元素 n-i，如果将其放在序列中的第一个元素，则此时再从 n-i+1,n-i+2,n-i+3,...,n 选出 j-1 个元素，
     * 满足能够看到 j-1 个棍子即可，因为此时 n-i 为序列中的最小元素，一定会被看到，则此时: dp[i][j] = dp[i-1][j-1]
     * 如果 n-i 不放在第一个位置，则可以将其放到后面 i-1 个元素中的任意一个元素后面，它都不可见，则此时: dp[i][j] = dp[i-1][j] * (i-1)
     * 综上可以得到: dp[i][j] = dp[i-1][j-1] + dp[i-1][j] * (i-1)
     */
    public static int rearrangeSticks(int n, int k) {
        int mod = 1000000007;
        long[][] dp = new long[n + 1][k + 1];
        dp[0][0] = 1;
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= Math.min(i, k); j++) {
                dp[i][j] = (dp[i - 1][j] * (long) (i - 1) + dp[i - 1][j - 1]) % mod;
            }
        }
        return (int) (dp[n][k] % mod);
    }
}
