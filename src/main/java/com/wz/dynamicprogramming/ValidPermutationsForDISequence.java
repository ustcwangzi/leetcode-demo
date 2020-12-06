package com.wz.dynamicprogramming;

/**
 * We are given S, a length n string of characters from the set {'D', 'I'}. (These letters stand for "decreasing" and "increasing".)
 * A valid permutation is a permutation P[0], P[1], ..., P[n] of integers {0, 1, ..., n}, such that for all i:
 * If S[i] == 'D', then P[i] > P[i+1], and;
 * If S[i] == 'I', then P[i] < P[i+1].
 * How many valid permutations are there?  Since the answer may be large, return your answer modulo 10^9 + 7.
 *
 * Example 1:
 * Input: "DID"
 * Output: 5
 * Explanation:
 * The 5 valid permutations of (0, 1, 2, 3) are:
 * (1, 0, 3, 2)
 * (2, 0, 3, 1)
 * (2, 1, 3, 0)
 * (3, 0, 2, 1)
 * (3, 1, 2, 0)
 *
 * Note:
 * 1. 1 <= S.length <= 200
 * 2. S consists only of characters from the set {'D', 'I'}.
 */
public class ValidPermutationsForDISequence {
    public static void main(String[] args) {
        System.out.println(numPermsDISequence("DID"));
    }

    /**
     * 不管是要新加升序还是降序，最后的数字的大小直接决定了能形成多少个不同的序列，这个就是本题的隐藏信息，
     * 所以在定义 dp 数组的时候必须要把最后一个数字考虑进去，这样就需要一个二维的 dp 数组，
     * 其中 dp[i][j] 表示由范围 [0, i] 内的数字组成且最后一个数字为j的不同序列的个数。
     * 例如，由数字 [0, 1, 2, 3] 组成 "DID" 模式的序列，首先 dp[0][0] 是要初始化为1，如下所示（括号里是实际的序列）：
     * dp[0][0] = 1 (0)
     * 然后需要加第二个数字，由于需要降序，那么根据之前的分析，加的数字不能大于最后一个数字0，则只能加0，如下所示：
     * 加0:  ( dp[1][0] = 1 )
     * 0 -> 1 -> 10
     * 然后需要加第三个数字，由于需要升序，那么根据之前的分析，加的数字不能小于最后一个数字0，那么实际上可以加的数字有 1，2，如下所示：
     * 加1:  ( dp[2][1] = 1 )
     * 10 -> 20 -> 201
     *
     * 加2:  ( dp[2][2] = 1 )
     * 10 -> 10 -> 102
     * 然后需要加第四个数字，由于需要降序，那么根据之前的分析，加的数字不能大于最后一个数字，上一轮的最后一个数字有1或2，
     * 那么实际上可以加的数字有 0，1，2，如下所示：
     * 加0:  ( dp[3][0] = 2 )
     * 201 -> 312 -> 3120
     * 102 -> 213 -> 2130
     *
     * 加1:  ( dp[3][1] = 2 )
     * 201 -> 302 -> 3021
     * 102 -> 203 -> 2031
     *
     * 加2:  ( dp[3][2] = 1 )
     * 102 -> 103 -> 1032
     * 这种方法算出的 dp 数组为：
     * 1 0 0 0
     * 1 0 0 0
     * 0 1 1 0
     * 2 2 1 0
     * 最后把 dp 数组的最后一行加起来 2+2+1 = 5 就是最终的结果，根据前面的分析，当是降序时，下一个数字不小于当前最后一个数字，
     * 反之是升序时，下一个数字小于当前最后一个数字，所以可以写出状态转移方程如下所示：
     * if (S[i-1] == 'D')    dp[i][j] += dp[i-1][k]    ( j <= k <= i-1 )
     * else                  dp[i][j] += dp[i-1][k]    ( 0 <= k < j )
     */
    public static int numPermsDISequence(String S) {
        int result = 0, n = S.length(), mod = 1000000007;
        int[][] dp = new int[n + 1][n + 1];
        dp[0][0] = 1;
        for (int i = 1; i <= n; ++i) {
            for (int j = 0; j <= i; ++j) {
                if (S.charAt(i - 1) == 'D') {
                    for (int k = j; k <= i - 1; ++k) {
                        dp[i][j] = (dp[i][j] + dp[i - 1][k]) % mod;
                    }
                } else {
                    for (int k = 0; k <= j - 1; ++k) {
                        dp[i][j] = (dp[i][j] + dp[i - 1][k]) % mod;
                    }
                }
            }
        }
        for (int i = 0; i <= n; ++i) {
            result = (result + dp[n][i]) % mod;
        }
        return result;
    }
}
