package com.wz.dynamicprogramming;

/**
 * Given two integers n and k, find how many different arrays consist of numbers from 1 to n such that there are exactly k inverse pairs.
 * We define an inverse pair as following: For ith and jth element in the array, if i < j and a[i] > a[j] then it's an inverse pair; Otherwise, it's not.
 * Since the answer may be very large, the answer should be modulo 109 + 7.
 *
 * Example 1:
 * Input: n = 3, k = 0
 * Output: 1
 * Explanation:
 * Only the array [1,2,3] which consists of numbers from 1 to 3 has exactly 0 inverse pair.
 *
 * Example 2:
 * Input: n = 3, k = 1
 * Output: 2
 * Explanation:
 * The array [1,3,2] and [2,1,3] have exactly 1 inverse pair.
 *
 * Note:
 * The integer n is in the range [1, 1000] and k is in the range [0, 1000].
 */
public class KInversePairsArray {
    public static void main(String[] args) {
        System.out.println(kInversePairs(3, 1));
    }

    /**
     * dp[i][j] 表示 1～i 的数字中有 j 个翻转对的排列总数，目标是要求出 dp[n][k]
     * 假设已经知道 dp[n][k]，怎么求 dp[n+1][k]，即 1～n+1 点数字中有 k 个翻转对的个数，
     * 那么实际上在 1～n 的数字中的某个位置加上 n+1 这个数，假设 n=4，那么实际上相当于在某个位置加上 5，那么加 5 的位置就有如下几种情况：
     * xxxx5
     * xxx5x
     * xx5xx
     * x5xxx
     * 5xxxx
     * 这里 xxxx 表示 1～4 的任意排列，那么第一种情况 xxxx5 不会增加任何新的翻转对，xxx5x 会新增加1个翻转对，
     * xx5xx，x5xxx，5xxxx 分别会增加 2，3，4 个翻转对。那么 xxxx5 就相当于 dp[n][k]，即 dp[4][k]，
     * 依次往前类推，就是 dp[n][k-1], dp[n][k-2] ... dp[n][k-n]，这样就可以得出 dp[n+1][k] 的求法了:
     * dp[n+1][k] = dp[n][k] + dp[n][k-1] + ... + dp[n][k-n]
     * 因此：
     * dp[n][k] = dp[n-1][k] + dp[n-1][k-1] + ... + dp[n-1][k-n+1]
     */
    public static int kInversePairs(int n, int k) {
        int mod = 1000000007;
        int[][] dp = new int[n + 1][k + 1];
        dp[0][0] = 1;
        for (int i = 0; i <= n; ++i) {
            for (int j = 0; j < i; ++j) {
                for (int m = 0; m <= k; ++m) {
                    if (m - j >= 0 && m - j <= k) {
                        dp[i][m] = (dp[i][m] + dp[i - 1][m - j]) % mod;
                    }
                }
            }
        }
        return dp[n][k];
    }
}
