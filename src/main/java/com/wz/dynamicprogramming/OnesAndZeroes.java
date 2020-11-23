package com.wz.dynamicprogramming;

/**
 * You are given an array of binary strings strs and two integers m and n.
 * Return the size of the largest subset of strs such that there are at most m 0's and n 1's in the subset.
 * A set x is a subset of a set y if all elements of x are also elements of y.
 *
 * Example 1:
 * Input: strs = ["10","0001","111001","1","0"], m = 5, n = 3
 * Output: 4
 * Explanation: The largest subset with at most 5 0's and 3 1's is {"10", "0001", "1", "0"}, so the answer is 4.
 * Other valid but smaller subsets include {"0001", "1"} and {"10", "1", "0"}.
 * {"111001"} is an invalid subset because it contains 4 1's, greater than the maximum of 3.
 *
 * Example 2:
 * Input: strs = ["10","0","1"], m = 1, n = 1
 * Output: 2
 * Explanation: The largest subset is {"0", "1"}, so the answer is 2.
 *
 * Constraints:
 * 1. 1 <= strs.length <= 600
 * 2. 1 <= strs[i].length <= 100
 * 3. strs[i] consists only of digits '0' and '1'.
 * 4. 1 <= m, n <= 100
 */
public class OnesAndZeroes {
    public static void main(String[] args) {
        System.out.println(findMaxForm(new String[]{"10", "0001", "111001", "1", "0"}, 5, 3));
    }

    /**
     * dp[i][j] 表示有 i 个 0 和 j 个 1 时能组成的最多字符串的个数
     * 对于当前遍历到的字符串，统计出其中 0 和 1 的个数为 zeros 和 ones，
     * dp[i-zeros][j-ones] 加上当前的 zeros 和 ones 就是当前 dp[i][j] 可以达到的个数，和原有数值对比取较大值即可，所以：
     * dp[i][j] = max(dp[i][j], dp[i-zeros][j-ones] + 1)
     */
    public static int findMaxForm(String[] strs, int m, int n) {
        int[][] dp = new int[m + 1][n + 1];
        for (String str : strs) {
            int zeros = 0, ones = 0;
            for (char c : str.toCharArray()) {
                if (c == '0') {
                    zeros++;
                } else {
                    ones++;
                }
            }
            for (int i = m; i >= zeros; i--) {
                for (int j = n; j >= ones; j--) {
                    dp[i][j] = Math.max(dp[i][j], dp[i - zeros][j - ones] + 1);
                }
            }
        }
        return dp[m][n];
    }
}
