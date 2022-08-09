package com.wz.dynamicprogramming;

import java.util.Arrays;

/**
 * You are given a list of strings of the same length words and a string target.
 * Your task is to form target using the given words under the following rules:
 * - target should be formed from left to right.
 * - To form the ith character (0-indexed) of target, you can choose the kth character of the jth string in words if target[i] = words[j][k].
 * - Once you use the kth character of the jth string of words, you can no longer use the xth character of any string in words where x <= k.
 *   In other words, all characters to the left of or at index k become unusuable for every string.
 * - Repeat the process until you form the string target.
 * Notice that you can use multiple characters from the same string in words provided the conditions above are met.
 * Return the number of ways to form target from words. Since the answer may be too large, return it modulo 10^9 + 7.
 *
 * Example 1:
 * Input: words = ["acca","bbbb","caca"], target = "aba"
 * Output: 6
 * Explanation: There are 6 ways to form target.
 * "aba" -> index 0 ("acca"), index 1 ("bbbb"), index 3 ("caca")
 * "aba" -> index 0 ("acca"), index 2 ("bbbb"), index 3 ("caca")
 * "aba" -> index 0 ("acca"), index 1 ("bbbb"), index 3 ("acca")
 * "aba" -> index 0 ("acca"), index 2 ("bbbb"), index 3 ("acca")
 * "aba" -> index 1 ("caca"), index 2 ("bbbb"), index 3 ("acca")
 * "aba" -> index 1 ("caca"), index 2 ("bbbb"), index 3 ("caca")
 *
 * Example 2:
 * Input: words = ["abba","baab"], target = "bab"
 * Output: 4
 * Explanation: There are 4 ways to form target.
 * "bab" -> index 0 ("baab"), index 1 ("baab"), index 2 ("abba")
 * "bab" -> index 0 ("baab"), index 1 ("baab"), index 3 ("baab")
 * "bab" -> index 0 ("baab"), index 2 ("baab"), index 3 ("baab")
 * "bab" -> index 1 ("abba"), index 2 ("baab"), index 3 ("baab")
 *
 * Constraints:
 * 1 <= words.length <= 1000
 * 1 <= words[i].length <= 1000
 * All strings in words have the same length.
 * 1 <= target.length <= 1000
 * words[i] and target contain only lowercase English letters.
 */
public class NumberOfWaysToFormTargetStringGivenDictionary {
    public static void main(String[] args) {
        System.out.println(numWays(new String[]{"acca", "bbbb", "caca"}, "aba"));
        System.out.println(numWays(new String[]{"abba", "baab"}, "bab"));
    }

    private static final int MOD = 1000000007;

    /**
     * 动态规划 + DFS
     * 使用 count[][] 记录每个位置上字符个数，然后进行 DFS，对于每个位置有两种选择：使用 OR 不使用
     * 结果为 dp[i][j] = dp[i+1][j] + count[i][c] * dp[i+1][j+1]
     */
    public static int numWays(String[] words, String target) {
        int m = words[0].length();
        int[][] dp = new int[m + 1][target.length()];
        for (int[] row : dp) {
            Arrays.fill(row, -1);
        }
        int[][] count = new int[m][26];
        for (String word : words) {
            for (int i = 0; i < word.length(); i++) {
                count[i][word.charAt(i) - 'a']++;
            }
        }

        return dfs(count, 0, 0, target, dp);
    }

    private static int dfs(int[][] count, int wordIndex, int targetIndex, String target, int[][] dp) {
        if (targetIndex >= target.length()) {
            return 1;
        }
        if (wordIndex >= count.length) {
            return 0;
        }
        if (dp[wordIndex][targetIndex] != -1) {
            return dp[wordIndex][targetIndex];
        }

        int index = target.charAt(targetIndex) - 'a';
        long total = 0;
        // 跳过当前字符
        total += dfs(count, wordIndex + 1, targetIndex, target, dp);
        // 使用当前字符
        total += (long) count[wordIndex][index] * dfs(count, wordIndex + 1, targetIndex + 1, target, dp);
        return dp[wordIndex][targetIndex] = (int) (total % MOD);
    }
}
