package com.wz.string;

/**
 * Given two words word1 and word2, find the minimum number of steps required to make word1 and word2 the same, where in each step you can delete one character in either string.
 *
 * Example:
 * Input: "sea", "eat"
 * Output: 2
 * Explanation: You need one step to make "sea" to "ea" and another step to make "eat" to "ea".
 *
 * Note:
 * 1. The length of given words won't exceed 500.
 * 2. Characters in given words can only be lower-case letters.
 */
public class DeleteOperationForTwoStrings {
    public static void main(String[] args) {
        System.out.println(minDistance("sea", "eat"));
    }

    /**
     * 与 {@link EditDistance} 类似
     * word2 删除元素等同于在 word1 对应位置插入元素
     * 因此问题转换为通过 insert 和 delete，将 word1 变为 word2 需要的最小操作次数，比 {@link EditDistance} 少了 replace 操作
     */
    public static int minDistance(String word1, String word2) {
        int m = word1.length(), n = word2.length();
        int[][] dp = new int[m + 1][n + 1];
        // 第一列
        for (int i = 0; i <= m; i++) {
            dp[i][0] = i;
        }
        // 第一行
        for (int j = 0; j <= n; j++) {
            dp[0][j] = j;
        }
        // 其他位置
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                if (word1.charAt(i - 1) == word2.charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j - 1];
                } else {
                    dp[i][j] = Math.min(dp[i][j - 1], dp[i - 1][j]) + 1;
                }
            }
        }

        return dp[m][n];
    }
}
