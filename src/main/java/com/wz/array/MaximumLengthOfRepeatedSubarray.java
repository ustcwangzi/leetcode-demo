package com.wz.array;

/**
 * Given two integer arrays A and B, return the maximum length of an subarray that appears in both arrays.
 *
 * Example 1:
 * Input:
 * A: [1,2,3,2,1]
 * B: [3,2,1,4,7]
 * Output: 3
 * Explanation:
 * The repeated subarray with maximum length is [3, 2, 1].
 */
public class MaximumLengthOfRepeatedSubarray {
    public static void main(String[] args) {
        int[] A = new int[]{1, 2, 3, 2, 1};
        int[] B = new int[]{3, 2, 1, 4, 7};
        System.out.println(findLength(A, B));
    }

    /**
     * 动态规划
     * 用dp[i][j]表示以A[i]和B[j]结尾的最长公共子串的长度
     * 如果A[i] == B[j]，dp[i][j] = dp[i−1][j−1]+1，否则 dp[i][j] = 0
     */
    public static int findLength(int[] A, int[] B) {
        int result = 0;
        int m = A.length, n = B.length;
        int[][] dp = new int[m + 1][n + 1];
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                dp[i][j] = A[i - 1] == B[j - 1] ? dp[i - 1][j - 1] + 1 : 0;
                result = Math.max(result, dp[i][j]);
            }
        }

        return result;
    }
}
