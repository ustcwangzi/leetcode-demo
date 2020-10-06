package com.wz.string;

/**
 * Given two words word1 and word2, find the minimum number of operations required to convert word1 to word2.
 * You have the following 3 operations permitted on a word:
 * 1. Insert a character
 * 2. Delete a character
 * 3. Replace a character
 *
 * Example 1:
 * Input: word1 = "horse", word2 = "ros"
 * Output: 3
 * Explanation:
 * horse -> rorse (replace 'h' with 'r')
 * rorse -> rose (remove 'r')
 * rose -> ros (remove 'e')
 *
 * Example 2:
 * Input: word1 = "intention", word2 = "execution"
 * Output: 5
 * Explanation:
 * intention -> inention (remove 't')
 * inention -> enention (replace 'i' with 'e')
 * enention -> exention (replace 'n' with 'x')
 * exention -> exection (replace 'n' with 'c')
 * exection -> execution (insert 'u')
 */
public class EditDistance {
    public static void main(String[] args) {
        System.out.println(minDistance("horse", "ros"));
        System.out.println(minDistance("intention", "execution"));
    }

    /**
     * 动态规划
     * dp[i][j] 表示 word1[0...i-1] 到 word2[0...j-1]的编辑距离
     * 1. 第一列 dp[i][0] 表示 word1[0...i] 到 空 的编辑距离，直接删除即可，就是 i
     * 2. 第一行 dp[0][j] 表示 空 到 word2[0...j] 的编辑距离，直接插入即可，就是 j
     * 3. 其他位置 dp[i][j]
     *    若 word1[i-1] == word2[j-1]，则 dp[i][j] = dp[i-1][j-1]，否则：
     *    3.1 word1[0...i-2] -> word2[0...i-2]，再将 word1[i-1] replace 为 word2[j-1]，此时 dp[i][j] = dp[i-1][j-1] + 1
     *    3.2 word1[0...i-2] -> word2[0...i-1]，再将 word1[i-1] delete，此时 dp[i][j] = dp[i-1][j] + 1
     *    3.3 word1[0...i-1] -> word2[0...i-2]，再将 word2[i-1] insert，此时 dp[i][j] = dp[i][j-1] + 1
     */
    public static int minDistance(String word1, String word2) {
        if (word1 == null || word2 == null) {
            return 0;
        }

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
                    dp[i][j] = Math.min(dp[i - 1][j - 1], Math.min(dp[i - 1][j], dp[i][j - 1])) + 1;
                }
            }
        }

        return dp[m][n];
    }
}
